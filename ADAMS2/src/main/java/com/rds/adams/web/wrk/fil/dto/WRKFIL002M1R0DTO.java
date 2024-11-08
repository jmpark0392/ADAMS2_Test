package com.rds.adams.web.wrk.fil.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 7. 9.
 * @author : BAE BYUNGSUN
 * E-MAIL  : bs.bae@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-07-09 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "컬럼관리 조회 결과 DTO")
@ToString
@Getter
@Setter
public class WRKFIL002M1R0DTO {
	
	@Schema(description = "칼럼명")
	private String itmNm;
	
	@Schema(description = "칼럼ID")
	private String itmId;
	
	@Schema(description = "데이터타입ID")
	private String dataTpCd;
	
	@Schema(description = "PK여부")
	private String pkYn;
	
	@Schema(description = "유효값검증여부")
	private String vlvlVrfYn;
	
	@Schema(description = "유효값코드")
	private String vlvlCd;
	
	@Schema(description = "정렬순서")
	private int ordSeq;
	
	@Schema(description = "항목설명")
	private String itmDsc;
	
	@Schema(description = "적재기준여부")
	private String selBasYn;

	@Schema(description = "검증ID")
	private String vrfId;
	
	@Schema(description = "일련번호")
	private int seqNo;
	
	@Schema(description = "검증코드")
	private String vrfTpCd;
	
	@Schema(description = "검증1레벨코드")
	private String vrf1LvlCd;
	
	@Schema(description = "검증2레벨코드")
	private String vrf2LvlCd;
	
	@Schema(description = "최종변경사원번호")
	private String fnlUpdEmpNo;

	@Schema(description = "최종변경일시")
	private String fnlUpdDtm;

	@Schema(description = "최초등록사원번호")
	private String frstRegEmpNo;

	@Schema(description = "최초등록일시")
	private String frstRegDtm;

}