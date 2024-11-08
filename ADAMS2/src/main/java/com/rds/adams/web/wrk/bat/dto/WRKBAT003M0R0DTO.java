package com.rds.adams.web.wrk.bat.dto;

import java.math.BigDecimal;

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
@Schema(description = "배치실행 조회 결과 DTO")
@ToString
@Getter
@Setter
public class WRKBAT003M0R0DTO {
	
	@Schema(description = "행번호")
	private int rowNumber;
	
	@Schema(description = "기준년월")
	private String stdYymm;
	
	@Schema(description = "배치실행ID")
	private String batExeId;
	
	@Schema(description = "배치프로그램ID")
	private String batProgId;
	
	@Schema(description = "배치프로그램명")
	private String batProgNm;
	
	@Schema(description = "배치실행결과코드")
	private String batExeRstCd;
	
	@Schema(description = "배치실행에러코드")
	private String batExeErrCd;
	
	@Schema(description = "배치시작일시")
	private String batStrDtm;
	
	@Schema(description = "배치종료일시")
	private String batEndDtm;
	
	@Schema(description = "배치상태코드")
	private String batLoadStatCd;
	
	@Schema(description = "주담당자")
	private String mainEmpNo;
	
	@Schema(description = "등록사용자ID")
	private String frstRegEmpNo;
	
	@Schema(description = "등록일")
	private String frstRegDtm;
	
	@Schema(description = "JOB실행시간")
	private BigDecimal jobTime;
}
