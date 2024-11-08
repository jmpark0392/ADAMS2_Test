package com.rds.adams.web.adm.srv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 22.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-22 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "서비스 가격정보 조회 결과 DTO")
@ToString
@Getter
@Setter
public class ADMSRV001M0R3DTO {

    
    @Schema(description = "서비스코드")
    private String srvcCd;
    
    @Schema(description = "서비스명")
    private String srvcNm;

    @Schema(description = "서비스단가")
    private String srvcUprc;

    @Schema(description = "기본사용자수")
    private String basicsUsrCnt;
    
}
