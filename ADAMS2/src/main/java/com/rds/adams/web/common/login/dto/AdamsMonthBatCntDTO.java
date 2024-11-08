package com.rds.adams.web.common.login.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 23.
 * @author : SON HYOMIN
 * E-MAIL  : hm.son@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-23 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "(전날 기준)30일간 일별 배치 실행 횟수 조회 정보 DTO")
@Getter
@Setter
public class AdamsMonthBatCntDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;

	@Schema(description = "날짜")
    private String batStrDtm;
	
	@Schema(description = "일별 배치 실행 횟수")
    private int batCnt;


}
