package com.rds.adams.web.adm.usr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.adm.usr.dto.ADMUSR002M0P0DTO;
import com.rds.adams.web.adm.usr.dto.ADMUSR002M0R0DTO;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 07.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-07 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Mapper
public interface ADMUSR002M0DAO {

	/**
	 * 사용자 목록 조회를 처리한다
	 * @param vo ADMUSR002M0P0DTO
	 * @return List<ADMUSR002M0R0DTO>
	 * @exception Exception
	 */
	public List<ADMUSR002M0R0DTO> selectUsrList(ADMUSR002M0P0DTO inVo);

	/**
	 * 사용자 정보를 저장한다
	 * @param vo ADMUSR002M0R0DTO
	 * @exception Exception
	 */
	public void updateUsr(ADMUSR002M0R0DTO inVo);

	/**
	 * 사용자 관리자 정보를 저장한다
	 * @param vo ADMUSR002M0R0DTO
	 * @exception Exception
	 */
	public void updateUsrAdmin(ADMUSR002M0R0DTO inVo);

	/**
	 * 사용자 명수 체크 조회를 처리한다
	 * @param vo ADMUSR002M0R0DTO
	 * @return String
	 * @exception Exception
	 */
	public String selectUsrCntChk(ADMUSR002M0R0DTO inVo);

	/**
	 * 사용자 관리자 명수 체크 조회를 처리한다
	 * @param vo ADMUSR002M0R0DTO
	 * @return String
	 * @exception Exception
	 */
	public String selectUsrAdminCntChk(ADMUSR002M0R0DTO inVo);

	/**
	 * 사용자 중복 여부 조회를 처리한다
	 * @param vo ADMUSR002M0R0DTO
	 * @return int
	 * @exception Exception
	 */
	public int selectUsrDupChk(ADMUSR002M0R0DTO inVo);

}