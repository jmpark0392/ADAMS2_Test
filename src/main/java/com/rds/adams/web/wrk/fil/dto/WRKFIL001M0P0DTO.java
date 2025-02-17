package com.rds.adams.web.wrk.fil.dto;

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
@Schema(description = "파일관리 조회조건 DTO")
@ToString
@Getter
@Setter
public class WRKFIL001M0P0DTO {
	
	@Schema(description = "검색텍스트")
	private String searchTxt;
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "사용자 이름")
	private String usrNm;
}

