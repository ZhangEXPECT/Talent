package com.example.talent.controller;

import com.example.talent.enums.ResultEnum;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping("/file")
public class UploadFileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResultVO uploadFile(@RequestParam("file") MultipartFile file) {
        String objectKey = file.getOriginalFilename();
//        File localFile = new File(file.getOriginalFilename());
        File localFile = fileService.multipartFileToFile(file);
        try {
            file.transferTo(localFile);
            String url = fileService.uploadFile(localFile, objectKey,"/file");
            if(url=="上传失败"){
                return new ResultVO(ResultEnum.FAIL);
            }
            return new  ResultVO(ResultEnum.SUCCESS,url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResultVO(ResultEnum.FAIL,"上传失败");
        } finally {
            // 删除临时文件
            localFile.delete();
        }
    }
}