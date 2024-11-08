package com.rds.adams.web.opn.srv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 16.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-16 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "서비스 정보 조회 조건 DTO")
@ToString
@Getter
@Setter
public class OPNSRV001M0P0DTO {


    @Schema(description = "서비스코드명")
    private String srvcCdNm;

}