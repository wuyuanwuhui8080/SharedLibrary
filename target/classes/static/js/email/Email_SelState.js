/*查找状态邮箱JS*/

var selMajor = $("#selMajor");
var selDraft = $("#selDraft");
var selDel = $("#selDel");

/**
 * 查找邮件状态为重要
 * @author 牛自豪
 * @time 2018/12/16 10:32
 */

selMajor.click(function () {
    location.href = "/sharedEmail/emailState/" + 3;
});

/**
 * 查找邮件状态为删除
 * @author 牛自豪
 * @time 2018/12/16 10:32
 */
selDel.click(function () {
    location.href = "/sharedEmail/emailState/" + 4;
});

/**
 * 查找邮件状态为草稿
 * @author 牛自豪
 * @time 2018/12/16 10:32
 */
selDraft.click(function () {
    location.href = "/sharedEmail/emailState/" + 5;
});