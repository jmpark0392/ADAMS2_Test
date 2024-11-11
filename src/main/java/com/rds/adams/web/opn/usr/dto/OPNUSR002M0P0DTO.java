package com.rds.adams.web.opn.usr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 9. 26.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-09-26 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "고객 정보 조회 조건 DTO")
@ToString
@Getter
@Setter
public class OPNUSR002M0P0DTO {


    @Schema(description = "회사번호구분코드")
    private String compNoDvCd;

    @Schema(description = "회사번호명")
    private String compNoNm;

    @Schema(description = "국가명")
    private String countryNm;

    @Schema(description = "담당자명")
    private String ptbNm;

}