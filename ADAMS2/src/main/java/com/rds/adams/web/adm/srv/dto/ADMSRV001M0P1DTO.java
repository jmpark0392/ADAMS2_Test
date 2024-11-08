package com.rds.adams.web.adm.srv.dto;

import java.util.Date;

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

public class ADMSRV001M0P1DTO {		
    private String csNo;            // 고객사번호 (Customer Number)
    private String seqNo;           // 일련번호 (Sequence Number) - optional if auto-generated
    private String srvcCd;          // 서비스코드 (Service Code) - for service history
    private String optCd;           // 옵션코드 (Option Code)
    private String optDtlsCd;       // 옵션상세코드 (Option Details Code)
    private Date optStrDtm;         // 옵션시작일시 (Option Start DateTime)
    private Date optEndDtm;         // 옵션종료일시 (Option End DateTime)
    private String fnlUpdEmpNo;     // 최종변경사원번호 (Final Update Employee Number)
    private String frstRegEmpNo;    // 최초등록사원번호 (First Register Employee Number)
}