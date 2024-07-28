package com.pzand.clinesdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtils {
    public static String genSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String context = body + "." + secretKey;
        // 5393554e94bf0eb6436f240a4fd71282
        return md5.digestHex(context);
    }
}
