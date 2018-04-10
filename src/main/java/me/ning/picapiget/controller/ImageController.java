package me.ning.picapiget.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.ning.picapiget.bean.Image;
import me.ning.picapiget.dto.OutputDTO;
import me.ning.picapiget.service.ImageService;
import me.ning.picapiget.util.HttpUtils.RequestUtil;
import me.ning.picapiget.util.ImageUtil;
import me.ning.picapiget.util.UrlUtils.GetRequestUrl;
import me.ning.picapiget.util.file.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RequestMapping("image")
@RestController
public class ImageController {

    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @RequestMapping("/url")
    public String url() {
        return GetRequestUrl.RedirectUrl();
    }

    @RequestMapping("/all")
    public PageInfo<Image> all(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize
    ) {
        //分页处理，显示第一页的10条数据
        PageHelper.startPage(1, 10);
        List<Image> images = imageService.allImages();
        PageInfo<Image> pageInfo = new PageInfo<>(images);
        return pageInfo;
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

    @RequestMapping("/download")
    public void download(HttpServletResponse response, HttpServletRequest request,String url){
        HttpURLConnection connection =RequestUtil.connection(url);
        String imageId = ImageUtil.getImageId(url);
        logger.info("开始下载："+imageId);
        String imageName = imageId+".jpg";
        String fullPath = "D:\\devf\\file server\\images\\"+imageName;
        logger.info("完整路径："+fullPath);
        File file = null;
        try {
             file = FileUtil.checkExist(fullPath);
            if(file == null ){
                ImageUtil.saveToServer(connection, fullPath);
                file = new File(fullPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + imageName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }else{

        }
        logger.info("文件路径："+file.getAbsoluteFile());
        boolean deleteResult = file.delete();
        if(deleteResult)
            logger.info("删除文件：删除成功!");
        else
            logger.info("删除文件：删除失败!");
        }






}
