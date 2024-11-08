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
public class MSTSYS001M0R0DTO {
	
	private String empNo;
    private String empNm;
    private String roleCd;
    private String email;
    private String pwd;
    private String useYn;
    private String regId;
    private String regDt;
    private String updId;
    private String updDt;

}