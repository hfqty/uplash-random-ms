$(function(){
   random_image();
})

function random_image(){
    var url = "http://localhost:8080/image/url";
    $.ajax({
        url: url,
        data: {},
        dataType: "text",
        type: "POST",
        success: function (data) {
            $("#image_url").text(data);
            $("#image_show").attr("src",data);
        },
        error: function () {
            alert("获取失败，请重试");
        }
    });
}

