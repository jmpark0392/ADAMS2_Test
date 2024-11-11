package com.rds.adams.web.adm.usr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 04.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-04 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "사용자 정보 조회 조건 DTO")
@ToString
@Getter
@Setter
public class ADMUSR002M0P0DTO {

	
    @Schema(description = "고객사번호")
    private String csNo;

    @Schema(description = "사용자아이디명")
    private String usrIdNm;

    @Schema(description = "상태구분코드")
    private String statDvCd;

}