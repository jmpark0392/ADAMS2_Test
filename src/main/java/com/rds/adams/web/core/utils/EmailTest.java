/**
 * 
 */
package com.rds.adams.web.core.utils;

import com.rds.adams.web.core.utils.dto.EmailDTO;

/**
 * @author JeongHyunseung
 *
 */
public class EmailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setRecipientAddress("hs.jeong@rnadatasystem.com");
		emailDTO.setSubject("창기!");
		emailDTO.setBodyPlainText("쨔사!");
		
		try {
			EmailUtil.sendEmail(emailDTO);
		} catch (Exception e) {}

	}

}
