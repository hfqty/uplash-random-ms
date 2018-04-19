var previewUrl = "";

$(function(){
    random_image();
})


function random_image(){
    var url = "/image/url";
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
        }
    });
}
/*
* 下载图片
* */
function nowUrl(){
    return  $("#image_url").text();
}


function download() {
    var imgurl = nowUrl();
    if (imgurl == "" || imgurl == null || imgurl == undefined) {
        alert("正在加载，请稍等");
    } else {
        location.href = "image/download?url=" + imgurl;
    }
}

/*
*   保存图片
 */

function save() {

    $.ajax({
        url: "image/save",
        data: {url: nowUrl()},
        dataType: "json",
        type: "POST",
        success: function (data) {
            $("#msg").text("正在下载，请稍等");
            $("#msg").show();
            $("#msg").text(data.msg);
            setTimeout(function () {
               $("#msg").hide();
            },1000);
            all_images(1,9);
        },
        error: function () {
            $("#msg").text("保存出错");
        }

    });
}

function back_pre_img(){
    $("#image_show").attr("src",$("#image_url").text());
}