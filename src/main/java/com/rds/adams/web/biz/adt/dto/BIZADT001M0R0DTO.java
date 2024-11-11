package com.rds.adams.web.biz.adt.dto;

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
@Schema(description = "계정대사 조회 DTO")
@ToString
@Getter
@Setter
public class BIZADT001M0R0DTO {
	
	@Schema(description = "기준년월")
	private String stdYymm;
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "일련번호")
	private String seqNo;
	
	@Schema(description = "계정과목코드")
	private String atitCd;
	
	@Schema(description = "계정과목명")
	private String atitNm;
	
	@Schema(description = "보험손익명")
	private String insuPlItmNm;
	
	@Schema(description = "보험손익항목코드")
	private String insuPlItmCd;
	
	@Schema(description = "계정금액")
	private BigDecimal acntAmt;
	
	@Schema(description = "보험손익항목금액")
	private BigDecimal insuPlItmAmt;
	
	@Schema(description = "차이금액")
	private BigDecimal diffAmt;
	
	@Schema(description = "최초등록사원번호")
	private String frstRegEmpNo;
	
	@Schema(description = "최초등록일시")
	private String frstRegDtm;
	
}

