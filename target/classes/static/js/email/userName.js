var path = document.getElementById("path").value;
var falg = 1;


/*function usernameblur() {
    var receiveName = $("#receiveName");
    if (!app.isNull(receiveName.val())) {
        selUserName();
        if (falg === 0) {
            //可以发送邮件
            receiveName.val(receiveName.val());
        } else if (falg === 1) {

        }
    } else {
        receiveName.val("");
    }

};*/

/**
 * 判断邮件接受人是否存在
 * @author 牛自豪
 * @time 2019/1/3 16:20
 * @returns {boolean}
 */
function selUserName() {
    var receiveName = $("#receiveName").val();
    if (!app.isNull(receiveName)) {
        $.ajax({
            type: "get",
            data: {name: receiveName},
            dataType: "json",
            url: path + "/sharedUsers/getUserNamorRealName",
            success: function (date) {
                if (date.status == 200) {
                    document.getElementById("userNameSpanId").innerHTML = "<font color='red'>查无此用户！</font>";
                    receiveName.val("");
                    falg = 1;
                } else {
                    document.getElementById("userNameSpanId").innerHTML = "";
                    falg = 0;
                }
            },
            error: function () {
                falg = 1;
            }
        });
    }
}