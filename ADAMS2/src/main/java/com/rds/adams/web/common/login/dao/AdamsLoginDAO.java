package com.rds.adams.web.common.login.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.common.login.dto.AdamsBatCntTotalDTO;
import com.rds.adams.web.common.login.dto.AdamsCheckUserParDTO;
import com.rds.adams.web.common.login.dto.AdamsCheckUserResDTO;
import com.rds.adams.web.common.login.dto.AdamsCsNoDTO;
import com.rds.adams.web.common.login.dto.AdamsFindPwDTO;
import com.rds.adams.web.common.login.dto.AdamsLastBatDtmDTO;
import com.rds.adams.web.common.login.dto.AdamsLastLoginDtmDTO;
import com.rds.adams.web.common.login.dto.AdamsLastUploadDtmDTO;
import com.rds.adams.web.common.login.dto.AdamsLoginCntTotalDTO;
import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.adams.web.common.login.dto.AdamsMenuDTO;
import com.rds.adams.web.common.login.dto.AdamsMonthBatCntDTO;
import com.rds.adams.web.common.login.dto.AdamsMonthLoginCntDTO;
import com.rds.adams.web.common.login.dto.AdamsMonthUploadCntDTO;
import com.rds.adams.web.common.login.dto.AdamsMypageUsrIdDTO;
import com.rds.adams.web.common.login.dto.AdamsNewCsDTO;
import com.rds.adams.web.common.login.dto.AdamsRegDtmTotalDTO;
import com.rds.adams.web.common.login.dto.AdamsUpdateAccountDTO;
import com.rds.adams.web.common.login.dto.AdamsUpdateLoginDTO;
import com.rds.adams.web.common.login.dto.AdamsUpdatePwDTO;
import com.rds.adams.web.common.login.dto.AdamsUploadCntTotalDTO;

