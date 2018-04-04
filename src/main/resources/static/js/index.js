function random(){
   var url = "http://localhost:8080/image/url";
    $.ajax({

        url:url,
        data:{},
        dataType:"text",
        type:"POST",
        success:function(data){
            $("#img_show").css("background-image","url("+data+")").css("background-size","800px 450px");
        },
        error:function(){
            alert("获取失败，请重试");
        }
        })

}
