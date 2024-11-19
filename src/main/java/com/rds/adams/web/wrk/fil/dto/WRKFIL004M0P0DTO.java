package com.rds.adams.web.wrk.fil.dto;

import com.rds.rsf.core.dto.RsfPagingDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 7. 16.
 * @author : BAE BYUNGSUN
 * E-MAIL  : bs.bae@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-07-16 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "적재이력 조회 DTO")
@ToString
@Getter
@Setter
public class WRKFIL004M0P0DTO extends RsfPagingDTO {
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "업로드파일번호")
	private int uploadFile;
	
	@Schema(description = "적재시작일시")
	private String startLoadDate;
	
	@Schema(description = "적재종료일시")
	private String endLoadDate;
	
	@Schema(description = "시작기준년월")
	private String startStdYymm;
	
	@Schema(description = "종료기준년월")
	private String endStdYymm;

}