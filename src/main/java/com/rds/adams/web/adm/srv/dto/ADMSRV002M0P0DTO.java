package com.rds.adams.web.adm.srv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 30. 12.
 * @author : Sardor Madaminov
 * E-MAIL  : sardor@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-30-09 : 최초 등록
 * 2024-30-09 : 코드 수정
 * ----------------------------------------------------------
 * </PRE>
 */
@ToString
@Getter
@Setter

public class ADMSRV002M0P0DTO {

    @Schema(description = "년월")
	private String yearMonth;

    @Schema(description = "고객사번호")
    private String csNo;
}
