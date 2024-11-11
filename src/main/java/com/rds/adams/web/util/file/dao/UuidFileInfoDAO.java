package com.rds.adams.web.util.file.dao;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.util.file.dto.UuidFileInfoDTO;

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
@Mapper
public interface UuidFileInfoDAO {

	public void insertUuidFileInf(UuidFileInfoDTO inVo);
}
