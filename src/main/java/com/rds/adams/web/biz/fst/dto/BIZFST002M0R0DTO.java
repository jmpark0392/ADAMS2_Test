package com.rds.adams.web.biz.fst.dto;

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
@Schema(description = "재무제표PL 조회 DTO")
@ToString
@Getter
@Setter
public class BIZFST002M0R0DTO {
	
	@Schema(description = "행번호")
	private int rowNumber;
	
	@Schema(description = "기준년월")
	private String stdYymm;
	
	@Schema(description = "계정과목코드")
	private String atitCd;

	@Schema(description = "상위계정과목코드")
	private String hgrkAtitCd;
	
	@Schema(description = "계정과목명")
	private String atitNm;
	
	@Schema(description = "재무제표구분코드")
	private String fsDvCd;
	
	@Schema(description = "기초금액")
	private BigDecimal basAmt;
	
	@Schema(description = "차변금액")
	private BigDecimal drAmt;
	
	@Schema(description = "대변금액")
	private BigDecimal crAmt;
	
	@Schema(description = "기말금액")
	private BigDecimal entmAmt;
	
	@Schema(description = "최초등록사원번호")
	private String frstRegEmpNo;

	@Schema(description = "최초등록일시")
	private String frstRegDtm;
	
	@Schema(description = "메뉴레벨코드")
	private String levelCd;
}

