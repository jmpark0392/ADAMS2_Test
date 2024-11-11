package com.rds.adams.web.util.file.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 6. 17.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-06-17 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@ToString
@Getter
@Setter
public class UuidFileInfoDTO {

	private String csNo;
	private String seq;
	private String uuid;
	private String uplPath;
	private String fileNm;
	private String ext;
	private Long fileSize;
	private String regUsrid;
	private String regDt;
	
}
