/**
 * 
 */
package com.rds.adams.web.core.utils.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author JeongHyunseung
 *
 */
@Schema(description = "실행 공통 DTO")
@ToString
@Getter
@Setter
public class ExecuteDTO {
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "기준년월")
	private String stdYymm;
	
	@Schema(description = "등록자ID")
	private String usrId;
	
	@Schema(description = "순번")
	private int seqNo;
	
	@Schema(description = "신규배치실행ID")	
	private String newBatExeId;
	
	@Schema(description = "배치프로그램ID")		
	private String batProgId;
	
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
}