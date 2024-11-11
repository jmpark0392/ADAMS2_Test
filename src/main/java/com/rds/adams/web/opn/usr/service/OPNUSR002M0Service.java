package com.rds.adams.web.opn.usr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rds.adams.web.core.utils.EmailUtil;
import com.rds.adams.web.core.utils.dto.EmailDTO;
import com.rds.adams.web.opn.usr.dao.OPNUSR002M0DAO;
import com.rds.adams.web.opn.usr.dto.OPNUSR002M0P0DTO;
import com.rds.adams.web.opn.usr.dto.OPNUSR002M0R0DTO;
import com.rds.rsf.core.util.RsfNumberUtil;
import com.rds.rsf.core.util.RsfStringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 9. 26.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-09-26 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class OPNUSR002M0Service {

	@Autowired
	private OPNUSR002M0DAO oPNUSR002M0DAO;

	/**
	 * 고객사 정보 조회를 처리한다
	 * @param vo OPNUSR002M0P0DTO
	 * @return List<OPNUSR002M0R0DTO>
	 * @exception Exception
	 */
	public List<OPNUSR002M0R0DTO> selectCsNoList(OPNUSR002M0P0DTO inVo) {
		
		List<OPNUSR002M0R0DTO>	oPNUSR002M0P0DTOList = oPNUSR002M0DAO.selectCsNoList(inVo);	// 조회 대상 테이블 정보 DTO
		
		log.debug(" oPNUSR002M0P0DTOList : " + oPNUSR002M0P0DTOList.toString());
		
		return oPNUSR002M0P0DTOList;
		
	}

	/**
	 * 고객사 정보 저장을 처리한다
	 * @param vo OPNUSR002M0R0DTO
	 * @return boolean
	 * @exception Exception
	 */
	public boolean saveCsNo(OPNUSR002M0R0DTO inVo) throws Exception {
		
		String sStatDvCd    = "";
		String sOldStatDvCd = "";
		String newpassword  = "";
		boolean bMail       = false;
		
		log.debug(" OPNUSR002M0R0DTO : " + inVo.toString());

		// 2. 관리자 비밀번호 공백 시 임시 비밀번호를 생성한다.(영+영+숫+영+영+숫=6자리)
		if ( (inVo.getCsNo() == null || "".equals(inVo.getCsNo())) && (inVo.getAdminPassword() == null || "".equals(inVo.getAdminPassword())) ) {

			for (int i = 1; i <= 6; i++) {
				// 영자
				if (i % 3 != 0) {
					newpassword += RsfStringUtil.getRandomStr('a', 'z');
					// 숫자
				} else {
					newpassword += RsfNumberUtil.getRandomNum(0, 9);
				}
			}
			
			inVo.setAdminPassword(newpassword);
			bMail = true;
		}
		
		oPNUSR002M0DAO.updateCsNo(inVo);	// 조회 대상 테이블 정보 DTO
		
		sStatDvCd = inVo.getStatDvCd();
		sOldStatDvCd = inVo.getOldStatDvCd();
		
		// 고객사 사용이 확정되어 정상사용인 경우 관리자 사용자를 생성시켜줌.
		if ( ( "1".equals(sOldStatDvCd) || "2".equals(sOldStatDvCd) ) && "0".equals(sStatDvCd) ) {
			oPNUSR002M0DAO.insertAdmUsr(inVo);
		}
		
		// 고객사 사용 중지 상태의 경우 고객사 사용자 상태 변경.
		if ( "9".equals(sStatDvCd) ) {
			oPNUSR002M0DAO.updateUsrEnd(inVo);
		}
		
		// 관리자 임시 비밀번호 발송
		if ( bMail ) {

			// 4. 임시 비밀번호를 사용자 이메일로 전송한다.
			EmailDTO emailDTO = new EmailDTO(); 
			
			emailDTO.setRecipientAddress(inVo.getPtbEmail());
			//emailDTO.setSubject("ADAMS : 사용자 임시 비밀번호 발송");
			emailDTO.setSubject("ADAMS: Send User Temporary Password");
			emailDTO.setBodyPlainText("User Temporary Password : " + newpassword);
			
			EmailUtil.sendEmail(emailDTO);
		}
		
		log.debug(" OPNUSR002M0R0DTO : " + inVo.toString());
		
		return true;
		
	}
	

}