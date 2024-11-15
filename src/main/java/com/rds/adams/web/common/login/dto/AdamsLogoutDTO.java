package com.rds.adams.web.common.login.dto;

import java.io.Serializable;

import com.rds.rsf.core.dto.RsfLoginDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 9. 05.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-09-05 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "사용자 정보 DTO")
@Getter
@Setter
public class AdamsLogoutDTO extends RsfLoginDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;

    @Schema(description = "요청만을 위한 비어있는 값")
    private String emptyValue;

    @Override
    public String getUsrId() {
    	return super.usrId;
    }
    
    @Override
    public void setUsrId(String usrId) {
    	super.usrId = usrId;
    }
    
}
