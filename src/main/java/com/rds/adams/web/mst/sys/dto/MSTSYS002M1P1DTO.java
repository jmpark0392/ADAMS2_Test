package com.rds.adams.web.mst.sys.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 1. 9.
 * @author : JEONG HYEONSEUNG
 * E-MAIL  : hs.jeong@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-01-09 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@ToString
@Getter
@Setter
public class MSTSYS002M1P1DTO {
	private String cdId;
	private String vlvlCd;
	private String vlvlNm;
	private String vlvlDesc;
	private String etc1Desc;
	private String etc2Desc;
	private String etc3Desc;
	private String useYn;
	private String regId;
	private String regDt;
}