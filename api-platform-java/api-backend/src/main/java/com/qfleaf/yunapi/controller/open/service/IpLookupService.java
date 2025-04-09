package com.qfleaf.yunapi.controller.open.service;

import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class IpLookupService {
    private static Searcher searcher;

    public IpLookupService() throws IOException {
        // 读取资源文件
        ClassPathResource resource = new ClassPathResource("ip2region.xdb");
        InputStream inputStream = resource.getInputStream();
        
        // 将数据库文件复制到临时目录
        Path tempFile = Files.createTempFile("ip2region", ".xdb");
        Files.copy(inputStream, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        inputStream.close();

        // 加载 IP2Region 查询器
        byte[] vIndex = Searcher.loadVectorIndexFromFile(tempFile.toString());
        searcher = Searcher.newWithVectorIndex(tempFile.toString(), vIndex);
    }

    /**
     * 根据 IP 查询地址信息
     */
    public String searchIp(String ip) {
        try {
            return searcher.search(ip);
        } catch (Exception e) {
            return "查询失败：" + e.getMessage();
        }
    }
}