package me.ning.picapiget.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import me.ning.picapiget.bean.Image;
import me.ning.picapiget.dto.OutputDTO;
import me.ning.picapiget.service.ImageService;
import me.ning.picapiget.util.HttpUtils.RequestUtil;
import me.ning.picapiget.util.HttpUtils.ResponseUtil;
import me.ning.picapiget.util.ImageUtil;
import me.ning.picapiget.util.UrlUtils.URLUtil;
import me.ning.picapiget.util.file.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;


@RequestMapping("/image")
@RestController
public class ImageController {

    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @RequestMapping("/url")
    public String url() {
        return URLUtil.RedirectUrl();
    }

    @RequestMapping("/all")
    public me.ning.picapiget.vo.Page all(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
                                         @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize
    ) {
        //分页处理，显示第一页的10条数据
         PageHelper.startPage(pageNum, pageSize);
        Page<Image> images = (Page<Image>) imageService.allImages();
        me.ning.picapiget.vo.Page page = new me.ning.picapiget.vo.Page(images.getResult());
        page.setTotal((int) images.getTotal());
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setPages(me.ning.picapiget.vo.Page.pages(pageSize,page.getTotal()));
        return page;
    }

    @RequestMapping("/save")
    public OutputDTO add(String url) {
        OutputDTO outputDTO = new OutputDTO();
        boolean result = false;
        try {
            boolean had = imageService.hadImage(url) > 0;
            if (had) {
                outputDTO.setMsg("已存在");
            }else {
                result = imageService.addImage(new Image(url, new Date(), new Date()));
            }
        } catch (Exception e) {
            outputDTO.setMsg("已经有了");
        }
        if (result) {
            outputDTO.setMsg("保存成功");
        }
        return outputDTO;
    }

    @RequestMapping("/had")
    public OutputDTO had(String url) {
        OutputDTO outputDTO = new OutputDTO();
        boolean result = imageService.hadImage(url) > 0;
        if (result) {
            outputDTO.setMsg("已存在");

        }else{
            outputDTO.setCode("1");
            outputDTO.setMsg("不存在");
        }
        return outputDTO;
    }

    @RequestMapping("/checkAndDownload")
    public OutputDTO valiExitsFile(String url){
        OutputDTO outputDTO = new OutputDTO();
        String fullPath = ImageUtil.getImageFullPath(url);
       boolean result =  ImageUtil.checkAndDownload( RequestUtil.connection(url),fullPath);
       if(result){
           return outputDTO;
       }
       outputDTO.setMsg("文件不存在，无法下载");
       outputDTO.setCode("-1");
       return outputDTO;
    }

    @RequestMapping("/download")
    public void download(HttpServletResponse response, String url) {
        String fullPath = ImageUtil.getImageFullPath(url);
        ImageUtil.checkAndDownload( RequestUtil.connection(url),fullPath);
        String fileName = ImageUtil.getImageName(url);
        File file = new File(ImageUtil.getImageFullPath(url));
        if (file.exists()) {
            ResponseUtil.responseHeader(response,fileName);
            FileUtil.downloadFile(response, file);
        }
    }



}
