<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>头像上传</title>
    <#include "../comm/script.ftl">
    <script src="${basePath}/js/ajaxfileupload.js"></script>
    <script src="${basePath}/js/users/uploadHeadImg.js"></script>
    <script src="${basePath}/js/amazeui.min.js" charset="utf-8"></script>
    <script src="${basePath}/js/cropper.min.js" charset="utf-8"></script>
    <script src="${basePath}/js/custom_up_img.js" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="${basePath}/css/amazeui.min.css">
    <link rel="stylesheet" href="${basePath}/css/amazeui.cropper.css">
    <link rel="stylesheet" href="${basePath}/css/custom_up_img.css">
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <input type="hidden" value="${users.id}" id="userId"/>
    <input type="hidden" value="${basePath}" id="path"/>
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>头像上传</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="form_editors.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <ul class="nav nav-tabs" id="avatar-tab">
                        <li class="active" id="upload"><a href="javascript:;">本地上传</a>
                        </li>
                    </ul>
                    <div class="m-t m-b" align="center">
                    <#--<form enctype="multipart/form-data" method="post" action="${basePath}/sharedUsers/uploadHeadImg"  target="hiddenFrame" id="headImgFrom">
                        <div id="drop_area" align="center"></div>
                        <button style="margin-top: 20px;" onclick="sumitHeadImg();" class="btn btn-success text-right" id="uploadId" type="button"><i
                                class="fa fa-upload"></i>&nbsp;&nbsp;<span class="bold">上传</span>

                    </form>-->
                    <#-- <div id="demo" class="demo"></div>
                     <form class="container" enctype="multipart/form-data" method="post" id='formBox' name="form">
                     </form>-->
                        <center>
                            <div class="up-img-cover" id="up-img-touch">
                                <img class="am-circle" alt="点击图片上传" src="${basePath}/img/upload.png"
                                     data-am-popover="{content: '点击上传', trigger: 'hover focus'}">
                            </div>
                        </center>
                        <div><a style="text-align: center; display: block;" id="pic"></a></div>

                        <!--图片上传框-->
                        <div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="doc-modal-1">
                            <div class="am-modal-dialog up-frame-parent up-frame-radius">
                                <div class="am-modal-hd up-frame-header">
                                    <label>修改头像</label>
                                    <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
                                </div>
                                <div class="am-modal-bd  up-frame-body">
                                    <div class="am-g am-fl">
                                        <div class="am-form-group am-form-file">
                                            <div class="am-fl">
                                                <button type="button" class="am-btn am-btn-default am-btn-sm">
                                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                                </button>
                                            </div>
                                            <input type="file" id="inputImage">
                                        </div>
                                    </div>
                                    <div class="am-g am-fl">
                                        <div class="up-pre-before up-frame-radius">
                                            <img alt="" src="" id="image">
                                        </div>
                                        <div class="up-pre-after up-frame-radius">
                                        </div>
                                    </div>
                                    <div class="am-g am-fl">
                                        <div class="up-control-btns">
                                            <span class="am-icon-rotate-left" onclick="rotateimgleft()"></span>
                                            <span class="am-icon-rotate-right" onclick="rotateimgright()"></span>
                                            <span class="am-icon-check" id="up-btn-ok"
                                                  url="admin/user/upload.action"></span>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <!--加载框-->
                        <div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="my-modal-loading">
                            <div class="am-modal-dialog">
                                <div class="am-modal-hd">正在上传...</div>
                                <div class="am-modal-bd">
                                    <span class="am-icon-spinner am-icon-spin"></span>
                                </div>
                            </div>
                        </div>

                        <!--警告框-->
                        <div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
                            <div class="am-modal-dialog">
                                <div class="am-modal-hd">信息</div>
                                <div class="am-modal-bd" id="alert_content">
                                    成功了
                                </div>
                                <div class="am-modal-footer">
                                    <span class="am-modal-btn">确定</span>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>