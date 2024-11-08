package com.rds.adams.web.common.login.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 9. 11.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-09-11 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "고객 정보 DTO")
@Getter
@Setter
public class AdamsCsNoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;

    @Schema(description = "고객사번호")
    private String csNo;

    @Schema(description = "회사명")
    private String compNm;
    
    @Schema(description = "회사번호구분코드")
    private String compNoDvCd;
	
	
}
