package com.rds.adams.web.wrk.fil.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 7. 9.
 * @author : BAE BYUNGSUN
 * E-MAIL  : bs.bae@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-07-09 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "파일업로드 파일명 조회 결과 DTO")
@ToString
@Getter
@Setter
public class WRKFIL003M0R1DTO {
	
	@Schema(description = "고객사번호")
	private String csNo;

	@Schema(description = "순번")
	private long seqNo;

	@Schema(description = "파일명")
	private String fileNm;

	@Schema(description = "데이터베이스ID")
	private String dbId;

	@Schema(description = "테이블ID")
	private String tblId;
	
	@Schema(description = "select옵션명")	
	private String opName;

}