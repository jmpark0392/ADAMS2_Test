package com.rds.adams.web.biz.adt.dto;

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
@Schema(description = "계정대사 조회 조건 DTO")
@ToString
@Getter
@Setter
public class BIZADT001M0P0DTO {
	
	@Schema(description = "검색텍스트")
	private String searchTxt;
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "기준년월")
	private String stdYymm;
}

