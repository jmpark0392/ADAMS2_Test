package com.rds.adams.web.common.login.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 23.
 * @author : SON HYOMIN
 * E-MAIL  : hm.son@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-23 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "파일업로드 총 횟수 조회 조회 DTO")
@Getter
@Setter
public class AdamsUploadCntTotalDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;

    @Schema(description = "파일업로드 총 횟수 조회")
    private int uploadCntTotal;

}
