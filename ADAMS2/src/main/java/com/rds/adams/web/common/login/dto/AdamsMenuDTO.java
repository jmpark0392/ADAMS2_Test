package com.rds.adams.web.common.login.dto;

import java.io.Serializable;
import java.util.List;

import com.rds.rsf.core.dto.RsfMenuDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 9. 09.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-09-09 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Schema(description = "메뉴 정보 DTO")
@Getter
@Setter
public class AdamsMenuDTO extends RsfMenuDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;

    @Schema(description = "메뉴명_한글")
    private String menuNmKor;

    @Schema(description = "메뉴정렬순서")
    private String menuSrtOrd;

    @Schema(description = "메뉴설명")
    private String menuDesc;

    @Schema(description = "LEVEL")
    private String level;

    @Schema(description = "정렬")
    private String sort;	

    @Schema(description = "메뉴정보리스트")
    private List<AdamsMenuDTO> adamsMenuDTOList;
	
}
