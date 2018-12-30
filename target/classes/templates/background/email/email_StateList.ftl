<div class="col-sm-9 animated fadeInRight">
<#--引入邮件列表Top-->
   <#include "email_ListTop.ftl">
    <div class="mail-box">
        <table class="table table-hover table-mail">
            <tbody id="emailList">
                   <#if (page.records?size > 0)>
                        <#list page.records as email>
                                <tr class="<#if (email.state==3)>unread</#if><#if (email.state!=3)>read</#if>">
                                    <td class="check-mail">
                                        <input type="checkbox" name="email_id" value="${email.id}" class="i-checks">
                                    </td>
                                    <td class="mail-ontact"><a
                                            href="/sharedEmail/emailLook/${email.id}">${email.friendsName}</a>
                                    <#if (email.state==1)>
                                        <span class="label label-warning pull-right"></span>
                                    <#elseif (email.state==2)>
                                        <span class="label label-warning pull-right">未读邮件</span>
                                    <#elseif (email.state==3)>
                                        <span class="label label-danger pull-right">重要文件</span>
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
            </tbody>
        </table>
        <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <div class="btn-group" role="group">
                <div dir="ltr" style="text-align:center" class="ui-paging-info">第 ${page.current} 页 | 共 ${page.total} 条 | 共 ${page.pages} 页　</div>
            </div>
        </div>
        <#include "email_page.ftl"/>
    </div>
</div>