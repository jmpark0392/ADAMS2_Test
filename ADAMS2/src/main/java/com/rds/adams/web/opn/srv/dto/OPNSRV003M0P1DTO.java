package com.rds.adams.web.opn.srv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 16.
 * @author : Sardor Madaminov
 * E-MAIL  : sardor@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-16 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

 @Schema(description = "option management parameter DTO")
 @ToString
 @Getter
 @Setter
 
public class OPNSRV003M0P1DTO {

    @Schema(description = "년월일")
    private String ymd;
    
    @Schema(description = "customer_number")
    private String csNo;

    @Schema(description = "company_name")
    private String compNm;    
    
}