/**
 * 일반 로그인을 처리하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.06  박지욱          최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Mapper
public interface AdamsLoginDAO {

	/**
	 * 일반 로그인을 처리한다
	 * @param vo AdamsLoginDAO
	 * @return AdamsLoginDAO
	 * @exception Exception
	 */
	public AdamsLoginDTO actionLogin(AdamsLoginDTO vo) ;

	/**
	 * 일반 로그인을 처리한다
	 * @param vo AdamsLoginDAO
	 * @return String
	 * @exception Exception
	 */
	public String checkUserUse(AdamsLoginDTO vo) ;

	/**
	 * 사용자 메뉴를 처리한다
	 * @param vo AdamsLoginDAO
	 * @return List<AdamsMenuDTO>
	 * @exception Exception
	 */
	public List<AdamsMenuDTO> actionMenu(AdamsLoginDTO vo) ;

	/**
	 * 고객사 목록 조회 한다
	 * @param vo AdamsLoginDAO
	 * @return List<AdamsCsNoDTO>
	 * @exception Exception
	 */
	public List<AdamsCsNoDTO> selectCsNoList(AdamsLoginDTO vo) ;

	/**
	 * 아이디를 찾는다.
	 * @param vo AdamsLoginDAO
	 * @return AdamsLoginDAO
	 * @exception Exception
	 */
	public AdamsLoginDTO searchId(AdamsLoginDTO vo);

	/**
	 * 비밀번호를 찾는다.
	 * @param vo AdamsLoginDAO
	 * @return AdamsFindPwDTO
	 * @exception Exception
	 */
	public AdamsLoginDTO searchPassword(AdamsFindPwDTO vo);

	/**
	 * 사용자 로그인 이력을 저장한다.
	 * @param vo AdamsLoginDAO
	 * @exception Exception
	 */
	public void insertLoginHist(AdamsLoginDTO vo) ;

	/**
	 * 변경된 비밀번호를 저장한다.
	 * @param vo AdamsLoginDAO
	 * @exception Exception
	 */
	public void updatePassword(AdamsLoginDTO vo) ;

	/**
	 * 초기화된 비밀번호를 저장한다.
	 * @param vo AdamsLoginDAO
	 * @exception Exception
	 */
	public void updatePasswordReset(AdamsLoginDTO vo) ;

	/**
	 * 인증서 로그인을 처리한다
	 * @param vo AdamsLoginDAO
	 * @return AdamsLoginDAO
	 * @exception Exception
	 */
	public AdamsLoginDTO actionCrtfctLogin(AdamsLoginDTO vo) ;

	/**
	 * 신규 고객 저장을 처리한다
	 * @param vo AdamsNewCsDTO
	 * @exception Exception
	 */
	public void insertNewCs(AdamsNewCsDTO vo) ;
	
	/**
	 * 신규 고객 저장을 처리한다
	 * @param vo AdamsNewCsDTO
	 * @exception Exception
	 */
	public AdamsCsNoDTO selectCsNm(AdamsCsNoDTO vo) ;
	
	/**
	 * 마이페이지에서 계정을 생성한 후 누적일 수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsRegDtmTotalDTO
	 * @exception Exception
	 */
	public AdamsRegDtmTotalDTO selectRegDtmTotal(AdamsMypageUsrIdDTO vo) ;
	
	/**
	 * 마이페이지에서 오늘을 제외하고 가장 최근에 로그인 했던 날짜를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsLastLoginDtmDTO
	 * @exception Exception
	 */
	public AdamsLastLoginDtmDTO selectLastLoginDtm(AdamsMypageUsrIdDTO vo) ;
	
	/**
	 * 마이페이지에서 로그인 총 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsLoginCntTotalDTO
	 * @exception Exception
	 */
	public AdamsLoginCntTotalDTO selectLoginCntTotal(AdamsMypageUsrIdDTO vo) ;
	
	/**
	 * 마이페이지에서 (전날 기준)30일간 일별 로그인 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return List<AdamsMonthLoginCntDTO>
	 * @exception Exception
	 */
	public List<AdamsMonthLoginCntDTO> selectMonthLoginCnt(AdamsMypageUsrIdDTO vo) ;
	
	/**
	 * 마이페이지에서 오늘을 제외하고 가장 최근에 파일 업로드 했던 날짜를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsLastUploadDtmDTO
	 * @exception Exception
	 */
	public AdamsLastUploadDtmDTO selectLastUploadDtm(AdamsMypageUsrIdDTO vo) ;
	
	/**
	 * 마이페이지에서 파일 업로드 총 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsUploadCntTotalDTO
	 * @exception Exception
	 */
	public AdamsUploadCntTotalDTO selectUploadCntTotal(AdamsMypageUsrIdDTO vo) ;
	
	/**
	 * 마이페이지에서 (전날 기준)30일간 일별 파일 업로드 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return List<AdamsMonthUploadCntDTO>
	 * @exception Exception
	 */
	public List<AdamsMonthUploadCntDTO> selectMonthUploadCnt(AdamsMypageUsrIdDTO vo) ;
	
	/**
	 * 마이페이지에서 오늘을 제외하고 가장 최근에 배치 실행 했던 날짜 를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsLastBatDtmDTO
	 * @exception Exception
	 */
	public AdamsLastBatDtmDTO selectLastBatDtm(AdamsMypageUsrIdDTO vo) ;
	
	/**
	 * 마이페이지에서 배치 실행 총 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsBatCntTotalDTO
	 * @exception Exception
	 */
	public AdamsBatCntTotalDTO selectBatCntTotal(AdamsMypageUsrIdDTO vo) ;
	
	/**
	 * 마이페이지에서 (전날 기준)30일간 일별 배치를 실행한 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return List<AdamsMonthBatCntDTO>
	 * @exception Exception
	 */
	public List<AdamsMonthBatCntDTO> selectMonthBatCnt(AdamsMypageUsrIdDTO vo) ;
	
	/**
	 * 마이페이지에서 변경된 사용자 정보를 저장한다.
	 * @param vo AdamsUpdateAccountDTO
	 * @exception Exception
	 */
	public int updateAccount(AdamsUpdateAccountDTO vo) ;
	
	/**
	 * 마이페이지에서 변경된 사용자 정보를 조회해서 세션에 정보를 업데이트한다
	 * @param vo AdamsUpdateLoginDTO
	 * @return AdamsLoginDTO
	 * @exception Exception
	 */
	public AdamsLoginDTO actionUpdateLogin(AdamsUpdateLoginDTO vo) ;
	
	/**
	 * 마이페이지에서 변경한 비밀번호를 저장한다.
	 * @param vo AdamsUpdatePwDTO
	 * @exception Exception
	 */
	public int changeMyPassword(AdamsUpdatePwDTO vo) ;
	
	/**
	 * 마이페이지에서 비밀번호 변경 전 사용자 인증 결과를 전달한다
	 * @param vo AdamsCheckUserParDTO
	 * @exception Exception
	 */
	public AdamsCheckUserResDTO checkUsrPw(AdamsCheckUserParDTO vo) ;
}
