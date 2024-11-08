package com.rds.adams.web.mst.sys.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 6. 28.
 * @author : BAE BYUNGSUN
 * E-MAIL  : bs.bae@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-06-28 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@ToString
@Getter
@Setter
public class MSTSYS002M0P1DTO {
	
	private String cdId;
	private String coCd;
    private String coCdNm;
    private String coCdDesc;
    private String etc1Desc;
    private String etc2Desc;
    private String etc3Desc;
    private String useYn;

}