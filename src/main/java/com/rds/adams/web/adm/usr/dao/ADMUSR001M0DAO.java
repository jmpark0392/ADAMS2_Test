package com.rds.adams.web.adm.usr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.rds.adams.web.adm.usr.dto.ADMUSR001M0P0DTO;
import com.rds.adams.web.adm.usr.dto.ADMUSR001M0P1DTO;
import com.rds.adams.web.adm.usr.dto.ADMUSR001M0R0DTO;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 10.
 * @author : PARK JUNMIN
 * E-MAIL  : jm.park@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-10 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Mapper
public interface ADMUSR001M0DAO {

	/**
	 * 사용자 목록 조회를 처리한다
	 * @param vo ADMUSR001M0P0DTO
	 * @return List<ADMUSR001M0R0DTO>
	 * @exception Exception
	 */
	public List<ADMUSR001M0R0DTO> selectCompList(ADMUSR001M0P0DTO inVo);

	public int updateCompList(ADMUSR001M0P1DTO inVo);
	
	public int updateServiceCode(ADMUSR001M0P1DTO inVo);


}