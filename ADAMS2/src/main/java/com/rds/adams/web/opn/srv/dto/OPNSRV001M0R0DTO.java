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
@Schema(description = "서비스 정보 조회 결과 DTO")
@ToString
@Getter
@Setter
public class OPNSRV001M0R0DTO {


    @Schema(description = "사용자ID")
    private String usrId;

    @Schema(description = "시퀀스")
    private int seqNo;
    
    @Schema(description = "서비스코드")
    private String srvcCd;
    
    @Schema(description = "서비스명")
    private String srvcNm;

    @Schema(description = "서비스단가")
    private BigDecimal srvcUprc;

    @Schema(description = "기본사용자수")
    private String basicsUsrCnt;

    @Schema(description = "서비스시작일시")
    private String srvcStrDtm;

    @Schema(description = "old서비스시작일시")
    private String oldSrvcStrDtm;

    @Schema(description = "서비스설명")
    private String srvcDesc;

    @Schema(description = "최종수정사원번호")
    private String fnlUpdEmpNo;

    @Schema(description = "최종수정일시")
    private String fnlUpdDtm;

    @Schema(description = "최초등록사원번호")
    private String frstRegEmpNo;

    @Schema(description = "최초등록일시")
    private String frstRegDtm;

}