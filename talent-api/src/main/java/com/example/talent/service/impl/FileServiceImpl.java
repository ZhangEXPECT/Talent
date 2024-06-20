package com.example.talent.service.impl;


import com.example.talent.service.FileService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * @author: 甘舟
 * @create: 2023/4/27 13:18
 * @name: FileServiceImpl
 * @version: 1.0
 */
@Service
@Transactional
@Slf4j
public class FileServiceImpl implements FileService {

    /**
     * 此方法将multipartFile转为file
     *
     * @param file
     * @return java.io.File
     * @author v_fuxshen
     * @date 2022-03-24 15:10:03
     **/
    public File multipartFileToFile(MultipartFile file)  {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {

            try {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return toFile;
    }

    /**
     * 获取流文件
     * @param ins
     * @param file
     */

    public void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除本地临时文件
     * @param file
     */
    public void deleteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }

    public String uploadFile(File file, String objectKey,String urlPrefix) {
        // 配置密钥和存储桶信息
        String secretId = "AKIDXv4FEzVYYLvHRNCpGXcWFsKagZHm9NPF"; // 密钥ID，需要填写您自己的SecretId
        String secretKey = "iBPWXfZFhvwjIdkeLoLejaDcnjTzHLA7"; // 密钥Key，需要填写您自己的SecretKey
        String bucketName = "ggjzhou-1316503969";
        Region region = new Region("ap-nanjing"); // 存储桶所在区域

        // 创建 COS 客户端对象。
        BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(secretId, secretKey, "");
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(region);
        COSClient cosClient = new COSClient(sessionCredentials, clientConfig);

        //文件名称 为了保证文件名称唯一，此处使用uuid来设置文件名称
        String filename = UUID.randomUUID().toString().replaceAll("-","") +"_"+objectKey;
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        //此处目录规则为  当前日期/文件名称
        String key = "/logistics"+urlPrefix+"/"+filename;

        // 构造上传请求。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        System.out.println(putObjectRequest);

        Date expiration = new Date(new Date().getTime() + 3*24*60*60*1000);

        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 可以设置任意一个未来的时间，推荐是设置 10 分钟到 3 天的过期时间
        req.setExpiration(expiration);
        URL url = cosClient.generatePresignedUrl(req);
        //拼接文件地址
        StringBuffer stringBuffer = new StringBuffer()
                .append(url.getProtocol())
                .append("://")
                .append(url.getHost())
                .append(url.getPath());

        try {
            // 执行上传操作并打印日志。
            cosClient.putObject(putObjectRequest);
            System.out.println("上传完成！");
            return stringBuffer.toString();
        } catch (CosServiceException e) {
            System.err.println(e.getErrorMessage());
            return "上传失败";
        } finally {
            // 关闭客户端连接。
            cosClient.shutdown();
        }
    }
}
