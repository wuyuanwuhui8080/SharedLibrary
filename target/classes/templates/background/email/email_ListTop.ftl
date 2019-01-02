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
        <#if state==0>
            收件箱 (${emailSum})
        <#else>
            ${bt}邮件
            <#if bt=="重要">
                (${emailMajorSum})
            <#elseif bt=="删除">
                (${emailDelSum})
            <#elseif bt=="草稿">
                (${emailDraftSum})
            </#if>
        </#if>
    </h2>
    <div class="mail-tools tooltip-demo m-t-md">
        <button class="btn btn-white btn-sm" id="Refresh" data-toggle="tooltip" data-placement="left"
                title="刷新邮件列表"><i class="fa fa-refresh"></i> 刷新
        </button>
        <button class="btn btn-white btn-sm" id="have_read" data-toggle="tooltip" data-placement="top"
                title="标为已读"><i class="fa fa-eye"></i>
        </button>
        <button class="btn btn-white btn-sm" id="major" data-toggle="tooltip" data-placement="top"
                title="标为重要">
            <i class="fa fa-exclamation"></i>
        </button>
        <button class="btn btn-white btn-sm" id="del" data-toggle="tooltip" data-placement="top"
                title="删除邮件">
            <i class="fa fa-trash-o"></i>
        </button>
    </div>
</div>