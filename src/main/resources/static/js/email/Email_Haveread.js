/*更改邮箱状态JS*/


var path = document.getElementById("path").value;
var have_read = $("#have_read");
var major = $("#major");
var delEmail = $("#del");
var refresh = $("#Refresh");


/**
 *刷新邮箱界面
 */
refresh.click(function () {
    location.href = "/sharedReceiveMail/emailIndex";
})

/**
 * 更改邮件状态为已读
 * @author 牛自豪
 * @time 2018/12/16 10:32
 */
have_read.click(function () {
    var ids = "";
    ids = getChecked(ids);
    if (ids === "") {
        return null;
    } else {
        location.href = "/sharedReceiveMail/emailUpState/" + ids + "/" + 1;
    }
});

/**
 * 更改邮件状态为重要
 * @author 牛自豪
 * @time 2018/12/16 10:32
 */

major.click(function () {
    var ids = "";
    ids = getChecked(ids);
    if (ids === "") {
        return null;
    } else {
        location.href = "/sharedReceiveMail/emailUpState/" + ids + "/" + 3;
    }
});

/**
 * 更改邮件状态为删除
 * @author 牛自豪
 * @time 2018/12/16 10:32
 */
delEmail.click(function () {
    var ids = "";
    ids = getChecked(ids);
    if (ids === "") {
        return null;
    } else {
        location.href = "/sharedReceiveMail/emailUpState/" + ids + "/" + 4;
    }

});

/**
 * 获取选中的邮件ID
 * @param ids
 */
function getChecked(ids) {
    var byName = document.getElementsByName("email_id");
    var objarray = byName.length;
    for (i = 0; i < objarray; i++) {
        if (byName[i].checked === true) {
            if (i === objarray - 1) {
                ids += byName[i].value;
            } else {
                ids += byName[i].value + ",";
            }
        }
    }
    return ids;
}
