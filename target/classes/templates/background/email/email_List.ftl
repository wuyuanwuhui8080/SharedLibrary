<div class="col-sm-9 animated fadeInRight">
<#--引入邮件列表Top-->
   <#include "email_ListTop.ftl">
    <div class="mail-box">
        <table class="table table-hover table-mail">
            <tbody id="emailList">
                    <#if (page.records?size > 0)>
                        <#list page.records as email>
                            <#if (email.state != 4 && email.state != 5)>
                                <tr class="<#if (email.state==3)>unread</#if><#if (email.state!=3)>read</#if>">
                                    <td class="check-mail">
                                        <input type="checkbox" name="email_id" value="${email.id}" class="i-checks">
                                    </td>
                                    <td class="mail-ontact"><a
                                            href="/sharedEmail/emailLook/${email.id}">${email.friendsName}</a>
                                    <#if (email.state==1)>
                                    <#elseif (email.state==2)>
                                        <span class="label label-warning pull-right">未读邮件</span>
                                    <#elseif (email.state==3)>
                                        <span class="label label-danger pull-right">重要文件</span>
                                    </#if>
                                    </td>
                                    <td class="mail-subject"><a
                                            href="/sharedEmail/emailLook/${email.id}">${email.emailDigest}</a></td>
                                    <td class=""></td>
                                    <td class="text-right mail-date">${email.creationDate?date}</td>
                                </tr>
                            </#if>
                        </#list>
                    <#else>
                        <tr align="center">
                            <td colspan="7">没有邮件</td>
                        </tr>
                    </#if>
            </tbody>
        </table>
        <#include "email_page.ftl"/>
    </div>

</div>
