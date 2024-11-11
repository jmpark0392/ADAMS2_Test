package com.rds.adams.web.opn.srv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.opn.srv.dto.OPNSRV002M0P0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0P1DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0P2DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0R0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0R1DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0R2DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV002M0R3DTO;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 10. 21.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-10-21 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Mapper
public interface OPNSRV002M0DAO {

	/**
	 * 옵션 목록 조회를 처리한다
	 * @param vo OPNSRV002M0P0DTO
	 * @return List<OPNSRV002M0R0DTO>
	 * @exception Exception
	 */
	public List<OPNSRV002M0R0DTO> selectOptList(OPNSRV002M0P0DTO inVo);

	/**
	 * 옵션 이력 목록 조회를 처리한다
	 * @param vo OPNSRV002M0P1DTO
	 * @return List<OPNSRV002M0R1DTO>
	 * @exception Exception
	 */
	public List<OPNSRV002M0R1DTO> selectOptHistList(OPNSRV002M0P1DTO inVo);

	/**
	 * 옵션 이력 목록 조회를 처리한다
	 * @param vo OPNSRV002M0P1DTO
	 * @return List<OPNSRV002M0R2DTO>
	 * @exception Exception
	 */
	public List<OPNSRV002M0R2DTO> selectOptDtlsList(OPNSRV002M0P1DTO inVo);

	/**
	 * 옵션 상세 이력 목록 조회를 처리한다
	 * @param vo OPNSRV002M0P2DTO
	 * @return List<OPNSRV002M0R3DTO>
	 * @exception Exception
	 */
	public List<OPNSRV002M0R3DTO> selectOptDtlsHistList(OPNSRV002M0P2DTO inVo);

	/**
	 * 옵션 이력 시작일자 적합성를 조회한다
	 * @param vo OPNSRV002M0R0DTO
	 * @return String
	 * @exception Exception
	 */
	public String selectChkDate(OPNSRV002M0R0DTO inVo);

	/**
	 * 옵션 상세 이력 시작일자 적합성를 조회한다
	 * @param vo OPNSRV002M0R2DTO
	 * @return String
	 * @exception Exception
	 */
	public String selectChkDateDtls(OPNSRV002M0R2DTO inVo);

	/**
	 * 옵션 이력 시작일자 기준 count 조회를 처리한다
	 * @param vo OPNSRV002M0R0DTO
	 * @return String
	 * @exception Exception
	 */
	public String selectChkHist(OPNSRV002M0R0DTO inVo);

	/**
	 * 옵션 상세 이력 시작일자 기준 count 조회를 처리한다
	 * @param vo OPNSRV002M0R2DTO
	 * @return String
	 * @exception Exception
	 */
	public String selectChkDtlsHist(OPNSRV002M0R2DTO inVo);

	/**
	 * 옵션 정보를 저장한다
	 * @param vo OPNSRV002M0R0DTO
	 * @exception Exception
	 */
	public void updateOpt(OPNSRV002M0R0DTO inVo);

	/**
	 * 옵션 정보를 저장한다
	 * @param vo OPNSRV002M0R2DTO
	 * @exception Exception
	 */
	public void updateOptDtls(OPNSRV002M0R2DTO inVo);

	/**
	 * 옵션 이력 정보를 저장한다
	 * @param vo OPNSRV002M0R0DTO
	 * @exception Exception
	 */
	public void updateOptHistDay(OPNSRV002M0R0DTO inVo);

	/**
	 * 옵션 상세 이력 정보를 저장한다
	 * @param vo OPNSRV002M0R2DTO
	 * @exception Exception
	 */
	public void updateOptDtlsHistDay(OPNSRV002M0R2DTO inVo);

	/**
	 * 옵션 이력 정보를 저장한다
	 * @param vo OPNSRV002M0R0DTO
	 * @exception Exception
	 */
	public void updateOptHistBefor(OPNSRV002M0R0DTO inVo);

	/**
	 * 옵션 상세 이력 정보를 저장한다
	 * @param vo OPNSRV002M0R2DTO
	 * @exception Exception
	 */
	public void updateOptDtlsHistBefor(OPNSRV002M0R2DTO inVo);

	/**
	 * 옵션 이력 정보를 저장한다
	 * @param vo OPNSRV002M0R0DTO
	 * @exception Exception
	 */
	public void insertOptHist(OPNSRV002M0R0DTO inVo);

	/**
	 * 옵션 상세 이력 정보를 저장한다
	 * @param vo OPNSRV002M0R2DTO
	 * @exception Exception
	 */
	public void insertOptDtlsHist(OPNSRV002M0R2DTO inVo);

	/**
	 * 옵션 정보를 저장한다
	 * @param vo OPNSRV002M0R0DTO
	 * @exception Exception
	 */
	public void insertOpt(OPNSRV002M0R0DTO inVo);

	/**
	 * 옵션 상세 정보를 저장한다
	 * @param vo OPNSRV002M0R0DTO
	 * @exception Exception
	 */
	public void insertOptDtls(OPNSRV002M0R2DTO inVo);

}