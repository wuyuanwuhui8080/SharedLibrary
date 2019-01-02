<div class="col-sm-9 animated fadeInRight">
    <div class="mail-box-header">
        <div class="pull-right tooltip-demo">
            <a href="mailbox.html" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top"
               title="存为草稿"><i class="fa fa-pencil"></i> 存为草稿</a>
            <a href="mailbox.html" class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="top"
               title="放弃"><i class="fa fa-times"></i> 放弃</a>
        </div>
        <h2>
            写信
        </h2>
    </div>
    <div class="mail-box">
        <div class="mail-body">
            <div   class="clearfix">
                <p id="error" style="align-content: center"/>
            </div>
            <form class="form-horizontal" action="/sharedEmail/emailAdd" id="compose" method="post">
                <div class="form-group">
                    <label class="col-sm-2 control-label">发送到：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="friendsName" name="friendsName" value="${email.friendsName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">主题：</label>
                    <div class="col-sm-10">
                        <input type="text" id="emailDigest" name="emailDigest" class="form-control" value="${email.emailDigest}">
                    </div>
                </div>
                <div class="mail-text h-200">
                    <div id="editor"></div>
                    <input type="text" id="emailContent" name="emailContent" value="${email.emailContent}" style="display:none"/>
                </div>
                <div class="mail-body text-right tooltip-demo">
                    <a href="javascript:void(0);" class="btn btn-sm btn-primary" id="fa_reply" onclick="fareply()" data-toggle="tooltip"
                            data-placement="top" title="Send"><i class="fa fa-reply"></i> 发送
                    </a>
                    <a href="javascript:void(0);" class="btn btn-white btn-sm" id="fa_times" onclick="fatimes()" data-toggle="tooltip"
                            data-placement="top"
                            title="Discard email"><i class="fa fa-times"></i> 放弃
                    </a>
                    <a href="javascript:void(0);" class="btn btn-white btn-sm" id="fa_pencil" onclick="fapencil()" data-toggle="tooltip"
                            data-placement="top"
                            title="Move to draft folder"><i class="fa fa-pencil"></i> 存为草稿
                    </a>
                </div>
                <div class="clearfix"></div>
            </form>
        </div>
    </div>
</div>