package com.summersec.shiroctf.Tools;

import java.io.*;
import java.util.Base64;

/**
 * @ClassName: Tools
 * @Description: TODO
 * @Author: Summer
 * @Date: 2020/12/23 14:26
 * @Version: v1.0.0
 * @Description:
 **/
public class Tools {
    public Tools() {
    }

    public static byte[] base64Decode(String base64) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(base64);
    }

    public static String base64Encode(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    public static byte[] serialize(final Object obj) throws Exception {
        ByteArrayOutputStream btout = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(btout);
        objOut.writeObject(obj);
        return btout.toByteArray();
    }

    public static Object deserialize(final byte[] serialized) throws Exception {
        ByteArrayInputStream btin = new ByteArrayInputStream(serialized);
        ObjectInputStream objIn = new ObjectInputStream(btin);
        return objIn.readObject();
    }

    public static String exeCmd(String commandStr) {
        BufferedReader br = null;
        String OS = System.getProperty("os.name").toLowerCase();


        try {
            Process p = null;
            if (OS.startsWith("win")){
                p = Runtime.getRuntime().exec(new String[]{"cmd", "/c", commandStr});
            }else {
                p = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", commandStr});
            }

            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();

            while((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

            return sb.toString();
        } catch (Exception var5) {
            var5.printStackTrace();
            return "error";
        }
    }
}
