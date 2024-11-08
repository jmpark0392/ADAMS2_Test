package com.rds.adams.web.opn.srv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.opn.srv.dto.OPNSRV001M0P0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV001M0P1DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV001M0R0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV001M0R1DTO;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 16.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-16 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Mapper
public interface OPNSRV001M0DAO {

	/**
	 * 서비스 목록 조회를 처리한다
	 * @param vo OPNSRV001M0P0DTO
	 * @return List<OPNSRV001M0R0DTO>
	 * @exception Exception
	 */
	public List<OPNSRV001M0R0DTO> selectSrvcList(OPNSRV001M0P0DTO inVo);

	/**
	 * 서비스 이력 목록 조회를 처리한다
	 * @param vo OPNSRV001M0P1DTO
	 * @return List<OPNSRV001M0R1DTO>
	 * @exception Exception
	 */
	public List<OPNSRV001M0R1DTO> selectSrvcHistList(OPNSRV001M0P1DTO inVo);

	/**
	 * 서비스 이력 시작일자 적합성를 조회한다
	 * @param vo OPNSRV001M0R0DTO
	 * @return String
	 * @exception Exception
	 */
	public String selectChkDate(OPNSRV001M0R0DTO inVo);

	/**
	 * 서비스 이력 시작일자 기준 count 조회를 처리한다
	 * @param vo OPNSRV001M0R0DTO
	 * @return String
	 * @exception Exception
	 */
	public String selectChkHist(OPNSRV001M0R0DTO inVo);

	/**
	 * 서비스 정보를 저장한다
	 * @param vo OPNSRV001M0R0DTO
	 * @exception Exception
	 */
	public void updateSrvc(OPNSRV001M0R0DTO inVo);

	/**
	 * 서비스 이력 정보를 저장한다
	 * @param vo OPNSRV001M0R0DTO
	 * @exception Exception
	 */
	public void updateSrvcHistDay(OPNSRV001M0R0DTO inVo);

	/**
	 * 서비스 이력 정보를 저장한다
	 * @param vo OPNSRV001M0R0DTO
	 * @exception Exception
	 */
	public void updateSrvcHistBefor(OPNSRV001M0R0DTO inVo);

	/**
	 * 서비스 이력 정보를 저장한다
	 * @param vo OPNSRV001M0R0DTO
	 * @exception Exception
	 */
	public void insertSrvcHist(OPNSRV001M0R0DTO inVo);

	/**
	 * 서비스 정보를 저장한다
	 * @param vo OPNSRV001M0R0DTO
	 * @exception Exception
	 */
	public void insertSrvc(OPNSRV001M0R0DTO inVo);

}