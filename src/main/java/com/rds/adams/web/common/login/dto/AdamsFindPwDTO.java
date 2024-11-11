package com.rds.adams.web.common.login.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 9. 24.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-09-05 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "비밀번호 찾기 DTO")
@Getter
@Setter
public class AdamsFindPwDTO implements Serializable{
	
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

	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @Schema(description = "사용자ID")
    private String usrId;

    @Schema(description = "사용자비밀번호")
    private String usrPassword;

    @Schema(description = "사용자신규비밀번호")
    private String usrNewPassword;

    @Schema(description = "사용자전화번호")
    private String usrPhNo;

    @Schema(description = "비밀번호초기화여부")
    private String passwordInitYn;

    @Schema(description = "사번")
    private String empNo;
    
	
}
