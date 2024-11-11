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
@Schema(description = "계정과목관리 DTO")
@ToString
@Getter
@Setter
public class BIZJNL001M0P1DTO {

	@Schema(description = "고객사번호")
	private String csNo;
	
	@Schema(description = "행번호")
	private int rowNumber;
	
	@Schema(description = "계정과목코드")
	private String atitCd;
	
	@Schema(description = "계정과목한글명")
	private String atitKorNm;
	
	@Schema(description = "계정과목영문명")
	private String atitEngNm;
	
	@Schema(description = "계정레벨코드")
	private String acLvlCd;
	
	@Schema(description = "상위계정과목코드")
	private String hgrkAtitCd;
	
	@Schema(description = "기표계정여부")
	private String bkgAcYn;
	
	@Schema(description = "재무제표구분코드")
	private String fsDvCd;
	
	@Schema(description = "계정구분코드")
	private String acDvCd;
	
	@Schema(description = "계정종류코드")
	private String acKindCd;
	
	@Schema(description = "적용시작일자")
	private String appStrDt;
	
	@Schema(description = "적용종료일자")
	private String appEndDt;
	
	@Schema(description = "정렬순서")
	private int ordSeq;
	
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

