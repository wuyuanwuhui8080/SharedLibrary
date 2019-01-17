package com.share.charsocket.handler;

import static com.share.charsocket.constant.MessageCodeConstant.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.share.charsocket.bo.FriendAndUsers;
import com.share.charsocket.bo.Users;
import com.share.charsocket.config.NettyConfig;
import com.share.charsocket.constant.WebSocketConstant;
import com.share.charsocket.enums.WebsocketUtilEnum;
import com.share.charsocket.util.FriendAndMyIdKey;
import com.share.charsocket.util.MessageUtil;
import com.share.charsocket.util.WebsocketUtil;
import com.share.enums.HttpStatus;

import com.share.util.DateUtils;
import com.share.util.RedisUtil;
import com.share.util.SpringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.Objects;

/**
 * 处理请求的自定义的handler
 *
 * @author 博博
 * @Title: CharHandler
 * @ProjectName SharedLibrary
 * @time 2019/1/10 9:59
 */
@Log4j2
public class CharHandler extends SimpleChannelInboundHandler<Object> {
    // 处理websocket服务器的请求
    private WebSocketServerHandshaker handshaker;
    // 获取单例实例
    private WebsocketUtil websocketUtil = WebsocketUtilEnum.INSTANCE
            .getInstance();


    /**
     * 请求开始的时候调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        websocketUtil.addChannel(ctx.channel());
        log.info("连接开启...");
    }

    /**
     * 断开的时候调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.error("连接断开..");
    }

    /**
     * 工程出现异常的时候调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // 判断传入是不是个http请求 FullHttpRequest
        if (msg instanceof FullHttpRequest) {
            handHttpRequest(ctx, (FullHttpRequest) msg);
            // 判断传入是否是websocket连接
        } else if (msg instanceof WebSocketFrame) {
            handWebsocketFrame(ctx, (WebSocketFrame) msg);
        }
    }


    /**
     * 处理客户端和websocket之间的业务
     *
     * @param ctx            上下文对象
     * @param webSocketFrame
     */
    private void handWebsocketFrame(ChannelHandlerContext ctx,
                                    WebSocketFrame webSocketFrame) {
        Channel channel = ctx.channel();
        // 判断是否是关闭websocket的指令
        if (webSocketFrame instanceof CloseWebSocketFrame) {
            // 关闭握手
            handshaker.close(channel, (CloseWebSocketFrame) webSocketFrame);
            // 删除对象
            websocketUtil.deleteChannel(channel);
            return;
        }

        // 判断是否是ping消息
        if (webSocketFrame instanceof PingWebSocketFrame) {
            channel.write(
                    new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }
        // 判断是否Pong消息
        if (webSocketFrame instanceof PongWebSocketFrame) {
            ctx.writeAndFlush(
                    new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }
        // 判断是否是二进制消息，如果是二进制消息，抛出异常
        if (!(webSocketFrame instanceof TextWebSocketFrame)) {
            System.out.println("目前我们不支持二进制消息");
            ctx.channel().write(
                    new PongWebSocketFrame(webSocketFrame.content().retain()));
            throw new RuntimeException(
                    "【" + this.getClass().getName() + "】不支持消息");
        }
        // 获取发送的文本
        String message = ((TextWebSocketFrame) webSocketFrame).text();
        // 把json数据转换成JSONObject
        JSONObject jsonObject = JSONObject.parseObject(message);

        // 获取请求状态
        Integer code = jsonObject.getInteger("code");
        // 获取真实姓名
        String realName = jsonObject.getString("realName");
        // 获取id
        String id = jsonObject.getString("id");
        // 获取头像信息
        String headImg = jsonObject.getString("headImg");
        // 获取聊天信息
        String chatMassage = jsonObject.getString("chatMassage");
        // 获取被发送人的id
        String receiverId = jsonObject.getString("receiverId");

        // 从concurrentMap中拿出当前的用户
        Users users = WebsocketUtil.concurrentMap.get(channel);

        TextWebSocketFrame socketFrame = null;
        String msg = null;
        switch (code) {
            case LOGIN_CODE:
                // 获取用户用户名
                String userName = jsonObject.getString("userName");
                // 添加用户信息进channel
                if (!websocketUtil.addUsers(channel, userName, realName, id,
                        headImg)) {
                    return;
                }
                // 更新信息
                websocketUtil.updateUserListAndCount();
                msg = MessageUtil.messageJSONStringFactory(SYSTEM_MESSAGE_CODE,
                        "" + realName + "加入了聊天室..", NORMAL_SYSTEM_MESSGAE_CODE,
                        null);
                socketFrame = new TextWebSocketFrame(msg);
                // 发送消息给所有客户端
                NettyConfig.channels.writeAndFlush(socketFrame);

                // 向自己发送用户信息
                msg = MessageUtil.messageJSONStringFactory(SYSTEM_MESSAGE_CODE,
                        null, PERSONAL_SYSTEM_MESSGAE_CODE, users);

                channel.writeAndFlush(msg);
                break;
            // 发送群聊信息
            case GROUP_CHAT_CODE:
                // 进行群发数据
                String chartCode = MessageUtil.messageJSONStringFactory(
                        GROUP_CHAT_MESSAGE_CODE, chatMassage, users, null);
                socketFrame = new TextWebSocketFrame(chartCode);
                // 给在线的所有人发送数据
                NettyConfig.channels.writeAndFlush(socketFrame);
                break;
            // 发送私聊信息
            case PRIVATE_CHAT_CODE:
                RedisUtil redisUtil = SpringUtil.getBean(RedisUtil.class);
                // 定义key
                String charMyKey = FriendAndMyIdKey.CharKey(users.getId(), receiverId);

                String forKey = FriendAndMyIdKey.CharKey(receiverId, users.getId());
                // 初始化数据
                FriendAndUsers friendAndUsers = new FriendAndUsers(receiverId, users.getId(), headImg, realName, DateUtils.date2String(new Date()), chatMassage);
                // 把数据塞进缓存
                redisUtil.sSet(charMyKey, JSON.toJSON(friendAndUsers));
                redisUtil.sSet(forKey, JSON.toJSON(friendAndUsers));
                // 定义私有数据
                String privateMassage = MessageUtil.messageJSONStringFactory(
                        PRIVATE_CHAT_MESSAGE_CODE, chatMassage, users, receiverId);
                socketFrame = new TextWebSocketFrame(privateMassage);
                websocketUtil.sendPrivateChatMessage(receiverId, socketFrame);
                if (!Objects.equals(id, receiverId)) {
                    socketFrame = new TextWebSocketFrame(privateMassage);
                    channel.writeAndFlush(socketFrame);
                }

                break;
        }

    }

    /**
     * 执行channel握手
     * <p>
     * <p>
     * 处理客户端向服务端发起 http 握手请求的业务
     * WebSocket在建立握手时，数据是通过HTTP传输的。但是建立之后，在真正传输时候是不需要HTTP协议的。 WebSocket 连接过程：
     * 首先，客户端发起http请求，经过3次握手后，建立起TCP连接；
     * http请求里存放WebSocket支持的版本号等信息，如：Upgrade、Connection、WebSocket-Version等；
     * 然后，服务器收到客户端的握手请求后，同样采用HTTP协议回馈数据； 最后，客户端收到连接成功的消息后，开始借助于TCP传输信道进行全双工通信。
     * </p>
     *
     * @param ctx     上下文对象
     * @param request 请求对象
     */
    private void handHttpRequest(ChannelHandlerContext ctx,
                                 FullHttpRequest request) {
        // 如果请求失败或者该请求不是客户端向服务端发起的 http 请求，则响应错误信息
        if (!request.decoderResult().isSuccess()
                || !("websocket".equals(request.headers().get("Upgrade")))) {
            // 400状态
            sendHttpResponse(ctx, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        // 定义websocket工厂类
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(
                WebSocketConstant.WEB_SOCKET_URL, null, false);
        handshaker = factory.newHandshaker(request);
        // 如果handshaker等于空，说明不支持websocket
        if (handshaker == null) {
            // 如果为空，返回响应：不受支持的 websocket 版本
            WebSocketServerHandshakerFactory
                    .sendUnsupportedVersionResponse(ctx.channel());
        } else {
            // 否则，执行握手
            handshaker.handshake(ctx.channel(), request);
        }

    }

    /**
     * 服务端向客户端响应消息
     */
    private void sendHttpResponse(ChannelHandlerContext ctx,
                                  DefaultFullHttpResponse response) {
        if (response.status().code() != HttpStatus.SUCCESS.getStatus()) {
            // 创建源缓冲区
            ByteBuf byteBuf = Unpooled.copiedBuffer(
                    response.status().toString(), CharsetUtil.UTF_8);
            // 将源缓冲区的数据传送到此缓冲区
            response.content().writeBytes(byteBuf);
            // 释放源缓冲区
            byteBuf.release();
        }
        // 写入请求，服务端向客户端发送数据
        ChannelFuture channelFuture = ctx.channel().writeAndFlush(response);
        if (response.status().code() != HttpStatus.SUCCESS.getStatus()) {
            /*
             * 如果请求失败，关闭 ChannelFuture
             *
             * ChannelFutureListener.CLOSE 源码：future.channel().close();
             */
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

}
