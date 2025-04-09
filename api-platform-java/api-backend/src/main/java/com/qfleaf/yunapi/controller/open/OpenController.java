package com.qfleaf.yunapi.controller.open;

import com.qfleaf.yunapi.controller.open.service.AvatarRandomService;
import com.qfleaf.yunapi.controller.open.service.IpLookupService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("openapi")
public class OpenController {
    private final IpLookupService ipLookupService;
    private final AvatarRandomService avatarRandomService;

    public OpenController(IpLookupService ipLookupService, AvatarRandomService avatarRandomService) {
        this.ipLookupService = ipLookupService;
        this.avatarRandomService = avatarRandomService;
    }

    @GetMapping("random-avatar")
    public ResponseEntity<byte[]> generateAvatar(@RequestParam("seed") String seed) throws IOException, NoSuchAlgorithmException {
        byte[] imageBytes = avatarRandomService.generateAvatar(seed);
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");
        return ResponseEntity.ok().headers(headers).body(imageBytes);
    }

    @GetMapping("/lookup-ip")
    public ResponseEntity<String> lookup(@RequestParam("ip") String ip) {
        String info = ipLookupService.searchIp(ip);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return ResponseEntity.ok().headers(headers).body(info);
    }
}
