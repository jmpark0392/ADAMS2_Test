package com.rds.adams.web.biz.jnl.dto;

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
@Schema(description = "분개룰관리 조회 DTO")
@ToString
@Getter
@Setter
public class BIZJNL002M0R0DTO {
	
	@Schema(description = "행번호")
	private int rowNumber;
	
	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "보험계약구분코드")
	private String inscDvCd;
	
	@Schema(description = "평가방법코드")
	private String valuMetdCd;
	
	@Schema(description = "레벨1")
	private String lvl1Cd;
	
	@Schema(description = "레벨2")
	private String lvl2Cd;
	
	@Schema(description = "레벨3")
	private String lvl3Cd;
	
	@Schema(description = "차변계정코드")
	private String drAcSubjCd;
	
	@Schema(description = "차변계정명")
	private String drAcSubjNm;
	
	@Schema(description = "대변계정코드")
	private String crAcSubjCd;
	
	@Schema(description = "대변계정명")
	private String crAcSubjNm;
	
	@Schema(description = "기타내용")
	private String etcDesc;
	
	@Schema(description = "사용여부")
	private String useYn;
	
    @Schema(description = "최종수정사원번호")
    private String fnlUpdEmpNo;

    @Schema(description = "최종수정일시")
    private String fnlUpdDtm;
	
	@Schema(description = "최초등록사원번호")
	private String frstRegEmpNo;
	
	@Schema(description = "최초등록일시")
	private String frstRegDtm;
	
}

