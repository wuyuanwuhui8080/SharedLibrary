<#-- 取得 应用的绝对根路径 -->
<#assign basePath=springMacroRequestContext.contextPath>

<ul class="pager">
    <li><a href="javascript:pages(document.forms[0],1);">首页</a></li>
    <#if page.hasPreviousPage>
        <li class=""><a href="javascript:pages(document.forms[0],${page.pageNum - 1});">&larr; 上一页</a></li>
    <#else >
         <li class="disabled"><a href="javascript:;">&larr; 上一页</a></li>
    </#if>
   <#-- <#list page.navigatepageNums as navigatepageNum>
        <#if navigatepageNum==page.pageNum>
          <li><a href="#">${navigatepageNum}</a></li>
        </#if>
        <#if navigatepageNum!=page.pageNum>
          <li><a href="#">${navigatepageNum}</a></li>
        </#if>
    </#list>-->
     <#if page.hasNextPage>
            <li><a href="javascript:pages(document.forms[0],${page.pageNum + 1});">下一页 &rarr;</a></li>
     <#else>
            <li class="disabled"><a href="javascript:;">下一页 &rarr;</a></li>
     </#if>
    <li><a href="javascript:pages(document.forms[0],${page.pages});">尾页</a></li>
</ul>