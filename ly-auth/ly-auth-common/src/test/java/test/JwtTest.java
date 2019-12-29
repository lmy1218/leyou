/*
 * Copyright (c) 2019 lmy All Rights Reserved.
 * ProjectName:  leyou
 * FileName: JwtTest.java
 * Author: lmy
 * Date: 19-12-24 上午10:51
 * Version: 1.0
 * LastModified: 19-12-24 上午10:47
 */

package test;

import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JwtUtils;
import com.leyou.common.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

    private static final String pubKeyPath = "C:\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "C:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "ly@Login(Auth}*^31)&yun6%f3q2");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        //token = eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU2MzYwMjI2NX0.f8JAZu8OTJGotPJuNuLSOufH6acwMlBQahkbGKnuz7evR5f6AYvT8O2JJPCCrrXf4wUhA5PVUKFhbywZrEhw3ihnNGphAB34P1LPuqUs9fm5sgSfOfu1hT5RVqTr1yR2b835Xgj8FeEIq6npEJzuwkDe4p_nc3v09o7pT3F2ZTQ

        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU3NzE1NTcwNH0.S9wxDd871Y_YHLxw4_fIGYSvqvpeFC0VDKGhc8whEyV7Jrv3FOGPqb1K2fVPC964C-mgBsOTjyMmPpjGtuN-z9x0HRrmVcb1xzt-H_wzxF1OWEcqTSdVK65SNDeHxBpk2Bx8APLPnZZvHtPPsXYN6uQ9OWDi3babfuNU0rIv-6E";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
