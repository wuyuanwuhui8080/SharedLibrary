/*发送邮箱JS*/

var path = document.getElementById("path").value;

var E = window.wangEditor;
var editor = new E('#editor');
var emailContent = $('#emailContent');
editor.customConfig.onchange = function (html) {
    // 监控变化，同步更新到 textarea
    emailContent.val(html)
};
// 使用 base64 保存图片
editor.customConfig.uploadImgShowBase64 = true;
editor.create();
// 初始化 textarea 的值
emailContent.val(editor.txt.html());

//发送邮件
function fareply() {
    var compose = $("#compose");
    var friendsName = $("#friendsName").val();
    var emailDigest = $("#emailDigest").val();
    var error = $("#error");
    if (friendsName.length <= 0) {
        error.html("发送人未填写!");
        return false;
    } else if (emailDigest.length <= 0) {
        error.html("主题未填写!");
        return false;
    } else {
        compose.submit();
    }
}

//取消发送
function fatimes() {

}

//存为草稿
function fapencil() {

}
