package com.rds.adams.web.wrk.fil.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 6. 11.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-06-11 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "파일업로드 조회 결과 DTO")
@ToString
@Getter
@Setter
public class WRKFIL003M0R0DTO {
	
	@Schema(description = "테이블ID")
	private String tblId;
	
	@Schema(description = "컬럼ID")
	private String clmnId;
	
	@Schema(description = "칼럼명")
	private String itmNm;
	
	@Schema(description = "정렬순서")
	private String ordSeq;
	
	@Schema(description = "PK여부")
	private String pkYn;
	
	@Schema(description = "데이터타입ID")
	private String dataTpCd;
	
	@Schema(description = "항목설명")
	private String itmDsc;
	
	@Schema(description = "유효값검증여부")
	private String vlvlVrfYn;
	
	@Schema(description = "유효값코드")
	private String vlvlCd;
	
	@Schema(description = "조회기준여부")
	private String selBasYn;
	
	@Schema(description = "데이터베이스ID")
	private String dbId;
	
	@Schema(description = "화면조회여부")
	private String uiSelYn;

}