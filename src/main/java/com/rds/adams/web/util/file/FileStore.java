package com.rds.adams.web.util.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rds.adams.web.util.file.dao.UuidFileInfoDAO;
import com.rds.adams.web.util.file.dto.UploadFile;
import com.rds.adams.web.util.file.dto.UuidFileInfoDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileStore {

    @Value("${file.upload.path}")  // @Value는 Spring 꺼를 사용해야한다 , @Value를 통해 appilcation.* 에 지정한 값을 가져올 수 있다.
    private String fileDir;        // 가져온 값은 여기에 저장됨.

    public String getFullPath(String filename) { //파일이름을 받아서 FullPath를 만들어주는 메소드
        return fileDir + filename;
    }
    
    @Autowired
    UuidFileInfoDAO uuidFileInfoDAO;
/*
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException { //여러개의 이미지를 한번에 저장하기 위한 메소드
        List<UploadFile> storeFileResult = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }

        return storeFileResult;
    }
*/

    public UploadFile storeFile(MultipartFile multipartFile, String usrId, String csNo) throws IOException { // 파일을 서버에 저장하는 메소드
        // MultipartFile로 받은후 서버에 저장하고, UploadFile 객체를 만들어 반환
        if (multipartFile.isEmpty()) {
            return null;
        }
        
        UuidFileInfoDTO uuidFileInfoDTO = new UuidFileInfoDTO();
        
        String originalFilename = multipartFile.getOriginalFilename(); //파일의 이름가져온다 (확장자가 포함되어있다.) ex) dog.png

        String storeFileName = createStoreFileName(originalFilename);  //서버에 저장될 파일명 만들기

        multipartFile.transferTo(new File(getFullPath(storeFileName))); // 파일을 서버에 저장한다 , getFullPath메소드를 이용해서 PullPath를 만들어줌
        //File은 객체를 생성할때 Path를 파라미터로 받는다.
        

        uuidFileInfoDTO.setCsNo(csNo);
        uuidFileInfoDTO.setUuid(storeFileName);
        uuidFileInfoDTO.setFileNm(originalFilename);
        uuidFileInfoDTO.setUplPath(fileDir);
        uuidFileInfoDTO.setExt(extractExt(originalFilename));
        uuidFileInfoDTO.setRegUsrid(usrId);
        uuidFileInfoDTO.setFileSize(multipartFile.getSize());
        
        log.debug(" ===> uuidFileInfoDTO : " + uuidFileInfoDTO.toString() );
        try {
        	uuidFileInfoDAO.insertUuidFileInf(uuidFileInfoDTO);
        } catch (Exception e) {
        	log.debug(" ===> e.getMessage() : " + e.getMessage() );
        }
        
        return new UploadFile(originalFilename, storeFileName, fileDir);
    }

    private static String createStoreFileName(String originalFilename) {//서버에 저장될 파일명 만드는 메소드

        //서버에 저장될 파일명은 고유해야하므로 UUID를 붙여서 만들어준다.
        String uuid = UUID.randomUUID().toString().replace("-", "");

        //확장자는 붙여줘야 구분하기 편하니까 originalFilename에서 확장자를 가져온다.
        String ext = extractExt(originalFilename);

        // uuid + "." + 확장자
        return uuid + "." + ext; // 서버에 저장될 파일이름 생성
    }

    private static String extractExt(String originalFilename) { //파일이름에서 확장자 부분만 반환하는 메소드

        int index = originalFilename.lastIndexOf("."); // 파일이름중 마지막 .의 인덱스를 가져온다.

        return originalFilename.substring(index + 1); // . 위치 뒷부분을 subString으로 가져온다.
    }


}
