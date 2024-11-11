package com.rds.adams.web.adm.srv.dto;

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



public class ADMSRV001M0P0DTO {
    private String csNo;            // 고객사번호
    private String srvcCd;          // 서비스코드
    private String optCd;           // 옵션코드
    private String optDtlsCd;       // 옵션상세코드
    private String optDtlsNm;       // 옵션상세명
    private String fnlUpdEmpNo;     // 최종수정자사번
    private String fnlUpdDtm;       // 최종수정일시
    private String fnlRegEmpNo;     // 최초등록자사번
    private String fnlRegDtm;       // 최초등록일시
}
