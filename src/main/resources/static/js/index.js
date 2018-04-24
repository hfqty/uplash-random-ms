var previewUrl = "";

$(function(){
    random_image();

    $("#auto_random").click(function () {
        if($("#auto_random").text()=="是"){
            $("#auto_random").text("否");
        }else{
            $("#auto_random").text("是");
        }
    });
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
    var imageUrl = nowUrl();
    $("#msg").show();
    if(imageUrl) {
        $("#msg").text("正在下载，请稍等");
    }else{
        $("#msg").text("图片正在加载");
        setTimeout(function () {
            $("#msg").hide();
        },1000);
    }
    $.ajax({
        url: "image/save",
        data: {url: nowUrl()},
        dataType: "json",
        type: "POST",
        success: function (data) {
            $("#msg").text(data.msg);
            setTimeout(function () {
                $("#msg").hide();
            },1000);
            all_images(1,9);

            //自动换图
            var auto = getConfig();
            if(auto != null && auto != '' && auto == "是"){
                random_image();
            }
        },
        error: function () {
            $("#msg").text("保存出错");
            setTimeout(function () {
                $("#msg").hide();
            },1000);
        }

    });
}
function showMsg(msg){

}
function back_pre_img(){
    $("#image_show").attr("src",$("#image_url").text());
    $("#back_pre_btn").hide();
}

function getConfig() {
    return $("#auto_random").text();
}