package com.rds.adams.web.adm.usr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.adm.usr.dao.ADMUSR002M0DAO;
import com.rds.adams.web.adm.usr.dto.ADMUSR002M0P0DTO;
import com.rds.adams.web.adm.usr.dto.ADMUSR002M0R0DTO;
import com.rds.adams.web.core.utils.EmailUtil;
import com.rds.adams.web.core.utils.dto.EmailDTO;
import com.rds.rsf.core.util.RsfNumberUtil;
import com.rds.rsf.core.util.RsfStringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@Service
@RequiredArgsConstructor
public class ADMUSR002M0Service {

	@Autowired
	private ADMUSR002M0DAO aDMUSR002M0DAO;

	/**
	 * 사용자 정보 조회를 처리한다
	 * @param vo ADMUSR002M0P0DTO
	 * @return List<ADMUSR002M0R0DTO>
	 * @exception Exception
	 */
	public List<ADMUSR002M0R0DTO> selectUsrList(ADMUSR002M0P0DTO inVo) {
		
		List<ADMUSR002M0R0DTO>	ADMUSR002M0P0DTOList = aDMUSR002M0DAO.selectUsrList(inVo);	// 조회 대상 테이블 정보 DTO
		
		log.debug(" ADMUSR002M0P0DTOList : " + ADMUSR002M0P0DTOList.toString());
		
		return ADMUSR002M0P0DTOList;
		
	}

	/**
	 * 사용자 변경 또는 신규 정보 저장을 처리한다
	 * @param vo ADMUSR002M0R0DTO
	 * @return boolean
	 * @exception Exception
	 */
	public boolean saveUsr(ADMUSR002M0R0DTO inVo, boolean bNewYn) throws Exception {

		String sMaxUsrCntYn   = "";
		String sUsrAdminCntYn = "";
		String newpassword    = "";
		int    iUsrCnt        = 0;
		boolean bMail         = false;
		
		log.debug(" ADMUSR002M0R0DTO : " + inVo.toString());

		try {
			// 1. 사용자 등록/변경에 따른 적합성 검증
			// 1-1. 고객사 사용가능한 사용자 수 체크
			sMaxUsrCntYn = aDMUSR002M0DAO.selectUsrCntChk(inVo);

			if( "N".equals(sMaxUsrCntYn) ) {
				// 최대 사용 사용자보다 더 많은 사람을 등록 할 수 없습니다. 일부 사용자를 추가하려면 '서비스 옵션'메뉴에서 옵션을 변경하십시오.
				throw new Exception("You cannot register more people than the maximum use users. To add some users, change the option from the 'Service Options' menu.");
			}
			// 1-2. 신규 고객 중복 ID 체크
			if ( bNewYn ) {
				iUsrCnt = aDMUSR002M0DAO.selectUsrDupChk(inVo);
				if ( iUsrCnt > 0 ) {
					// 중복된 사용자 ID.
					throw new Exception("Duplicate user ID.");
				}
			}
			// 1-3. 관리자는 항상 1명 체크.
			if ( "3".equals(inVo.getUsrDvCd()) ) {
				sUsrAdminCntYn = aDMUSR002M0DAO.selectUsrAdminCntChk(inVo);
	
				if( "N".equals(sUsrAdminCntYn) ) {
					// 항상 관리자는 1명이어야 합니다..
					throw new Exception("There must always be 1 administrator.");
				}
			}
			
			// 2. 관리자 비밀번호 공백 시 임시 비밀번호를 생성한다.(영+영+숫+영+영+숫=6자리)
			if ( inVo.getOldUsrId() == null || "".equals(inVo.getOldUsrId()) ) {
				
				for (int i = 1; i <= 6; i++) {
					// 영자
					if (i % 3 != 0) {
						newpassword += RsfStringUtil.getRandomStr('a', 'z');
						// 숫자
					} else {
						newpassword += RsfNumberUtil.getRandomNum(0, 9);
					}
				}
				
				inVo.setUsrPassword(newpassword);
				bMail = true;
			}
			
			if ( "2".equals(inVo.getUsrDvCd()) ) {
				aDMUSR002M0DAO.updateUsrAdmin(inVo);	// 조회 대상 테이블 정보 DTO
			} else {
				aDMUSR002M0DAO.updateUsr(inVo);	       // 조회 대상 테이블 정보 DTO
			}
			
			// 관리자 임시 비밀번호 발송
			if ( bMail ) {
	
				// 4. 임시 비밀번호를 사용자 이메일로 전송한다.
				EmailDTO emailDTO = new EmailDTO(); 
				
				inVo.setUsrEmail(inVo.getUsrId());
				emailDTO.setRecipientAddress(inVo.getUsrEmail());
				//emailDTO.setSubject("ADAMS : 사용자 임시 비밀번호 발송");
				emailDTO.setSubject("ADAMS: Send User Temporary Password");
				emailDTO.setBodyPlainText("User Temporary Password : " + newpassword);
				
				EmailUtil.sendEmail(emailDTO);
			}
			
			log.debug(" ADMUSR002M0R0DTO : " + inVo.toString());
		} catch (Exception e) {
			throw new Exception("ADMUSR002M0Service.saveUsr Error : " + e.getMessage());
		}
		
		return true;
		
	}
	

}