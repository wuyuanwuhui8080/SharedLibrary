<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>好友博客</title>
    <#include "../comm/script.ftl">
    <script src="${basePath}/js/users/usersBlogs.js"></script>
</head>
<body class="gray-bg">
<div id="dg" style="z-index: 9999; position: fixed ! important; right: 0px; top: 30%;right: 20px">
    <table width="" 100% style="position: absolute; width:260px; right: 0px; top: 0px;">
        <button class="btn btn-success btn-circle btn-lg" title="点我刷新" type="button" id="rightreigh"><i
                class="fa fa-refresh"></i>
        </button>
    </table>
</div>
<div class="wrapper wrapper-content  animated fadeInRight">
    <input type="hidden" value="${basePath}" id="path"/>
    <input type="hidden" value="${users.id}" id="userId"/>
    <input type="hidden" id="pageIndex" value="1">
    <div class="row">
        <div class="col-sm-1">

        </div>


        <div class="col-sm-10 fistClassBlosg">
            <div class="ibox">
                <div class="ibox-content text-center">

                    <h3 class="m-b-xxs">好友博客</h3>
                </div>

            </div>
            <#if shareBlogsList?? && (shareBlogsList?size > 0)>
                <#list shareBlogsList as li>
            <div class="social-feed-box">
                <div class="pull-right social-action dropdown">
                    <#if Session.users.id == li.blosUserId>
                        <button data-toggle="dropdown" class="dropdown-toggle btn-white">
                            <i class="fa fa-angle-down"></i>
                        </button>
                        <ul class="dropdown-menu m-t-xs">
                            <li><a href="javascript:;" class="delBlos" blosId="${li.blosId}">删除当前博客</a></li>
                        </ul>
                    </#if>
                </div>
                <div class="social-avatar">
                    <a href="" class="pull-left">
                        <img alt="image" src="${basePath}/images/${li.blosUserImg!}">
                    </a>
                    <div class="media-body">
                        <a href="#">
                            ${li.blosUserRealName!}
                        </a>
                        <small class="text-muted">${li.blosDate?datetime}</small>
                    </div>
                </div>
                <div class="social-body">
                    <p>
                        ${li.blosContent!}
                    </p>
                    <div class="btn-group">
                        <div class="fistDivOk">
                        <#if li.giveCount?? && li.giveCount != 0>
                            <#list li.blogsGiveBOList as giveLi>
                                <#if giveLi.giveUserid == Session.users.id>
                                    <button class="btn btn-white btn-xs BtnGiveTrue " blogId="${li.blosId}"
                                            name="btnFist" giveId="${giveLi.giveId}"
                                            giveCount="${li.giveCount}">
                                        <i class="fa fa-thumbs-up" style="color: red;"></i> <span>${li.giveCount}</span>人已赞
                                    </button>
                                    <#break>
                                <#else >
                                    <#if (giveLi_index+1) == li.blogsGiveBOList?size>
                                        <button class="btn btn-white btn-xs BtnGive" name="btnFist"
                                                blogId="${li.blosId}">
                                            <i class="fa fa-thumbs-up"></i> <span>${li.giveCount}</span>人赞
                                        </button>
                                    </#if>
                                </#if>
                            </#list>
                        <#else >
                            <button class="btn btn-white btn-xs BtnGive" blogId="${li.blosId}" name="btnFist">
                                <i class="fa fa-thumbs-up"></i> <span>0</span>人赞
                            </button>
                        </#if>
                            <button class="btn btn-white btn-xs"><i class="fa fa-comments Mydiv"></i> 评论</button>
                        </div>
                    </div>
                </div>
                <div class="social-footer commhuifu">
                <#if li.blogsCommBOList?? && (li.blogsCommBOList?size > 0)>
                    <#list li.blogsCommBOList?sort_by("blosCommDate") as blogsComm>
                                <div class="social-comment ${blogsComm.blosCommId}">
                                    <a href="" class="pull-left">
                                        <img alt="image" src="${basePath}/images/${blogsComm.blosCommUsersImg!}">
                                    </a>
                                    <div class="media-body bodyCLass">
                                        <a href="#">
                                            ${blogsComm.blosCommUsersRealName!}
                                        </a>:
                                        ${blogsComm.blosCommRetext!}
                                        <span>
                                             <#if (Session.users.id != blogsComm.blosCommUsersId)>
                                                         <a href="javascript:;" style="margin-left: 10px;"
                                                            byreplyId="${blogsComm.blosCommUsersId}"
                                                            replyId="${Session.users.id}"
                                                            commId="${blogsComm.blosCommId}"
                                                            blogsId="${li.blosId}"
                                                            userRealName="${blogsComm.blosCommUsersRealName}"
                                                            class="pull-right commRelf">回复</a>
                                             </#if>
                                            <#if (Session.users.id == blogsComm.blosCommUsersId) || (Session.users.id == li.blosUserId)>
                                                         <a href="javascript:;" class="pull-right deleteComm"
                                                            commId="${blogsComm.blosCommId}">删除</a>
                                            </#if>
                                        </span>
                                        <br/>
                                        <small class="text-muted">${blogsComm.blosCommDate?datetime}</small>

                                    </div>
                                    <div class="mydivcomm">
                                         <#if li.blosCommReplyBOList?? && (li.blosCommReplyBOList?size > 0)>
                                        <#list li.blosCommReplyBOList?sort_by("blosCommReplyDate")  as blosCommReply>
                                            <#if blogsComm.blosCommId == blosCommReply.commReplyBlosId>
                                            <div class="social-comment moveCommRigh ${blosCommReply.commentByuserId}${blogsComm.blosCommId}">
                                                <a href="" class="pull-left">
                                                    <img alt="image"
                                                         src="${basePath}/images/${blosCommReply.blosCommReplyUsersImg!}">
                                                </a>
                                                <div class="media-body">
                                                    <a href="#"> ${blosCommReply.blosCommReplyUsersRealName}</a><span
                                                        style="color: #999999;"> 回复 </span><a
                                                        href="#">${blosCommReply.commentByuserRealName!}</a>
                                                    <span class="moveChen">
                                                         : ${blosCommReply.blosCommReplyRetext}

                                                    <#if (Session.users.id != blosCommReply.blosCommReplyUsersId)>
                                                        <a href="javascript:;" style="margin-left: 10px;"
                                                           class="pull-right commRelf"
                                                           byreplyId="${blosCommReply.blosCommReplyUsersId}"
                                                           replyId="${Session.users.id}"
                                                           commId="${blogsComm.blosCommId}"
                                                           blogsId="${li.blosId}"
                                                           userRealName="${blosCommReply.blosCommReplyUsersRealName}">回复</a>
                                                    </#if>
                                                     <#if (Session.users.id == blosCommReply.blosCommReplyUsersId) || (Session.users.id == li.blosUserId)>
                                                         <a href="javascript:;" class="pull-right commRelyDel"
                                                            repyId="${blosCommReply.blosCommReplyId}"
                                                            byUserId="${blosCommReply.blosCommReplyUsersId}"
                                                            blogCommId="${blogsComm.blosCommId}">删除</a>
                                                     </#if>
                                                    </span>

                                                    <br/>
                                                    <small class="text-muted">${blosCommReply.blosCommReplyDate?datetime}</small>
                                                </div>
                                            </div>
                                            </#if>
                                        </#list>
                                         </#if>
                                    </div>
                                </div>

                    </#list>
                </#if>
                </div>
                <div class="social-footer">
                    <div class="social-comment">
                        <a href="" class="pull-left">
                            <img alt="image" src="${basePath}/images/${Session.users.headImg}">
                        </a>
                        <div class="media-body">
                            <div class="row">
                                <div class="col-sm-11 testareafist">
                                    <textarea style="resize:none" class="form-control" placeholder="填写评论..."></textarea>
                                </div>
                                <div class="col-sm-1 btnsumitTest">
                                    <button class="btn btn-info btn-circle btn-lg btntoBlogsComm" blogsId="${li.blosId}"
                                            type="button"><i
                                            class="fa fa-check"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                </#list>
            <#else >
            <h3 class="text-center">暂时没有博客动态...</h3>
            </#if>
    <#if (shareBlogsList?size > 4)>
    <div class="MyDivParent">
        <div class="ManyDiv">
            <button class="btn btn-primary btn-block gomany"><i class="fa fa-arrow-down"></i>
                显示更多
            </button>
        </div>
    </div>

    </#if>
        </div>
    </div>
    <div class="col-sm-1"></div>
</div>
</div>
</body>
</html>
<script type="text/javascript">

    $(document).ready(function () {
        $("#rightreigh").click(function () {
            location.reload();
        });
    });
    var userHeadImg = '${Session.users.headImg}';

</script>