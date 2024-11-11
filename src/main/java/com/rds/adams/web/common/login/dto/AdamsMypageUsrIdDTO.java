package com.rds.adams.web.common.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 22.
 * @author : SON HYOMIN
 * E-MAIL  : hm.son@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-22 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "마이페이지 사용자 아이디 반환 파라미터 DTO")
@Getter
@Setter
public class AdamsMypageUsrIdDTO {

	@Schema(description = "고객사번호")
    private String csNo;
	
	@Schema(description = "사용자ID")
    private String usrId;
}
