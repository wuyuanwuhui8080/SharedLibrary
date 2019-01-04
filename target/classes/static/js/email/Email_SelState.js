/*查找状态邮箱JS*/

var selMajor = $("#selMajor");
var selDraft = $("#selDraft");
var selDel = $("#selDel");
var selFa = $("#selFa");

/**
 * 查找自己发送的邮箱
 * @author 牛自豪
 * @time 2018/12/16 10:32
 */
selFa.click(function () {
    location.href = "/sharedReceiveMail/emailState/" + 1;
});


/**
 * 查找邮件状态为重要
 * @author 牛自豪
 * @time 2018/12/16 10:32
 */
selMajor.click(function () {
    location.href = "/sharedReceiveMail/emailState/" + 3;
});

/**
 * 查找邮件状态为删除
 * @author 牛自豪
 * @time 2018/12/16 10:32
 */
selDel.click(function () {
    location.href = "/sharedReceiveMail/emailState/" + 4;
});

/**
 * 查找邮件状态为草稿
 * @author 牛自豪
 * @time 2018/12/16 10:32
 */
selDraft.click(function () {
    location.href = "/sharedReceiveMail/emailState/" + 5;
});