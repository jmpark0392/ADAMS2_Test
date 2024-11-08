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
@Schema(description = "옵션 상세 정보 조회 결과 DTO")
@ToString
@Getter
@Setter
public class OPNSRV002M0R2DTO {


    @Schema(description = "사용자ID")
    private String usrId;

    @Schema(description = "시퀀스")
    private int seqNoDtls;
    
    @Schema(description = "옵션코드")
    private String optCdDtls;
    
    @Schema(description = "옵션상세코드")
    private String optDtlsCd;
    
    @Schema(description = "옵션상세명")
    private String optDtlsNm;

    @Schema(description = "옵션상세값")
    private BigDecimal optDtlsVlu;

    @Schema(description = "옵션상세할인율")
    private BigDecimal optDtlsDcRt;
    
    @Schema(description = "사용여부")
    private String useYn;
    
    @Schema(description = "삭제여부")
    private String delYn;

    @Schema(description = "옵션상세시작일시")
    private String optDtlsStrDtm;

    @Schema(description = "옵션상세종료일시")
    private String optDtlsEndDtm;

    // this should be regarded as not important to use in the ui side
    @Schema(description = "최종수정사원번호")
    private String fnlUpdEmpNoDtls;

    @Schema(description = "최종수정일시")
    private String fnlUpdDtmDtls;

    @Schema(description = "최초등록사원번호")
    private String frstRegEmpNoDtls;

    @Schema(description = "최초등록일시")
    private String frstRegDtmDtls;

}