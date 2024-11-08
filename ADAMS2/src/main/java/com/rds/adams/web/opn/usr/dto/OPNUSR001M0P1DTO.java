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
@Schema(description = "메뉴 추가 DTO")
@ToString
@Getter
@Setter
public class OPNUSR001M0P1DTO {

    @Schema(description = "메뉴ID")
    private String menuId;

    @Schema(description = "메뉴명_한글")
    private String menuNmKor;

    @Schema(description = "메뉴명_영어")
    private String menuNmEng;

    @Schema(description = "상위메뉴ID")
    private String upprMenuId;

    @Schema(description = "메뉴정렬순서")
    private Integer menuSrtOrd;

    @Schema(description = "메뉴설명")
    private String menuDesc;

    @Schema(description = "프로그램이름")
    private String pgmNm;

    @Schema(description = "프로그램경로이름")
    private String pgmPathNm;

    @Schema(description = "서비스코드")
    private String srvcCd;

    @Schema(description = "관리자권한여부")
    private String adminAuthYn;

    @Schema(description = "시스템운영자권한여부")
    private String sysopAuthYn;

    @Schema(description = "사용여부")
    private String useYn;

    @Schema(description = "삭제여부")
    private String delYn;

    @Schema(description = "사용자ID")
    private String usrId;
}