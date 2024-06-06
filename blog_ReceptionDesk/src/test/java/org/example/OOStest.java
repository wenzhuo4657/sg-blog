package org.example;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.example.domain.enity.SgArticle;
import org.example.mapper.SgArticleMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@SpringBootTest
public class OOStest {



    @Value("${myoss.accessKey}")
    public String accessKey;
    @Value("${myoss.secretKey}")
    public String secretKey;
    @Value("${myoss.bucket}")
    public String bucket;

    @Test
    public void test() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "33hzmmqPOwSsWXDjOVPI_8Vxd8CIEYxIuX-4d-Xm";
        String secretKey = "1yX5LrMf15Y93IpVgB6gVF8tn2DUg1Qq2g7VO_zc";
        String bucket = "wz--blog";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "/home/qiniu/test.png";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        File file = new File("D:\\桌面\\Snipaste_2024-02-16_12-49-58.png");


        try {
            InputStream s = new FileInputStream("D:\\桌面\\Snipaste_2024-02-16_12-49-58.png");
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(s, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                ex.printStackTrace();
                if (ex.response != null) {
                    System.err.println(ex.response);
                    try {
                        String body = ex.response.toString();
                        System.err.println(body);
                    } catch (Exception ignored) {
                    }
                }
            }
        } catch (Exception ex) {
            //ignore
        }


    }
    @Autowired
    private SgArticleMapper mapper;
    @Test
    public  void te(){
        List<SgArticle> list = mapper.selectList(null);

    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
