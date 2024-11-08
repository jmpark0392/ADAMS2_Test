package com.rds.adams.web.biz.jnl.dto;

import java.math.BigDecimal;

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
@Schema(description = "분개내역 DTO")
@ToString
@Getter
@Setter
public class BIZJNL003M0P1DTO {
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "행번호")
	private int rowNumber;
	
	@Schema(description = "기준년월")
	private String stdYymm;
	
	@Schema(description = "분개번호")
	private String jrnlNo;
	
	@Schema(description = "계정과목코드")
	private String atitCd;
	
	@Schema(description = "과목명")
	private String atitNm;
	
	@Schema(description = "차대구분")
	private String drCrDvCd;
	
	@Schema(description = "분개금액")
	private BigDecimal jrnlAmt;
	
	
}

