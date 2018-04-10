$(function () {
    random();
    all();

})

function back() {
    var data = $("#image_url").text();
  changeImage(data);
    $("#back_btn").hide();
}

function look() {
    alert("看吧");
}

function changeImage(data) {
    $("#image_name").text(data);
    $("#image_panel").attr("src", data);
}

function show(td) {
    var data = td.innerText;
    changeImage(data);
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
          changeImage(data);
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

function all(){
    $.ajax({
        url: "http://localhost:8080/image/all",
        data: {},
        dataType: "json",
        type: "POST",
        success: function (data) {

           if(data){
               $.each(data.list,function (i,img) {
                   var tbody = $("#all_images");
                   var imagetr = $("<tr></tr>");
                   var idtd = $("<td></td>");
                   var urltd = $("<td onclick='show(this)'></td>");
                   var cttd = $("<td></td>");
                   var preview = $("<td></td>");
                   var tdimg = $("<img>");
                   tdimg.attr("src",img.url).css({"background-size":"10%"});
                   preview.append(img);
                   imagetr/*.append(preview)*/
                      /* .append(idtd.text(img.id))*/
                       .append(urltd.text(img.url))
                       .append(cttd.text(img.create_time));
                   tbody.append(imagetr);
               })
           }
        },
        error: function () {
            alert("保存失败，请重试");
        }

    });
}
