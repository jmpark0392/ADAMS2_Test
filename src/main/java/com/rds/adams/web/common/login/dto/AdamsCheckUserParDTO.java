package com.rds.adams.web.common.login.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 28.
 * @author : SON HYOMIN
 * E-MAIL  : hm.son@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-28 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "마이페이지 비밀번호 변경 전 사용자 인증 파라미터 DTO")
@Getter
@Setter
public class AdamsCheckUserParDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;

    @Schema(description = "고객사번호")
    private String csNo;

	@Schema(description = "담당자이메일") 
	private String usrId;
	
	@Schema(description = "사용자 기존 비밀번호") 
	private String usrCurrentPassword;

}
