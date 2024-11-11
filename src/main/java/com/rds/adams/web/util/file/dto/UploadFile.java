package com.rds.adams.web.util.file.dto;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName;   // 업로드되는 파일이름
    private String storeFileName;    // 서버에 저장될 파일 이름 (업로드 되는 파일명이 겹칠 수 있으므로)
    private String storeFilePath;    // 서버에 저장될 파일 경로

    public UploadFile(String uploadFileName, String storeFileName, String storeFilePath) {
        this.uploadFileName = uploadFileName;
        this.storeFileName  = storeFileName;
        this.storeFilePath  = storeFilePath;
    }
}