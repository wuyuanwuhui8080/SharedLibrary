<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <#include "../comm/script.ftl">
    <script src="${basePath}/js/users/userListJs.js"></script>
    <script src="${basePath}/js/comm/page.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>用户列表</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="table_basic.html#">选项1</a>
                            </li>
                            <li><a href="table_basic.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <form action="${basePath}/sharedUsers/goUserList" method="get">
                        <div class="row">
                            <div class="col-sm-5 m-b-xs">
                                <select class="input-sm form-control input-s-sm inline selectClass" name="position">
                                    <option value="">请选择</option>
                                   <#list positionList as li>
                                       <option value="${li.id}"
                                               <#if position?? && li.id == position>selected="selected" <#else> </#if>
                                       >${li.positionName}</option>
                                   </#list>
                                </select>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text" placeholder="请输入关键词" name="name" value="${name!}"
                                           class="input-sm form-control RealnameOrUsername"> <span class="input-group-btn">
                                        <button type="submit" class="btn btn-sm btn-primary sarchSumitUser"> 搜索</button> </span>
                                </div>
                            </div>
                            <input type="hidden" id="pageId" name="pageIndex" value="${page.pageNum}"/>
                            <button class="btn btn-success" type="button" onclick="goSaveUsre();" style="margin-left: 30px;">添加用户
                            </button>
                        </div>
                    </form>
                    <div style="display: none"><button type="submit"></button></div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>用户名</th>
                                <th>真实姓名</th>
                                <th>电话</th>
                                <th>角色</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody class="tableTbody">
                               <#list page.list as sus>
                               <tr>
                                   <td>${sus.userName}</td>
                                   <td>${sus.realName}</td>
                                   <td>${sus.phone}</td>
                                   <td>${sus.positionName}</td>
                                   <td>${sus.creationDate?datetime}</td>
                                   <td><a href="${basePath}/sharedUsers/showUsers/${sus.id}">查看</a> <a
                                           href="javascript:;" class="del" userId="${sus.id}">删除</a>
                                       <a href="${basePath}/sharedUsers/goUpdate/${sus.id}">修改</a></td>
                               </tr>
                               </#list>
                            </tbody>
                        </table>
                        <#include "../comm/page.ftl">
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</div>
</body>
</html>
    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
        var path = "${basePath}";

        function goSaveUsre() {
            location.href = path + "/sharedUsers/goSaveUser";
        }

    </script>