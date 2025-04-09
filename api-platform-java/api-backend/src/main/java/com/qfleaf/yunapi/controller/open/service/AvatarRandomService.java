package com.qfleaf.yunapi.controller.open.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AvatarRandomService {
    private static final int SCALE = 40; // 每个方块大小
    private static final int SIZE = 5; // 5x5 网格

    public byte[] generateAvatar(String seed) throws NoSuchAlgorithmException, IOException {
        // 计算 MD5 哈希
        byte[] hash = MessageDigest.getInstance("MD5").digest(seed.getBytes());

        // 颜色（前 3 个字节转换为 RGB）
        Color color = new Color(hash[0] & 0xFF, hash[1] & 0xFF, hash[2] & 0xFF);

        // 5x5 的正方形网格，只绘制左半部分，右半部分镜像对称
        boolean[][] grid = new boolean[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE / 2 + 1; x++) {
                grid[x][y] = (hash[y] >> x & 1) == 1; // 使用哈希值决定方块填充
                grid[SIZE - 1 - x][y] = grid[x][y]; // 右侧对称
            }
        }

        // 生成图片
        BufferedImage image = new BufferedImage(SIZE * SCALE, SIZE * SCALE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE); // 背景色
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.setColor(color); // 设定前景色

        // 绘制方块
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (grid[x][y]) {
                    g.fillRect(x * SCALE, y * SCALE, SCALE, SCALE);
                }
            }
        }
        g.dispose();

        // 转换为 PNG 数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        return baos.toByteArray();
    }
}
