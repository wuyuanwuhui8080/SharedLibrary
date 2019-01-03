<div class="col-sm-3">
    <div class="ibox float-e-margins">
        <div class="ibox-content mailbox-content">
            <div class="file-manager">
                <a class="btn btn-block btn-primary compose-mail" href="${basePath}/sharedReceiveMail/emailCompose">写信</a>
                <div class="space-25"></div>
                <h5>文件夹</h5>
                <ul class="folder-list m-b-md" style="padding: 0">
                    <li>
                        <a href="/sharedReceiveMail/emailIndex"> <i class="fa fa-inbox "></i> 收件箱
                            <#if (emailSum==0)>
                            <#else>
                                <span class="label label-warning pull-right">${emailSum}</span>
                            </#if>
                        </a>
                    </li>
                    <li>
                        <a id="selFa" href="javaScript:void();"> <i class="fa fa-envelope-o"></i> 发信</a>
                    </li>
                    <li>
                        <a id="selMajor" href="javaScript:void();"> <i class="fa fa-certificate"></i> 重要
                            <#if (emailMajorSum==0)>
                            <#else>
                                <span class="label label-danger pull-right">${emailMajorSum}</span>
                            </#if>
                        </a>
                    </li>
                    <li>
                        <a id="selDraft" href="javaScript:void();"> <i class="fa fa-file-text-o"></i> 草稿
                            <#if (emailDraftSum==0)>
                            <#else>
                                    <span class="label label-default pull-right">${emailDraftSum}</span>
                            </#if>
                        </a>
                    </li>
                    <li>
                    <a id="selDel" href="javaScript:void();"> <i class="fa fa-trash-o"></i> 垃圾箱
                        <#if (emailDelSum==0)>
                        <#else>
                            <span class="label label-default pull-right">${emailDelSum}</span></a>
                        </#if>
                    </li>
                </ul>
            <#-- <h5>分类</h5>
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
             </ul>-->

            <#-- <h5 class="tag-title">标签</h5>
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
             </ul>-->
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>