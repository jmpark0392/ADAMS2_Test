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
@Schema(description = "옵션 상세 이력 정보 조회 결과 DTO")
@ToString
@Getter
@Setter
public class OPNSRV002M0R3DTO {


    @Schema(description = "사용자ID")
    private String usrId;

    @Schema(description = "시퀀스")
    private int seqNoDtlsHist;
    
    @Schema(description = "옵션코드")
    private String optCdDtlsHist;
    
    @Schema(description = "옵션상세코드")
    private String optDtlsCdHist;
    
    @Schema(description = "옵션상세명")
    private String optDtlsNmHist;

    @Schema(description = "옵션상세값")
    private BigDecimal optDtlsVluHist;

    @Schema(description = "옵션상세할인율")
    private BigDecimal optDtlsDcRtHist;
    
    @Schema(description = "사용여부")
    private String useYnHist;
    
    @Schema(description = "삭제여부")
    private String delYnHist;

    @Schema(description = "옵션상세시작일시")
    private String optDtlsStrDtmHist;

    @Schema(description = "옵션상세종료일시")
    private String optDtlsEndDtmHist;

    @Schema(description = "최종수정사원번호")
    private String fnlUpdEmpNoDtlsHist;

    @Schema(description = "최종수정일시")
    private String fnlUpdDtmDtlsHist;

    @Schema(description = "최초등록사원번호")
    private String frstRegEmpNoDtlsHist;

    @Schema(description = "최초등록일시")
    private String frstRegDtmDtlsHist;

}