package com.rds.adams.web.common.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rds.adams.web.common.login.dao.AdamsLoginDAO;
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
import com.rds.adams.web.core.utils.EmailUtil;
import com.rds.adams.web.core.utils.dto.EmailDTO;
import com.rds.rsf.core.util.RsfNumberUtil;
import com.rds.rsf.core.util.RsfStringUtil;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 일반 로그인을 처리하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2024.09.05
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2024.09.05  LCG          최초 생성
 *
 *  </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdamsLoginService {

	@Resource(name = "adamsLoginDAO")
	private AdamsLoginDAO adamsLoginDAO;

	/**
	 * 일반 로그인을 처리한다
	 * @param vo AdamsLoginDTO
	 * @return AdamsLoginDTO
	 * @exception Exception
	 */
	public AdamsLoginDTO selectLoginInfo(AdamsLoginDTO vo) throws Exception {
		
		log.debug(" =====================> AdamsLoginDTO : " + vo.toString() );
		// 1. 입력한 비밀번호를 암호화한다.
		//생략 DB에서 진행
		
		// 2. 아이디와 비밀번호가 DB와 일치하는지 확인한다.
		AdamsLoginDTO adamsLoginDTO = adamsLoginDAO.actionLogin(vo);

		// 3. 결과를 리턴한다.
		if (adamsLoginDTO != null && !adamsLoginDTO.getUsrId().equals("") && !adamsLoginDTO.getUsrDvCd().equals("")) {
			return adamsLoginDTO;
		} else {
			adamsLoginDTO = new AdamsLoginDTO();
		}

		return adamsLoginDTO;
	}

	/**
	 * 사용자 사용 가는 여부를 조회한다.
	 * @param vo AdamsLoginDTO
	 * @return String
	 * @exception Exception
	 */
	public String checkUserUse(AdamsLoginDTO vo) throws Exception {
		return adamsLoginDAO.checkUserUse(vo);
	}

	/**
	 * 사용자 메뉴를 처리한다
	 * @param vo AdamsLoginDTO
	 * @return List<AdamsMenuDTO>
	 * @exception Exception
	 */
	public List<AdamsMenuDTO> selectMenuList(AdamsLoginDTO vo) throws Exception {

		// 1. 사용자 메뉴 옥록을 확인한다.
		List<AdamsMenuDTO> adamsLoginDTOs = adamsLoginDAO.actionMenu(vo);

		// 2. 결과를 리턴한다.
		if (adamsLoginDTOs != null && adamsLoginDTOs.size() != 0 && !adamsLoginDTOs.get(0).getMenuId().equals("")) {
			return adamsLoginDTOs;
		} else {
			adamsLoginDTOs = new ArrayList<>();
		}

		return adamsLoginDTOs;
	}

	/**
	 * 사용자 메뉴를 Tree 구조 처리한다
	 * @param vo AdamsLoginDTO
	 * @return List<AdamsMenuDTO>
	 * @exception Exception
	 */
	public List<AdamsMenuDTO> selectMenuTreeList(List<AdamsMenuDTO> adamsMenuDTOs) throws Exception {
		log.debug(" ================= AdamsLoginService.selectMenuTreeList [START]] ===================== ");
		List<AdamsMenuDTO> rAdamssMenuDTOs = null;
		AdamsMenuDTO       uAdamsMenuDTO   = null;
		List<AdamsMenuDTO> sAdamssMenuDTOs = null;
		int                           cnt  = 0;
		
		// 2. 결과를 리턴한다.
		if (adamsMenuDTOs != null && adamsMenuDTOs.size() != 0 && !adamsMenuDTOs.get(0).getMenuId().equals("")) {
			rAdamssMenuDTOs = new ArrayList<>();

			log.debug(" ================= adamsLoginDTOs ===================== ");
			
			for( AdamsMenuDTO adamsLoginDTO : adamsMenuDTOs ) {
				if( "0".equals( adamsLoginDTO.getLevel() ) ) {

					log.debug(" ================= adamsLoginDTO LEVEL 0 : " + adamsLoginDTO.getMenuNmKor() + " ===================== ");
					if( cnt > 0 ) {
						uAdamsMenuDTO.setAdamsMenuDTOList(sAdamssMenuDTOs);
						rAdamssMenuDTOs.add(uAdamsMenuDTO);
					}
					
					uAdamsMenuDTO = new AdamsMenuDTO();
					uAdamsMenuDTO.setMenuId(adamsLoginDTO.getMenuId());
					uAdamsMenuDTO.setMenuNmKor(adamsLoginDTO.getMenuNmKor());
					uAdamsMenuDTO.setMenuNm(adamsLoginDTO.getMenuNm());
					uAdamsMenuDTO.setUpprMenuId(adamsLoginDTO.getUpprMenuId());
					uAdamsMenuDTO.setMenuSrtOrd(adamsLoginDTO.getMenuSrtOrd());
					uAdamsMenuDTO.setMenuDesc(adamsLoginDTO.getMenuDesc());
					uAdamsMenuDTO.setPgmUrl(adamsLoginDTO.getPgmUrl());
					uAdamsMenuDTO.setLevel(adamsLoginDTO.getLevel());
					uAdamsMenuDTO.setSort(adamsLoginDTO.getSort());
					sAdamssMenuDTOs = new ArrayList<>();
					cnt++;
				} else {
					log.debug(" ================= adamsLoginDTO LEVEL 1 : " + adamsLoginDTO.getMenuNmKor() + " ===================== ");
					sAdamssMenuDTOs.add(adamsLoginDTO);
				}
				
			}
			uAdamsMenuDTO.setAdamsMenuDTOList(sAdamssMenuDTOs);
			rAdamssMenuDTOs.add(uAdamsMenuDTO);
			
			return rAdamssMenuDTOs;
		} else {
			rAdamssMenuDTOs = new ArrayList<>();
		}

		log.debug(" ================= AdamsLoginService.selectMenuTreeList [END]] ===================== ");
		return rAdamssMenuDTOs;
	}

	/**
	 * 고객사 목록을 조회한다
	 * @param vo AdamsLoginDTO
	 * @return List<AdamsCsNoDTO>
	 * @exception Exception
	 */
	public List<AdamsCsNoDTO> selectCsNoList(AdamsLoginDTO vo) throws Exception {

		// 1. 사용자 메뉴 옥록을 확인한다.
		List<AdamsCsNoDTO> adamsCsNoDTOs = adamsLoginDAO.selectCsNoList(vo);

		// 2. 결과를 리턴한다.
		if (adamsCsNoDTOs != null && adamsCsNoDTOs.size() != 0 && !adamsCsNoDTOs.get(0).getCsNo().equals("")) {
			return adamsCsNoDTOs;
		} else {
			adamsCsNoDTOs = new ArrayList<>();
		}

		return adamsCsNoDTOs;
	}

	/**
	 * 아이디를 찾는다.
	 * @param vo AdamsLoginDTO
	 * @return AdamsLoginDTO
	 * @exception Exception
	 */
	public AdamsLoginDTO searchId(AdamsLoginDTO vo) throws Exception {

		// 1. 이름, 이메일주소가 DB와 일치하는 사용자 ID를 조회한다.
		AdamsLoginDTO adamsLoginDTO = adamsLoginDAO.searchId(vo);

		// 2. 결과를 리턴한다.
		if (adamsLoginDTO != null && !adamsLoginDTO.getUsrId().equals("")) {
			return adamsLoginDTO;
		} else {
			adamsLoginDTO = new AdamsLoginDTO();
		}

		return adamsLoginDTO;
	}

	/**
	 * 사용자 로그인 이력을 저장한다.
	 * @param vo AdamsLoginDTO
	 * @return boolean
	 * @exception Exception
	 */
	public boolean insertLoginHist(AdamsLoginDTO vo, HttpServletRequest request) throws Exception {

		boolean result = true;

		// 1. 로그인 정보가 있는지 확인한다.
		if (vo == null || vo.getUsrId() == null || "".equals(vo.getUsrId())) {
			return false;
		}
		
		// 2. 사용자의 접속IP를 가져온다. 
		String ip = request.getHeader("X-Forwarded-For");
		
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    
	    vo.setAccIp(ip);

		// 3. 신규 비밀번호를 DB에 저장한다.
		adamsLoginDAO.insertLoginHist(vo);

		return result;
	}

	/**
	 * 사용자 로그인 이력을 저장한다.
	 * @param vo AdamsLoginDTO
	 * @return boolean
	 * @exception Exception
	 */
	public boolean updateUsrPassword(AdamsLoginDTO vo) throws Exception {

		boolean result = true;

		// 1. 사용자 비밀번호 변경 정보가 있는지 확인한다.
		if (vo == null || vo.getUsrId() == null || "".equals(vo.getUsrId()) || vo.getUsrPassword() == null || "".equals(vo.getUsrPassword())) {
			return false;
		}
		
		// 3. 신규 비밀번호를 DB에 저장한다.
		adamsLoginDAO.updatePassword(vo);

		return result;
	}

	/**
	 * 비밀번호를 찾는다.
	 * @param vo AdamsLoginDTO
	 * @return boolean
	 * @exception Exception
	 */
	public boolean searchPassword(AdamsFindPwDTO vo) throws Exception {

		boolean result = true;

		// 1. 아이디, 이름, 이메일주소, 비밀번호 힌트, 비밀번호 정답이 DB와 일치하는 사용자 Password를 조회한다.
		AdamsLoginDTO adamsLoginDTO = adamsLoginDAO.searchPassword(vo);
		if (adamsLoginDTO == null || adamsLoginDTO.getUsrId() == null || "".equals(adamsLoginDTO.getUsrId())) {
			return false;
		}

		// 2. 임시 비밀번호를 생성한다.(영+영+숫+영+영+숫=6자리)
		String newpassword = "";
		for (int i = 1; i <= 6; i++) {
			// 영자
			if (i % 3 != 0) {
				newpassword += RsfStringUtil.getRandomStr('a', 'z');
				// 숫자
			} else {
				newpassword += RsfNumberUtil.getRandomNum(0, 9);
			}
		}

		// 3. 임시 비밀번호를 암호화하여 DB에 저장한다.
		AdamsLoginDTO pwVO = new AdamsLoginDTO();
		pwVO.setUsrId(adamsLoginDTO.getUsrId());
		pwVO.setCsNo(adamsLoginDTO.getCsNo());
		pwVO.setUsrNewPassword(newpassword);
		//pwVO.setUsrDvCd(vo.getUsrDvCd());
		adamsLoginDAO.updatePasswordReset(pwVO);
		
		// 4. 임시 비밀번호를 사용자 이메일로 전송한다.
		EmailDTO emailDTO = new EmailDTO(); 
		
		emailDTO.setRecipientAddress(adamsLoginDTO.getUsrEmail());
		//emailDTO.setSubject("ADAMS : 사용자 임시 비밀번호 발송");
		emailDTO.setSubject("ADAMS: Send User Temporary Password");
		emailDTO.setBodyPlainText("User Temporary Password : " + newpassword);
		
		EmailUtil.sendEmail(emailDTO);
		
		return result;
	}

	/**
	 * 신규 고객 신청 정보를 저장한다.
	 * @param vo AdamsNewCsDTO
	 * @return boolean
	 * @exception Exception
	 */
	public boolean insertNewCs(AdamsNewCsDTO vo) throws Exception {

		boolean result = true;

		// 1. 신규 고객 신청 정보가 있는지 확인한다.
		if (vo == null || vo.getPtbEmail() == null || "".equals(vo.getPtbEmail())
				 || vo.getPtbPhNo() == null || "".equals(vo.getPtbPhNo())
				 || vo.getCompNoDvCd() == null || "".equals(vo.getCompNoDvCd())
				 || vo.getCompNo() == null || "".equals(vo.getCompNo()) ) {
			return false;
		}
		// 2. 신규 고객 신청 정보를 DB에 저장한다.
		adamsLoginDAO.insertNewCs(vo);

		return result;
	}

	
	/**
	 * 회사번호(CS_NO)로 회사명(COMP_NM)
	 * @param csNo
	 * @return
	 */
	public AdamsCsNoDTO getCsNmByCsNo(String csNo) {
		
		AdamsCsNoDTO inVO = new AdamsCsNoDTO();
		AdamsCsNoDTO outVO = new AdamsCsNoDTO();
		
		inVO.setCsNo(csNo);
		
		outVO = adamsLoginDAO.selectCsNm(inVO);
		
		if (outVO == null) {
			return new AdamsCsNoDTO();
		} else {
			return outVO;
		}

	}
	
	/**
	 * 마이페이지에서 계정을 생성한 후 누적일 수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsRegDtmTotalDTO
	 * @exception Exception
	 */
	public AdamsRegDtmTotalDTO selectRegDtmTotal(AdamsMypageUsrIdDTO vo) {
		
		log.debug(" AdamsMypageUsrIdDTO : " + vo.getUsrId() );
		
		// 1. 사용자 아이디를 이용해 계정을 생성한 후부터 오늘까지의 누적일을 계산한다.
		AdamsRegDtmTotalDTO adamsRegDtmTotalDTO = adamsLoginDAO.selectRegDtmTotal(vo);		
		
		// 2. 결과를 리턴한다.
		if (adamsRegDtmTotalDTO != null ) {
			return adamsRegDtmTotalDTO;
		} else {
			adamsRegDtmTotalDTO = new AdamsRegDtmTotalDTO();
		}
		return adamsRegDtmTotalDTO;
		
	}
	
	/**
	 * 마이페이지에서 오늘을 제외하고 가장 최근에 로그인 했던 날짜를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsLastLoginDtmDTO
	 * @exception Exception
	 */
	public AdamsLastLoginDtmDTO selectLastLoginDtm (AdamsMypageUsrIdDTO vo) {
				
		// 1. 사용자 아이디를 이용해 사용자가 오늘을 제외하고 가장 최근에 로그인 했던 날짜를 조회한다.
		AdamsLastLoginDtmDTO adamsLastLoginDtmDTO = adamsLoginDAO.selectLastLoginDtm(vo);
		
		// 2. 결과를 리턴한다.		
		if (adamsLastLoginDtmDTO != null && !adamsLastLoginDtmDTO.getLastLoginDtm().equals("")) {
			return adamsLastLoginDtmDTO;
		} else {
			adamsLastLoginDtmDTO = new AdamsLastLoginDtmDTO();
		}
		return adamsLastLoginDtmDTO;
	}
	
	/**
	 * 마이페이지에서 로그인 총 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsLoginCntTotalDTO
	 * @exception Exception
	 */
	public AdamsLoginCntTotalDTO selectLoginCntTotal (AdamsMypageUsrIdDTO vo) {
		
		// 1. 사용자 아이디를 이용해 로그인 한 총 횟수를 조회한다.
		AdamsLoginCntTotalDTO adamsLoginCntTotalDTO = adamsLoginDAO.selectLoginCntTotal(vo);
		
		// 2. 결과를 리턴한다.	
		if (adamsLoginCntTotalDTO != null ) {
			return adamsLoginCntTotalDTO;
		} else {
			adamsLoginCntTotalDTO = new AdamsLoginCntTotalDTO();
		}
		return adamsLoginCntTotalDTO;
	}
	
	/**
	 * 마이페이지에서 (전날 기준)30일간 일별 로그인 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return List<AdamsMonthLoginCntDTO>
	 * @exception Exception
	 */
	public List<AdamsMonthLoginCntDTO> selectMonthLoginCnt (AdamsMypageUsrIdDTO vo) {
		
		// 1. 사용자 아이디를 이용해 마이페이지에서 30일간 일별 로그인 횟수 리스트를 조회한다.
		List<AdamsMonthLoginCntDTO> adamsMonthLoginCntDTOs = adamsLoginDAO.selectMonthLoginCnt(vo);

		// 2. 결과를 리턴한다.
		if (adamsMonthLoginCntDTOs != null ) {
			return adamsMonthLoginCntDTOs;
		} else {
			adamsMonthLoginCntDTOs = new ArrayList<>();
		}
		return adamsMonthLoginCntDTOs;
	}
	
	/**
	 * 마이페이지에서 오늘을 제외하고 가장 최근에 파일 업로드 했던 날짜를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsLastUploadDtmDTO
	 * @exception Exception
	 */
	public AdamsLastUploadDtmDTO selectLastUploadDtm(AdamsMypageUsrIdDTO vo) {

		// 1. 사용자 아이디를 이용해 사용자가 오늘을 제외하고 가장 최근에 파일 업로드 했던 날짜를 조회한다.
		AdamsLastUploadDtmDTO adamsLastUploadDtmDTO = adamsLoginDAO.selectLastUploadDtm(vo);
				
		// 2. 결과를 리턴한다.		
		if (adamsLastUploadDtmDTO != null && !adamsLastUploadDtmDTO.getLastUploadDtm().equals("")) {
			return adamsLastUploadDtmDTO;
		} else {
			adamsLastUploadDtmDTO = new AdamsLastUploadDtmDTO();
		}
		return adamsLastUploadDtmDTO;
	}
	
	/**
	 * 마이페이지에서 파일 업로드 총 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsUploadCntTotalDTO
	 * @exception Exception
	 */
	public AdamsUploadCntTotalDTO selectUploadCntTotal(AdamsMypageUsrIdDTO vo) {
		
		// 1. 사용자 아이디를 이용해 파일 업로드 한 총 횟수를 조회한다.
		AdamsUploadCntTotalDTO adamsUploadCntTotalDTO = adamsLoginDAO.selectUploadCntTotal(vo);
				
		// 2. 결과를 리턴한다.	
		if (adamsUploadCntTotalDTO != null ) {
			return adamsUploadCntTotalDTO;
		} else {
			adamsUploadCntTotalDTO = new AdamsUploadCntTotalDTO();
		}
		return adamsUploadCntTotalDTO;
	}
	
	/**
	 * 마이페이지에서 (전날 기준)30일간 일별 파일 업로드 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return List<AdamsMonthUploadCntDTO>
	 * @exception Exception
	 */
	public List<AdamsMonthUploadCntDTO> selectMonthUploadCnt(AdamsMypageUsrIdDTO vo){
		
		// 1. 사용자 아이디를 이용해 마이페이지에서 30일간 일별 파일업로드 횟수 리스트를 조회한다.
		List<AdamsMonthUploadCntDTO> adamsMonthUploadCntDTOs = adamsLoginDAO.selectMonthUploadCnt(vo);

		// 2. 결과를 리턴한다.
		if (adamsMonthUploadCntDTOs != null ) {
			return adamsMonthUploadCntDTOs;
		} else {
			adamsMonthUploadCntDTOs = new ArrayList<>();
		}
		return adamsMonthUploadCntDTOs;
	}
	
	/**
	 * 마이페이지에서 오늘을 제외하고 가장 최근에 배치 실행 했던 날짜를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsLastBatDtmDTO
	 * @exception Exception
	 */
	public AdamsLastBatDtmDTO selectLastBatDtm(AdamsMypageUsrIdDTO vo) {

		// 1. 사용자 아이디를 이용해 사용자가 오늘을 제외하고 가장 최근에 배치를 실행 했던 날짜를 조회한다.
		AdamsLastBatDtmDTO adamsLastBatDtmDTO = adamsLoginDAO.selectLastBatDtm(vo);
				
		// 2. 결과를 리턴한다.		
		if (adamsLastBatDtmDTO != null && !adamsLastBatDtmDTO.getLastBatDtm().equals("")) {
			return adamsLastBatDtmDTO;
		} else {
			adamsLastBatDtmDTO = new AdamsLastBatDtmDTO();
		}
		return adamsLastBatDtmDTO;
	}
	
	/**
	 * 마이페이지에서 배치 실행 총 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return AdamsBatCntTotalDTO
	 * @exception Exception
	 */
	public AdamsBatCntTotalDTO selectBatCntTotal(AdamsMypageUsrIdDTO vo) {
		
		// 1. 사용자 아이디를 이용해 배치를 실행한 총 횟수를 조회한다.
		AdamsBatCntTotalDTO adamsBatCntTotalDTO = adamsLoginDAO.selectBatCntTotal(vo);
						
		// 2. 결과를 리턴한다.	
		if (adamsBatCntTotalDTO != null ) {
			return adamsBatCntTotalDTO;
		} else {
			adamsBatCntTotalDTO = new AdamsBatCntTotalDTO();
		}
		return adamsBatCntTotalDTO;
	}
	
	/**
	 * 마이페이지에서 (전날 기준)30일간 일별 배치를 실행한 횟수를 조회한다
	 * @param vo AdamsMypageUsrIdDTO
	 * @return List<AdamsMonthBatCntDTO>
	 * @exception Exception
	 */
	public List<AdamsMonthBatCntDTO> selectMonthBatCnt(AdamsMypageUsrIdDTO vo){
		
		// 1. 사용자 아이디를 이용해 마이페이지에서 30일간 일별 배치를 실행한 횟수 리스트를 조회한다.
		List<AdamsMonthBatCntDTO> adamsMonthBatCntDTOs = adamsLoginDAO.selectMonthBatCnt(vo);

		// 2. 결과를 리턴한다.
		if (adamsMonthBatCntDTOs != null ) {
				return adamsMonthBatCntDTOs;
		} else {
			adamsMonthBatCntDTOs = new ArrayList<>();
		}
		return adamsMonthBatCntDTOs;
	}
	
	/**
	 * 마이페이지에서 변경된 사용자 정보를 저장한다.
	 * @param vo AdamsUpdateAccountDTO
	 * @return boolean
	 * @exception Exception
	 */
	public boolean updateAccount(AdamsUpdateAccountDTO vo) throws Exception {

		// 1. 사용자 비밀번호 변경 정보가 있는지 확인한다.
		if (vo == null || vo.getPtbEmail() == null || "".equals(vo.getPtbEmail()) || vo.getUsrPassword() == null || "".equals(vo.getUsrPassword())) {
			return false;
		}
		
		// 2. 변경된 사용자 정보를 DB에 저장한다.
		int updateCount = adamsLoginDAO.updateAccount(vo);
		return updateCount > 0; // 비밀번호를 정확히 입력했을 때 업데이트된 행의 수 반환, 실패하면 0 반환
	}
	
	/**
	 * 마이페이지에서 변경된 사용자 정보를 조회해서 세션 정보를 업데이트한다
	 * @param vo AdamsLoginDTO
	 * @return AdamsUpdateLoginDTO
	 * @exception Exception
	 */
	public AdamsLoginDTO actionUpdateLogin(AdamsUpdateLoginDTO vo) throws Exception {
		
		log.debug(" =====================> AdamsLoginDTO : " + vo.toString() );
		// 1. 입력한 비밀번호를 암호화한다.
		//생략 DB에서 진행
		
		// 2. 아이디와 비밀번호가 DB와 일치하는지 확인한다.
		AdamsLoginDTO adamsUpdateLoginDTO = adamsLoginDAO.actionUpdateLogin(vo);

		// 3. 결과를 리턴한다.
		if (adamsUpdateLoginDTO != null && !adamsUpdateLoginDTO.getUsrId().equals("") && !adamsUpdateLoginDTO.getUsrDvCd().equals("")) {
			return adamsUpdateLoginDTO;
		} else {
			adamsUpdateLoginDTO = new AdamsLoginDTO();
		}

		return adamsUpdateLoginDTO;
	}
	
	/**
	 * 마이페이지에서 변경한 비밀번호를 저장한다.
	 * @param vo AdamsUpdateAccountDTO
	 * @return boolean
	 * @exception Exception
	 */
	public boolean changeMyPassword(AdamsUpdatePwDTO vo) throws Exception {

		// 1. 사용자 정보가 있는지 확인한다.
		if (vo == null || vo.getUsrId() == null || "".equals(vo.getUsrId()) || vo.getUsrNewPassword() == null || "".equals(vo.getUsrNewPassword())) {
			return false;
		}
		
		// 2. 변경된 사용자의 비밀번호를 DB에 저장한다.
		// 비밀번호 확인, 기존 비밀번호와 새로운 비밀번호의 비교는 프론트에서
		int updateCount = adamsLoginDAO.changeMyPassword(vo);		
		return updateCount > 0; // 현재 비밀번호를 정확히 입력했을 때 업데이트된 행의 수 반환, 비밀번호가 일치하지 않거나 사용자 정보가 잘못된 경우 = 실패하면 0 반환
	}
	
	/**
	 * 마이페이지에서 비밀번호 변경 전 사용자 인증 결과를 전달한다
	 * @param vo AdamsCheckUserParDTO
	 * @exception Exception
	 */
	public AdamsCheckUserResDTO checkUsrPw(AdamsCheckUserParDTO vo) throws Exception {
		
		// 1. 사용자가 입력한 비밀번호가 DB와 일치하는지 확인한다.
		AdamsCheckUserResDTO adamsCheckUserResDTO = adamsLoginDAO.checkUsrPw(vo);
		
		// 2. 사용자 인증 결과를 리턴한다.
		if (adamsCheckUserResDTO != null) {
			return adamsCheckUserResDTO;
		} else {
			adamsCheckUserResDTO = new AdamsCheckUserResDTO();
		}
				
		return adamsCheckUserResDTO;
	}
}