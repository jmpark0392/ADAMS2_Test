package com.rds.adams.web.wrk.bat.dto;

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
@Schema(description = "배치결과 조회 조건 DTO")
@ToString
@Getter
@Setter
public class WRKBAT002M0P0DTO {
	
	private String csNo;
	private String searchTxt;
	private String[] exePrdList;
}

