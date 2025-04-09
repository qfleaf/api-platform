package com.qfleaf.web.token;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.ParseException;

@Service
public class JwtService implements InitializingBean {
    private JWSSigner signer;
    private JWSVerifier verifier;
    private JWSHeader jwsHeader;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 生成随机secret
        SecureRandom secureRandom = new SecureRandom();
        byte[] secret = new byte[32];
        secureRandom.nextBytes(secret);
        // 创建签名器
        signer = new MACSigner(secret);
        // 创建验证器
        verifier = new MACVerifier(secret);
        // 创建jwt header
        jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
    }

    /**
     * 创建jwt字符串。
     *
     * @param subject 主题
     * @return jwt:string
     */
    public String createJWT(String subject) {
        // 创建jwt claims
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .build();
        // 创建jwt
        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
        // 签名
        try {
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        // 序列化jwt -> string
        return signedJWT.serialize();
    }

    /**
     * 验证token有效性。
     *
     * @param jwt token令牌
     * @return true - 有效, false - 无效
     */
    public boolean verifyJWT(String jwt) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(jwt);
            return signedJWT.verify(verifier);
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析jwt claims中的sub。
     *
     * @param jwt token令牌
     * @return subject
     */
    public String getSubject(String jwt) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(jwt);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
