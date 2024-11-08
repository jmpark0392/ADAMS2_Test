package com.rds.adams.web.wrk.bat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 1. 9.
 * @author : JEONG HYEONSEUNG
 * E-MAIL  : hs.jeong@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-01-09 : 최초 등록
 * 2024-06-11 : 코드 수정
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "배치관리 조회 결과 DTO")
@ToString
@Getter
@Setter
public class WRKBAT001M0R0DTO {
	
	@Schema(description = "행번호")
	private int rowNumber;
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "배치프로그램ID")
	private String batProgId;
	
	@Schema(description = "배치프로그램명")
	private String batProgNm;
	
	@Schema(description = "상위프로그램ID")
	private String upProgId;
	
	@Schema(description = "최하위프로그램여부")
	private String lwstProgYn;
    
	@Schema(description = "실행주기")
	private String exePrd;
	
	@Schema(description = "주담당자")
	private String mainEmpNo;
	
	@Schema(description = "부담당자")
	private String subEmpNo;
	
	@Schema(description = "주담당자ID")
	private String mainEmpId;
	
	@Schema(description = "부담당자ID")
	private String subEmpId;
	
	@Schema(description = "비고")
	private String etcDesc;
	
	@Schema(description = "사용여부")
	private String useYn;
	
	@Schema(description = "등록사용자ID")
	private String frstRegEmpNo;
	
	@Schema(description = "등록일")
	private String frstRegDtm;

}