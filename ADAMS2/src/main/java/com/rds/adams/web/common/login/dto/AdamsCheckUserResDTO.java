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
@Schema(description = "마이페이지 비밀번호 변경 전 사용자 인증 결과 DTO")
@Getter
@Setter
public class AdamsCheckUserResDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;

    @Schema(description = "사용자 인증 - 비밀번호 비교") /*1: 사용자 인증, 0: 사용자 인증 안됨*/
    private int checkUsrPwd;

}
