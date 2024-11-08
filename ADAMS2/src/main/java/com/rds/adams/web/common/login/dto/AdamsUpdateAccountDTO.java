package com.rds.adams.web.common.login.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 25.
 * @author : SON HYOMIN
 * E-MAIL  : hm.son@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-25 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "사용자 정보 수정 update 파라미터 DTO")
@Getter
@Setter
public class AdamsUpdateAccountDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;
	
	@Schema(description = "고객사번호")
    private String csNo;
	
	@Schema(description = "담당자명") 
	private String ptbNm;
	
	@Schema(description = "담당자부서명") 
	private String ptbDeptNm;
	
	@Schema(description = "담당자전화번호") 
	private String ptbPhNo;
	
	@Schema(description = "담당자이메일") 
	private String ptbEmail;
	
	@Schema(description = "사용자비밀번호") 
	private String usrPassword;

	@Schema(description = "담당자이메일") 
	private String usrId;

}
