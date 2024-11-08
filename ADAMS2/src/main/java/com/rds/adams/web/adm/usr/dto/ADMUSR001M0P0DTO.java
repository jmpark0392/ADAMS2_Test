package com.rds.adams.web.adm.usr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 10.
 * @author : PARK JUNMIN
 * E-MAIL  : jm.park@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-10 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "고객사 정보 조회 조건 DTO")
@ToString
@Getter
@Setter
public class ADMUSR001M0P0DTO {

	
    @Schema(description = "고객사번호")
    private String csNo;


}