package com.rds.adams.web.util.file;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	// 파일 업로드 폼
	@RequestMapping("/fileUploadForm")
	public String fileUploadFormView() {
		return "upload/fileUploadForm";
	}
	
	// 파일업로드
	@RequestMapping("/fileUpload")
	public String fileUploadView( MultipartFile[] uploadFile ) throws IOException {
		
		// 파일 저장 경로 설정 : 실제 서비스되는 위치(프로젝트 외부에 저장)
		String uploadPath = "/Library/springWorkspace/upload/";
		// 마지막에 / 있어야함
		
		
		return "upload/fileUploadResult";
	}
	
	
}
