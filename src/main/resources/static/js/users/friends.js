$(function () {
    var  falg = true;
    $(".userName").click(function () {
            if(falg){
                $("#MyFriendsFIst").attr("class", "col-sm-8");
                $("#FrendsUsers").show();
                falg = false;
            }else if(falg == false){
                $("#MyFriendsFIst").attr("class", "col-sm-12");
                $("#FrendsUsers").hide();
                falg = true;
            }
    });
});