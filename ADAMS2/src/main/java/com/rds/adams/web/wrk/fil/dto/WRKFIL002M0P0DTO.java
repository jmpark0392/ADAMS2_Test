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
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "컬럼관리 파일명 조회 DTO")
@ToString
@Getter
@Setter
public class WRKFIL002M0P0DTO {
	
	@Schema(description = "더미스트링")
	private String dummyStr;
	
	@Schema(description = "고객사번호")
	private String csNo;
}