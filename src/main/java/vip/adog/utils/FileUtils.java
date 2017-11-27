/*
package vip.adog.utils;

import com.alibaba.fastjson.JSONException;

import java.io.InputStream;


import javax.crypto.Mac;
import javax.security.auth.message.AuthException;


*/
/**
 * 图片工具类（使用七牛云存储服务）
 * @author www.shiyanlou.com
 *
 *//*

public class FileUtils {
    private static final String ACCESS_KEY = "3NzZTuBCEHkhxNofGWhX71tYzQyFTDI4Bf7Pr9ET";//这里填上面我们讲到的，密钥管理里面的密钥
    private static final String SECRET_KEY = "IPsivjkDZ2wMMMl34Gnl_A5xcMzykK7WMJkA0i7z";
    private static final String BUCKET_NAME = "jekaysnow";//填我们在七牛云创建的 Bucket 名字

    */
/**
     * 上传图片到七牛云存储
     * @param reader
     * @param fileName
     *//*

    public static void upload(InputStream reader, String fileName) {
        String uptoken;
        try {
            Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
            PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
            uptoken = putPolicy.token(mac);
            IoApi.Put(uptoken, fileName, reader, null);
        } catch (AuthException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    */
/**
     * 删除七牛云存储上的图片
     * @param key
     *//*

    public static void delete( String key) {
        Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
        RSClient client = new RSClient(mac);
        client.delete(BUCKET_NAME, key);
    }
}*/
