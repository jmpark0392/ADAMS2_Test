package com.rds.adams.web.common.login.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 9. 25.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-09-11 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "신규 고객 정보 DTO")
@Getter
@Setter
public class AdamsNewCsDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;

    @Schema(description = "고객사번호")
    private String csNo;

    @Schema(description = "회사번호구분코드")
    private String compNoDvCd;

    @Schema(description = "회사번호")
    private String compNo;

    @Schema(description = "회사명")
    private String compNm;

    @Schema(description = "대표전화번호")
    private String repPhNo;

    @Schema(description = "대표자명")
    private String reperNm;

    @Schema(description = "담당자명")
    private String ptbNm;

    @Schema(description = "담당자전화번호")
    private String ptbPhNo;

    @Schema(description = "담당자이메일")
    private String ptbEmail;

    @Schema(description = "담당자부서명")
    private String ptbDeptNm;

    @Schema(description = "사용자비밀번호")
    private String usrPassword;

    @Schema(description = "회사홈페이지URL")
    private String compHmpgUrl;

    @Schema(description = "우편번호")
    private String postNo;

    @Schema(description = "주소")
    private String addrs;

    @Schema(description = "상세주소")
    private String dtlsAddrs;

    @Schema(description = "국가명_영문")
    private String countryNmEng;
	
	
}
