package com.rds.adams.web.adm.srv.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 12. 9.
 * @author : Sardor Madaminov
 * E-MAIL  : sardor@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-12-09 : 최초 등록
 * 2024-12-09 : 코드 수정
 * ----------------------------------------------------------
 * </PRE>
 */
@ToString
@Getter
@Setter

public class ADMSRV001M0R0DTO {
    private String csNo;            // 고객사번호 (Customer Number)
    private String srvcCd;          // 서비스코드 (Service Code)
    private String optCd;           // 옵션코드 (Option Code)
    private String optDtlsCd;       // 옵션상세코드 (Option Details Code)
    private String optDtlsNm;       // 옵션상세명 (Option Details Name) - only for selectSubscriptionList
    private String fnlUpdEmpNo;     // 최종변경사원번호 (Final Update Employee Number)
    private Date fnlUpdDtm;         // 최종변경일시 (Final Update DateTime)
    private String frstRegEmpNo;    // 최초등록사원번호 (First Register Employee Number)
    private Date frstRegDtm;        // 최초등록일시 (First Register DateTime)
}
