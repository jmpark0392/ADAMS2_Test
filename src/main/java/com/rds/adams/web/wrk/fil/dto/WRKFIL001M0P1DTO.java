package com.rds.adams.web.wrk.fil.dto;

import com.rds.rsf.core.dto.RsfDatasetDTO;

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
@Schema(description = "파일관리 DTO")
@ToString
@Getter
@Setter
public class WRKFIL001M0P1DTO extends RsfDatasetDTO {
	
	@Schema(description = "순번")
	private int seqNo;
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "파일명")
	private String fileNm;
	
	@Schema(description = "데이터베이스ID")
	private String dbId;
	
	@Schema(description = "테이블ID")
	private String tblId;
	
	@Schema(description = "파일설명")
	private String fileDsc;
	
	@Schema(description = "파일삭제여부")
	private String fileDelYn;
	
	@Schema(description = "화면조회여부")
	private String uiSelYn;
	
	@Schema(description = "검증사용여부")
	private String useYn;
	
	@Schema(description = "최종변경사원번호")
	private String vrfUseYn;
	
	@Schema(description = "최종변경사원번호")
	private String fnlUpdEmpNo;

	@Schema(description = "최종변경일시")
	private String fnlUpdDtm;

	@Schema(description = "최초등록사원번호")
	private String frstRegEmpNo;

	@Schema(description = "최초등록일시")
	private String frstRegDtm;

}