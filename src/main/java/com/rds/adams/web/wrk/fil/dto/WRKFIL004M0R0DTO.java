package com.rds.adams.web.wrk.fil.dto;

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
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "적재이력 조회 결과 DTO")
@ToString
@Getter
@Setter
public class WRKFIL004M0R0DTO {
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "순번")
	private int seqNo;
	
	@Schema(description = "파일명")
	private String fileNm;
	
	@Schema(description = "데이터베이스ID")
	private String dbId;
	
	@Schema(description = "테이블ID")
	private String tblId;
	
	@Schema(description = "적재건수")
	private int loadCnt;
	
	@Schema(description = "기준년월")
	private String stdYymm;
	
	@Schema(description = "적재시작일시")
	private String loadStrDtm;
	
	@Schema(description = "적재종료일시")
	private String loadEndDtm;
	
	@Schema(description = "시작기준년월")
	private String startStdYymm;
	
	@Schema(description = "종료기준년월")
	private String endStdYymm;
	
	@Schema(description = "적재성공여부")
	private String loadSuccYn;

	@Schema(description = "최초등록사원번호")
	private String frstRegEmpNo;

	@Schema(description = "파일사이즈")
	private BigDecimal fileSize;

}
