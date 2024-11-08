package com.rds.adams.web.biz.ins.dto;

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
@Schema(description = "보험손익BEL DTO")
@ToString
@Getter
@Setter
public class BIZINS001M0P1DTO {
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "행번호")
	private int rowNumber;
	
	@Schema(description = "기준년월")
	private String stdYymm;
	
	@Schema(description = "보험계약구분코드")
	private String inscDvCd;
	
	@Schema(description = "평가방법구분코드")
	private String valuMetdCd;
	
	@Schema(description = "포트폴리오")
	private String ptfCd;
	
	@Schema(description = "기초금액")
	private BigDecimal basAmt;
	
	@Schema(description = "OCI금액")
	private BigDecimal ociAmt;
	
	@Schema(description = "신계약금액")
	private BigDecimal newContAmt;
	
	@Schema(description = "이자효과금액")
	private BigDecimal intEfftAmt;
	
	@Schema(description = "예상보험료")
	private BigDecimal estmPrm;
	
	@Schema(description = "예상수수료")
	private BigDecimal estmFee;
	
	@Schema(description = "예상신계약체결금액")
	private BigDecimal estmNewContCclsAmt;
	
	@Schema(description = "예상수수료환입금액")
	private BigDecimal estmFeeRfndAmt;
	
	@Schema(description = "예상계약유지금액")
	private BigDecimal estmContMtnAmt;
	
	@Schema(description = "예상손해조사금액")
	private BigDecimal estmIvsgExpAmt;
	
	@Schema(description = "계리적가정변경금액")
	private BigDecimal atsAssmUpdAmt;
	
	@Schema(description = "환율효과금액")
	private BigDecimal exrtEfftAmt;
	
	@Schema(description = "기말금액")
	private BigDecimal entmAmt;
}

