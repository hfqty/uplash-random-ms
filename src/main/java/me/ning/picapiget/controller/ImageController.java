package me.ning.picapiget.controller;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import me.ning.picapiget.bean.Image;
import me.ning.picapiget.dto.OutputDTO;
import me.ning.picapiget.service.ImageService;
import me.ning.picapiget.util.UrlUtils.GetRequestUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequestMapping("image")
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping("/url")
    public String url() {
        return GetRequestUrl.RedirectUrl();
    }

    @RequestMapping("/all")
    public List<Image> all() {
        return imageService.allImages();
    }

    @RequestMapping("/save")
    public OutputDTO add(String url) {
        OutputDTO outputDTO = new OutputDTO();
        boolean result = false;
        try {
            result = imageService.addImage(new Image(url, new Date(), new Date()));
        } catch (Exception e) {
            outputDTO.setMsg("已经有了");
        }
        if (result) {
            outputDTO.setMsg("保存成功");
            return outputDTO;
        }
        return new OutputDTO("-1", "操作失败");
    }

    @RequestMapping("/had")
    public OutputDTO had(String url) {
        OutputDTO outputDTO = new OutputDTO();
        boolean result = imageService.hadImage(url) > 0;
        if (result) {
            outputDTO.setMsg("已存在");
            return outputDTO;
        }
        return new OutputDTO("-1", "可以添加");
    }

}
