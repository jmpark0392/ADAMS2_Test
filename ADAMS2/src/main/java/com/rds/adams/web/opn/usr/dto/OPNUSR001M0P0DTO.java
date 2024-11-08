package com.rds.adams.web.opn.usr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 11.
 * @author : BAE BYUNGSUN
 * E-MAIL  : bs.bae@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-11 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "메뉴 조회 조건 DTO")
@ToString
@Getter
@Setter
public class OPNUSR001M0P0DTO {

    @Schema(description = "메뉴명 한글/영어")
    private String menuNm;
    
    @Schema(description = "메뉴 설명")
    private String menuDesc;
    
    @Schema(description = "프로그램명")
    private String pgmNm;

}
