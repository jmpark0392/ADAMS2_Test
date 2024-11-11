package com.rds.adams.web.wrk.fil.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 7. 15.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-07-15 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "파일업로드 히스토리 DTO")
@ToString
@Getter
@Setter
public class WRKFIL003M0P1DTO {
	
	@Schema(description = "순번")
	private String seqNo;
	
	@Schema(description = "데이터베이스ID")
	private String dbId;
	
	@Schema(description = "테이블ID")
	private String tblId;
	
	@Schema(description = "파일명")
	private String fileNm;
	
	@Schema(description = "적재건수")
	private int    loadCnt;
	
	@Schema(description = "적재성공여부")
	private String loadSuccYn;
	
	@Schema(description = "기준년월")
	private String stdYymm;
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "사용자ID")
	private String usrId;
	
	@Schema(description = "파일명(uuid)")
	private String uuid;
}