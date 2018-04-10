var baseUrl = "http://localhost:8080/image";
$(function () {
})
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
        location.href = "http://localhost:8080/image/download?url=" + imgurl;
    }
}

/*
*   保存图片
 */

function save() {
    $.ajax({
        url: "http://localhost:8080/image/save",
        data: {url: nowUrl()},
        dataType: "json",
        type: "POST",
        success: function (data) {
            alert(data.msg);
        },
        error: function () {
            alert("保存失败，请重试");
        }

    });
}
