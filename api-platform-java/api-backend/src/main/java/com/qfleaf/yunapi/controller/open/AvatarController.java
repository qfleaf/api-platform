package com.qfleaf.yunapi.controller.open;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avatar")
public class AvatarController {
    @GetMapping("random")
    public String random() {
        // todo 实现真实功能
        return "random";
    }
}
