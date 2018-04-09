package me.ning.picapiget;

import me.ning.picapiget.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("home")
@Controller
public class HelloController {

    @Autowired
    private ImageService imageService;
    @RequestMapping("**")
    public String index(Model model){
        model.addAttribute("images",imageService.allImages());
        return "index";
    }
}
