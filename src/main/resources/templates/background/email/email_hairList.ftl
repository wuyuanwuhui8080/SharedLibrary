<div class="col-sm-9 animated fadeInRight">
<#--引入邮件列表Top-->
   <#include "email_ListTop.ftl">
    <div class="mail-box">
        <table class="table table-hover table-mail">
            <tbody id="emailList">
                   <#if (page.list?size > 0)>
                        <#list page.list as email>
                                <tr class="unread">
                                    <td class="check-mail">
                                        <input type="checkbox" name="email_id" value="${email.id}" class="i-checks">
                                    </td>
                                    <#if email.state == 5>
                                    <#--草稿邮件,点击进入编辑邮件页面-->
                                        <td class="mail-ontact">
                                            <a href="/sharedEmail/DraftToCompese/${email.id}">${email.receiveName}</a>
                                            <span class="label label-default pull-right">草稿</span>
                                        </td>
                                        <td class="mail-subject">
                                            <a href="/sharedEmail/DraftToCompese/${email.id}">${email.emailDigest}</a>
                                        </td>
                                    <#else >
                                    <#--自己曾发送的邮件,点击查看-->
                                        <td class="mail-ontact">
                                            <a href="/sharedEmail/LookHairEmail/${email.id}">${email.receiveName}</a>
                                            <span class="label label-info pull-right">已发送</span>
                                        </td>
                                        <td class="mail-subject">
                                            <a href="/sharedEmail/LookHairEmail/${email.id}">${email.emailDigest}</a>
                                        </td>
                                    </#if>

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
        <#include "../comm/page.ftl">
    </div>
</div>