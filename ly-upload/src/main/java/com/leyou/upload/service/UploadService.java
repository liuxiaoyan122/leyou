package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadService {
    @Autowired
    private FastFileStorageClient storageClient;

   public String uploadImage(MultipartFile file) {
       String url=null;
      //获取浏览器传过来图片
       String originalFilename=file.getOriginalFilename();
       //获取图片的后缀
       String ext=StringUtils.substringAfter(originalFilename,".");
       //上传
       try {
           StorePath storePath=storageClient.uploadFile(file.getInputStream(),file.getSize(),ext,null);
           storePath.getFullPath();
           url="http://image.leyou.com/"+storePath.getFullPath();
       } catch (IOException e) {
           e.printStackTrace();
       }
       return url;
   }

}
