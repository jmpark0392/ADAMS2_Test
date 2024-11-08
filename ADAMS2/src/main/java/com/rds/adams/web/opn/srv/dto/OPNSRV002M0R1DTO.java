package com.rds.adams.web.opn.srv.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 21.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-21 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "옵션 이력 정보 조회 결과 DTO")
@ToString
@Getter
@Setter
public class OPNSRV002M0R1DTO {


    @Schema(description = "사용자ID")
    private String usrIdHist;

    @Schema(description = "시퀀스")
    private int seqNoHist;
    
    @Schema(description = "옵션코드")
    private String optCdHist;
    
    @Schema(description = "옵션명")
    private String optNmHist;

    @Schema(description = "옵션단가")
    private BigDecimal optUprcHist;

    @Schema(description = "옵션시작일시")
    private String optStrDtmHist;

    @Schema(description = "옵션종료일시")
    private String optEndDtmHist;

    @Schema(description = "최종수정사원번호")
    private String fnlUpdEmpNoHist;

    @Schema(description = "최종수정일시")
    private String fnlUpdDtmHist;

    @Schema(description = "최초등록사원번호")
    private String frstRegEmpNoHist;

    @Schema(description = "최초등록일시")
    private String frstRegDtmHist;

}