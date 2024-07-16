package com.example.DiamondShopSystem.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.DiamondShopSystem.repository.StaffRepository;
import com.example.DiamondShopSystem.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/api/admin/upload")
public class UploadController {

    @Autowired
    private AmazonS3 s3client;

    @Autowired
    private JWTUtils jwtUtils;

    @Value("${linode.bucket-name}")
    private String bucketName;
    @Autowired
    private StaffRepository staffRepository;

    @PostMapping
    public URL handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {


            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);  // Setting the file to be publicly accessible

            s3client.putObject(putObjectRequest);

            // Get public URL
            URL url = s3client.getUrl(bucketName, fileName);
            return url;
    }
}
