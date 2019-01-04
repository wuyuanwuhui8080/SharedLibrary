<div class="col-sm-9 animated fadeInRight">
    <div class="mail-box-header">
        <div class="pull-right tooltip-demo">
            <a href="javascript:void(0);" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top"
               title="存为草稿"><i class="fa fa-pencil"></i> 存为草稿</a>
            <a href="${basePath}/sharedEmail/dele/${email.id}" class="btn btn-info btn-sm"
               data-toggle="tooltip" data-placement="top" title="删除草稿"><i class="fa fa-times"></i> 删除草稿</a>
            <a href="${basePath}/sharedReceiveMail/emailIndex" class="btn btn-danger btn-sm" data-toggle="tooltip"
               data-placement="top"
               title="放弃"><i class="fa fa-times"></i> 放弃</a>
        </div>
        <h2>
            写信
        </h2>
    </div>
    <div class="mail-box">
        <div class="mail-body">
            <div class="clearfix">
                <p id="error" style="align-content: center"/>
            </div>
            <form class="form-horizontal" action="/sharedEmail/emailAdd" id="compose" method="post">
                <div class="form-group">
                    <label class="col-sm-2 control-label">发送到：</label>
                    <div class="col-sm-10">
                      <#--  <input type="text" class="form-control baidu" onblur="selUserName();"  id="receiveName"
                               name="receiveName" value="${email.receiveName}">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-white dropdown-toggle"
                                    data-toggle="dropdown">
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                            </ul>
                        </div>
                        <span id="userNameSpanId"></span>-->
                          <div class="row">
                                <div class="col-sm-10">

                                    <div class="input-group dropdown" style="width: 100%;">
                                        <input type="text"  onblur="selUserName();" id="receiveName" placeholder="好友查询" class="form-control baidu" >
                                        <div class="input-group-btn">
                                            <button type="button" class="btn btn-white dropdown-toggle" id="dropdownui"
                                                             data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                            <span class="caret"></span>
                                        </button>
                                            <ul class="dropdown-menu dropdown-menu-right"   aria-labelledby="dropdownui" style="background-color: #333333;color: white;z-index: 999999999;" role="menu">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                              <div class="col-sm-2 center-left"> <span id="userNameSpanId"  style="font-size: 16px">用户名不存在</span></div>
                          </div>

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">主题：</label>
                    <div class="col-sm-10">
                        <input type="text" id="emailDigest" name="emailDigest" class="form-control"
                               value="${email.emailDigest}">
                    </div>
                </div>
                <div class="mail-text h-200">
                    <div style="display:none">
                        <input type="text" id="id" name="id" class="form-control" value="${email.id}">
                        <input type="text" id="emailContent" name="emailContent" value="${email.emailContent}">
                    </div>
                    <div id="editor">${email.emailContent}</div>
                </div>
                <div class="mail-body text-right tooltip-demo">
                    <a href="javascript:void(0);" class="btn btn-sm btn-primary" id="fa_reply" onclick="fareply()"
                       data-toggle="tooltip"
                       data-placement="top" title="Send"><i class="fa fa-reply"></i> 发送
                    </a>
                    <a href="${basePath}/sharedReceiveMail/emailIndex" class="btn btn-danger btn-sm"
                       data-toggle="tooltip" data-placement="top"
                       title="放弃"><i class="fa fa-times"></i> 放弃</a>
                    <a href="javascript:void(0);" class="btn btn-white btn-sm" id="fa_pencil" onclick="fapencil()"
                       data-toggle="tooltip"
                       data-placement="top"
                       title="Move to draft folder"><i class="fa fa-pencil"></i> 存为草稿
                    </a>
                    <a href="${basePath}/sharedReceiveMail/emailIndex" class="btn btn-info btn-sm"
                       data-toggle="tooltip" data-placement="top" title="删除草稿"><i class="fa fa-times"></i> 删除草稿</a>
                    <input type="hidden" name="draft" id="draft" value=""/>
                </div>
                <div class="clearfix"></div>
            </form>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.contact-box').each(function () {
            animationHover(this, 'pulse');
        });
    });
    var userid = '${Session.users.id}';
    //百度搜索测试
    var baiduBsSuggest = $(".baidu").bsSuggest({
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
            var test = document.getElementsByClassName("baidu").value;
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