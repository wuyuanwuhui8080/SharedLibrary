$(function () {
    var blog = true;
    $(".BtnGive").click(function () {
        var obj = $(this);
        // 获取当前博客id
        var id = obj.prop("id");
        // 获取当前点赞数
        var getcount = obj.attr("giveCount");
        // 当前点赞的id
        var giveId;
        if (app.isNull(obj.attr("giveId"))) {
            giveId = "0";
        } else {
            giveId = obj.attr("giveId");
        }

        // 获取当前登录的id
        var userId = $("#userId").val();

        var numCout;
        /*  if(blog == true){
              $.ajax({
                  type : "post",
                  url : app.path()+"/shareBlogsGive/getGiveNum",
                  data : {blogsId:id,getcount:getcount,giveId:giveId,giveUserId:userId},
                  dateType : "json",
                  success : function (date) {
                      if(date.status == 200 && date.obj == 0){
                          numCout = getcount + 1;
                          obj.html('<i class="fa fa-thumbs-up" style="color: red;"></i> <span>'+numCout+'</span>赞');
                      }else if(date.status == 200 && date.obj == 1){
                          numCout = getcount - 1;
                          obj.html('<i class="fa fa-thumbs-up"></i> <span>'+numCout+'</span>赞');
                      }else{
                          swal({
                              title : date.msg,
                              type : "error"
                          });
                      }
                  },
                  error:function () {
                      swal({
                          title : "网络错误!",
                          type : "error"
                      });
                  },
                  complete: function () {
                      blog = false;
                  }
              });
          }else if(blog == false){
              $.ajax({
                  type : "post",
                  url : app.path()+"/shareBlogsGive/getGiveNum",
                  data : {blogsId:id,getcount:getcount,giveId:giveId,giveUserId:userId},
                  dateType : "json",
                  success : function (date) {
                      if(date.status == 200 && date.obj == 0){
                          numCout = getcount + 1;
                          obj.html('<i class="fa fa-thumbs-up" style="color: red;"></i> <span>'+numCout+'</span>赞');
                      }else if(date.status == 200 && date.obj == 1){
                          numCout = getcount - 1;
                          obj.html('<i class="fa fa-thumbs-up"></i> <span>'+numCout+'</span>赞');
                      }else{
                          swal({
                              title : date.msg,
                              type : "error"
                          });
                      }
                  },
                  error:function () {
                      swal({
                          title : "网络错误!",
                          type : "error"
                      });
                  },
                  complete: function () {
                      blog = true;
                  }
              });
          }*/
    });

});