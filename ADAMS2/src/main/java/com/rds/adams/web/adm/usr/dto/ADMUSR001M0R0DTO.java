package com.rds.adams.web.adm.usr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 10.
 * @author : PARK JUNMIN
 * E-MAIL  : jm.park@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-10 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "고객사 정보 조회 결과 DTO")
@ToString
@Getter
@Setter
public class ADMUSR001M0R0DTO {

	   @Schema(description = "고객사번호")
	    private String csNo;

		@Schema(description = "회사명")
	    private String compNm;
		
		@Schema(description = "회사구분코드")
	    private String compNoDvCd;
		
		@Schema(description = "회사번호")
	    private String CompNo;
		
		@Schema(description = "국가명영문")
	    private String countryNmEng;
		
		@Schema(description = "국가명한글")
	    private String countryNmKor;
		
	    @Schema(description = "회사홈페이지")
	    private String compHmpgUrl;
	    
	    @Schema(description = "서비스사용코드")
	    private String statDvCd;
		
		@Schema(description = "기본주소")
	    private String addrs;
		
		@Schema(description = "상세주소")
	    private String dtlsAddrs;
		
		@Schema(description = "우편번호")
	    private String postNo;
		
		@Schema(description = "대표자명")
	    private String reperNm;
		
		@Schema(description = "대표전화번호")
	    private String repPhNo;
		
		@Schema(description = "담당자명")
	    private String ptbName;
		
		@Schema(description = "당당자전화번호")
	    private String ptbPhNo;
		
		@Schema(description = "당당자이메일")
	    private String ptbEmail;
		
		@Schema(description = "사용종료일시")
	    private String useEndDtm;
		
		@Schema(description = "사용시작일시")
	    private String useStrDtm;
		
		@Schema(description = "최초요청사용자")
	    private String frstRegEmpNo;
		
		@Schema(description = "최초요청일시")
	    private String FrstRegDtm;
		
		@Schema(description = "국가코드")
	    private String countryCd;
		
		@Schema(description = "최종업데이트사용자")
	    private String fnlUpdEmpNo;
		
		@Schema(description = "최종업데이트일시")
	    private String fnlUpdDtm;
		
}