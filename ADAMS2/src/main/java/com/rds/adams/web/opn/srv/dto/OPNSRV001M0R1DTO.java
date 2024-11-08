package com.rds.adams.web.opn.srv.dto;

import java.math.BigDecimal;

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
@Schema(description = "서비스 이력 정보 조회 결과 DTO")
@ToString
@Getter
@Setter
public class OPNSRV001M0R1DTO {


    @Schema(description = "사용자ID")
    private String usrIdHist;

    @Schema(description = "시퀀스")
    private int seqNoHist;
    
    @Schema(description = "서비스코드")
    private String srvcCdHist;
    
    @Schema(description = "서비스명")
    private String srvcNmHist;

    @Schema(description = "서비스단가")
    private BigDecimal srvcUprcHist;

    @Schema(description = "기본사용자수")
    private String basicsUsrCntHist;

    @Schema(description = "서비스시작일시")
    private String srvcStrDtmHist;

    @Schema(description = "서비스종료일시")
    private String srvcEndDtmHist;

    @Schema(description = "서비스설명")
    private String srvcDescHist;

    @Schema(description = "최종수정사원번호")
    private String fnlUpdEmpNoHist;

    @Schema(description = "최종수정일시")
    private String fnlUpdDtmHist;

    @Schema(description = "최초등록사원번호")
    private String frstRegEmpNoHist;

    @Schema(description = "최초등록일시")
    private String frstRegDtmHist;

}