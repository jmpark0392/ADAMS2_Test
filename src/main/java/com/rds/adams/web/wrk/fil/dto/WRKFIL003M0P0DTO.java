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
@Schema(description = "파일업로드 조회 DTO")
@ToString
@Getter
@Setter
public class WRKFIL003M0P0DTO {
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "업로드파일번호")
	private int uploadFile;
	
	@Schema(description = "기준년월")
    private String stdYymm;

}