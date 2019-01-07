/*
function pages(from,num) {
    num += parseInt(num) + 1;
    var option = {
        data : {pageIndex : num},
        success:function(date){
            // 循环查询出来的值
            var div = "";
            if(date.obj.page.list.length > 0){
                for (var i = 0; i < date.obj.page.list.length; i++) {
                    div += '<tr>\n' +
                        '    <td>' + date.obj.page.list[i].userName + '</td>\n' +
                        '    <td>' + date.obj.page.list[i].realName + '</td>\n' +
                        '    <td>' + date.obj.page.list[i].phone + '</td>\n' +
                        '    <td>' + date.obj.page.list[i].positionName + '</td>\n' +
                        '    <td>' + app.dateTime(date.obj.page.list[i].creationDate, "YY-MM-DD hh:mm:ss") + '</td>\n' +
                        '    <td><a href="' + path + '/sharedUsers/showUsers/' + date.obj.page.list[i].id + '">查看</a> <a\n' +
                        '             href="javascript:;" class="del" userId="' + date.obj.page.list[i].id + '">删除</a>\n' +
                        '             <a href="' + path + '/sharedUsers/goUpdate/' + date.obj.page.list[i].id + '">修改</a></td>\n' +
                        '    </tr>';
                }
            }else{
                div += '<tr>\n' +
                    '    <td colspan="6" align="center">暂无数据！</td>\n' +
                    '    </tr>';
            }
            document.getElementById("pageId").value = date.obj.page.pageNum + 1;
            $(".tableTbody").html(div);
        }

    };
    //提交表单
    $(from).ajaxSubmit(option);
    return false; //阻止表单默认提交
}*/

function pages(from,num) {
    from.pageIndex.value = num;
    from.submit();
}