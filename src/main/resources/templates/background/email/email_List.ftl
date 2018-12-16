<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 收件箱</title>
    <#include "../comm/script.ftl">
    <link href="${basePath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${basePath}/css/plugins/iCheck/custom.css" rel="stylesheet">
</head>
<body class="gray-bg">
<input type="hidden" id="path" value="${basePath}"/>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-3">
            <div class="ibox float-e-margins">
                <div class="ibox-content mailbox-content">
                    <div class="file-manager">
                        <a class="btn btn-block btn-primary compose-mail" href="mail_compose.html">写信</a>
                        <div class="space-25"></div>
                        <h5>文件夹</h5>
                        <ul class="folder-list m-b-md" style="padding: 0">
                            <li>
                                <a href="email_List.ftl"> <i class="fa fa-inbox "></i> 收件箱
                                <#if (emailSum==0)>
                                    <span class="fa arrow"></span>
                                <#else>
                                    <span class="label label-warning pull-right">${emailSum}</span>
                                </#if>
                                </a>
                            </li>
                            <li>
                                <a href="email_List.ftl"> <i class="fa fa-envelope-o"></i> 发信</a>
                            </li>
                            <li>
                                <a href="email_List.ftl"> <i class="fa fa-certificate"></i> 重要</a>
                            </li>
                            <li>
                                <a href="email_List.ftl"> <i class="fa fa-file-text-o"></i> 草稿 <span
                                        class="label label-danger pull-right">2</span>
                                </a>
                            </li>
                            <li>
                                <a href="email_List.ftl"> <i class="fa fa-trash-o"></i> 垃圾箱</a>
                            </li>
                        </ul>
                        <h5>分类</h5>
                        <ul class="category-list" style="padding: 0">
                            <li>
                                <a href="mail_compose.html#"> <i class="fa fa-circle text-navy"></i> 工作</a>
                            </li>
                            <li>
                                <a href="mail_compose.html#"> <i class="fa fa-circle text-danger"></i> 文档</a>
                            </li>
                            <li>
                                <a href="mail_compose.html#"> <i class="fa fa-circle text-primary"></i> 社交</a>
                            </li>
                            <li>
                                <a href="mail_compose.html#"> <i class="fa fa-circle text-info"></i> 广告</a>
                            </li>
                            <li>
                                <a href="mail_compose.html#"> <i class="fa fa-circle text-warning"></i> 客户端</a>
                            </li>
                        </ul>

                        <h5 class="tag-title">标签</h5>
                        <ul class="tag-list" style="padding: 0">
                            <li><a href="mail_compose.html"><i class="fa fa-tag"></i> 朋友</a>
                            </li>
                            <li><a href="mail_compose.html"><i class="fa fa-tag"></i> 工作</a>
                            </li>
                            <li><a href="mail_compose.html"><i class="fa fa-tag"></i> 家庭</a>
                            </li>
                            <li><a href="mail_compose.html"><i class="fa fa-tag"></i> 孩子</a>
                            </li>
                            <li><a href="mail_compose.html"><i class="fa fa-tag"></i> 假期</a>
                            </li>
                            <li><a href="mail_compose.html"><i class="fa fa-tag"></i> 音乐</a>
                            </li>
                            <li><a href="mail_compose.html"><i class="fa fa-tag"></i> 照片</a>
                            </li>
                            <li><a href="mail_compose.html"><i class="fa fa-tag"></i> 电影</a>
                            </li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9 animated fadeInRight">
            <div class="mail-box-header">

                <form method="get" action="index.html" class="pull-right mail-search">
                    <div class="input-group">
                        <input type="text" class="form-control input-sm" name="emailDigest" placeholder="搜索邮件标题">
                        <div class="input-group-btn">
                            <button type="submit" class="btn btn-sm btn-primary">
                                搜索
                            </button>
                        </div>
                    </div>
                </form>
                <h2>
                    收件箱 (${emailSum})
                </h2>
                <div class="mail-tools tooltip-demo m-t-md">
                    <div class="btn-group pull-right">
                        <button class="btn btn-white btn-sm"><i class="fa fa-arrow-left"></i>
                        </button>
                        <button class="btn btn-white btn-sm"><i class="fa fa-arrow-right"></i>
                        </button>

                    </div>
                <#--<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" title="刷新邮件列表"><i
                        class="fa fa-refresh"></i> 刷新
                </button>
                <button class="btn btn-white btn-sm" id="Have_read" data-toggle="tooltip" data-placement="top"
                        title="标为已读"><i
                        class="fa fa-eye"></i>
                </button>
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="标为重要"><i
                        class="fa fa-exclamation"></i>
                </button>-->
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="标为垃圾邮件"><i
                        class="fa fa-trash-o"></i>
                </button>

                </div>
            </div>
            <div class="mail-box">
                <table class="table table-hover table-mail">
                    <tbody id="emailList">
                    <#if (emailList?size > 0)>
                        <#list emailList as email>
                               <tr class="read">
                                   <td class="check-mail">
                                       <input type="checkbox" name="email_id" value="${email.id}" class="i-checks">
                                   </td>
                                   <td class="mail-ontact"><a
                                           href="/sharedEmail/emailLook/${email.id}">${email.friend_name}</a>
                                    <#if (email.state==2)>
                                        <span class="label label-warning pull-right">未读邮件</span>
                                    <#else>
                                        <span class="label label-warning pull-right"></span>
                                    </#if>
                                   </td>
                                   <td class="mail-subject"><a href="email_detail.ftl">${email.emailDigest}</a></td>
                                   <td class=""></td>
                                   <td class="text-right mail-date">${email.creationDate?date}</td>
                               </tr>
                        </#list>
                    <#else>
                        <tr align="center">
                            <td colspan="7">没有邮件</td>
                        </tr>
                    </#if>
                    <#--<tr class="unread">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">支付宝</a>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">支付宝提醒</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">昨天 10:20</td>
                    </tr>
                    <tr class="unread">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks" checked>
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">Amaze UI</a>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">Amaze UI Beta2 发布</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午10:57</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact">
                            <a href="email_detail.ftl">WordPress</a>
                            <span class="label label-warning pull-right">验证邮件</span>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">wp-user-frontend-pro v2.1.9</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午9:21</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">淘宝网</a>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">史上最全！淘宝双11红包疯抢攻略！</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">中午12:24</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">淘宝网</a> <span
                                class="label label-danger pull-right">AD</span>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">亲，双11来啦！帮你挑货，还送你4999元红包！仅此一次！</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">上午6:48</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">支付宝</a>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">支付宝提醒</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">昨天 10:20</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">Amaze UI</a>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">Amaze UI Beta2 发布</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午10:57</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">WordPress</a> <span
                                class="label label-warning pull-right">验证邮件</span>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">wp-user-frontend-pro v2.1.9</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午9:21</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">淘宝网</a>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">史上最全！淘宝双11红包疯抢攻略！</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">中午12:24</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">淘宝网</a> <span
                                class="label label-danger pull-right">AD</span>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">亲，双11来啦！帮你挑货，还送你4999元红包！仅此一次！</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">上午6:48</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">支付宝</a>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">支付宝提醒</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">昨天 10:20</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">Amaze UI</a>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">Amaze UI Beta2 发布</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午10:57</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">WordPress</a> <span
                                class="label label-warning pull-right">验证邮件</span>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">wp-user-frontend-pro v2.1.9</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">上午9:21</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">淘宝网</a>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">史上最全！淘宝双11红包疯抢攻略！</a>
                        </td>
                        <td class=""></td>
                        <td class="text-right mail-date">中午12:24</td>
                    </tr>
                    <tr class="read">
                        <td class="check-mail">
                            <input type="checkbox" class="i-checks">
                        </td>
                        <td class="mail-ontact"><a href="email_detail.ftl">淘宝网</a> <span
                                class="label label-danger pull-right">AD</span>
                        </td>
                        <td class="mail-subject"><a href="email_detail.ftl">亲，双11来啦！帮你挑货，还送你4999元红包！仅此一次！</a>
                        </td>
                        <td class=""><i class="fa fa-paperclip"></i>
                        </td>
                        <td class="text-right mail-date">上午6:48</td>
                    </tr>-->

                    </tbody>
                </table>


            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
</script>
<script src="${basePath}/js/email/Email_Haveread.js"></script>
</body>

</html>
