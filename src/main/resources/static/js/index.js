$(function () {
    random();


})

function back() {
    var data = $("#image_url").text();
    $("#title_bar h1").text(data.substring(28, 60));
    $("#image_panel").attr("src", data);
    $("#back_btn").hide();
}

function look() {
    alert("看吧");
}

function show(td) {
    var data = td.innerText;
    $("#title_bar h1").text(data.substring(28, 60));
    $("#image_panel").attr("src", data);
    $("#back_btn").show();
}

function random() {
    var url = "http://localhost:8080/image/url";
    $.ajax({
        url: url,
        data: {},
        dataType: "text",
        type: "POST",
        success: function (data) {
            $("#image_url").text(data);
            $("#title_bar h1").text(data.substring(28, 60));
            $("#image_panel").attr("src", data);
        },
        error: function () {
            alert("获取失败，请重试");
        }
    });
}

function url() {
    var url = $("#image_url").text();
    return url;
}

function had() {

    $.ajax({
        url: "http://localhost:8080/image/had",
        data: {url: url()},
        dataType: "json",
        type: "POST",
        success: function (data) {
            alert(data.msg);
            if (data.code == "0") {
                return true;
            } else {
                return false;
            }
        },
        error: function () {
            alert("查询失败，请重试");
        }

    });
}

function save() {
    $.ajax({
        url: "http://localhost:8080/image/save",
        data: {url: url()},
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

function download() {
    var imgurl = url();
    if (imgurl == "" || imgurl == null || imgurl == undefined) {
        alert("正在加载，请稍等");

    } else {
        location.href = "http://localhost:8080/image/download?url=" + imgurl;
    }

}
