<div class="col-sm-9 animated fadeInRight">
    <div class="mail-box-header">
        <div class="pull-ri ght tooltip-demo">
            <a href="mail_compose.html" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top"
               title="回复"><i class="fa fa-reply"></i> 回复</a>
            <a href="email_detail.ftl#" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top"
               title="打印邮件"><i class="fa fa-print"></i> </a>
            <a href="email_Index.ftl" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top"
               title="标为垃圾邮件"><i class="fa fa-trash-o"></i> </a>
        </div>
        <h2>
            查看邮件
        </h2>
        <div class="mail-tools tooltip-demo m-t-md">
            <h3>
                <span class="font-noraml">主题： </span>${email.emailDigest}
            </h3>
            <h5>
                <span class="pull-right font-noraml">${email.creationDate?date}</span>
                        <#if type == 0>
                            <span class="font-noraml">发件人： </span>${email.hairName}
                        <#elseif type == 1>
                            <span class="font-noraml">收件人： </span>${email.receiveName}
                        </#if>
            </h5>
        </div>
    </div>
    <div class="mail-box">
        <div class="mail-body">
        ${email.emailContent}
        </div>
    <#--<div class="mail-attachment">
        <p>
            <span><i class="fa fa-paperclip"></i> 2 个附件 - </span>
            <a href="email_detail.ftl#">下载全部</a> |
            <a href="email_detail.ftl#">预览全部图片</a>
        </p>
        <div class="attachment">
            <div class="file-box">
                <div class="file">
                    <a href="email_detail.ftl#">
                        <span class="corner"></span>
                        <div class="icon">
                            <i class="fa fa-file"></i>
                        </div>
                        <div class="file-name">
                            Document_2014.doc
                        </div>
                    </a>
                </div>

            </div>
            <div class="file-box">
                <div class="file">
                    <a href="email_detail.ftl#">
                        <span class="corner"></span>

                        <div class="image">
                            <img alt="image" class="img-responsive" src="img/p1.jpg">
                        </div>
                        <div class="file-name">
                            Italy street.jpg
                        </div>
                    </a>

                </div>
            </div>
            <div class="file-box">
                <div class="file">
                    <a href="email_detail.ftl#">
                        <span class="corner"></span>

                        <div class="image">
                            <img alt="image" class="img-responsive" src="img/p2.jpg">
                        </div>
                        <div class="file-name">
                            My feel.png
                        </div>
                    </a>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>-->
        <div class="mail-body text-right tooltip-demo">
            <a class="btn btn-sm btn-white" href="mail_compose.html"><i class="fa fa-reply"></i> 回复</a>
            <a class="btn btn-sm btn-white" href="mail_compose.html"><i class="fa fa-arrow-right"></i> 下一封</a>
            <button title="" data-placement="top" data-toggle="tooltip" type="button"
                    data-original-title="打印这封邮件" class="btn btn-sm btn-white"><i class="fa fa-print"></i> 打印
            </button>
            <button title="" data-placement="top" data-toggle="tooltip" data-original-title="删除邮件"
                    class="btn btn-sm btn-white"><i class="fa fa-trash-o"></i> 删除
            </button>
        </div>
        <div class="clearfix"></div>
    </div>
</div>