package com.rds.adams.web.opn.usr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.core.utils.EmailUtil;
import com.rds.adams.web.core.utils.dto.EmailDTO;
import com.rds.adams.web.opn.usr.dao.OPNUSR003M0DAO;
import com.rds.adams.web.opn.usr.dto.OPNUSR003M0P0DTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR003M0R0DTO;
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
public class OPNUSR003M0Service {

	@Autowired
	private OPNUSR003M0DAO oPNUSR003M0DAO;

	/**
	 * 사용자 정보 조회를 처리한다
	 * @param vo OPNUSR003M0P0DTO
	 * @return List<OPNUSR003M0R0DTO>
	 * @exception Exception
	 */
	public List<OPNUSR003M0R0DTO> selectUsrList(OPNUSR003M0P0DTO inVo) {
		
		List<OPNUSR003M0R0DTO>	oPNUSR003M0P0DTOList = oPNUSR003M0DAO.selectUsrList(inVo);	// 조회 대상 테이블 정보 DTO
		
		log.debug(" oPNUSR003M0P0DTOList : " + oPNUSR003M0P0DTOList.toString());
		
		return oPNUSR003M0P0DTOList;
		
	}

	/**
	 * 사용자 변경 또는 신규 정보 저장을 처리한다
	 * @param vo OPNUSR003M0R0DTO
	 * @return boolean
	 * @exception Exception
	 */
	public boolean saveUsr(OPNUSR003M0R0DTO inVo) throws Exception {

		String sMaxUsrCntYn   = "";
		String newpassword  = "";
		boolean bMail       = false;
		
		log.debug(" OPNUSR003M0R0DTO : " + inVo.toString());

		try {
			// 1. 고객사 사용자 수 체크
			sMaxUsrCntYn = oPNUSR003M0DAO.selectUsrCntChk(inVo.getCsNo());
			
			if( "N".equals(sMaxUsrCntYn) ) {
				// 회사는 최대 사용자 수보다 많은 사람을 등록할 수 없습니다.
				throw new Exception("A company[" + inVo.getCompNm() + "] cannot register more than the maximum number of users.");
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
			
			oPNUSR003M0DAO.updateUsr(inVo);	// 조회 대상 테이블 정보 DTO
			
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
			
			log.debug(" OPNUSR003M0R0DTO : " + inVo.toString());
		} catch (Exception e) {
			throw new Exception("OPNUSR003M0Service.saveUsr Error : " + e.getMessage());
		}
		
		return true;
		
	}
	
	/**
	 * 고객사의 사용자 사용수 체크를 처리한다
	 * @param String
	 * @return boolean
	 * @exception Exception
	 */
	public boolean selectUsrCntChk(String sCsNo) {
		
		boolean bResult = false;
		
		String sResult = oPNUSR003M0DAO.selectUsrCntChk(sCsNo);	// 조회 대상 테이블 정보 DTO
		
		log.debug(" selectUsrCntChk : " + sResult);
		
		if ( "Y".equals(sResult) ) {
			bResult = true;
		}
		
		return bResult;
		
	}
	

}