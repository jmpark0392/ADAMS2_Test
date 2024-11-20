package com.rds.adams.web.wrk.fil.dto;

import com.rds.rsf.core.dto.RsfPagingDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 7. 10.
 * @author : BAE BYUNGSUN
 * E-MAIL  : bs.bae@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-07-10 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@ToString
@Getter
@Setter
public class WRKFIL002M1P0DTO extends RsfPagingDTO {
	private String tblIdTxt;
	private String csNo;
}