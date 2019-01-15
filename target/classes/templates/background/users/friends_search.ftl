<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>查询好友</title>
    <#include "../comm/script.ftl">
    <link href="${basePath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <script src="${basePath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${basePath}/js/users/friends.js"></script>
    <style>
        .col-sm-8 {
            width: 66.66666667%
        }
    </style>
</head>
<body class="gray-bg">
<input type="hidden" value="${basePath}" id="path"/>
<input type="hidden" value="${users.id}" id="userId"/>
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12" id="MyFriendsFIst">
            <div class="ibox">
                <div class="ibox-content">
                    <h2>查询好友</h2>
                    <p>
                        根据用户名或者真实姓名
                    </p>
                    <div class="input-group">
                        <input type="text" placeholder="查找好友" name="name" id="firendName" class="input form-control">
                        <span class="input-group-btn">
                                        <button type="button" id="sachFriendsSumit" class="btn btn btn-primary"> <i
                                                class="fa fa-search"></i> 搜索</button>
                                </span>
                    </div>
                    <div class="clients-list">
                        <ul class="nav nav-tabs">
                            <span class="pull-right small text-muted" id="fendIDFriend">0个好友</span>
                            <li class="active"><a data-toggle="tab" href="#tab-1"><i class="fa fa-user"></i> 联系人</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="full-height-scroll">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-hover">
                                            <tbody id="tbodyId">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-5">
            <div class="ibox ">
                <div class="ibox-content" id="FrendsUsers" style="display: none">
                    <div class="tab-content" id="UserDivId">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $('.full-height-scroll').slimScroll({
            height: '100%'
        });
    });
</script>


</body>

</html>
