package org.example.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.example.domain.AppHttpCodeEnum;
import org.example.domain.ResponseResult;
import org.example.exception.SystemException;
import org.example.service.UploadService;
import org.example.utils.PathUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public ResponseResult uploadimg(MultipartFile img) {
//        判断文件类型是否合格
        String ortname=img.getOriginalFilename();

        long size=img.getSize();
        if (size>2*1024*1024){
            throw  new SystemException(AppHttpCodeEnum.FileSize_NO);
        }
        if (!ortname.endsWith(".png")){
            throw  new SystemException(AppHttpCodeEnum.FileEnd_No);
        }


//        上传七牛云并返回外链
        String filepath= PathUtils.generateFilePath(ortname);
        String url=upload(img,filepath);


        return ResponseResult.okResult(url);
    }


    public String upload(MultipartFile img, String path) {
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
        String key = path;



        try {
            InputStream s = img.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(s, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return "s8xk9ul85.hn-bkt.clouddn.com/"+key;
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

        return  "上传失败";



    }
}
