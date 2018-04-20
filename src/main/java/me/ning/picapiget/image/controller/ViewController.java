package me.ning.picapiget.image.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("image_view")
    public String image_view(){
        return "image/image_view";
    }

    @RequestMapping("image_table")
    public String image_list(){
        return "image/image_table";
    }



}
