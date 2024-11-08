package com.rds.adams.web.opn.usr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 9. 26.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-09-26 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "고객 정보 조회 결과 DTO")
@ToString
@Getter
@Setter
public class OPNUSR002M0R0DTO {


    @Schema(description = "사용자ID")
    private String usrId;

    @Schema(description = "시퀀스")
    private int seqNo;
    
    @Schema(description = "신규고객사번호")
    private String newCsNo;
    
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

    @Schema(description = "관리자ID")
    private String adminId;

    @Schema(description = "관리자비밀번호")
    private String adminPassword;

    @Schema(description = "회사홈페이지URL")
    private String compHmpgUrl;

    @Schema(description = "우편번호")
    private String postNo;

    @Schema(description = "주소")
    private String addrs;

    @Schema(description = "상세주소")
    private String dtlsAddrs;

    @Schema(description = "상태구분코드")
    private String statDvCd;

    @Schema(description = "old상태구분코드")
    private String oldStatDvCd;

    @Schema(description = "사용시작일시")
    private String useStrDtm;

    @Schema(description = "사용종료일시")
    private String useEndDtm;

    @Schema(description = "국가코드")
    private String countryCd;

    @Schema(description = "국가명_한글")
    private String countryNmKor;

    @Schema(description = "국가명_영문")
    private String countryNmEng;

    @Schema(description = "최종수정사원번호")
    private String fnlUpdEmpNo;

    @Schema(description = "최종수정일시")
    private String fnlUpdDtm;

    @Schema(description = "최초등록사원번호")
    private String frstRegEmpNo;

    @Schema(description = "최초등록일시")
    private String frstRegDtm;

}