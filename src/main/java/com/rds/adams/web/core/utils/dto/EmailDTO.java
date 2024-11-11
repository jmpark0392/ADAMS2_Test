/**
 * 
 */
package com.rds.adams.web.core.utils.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author JeongHyunseung
 *
 */
@ToString
@Getter
@Setter
public class EmailDTO {
	
	private String recipientAddress;
	private String subject;
	private String bodyPlainText;

}