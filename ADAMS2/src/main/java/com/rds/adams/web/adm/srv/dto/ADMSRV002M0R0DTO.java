package com.rds.adams.web.adm.srv.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 30. 12.
 * @author : Sardor Madaminov
 * E-MAIL  : sardor@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-30-09 : 최초 등록
 * 2024-30-09 : 코드 수정
 * ----------------------------------------------------------
 * </PRE>
 */
@ToString
@Getter
@Setter

public class ADMSRV002M0R0DTO {

	@Schema(description = "일자")
    private String ymd;

	@Schema(description = "베이직 서비스 값")
    private BigDecimal bSrvcVal;

	@Schema(description = "프리미엄 서비스 값")
    private BigDecimal pSrvcVal;

	@Schema(description = "서비스 코드 현재")
    private String srvcCd;

	@Schema(description = "서비스 코드 예상")
    private String srvcCdExp;

	@Schema(description = "서비스, 옵션 일별 누적 합계 비용")
    private BigDecimal srvcOptCostSum;

	@Schema(description = "서비스, 옵션 일별 누적 합계 예상 비용")
    private BigDecimal srvcOptCostExpSum;

	@Schema(description = "서비스, 옵션 현재 비용")
    private BigDecimal maxSrvcOptCostSum;

	@Schema(description = "서비스, 옵션 예상 비용")
    private BigDecimal maxSrvcOptCostExpSum;

	@Schema(description = "파일 사이즈 일별 사용량")
    private BigDecimal fileSize;

	@Schema(description = "JOB 실행 시간 일별 사용량")
    private BigDecimal jobTime;

	@Schema(description = "파일 사이즈 현재사용량")
    private BigDecimal maxFileSizeSum;

	@Schema(description = "JOB 실행 시간 현재사용량")
    private BigDecimal maxJobTimeSum;

	@Schema(description = "파일 사이즈 현재비용")
    private BigDecimal maxFileCostSum;

	@Schema(description = "JOB 실행 시간 현재비용")
    private BigDecimal maxJobCostSum;

	@Schema(description = "전체 서비스 일별 비용")
    private BigDecimal srvcCost;

	@Schema(description = "전체 서비스 일별 예상 비용")
    private BigDecimal srvcCostExp;

	@Schema(description = "전체 서비스 누적 합계")
    private BigDecimal srvcCostSum;

	@Schema(description = "전체 서비스 누적 합계 예상 비용")
    private BigDecimal srvcCostExpSum;

	@Schema(description = "전체 서비스 현재 비용")
    private BigDecimal maxSrvcCostSum;

	@Schema(description = "전체 서비스 예상 비용")
    private BigDecimal maxSrvcCostExpSum;
}
