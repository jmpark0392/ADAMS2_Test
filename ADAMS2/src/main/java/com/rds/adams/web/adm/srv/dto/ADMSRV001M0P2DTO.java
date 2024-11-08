package com.rds.adams.web.adm.srv.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 19. 9.
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

public class ADMSRV001M0P2DTO {
    private String optCd;         // 옵션코드 (Option Code)
    private String optDtlsCd;     // 옵션상세코드 (Option Details Code)
    private String optDtlsNm;     // 옵션상세명 (Option Details Name)
    private String optDtlsDesc;   // 옵션상세설명 (Option Details Description)    
}
