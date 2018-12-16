var path = document.getElementById("path").value;

/**
 * 更改邮件状态
 * @author 牛自豪
 * @time 2018/12/16 10:32
 */
var Have_read = $("#Have_read");
Have_read.click(function () {
    var byName = document.getElementsByName("email_id");
    var objarray = byName.length;
    var chestr = "";
    for (i = 0; i < objarray; i++) {
        if (byName[i].checked === true) {
            if (i === objarray - 1) {
                chestr += byName[i].value;
            } else {
                chestr += byName[i].value + ",";
            }
        }
    }
    upState(chestr);


});

function upState(chestr) {
    var emailList = $("#emailList");
    $.ajax({
        type: "GET",//请求类型
        url: path + "/sharedEmail/emailUpState",//请求的url
        data: {ids: chestr},//请求参数
        dataType: "html",//ajax接口（请求url）返回的数据类型
        success: function (data) {//data：返回数据（json对象）
            emailList.html(data);
        }
    });
}