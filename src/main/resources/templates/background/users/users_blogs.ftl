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
<div class="wrapper wrapper-content  animated fadeInRight">
    <input type="hidden" value="${basePath}" id="path"/>
    <input type="hidden" value="${users.id}" id="userId"/>
    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">
            <div class="ibox">
                <div class="ibox-content text-center">

                    <h3 class="m-b-xxs">好友博客</h3>
                </div>

            </div>
            <#if shareBlogsList?? && (shareBlogsList?size > 0)>
                <#list shareBlogsList as li>
            <div class="social-feed-box">
                <div class="pull-right social-action dropdown">
                    <button data-toggle="dropdown" class="dropdown-toggle btn-white">
                        <i class="fa fa-angle-down"></i>
                    </button>
                    <ul class="dropdown-menu m-t-xs">
                        <li><a href="#">设置</a></li>
                    </ul>
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
                        <#if li.giveCount?? && li.giveCount != 0>
                            <#list li.blogsGiveBOList as giveLi>
                                <#if giveLi.giveUserid == Session.users.id>
                                    <button class="btn btn-white btn-xs BtnGive" giveId="${giveLi.giveId}"
                                            id="${li.blosId}"
                                            giveCount="${li.giveCount}">
                                        <i class="fa fa-thumbs-up" style="color: red;"></i> <span>${li.giveCount}</span>人已赞
                                    </button>
                                    <#break>
                                <#else >
                                          <button class="btn btn-white btn-xs BtnGive">
                                              <i class="fa fa-thumbs-up"></i> <span>${li.giveCount}</span>人赞
                                          </button>
                                </#if>
                            </#list>
                        <#else >
                            <button class="btn btn-white btn-xs BtnGive">
                                <i class="fa fa-thumbs-up"></i> <span>${li.giveCount}</span>人赞
                            </button>
                        </#if>
                        <button class="btn btn-white btn-xs"><i class="fa fa-comments Mydiv"></i> 评论</button>
                        <button class="btn btn-white btn-xs"><i class="fa fa-share"></i> 分享</button>
                    </div>
                </div>
                <#if li.blosCommDate??>
                    <div class="social-footer">
                        <div class="social-comment">
                            <a href="" class="pull-left">
                                <img alt="image" src="${basePath}/images/${li.blosCommUsersImg!}">
                            </a>
                            <div class="media-body">
                                <a href="#">
                                    ${li.blosCommUsersRealName!}
                                </a> ${li.blosCommRetext!}
                                <br/>
                                <small class="text-muted">${li.blosCommDate?datetime}</small>
                            </div>
                            <#if li.blosCommReplyDate??>
                            <div class="social-comment">
                                <a href="" class="pull-left">
                                    <img alt="image" src="${basePath}/images/${li.blosCommReplyUsersImg}">
                                </a>
                                <div class="media-body">
                                    <a href="#"> ${li.blosCommReplyUsersRealName}</a><span
                                        style="color: #999999;">回复</span><a href="#">${li.blosCommUsersRealName!}</a>
                                    ${li.blosCommReplyRetext}
                                    <br/>
                                    <small class="text-muted">${li.blosCommReplyDate?datetime}</small>
                                </div>
                            </div>
                            </#if>
                        </div>
                        <div class="social-comment">
                            <a href="" class="pull-left">
                                <img alt="image" src="${basePath}/images/${Session.users.headImg}">
                            </a>
                            <div class="media-body">
                                <div class="row">
                                    <div class="col-sm-11">
                                        <textarea style="resize:none" class="form-control"
                                                  placeholder="填写评论..."></textarea>
                                    </div>
                                    <div class="col-sm-1">
                                        <button class="btn btn-info btn-circle btn-lg" type="button"><i
                                                class="fa fa-check"></i>
                                        </button>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                <#else >
                <div class="social-footer">
                    <div class="social-comment">
                        <a href="" class="pull-left">
                            <img alt="image" src="${basePath}/images/${Session.users.headImg}">
                        </a>
                        <div class="media-body">
                            <div class="row">
                                <div class="col-sm-11">
                                    <textarea style="resize:none" class="form-control" placeholder="填写评论..."></textarea>
                                </div>
                                <div class="col-sm-1">
                                    <button class="btn btn-info btn-circle btn-lg" type="button"><i
                                            class="fa fa-check"></i>
                                    </button>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
                </#if>
            </div>
                </#list>
            <#else >
            <h3 class="text-center">暂时没有博客动态...</h3>
            </#if>
        </div>
        <div class="col-sm-1"></div>
    </div>
</div>
</body>
</html>
