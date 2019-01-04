<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>好友</title>
    <#include "../comm/script.ftl">
    <script src="${basePath}/js/plugins/suggest/bootstrap-suggest.min.js"></script>
    <script src="${basePath}/js/users/friendsSearch.js"></script>
</head>

<body class="gray-bg">
<input type="hidden" value="${basePath}" id="path"/>
<input type="hidden" value="${users.id}" id="userid"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <#if friendsList??&&(friendsList?size>0)>
            <div class="col-sm-12">
                <nav class="navbar navbar-default" role="navigation">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <h3>好友列表</h3>
                        </div>
                        <form class="navbar-form navbar-left" role="search">
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="input-group" style="width: 300px;">
                                            <input type="text" placeholder="好友查询" class="form-control" id="baidu">
                                            <div class="input-group-btn">
                                                <button type="button" class="btn btn-white dropdown-toggle"
                                                        data-toggle="dropdown">
                                                    <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button type="submit" id="userSearch" class="btn btn-primary">查询</button>
                        </form>
                    </div>
                </nav>
            </div>
            <div id="MyfendList">
<#list friendsList as li>
    <div class="col-sm-4">
        <div class="contact-box">
            <a href="${basePath}/sharedUsers/lookProfile/${li.id}">
                <div class="col-sm-4">
                    <div class="text-center">
                        <img alt="image" class="img-circle m-t-xs img-responsive"
                             src="${basePath}/images/${li.headImg}">
                        <div class="m-t-xs font-bold"></div>
                    </div>
                </div>
                <div class="col-sm-8">
                    <h3><strong>${li.realName}</strong></h3>
                    <address>
                        <strong>个性签名</strong><br>
                        ${li.individual}
                        <br>
                    </address>
                </div>
                <div class="clearfix"></div>
            </a>
        </div>
    </div>
</#list>
            </div>
        <#else >
            <div align="center">
                <h2>您还没有好友...</h2>
            </div>
        </#if>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.contact-box').each(function () {
            animationHover(this, 'pulse');
        });
    });
    var userid = document.getElementById("userid").value;
    //百度搜索测试
    var baiduBsSuggest = $("#baidu").bsSuggest({
        allowNoKeyword: false, //是否允许无关键字时请求数据
        multiWord: true, //以分隔符号分割的多关键字支持
        separator: ",", //多关键字支持时的分隔符，默认为空格
        getDataMethod: "url", //获取数据的方式，总是从 URL 获取
        // url: 'http://unionsug.baidu.com/su?p=3&t=' + (new Date()).getTime() + '&wd=',
        url: app.path() + '/friendUtil/listFirendUsers?d=' + (new Date()).getTime() + '&userId=' + userid + '&name=',
        /*优先从url ajax 请求 json 帮助数据，注意最后一个参数为关键字请求参数*/
        // jsonp: 'cb',
        /*如果从 url 获取数据，并且需要跨域，则该参数必须设置*/
        processData: function (json) { // url 获取数据时，对数据的处理，作为 getData 的回调函数
            var i, len, data = {
                value: []
            };
            /* if (!json || !json[0].realName || json[0].realName.length === 0) {
                 return false;
             }

             console.log(json);*/
            len = json.length;
            var test = document.getElementById("baidu").value;
            jsonStr = "{'value':[";
            for (i = 0; i < len; i++) {
                data.value.push({
                    word: json[i].realName
                });
            }
            data.defaults = 'baidu';

            //字符串转化为 js 对象
            return data;
        }
    });
</script>


</body>

</html>
