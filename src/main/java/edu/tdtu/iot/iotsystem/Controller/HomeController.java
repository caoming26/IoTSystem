package edu.tdtu.iot.iotsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // Trả về tên file HTML (không cần phần đuôi .html)
    }
}
