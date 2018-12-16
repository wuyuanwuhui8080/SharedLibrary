<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <#include "../comm/script.ftl">
    <!-- 全局js -->
    <link href="${basePath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>自定义响应式表格</h5>
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
                                <select class="input-sm form-control input-s-sm inline" name="position">
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
                                           class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>用户名</th>
                                <th>真实姓名</th>
                                <th>电话</th>
                                <th>角色</th>
                                <th>出生日期</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                               <#list usersList as sus>
                               <tr>
                                   <td>${sus.userName}</td>
                                   <td>${sus.realName}</td>
                                   <td>${sus.phone}</td>
                                   <#if (sus.positionId) == 1 >
                                      <td>普通用户</td>
                                   <#elseif (sus.positionId) == 2 >
                                      <td>扫地僧</td>
                                   <#elseif (sus.positionId) == 3>
                                      <td>管理员</td>
                                   </#if>
                                   <td>${sus.birthday?date}</td>
                                   <td><a href="#">查看</a> <a href="#">删除</a> <a href="#">修改</a></td>
                               </tr>
                               </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</div>
</body>
</html>
 <!-- 全局js -->
    <!-- Peity -->
    <script src="${basePath}/js/plugins/peity/jquery.peity.min.js"></script>

    <!-- iCheck -->
    <script src="${basePath}/js/plugins/iCheck/icheck.min.js"></script>

    <!-- Peity -->
    <script src="${basePath}/js/demo/peity-demo.js"></script>

    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>