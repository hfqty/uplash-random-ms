package me.ning.picapiget;

import me.ning.picapiget.image.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {


    @RequestMapping("home")
    public String index(Model model){
        return "index";
    }
}
