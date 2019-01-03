<div class="col-sm-9 animated fadeInRight">
    <div class="mail-box-header">
        <div class="pull-right tooltip-demo">
            <a href="javascript:void(0);" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top"
               title="存为草稿"><i class="fa fa-pencil"></i> 存为草稿</a>
            <a href="${basePath}/sharedEmail/dele/${email.id}" class="btn btn-info btn-sm"
               data-toggle="tooltip" data-placement="top" title="删除草稿"><i class="fa fa-times"></i> 删除草稿</a>
            <a href="${basePath}/sharedReceiveMail/emailIndex" class="btn btn-danger btn-sm" data-toggle="tooltip"
               data-placement="top"
               title="放弃"><i class="fa fa-times"></i> 放弃</a>
        </div>
        <h2>
            写信
        </h2>
    </div>
    <div class="mail-box">
        <div class="mail-body">
            <div class="clearfix">
                <p id="error" style="align-content: center"/>
            </div>
            <form class="form-horizontal" action="/sharedEmail/emailAdd" id="compose" method="post">
                <div class="form-group">
                    <label class="col-sm-2 control-label">发送到：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" onblur="selUserName();" id="receiveName"
                               name="receiveName" value="${email.receiveName}">
                        <span id="userNameSpanId"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">主题：</label>
                    <div class="col-sm-10">
                        <input type="text" id="emailDigest" name="emailDigest" class="form-control"
                               value="${email.emailDigest}">
                    </div>
                </div>
                <div class="mail-text h-200">
                    <div style="display:none">
                        <input type="text" id="id" name="id" class="form-control" value="${email.id}">
                        <input type="text" id="emailContent" name="emailContent" value="${email.emailContent}">
                    </div>
                    <div id="editor">${email.emailContent}</div>
                </div>
                <div class="mail-body text-right tooltip-demo">
                    <a href="javascript:void(0);" class="btn btn-sm btn-primary" id="fa_reply" onclick="fareply()"
                       data-toggle="tooltip"
                       data-placement="top" title="Send"><i class="fa fa-reply"></i> 发送
                    </a>
                    <a href="${basePath}/sharedReceiveMail/emailIndex" class="btn btn-danger btn-sm"
                       data-toggle="tooltip" data-placement="top"
                       title="放弃"><i class="fa fa-times"></i> 放弃</a>
                    <a href="javascript:void(0);" class="btn btn-white btn-sm" id="fa_pencil" onclick="fapencil()"
                       data-toggle="tooltip"
                       data-placement="top"
                       title="Move to draft folder"><i class="fa fa-pencil"></i> 存为草稿
                    </a>
                    <a href="${basePath}/sharedReceiveMail/emailIndex" class="btn btn-info btn-sm"
                       data-toggle="tooltip" data-placement="top" title="删除草稿"><i class="fa fa-times"></i> 删除草稿</a>
                    <input type="hidden" name="draft" id="draft" value=""/>
                </div>
                <div class="clearfix"></div>
            </form>
        </div>
    </div>
</div>