package me.ning.picapiget.controller;

import me.ning.picapiget.util.UrlUtils.GetRequestUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("image")
@RestController
public class ImageController {


    @RequestMapping("/url")
    public String url(){
        return GetRequestUrl.RedirectUrl();
    }

}
