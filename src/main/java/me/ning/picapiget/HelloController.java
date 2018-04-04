package me.ning.picapiget;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("home")
@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
