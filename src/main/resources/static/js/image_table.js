$(function(){
all_images(1,9);


})

function all_images(num,size){

    $.ajax({
        url:'image/all',
        data:{pageNum:num,pageSize:size},
        method:'post',
        dataType:'json',
        success:function(data){
            $("#pageNum").text(data.pageNum);
            $("#pages").text(data.pages);
            $("#pageSize").text(data.pageSize);
            $("#total").text(data.total);
            $("#images_ul").empty();
            $.each(data.row,function(i,img){
               var imgli = $("<li ></li>");
               var imgimg = $("<img onclick='lgimg(this)'/>");
               imgimg.attr("src", getUrl(img.url,400,300));
               imgli.append(imgimg);
               $("#images_ul").append(imgli);
           })
        }
    })
}

function pre_page(){
    var pageSize = 9;
    var pageNum = $("#pageNum").text();
    pageNum = pageNum>1?pageNum-1:pageNum;
    all_images(pageNum,pageSize);
}

function next_page(){
    var pageSize = 9;
    var pages = parseInt($("#pages").text());
    var pageNum = parseInt($("#pageNum").text());
    pageNum = pageNum<pages?pageNum+1:pageNum;
    all_images(pageNum,pageSize);
}

function getUrl(url,w,h){
    var a=  url.substring(0,115);
    var b= url.substring(119,122);
    var c = url.substring(126);
    return a+w+b+h+c;
}
function getUrl2(url,w,h){
    var a=  url.substring(0,115);
    var b= url.substring(119,122);
    var c = url.substring(126);
    return a+w+b+h+c;
}
function lgimg(img){

}