package com.rds.adams.web.common.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.common.jwt.AdamsJwtTokenUtil;
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
import com.rds.adams.web.common.login.dto.AdamsLogoutDTO;
import com.rds.adams.web.common.login.dto.AdamsMenuDTO;
import com.rds.adams.web.common.login.dto.AdamsMonthBatCntDTO;
import com.rds.adams.web.common.login.dto.AdamsMonthLoginCntDTO;
import com.rds.adams.web.common.login.dto.AdamsMonthUploadCntDTO;
import com.rds.adams.web.common.login.dto.AdamsMypageUsrIdDTO;
import com.rds.adams.web.common.login.dto.AdamsNewCsDTO;
import com.rds.adams.web.common.login.dto.AdamsRegDtmTotalDTO;
import com.rds.adams.web.common.login.dto.AdamsResultDTO;
import com.rds.adams.web.common.login.dto.AdamsUpdateAccountDTO;
import com.rds.adams.web.common.login.dto.AdamsUpdateLoginDTO;
import com.rds.adams.web.common.login.dto.AdamsUpdatePwDTO;
import com.rds.adams.web.common.login.dto.AdamsUploadCntTotalDTO;
import com.rds.adams.web.common.login.service.AdamsLoginService;
import com.rds.adams.web.core.utils.StringUtil;
import com.rds.rsf.core.common.ResponseCode;
import com.rds.rsf.core.constant.RsfConstant;
import com.rds.rsf.core.util.RsfPropertiesUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


/**
 * 일반 로그인을 처리하는 컨트롤러 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일      수정자      수정내용
 *  -------            --------        ---------------------------
 *  2009.03.06  박지욱     최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *  
 *
 *  </pre>
 */
@Slf4j
@RestController
@Tag(name="AdamsLoginController",description = "로그인 관련")
public class AdamsLoginController {

	/** EgovLoginService */
	@Resource(name = "adamsLoginService")
	private AdamsLoginService adamsLoginService;

	/** JWT */
	@Autowired
    private AdamsJwtTokenUtil adamsJwtTokenUtil;
	
	private CsrfTokenRepository csrfTokenRepository;

	/**
	 * 일반 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 로그인결과(세션정보)
	 * @exception Exception
	 */

	@Operation(
			summary = "일반 로그인",
			description = "일반 로그인 처리",
			tags = {"AdamsLoginController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "로그인 성공"),
			@ApiResponse(responseCode = "300", description = "로그인 실패")
	})
	@RequestMapping(value = "/auth/adamsLogin", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.TEXT_HTML_VALUE})
	public HashMap<String, Object> selectLoginInfo(@RequestBody AdamsLoginDTO adamsLoginDTO, HttpServletRequest request) throws Exception {
	    HashMap<String, Object> resultMap = processLogin(adamsLoginDTO, request, false);
	    String nextPage = ""; 
	    		
	    // 로그인 성공 시 페이지 이동 정보 추가
	    if ("200".equals(resultMap.get("resultCode"))) {
	        
	    	AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
	    	
	    	// 로그인 성공시 로그린 이력 저장
	    	adamsLoginService.insertLoginHist(sAdamsLoginDTO, request);
	    	
	    	nextPage = "myPage"; // 로그인 후 이동할 기본 페이지 
	    	
	    	if ( "Y".equals(sAdamsLoginDTO.getPasswordInitYn()) ) {
	    		// 비밀번호 초기화 된 경우
	    		nextPage = "pwChange";
		        resultMap.put("loginMsg", "Your password has been reset. You will be taken to the reset screen.");
	    	} else if ( "9".equals(sAdamsLoginDTO.getStatDvCd()) ) {
	    		// 사용자의 상태가 종료인 경우 로그인 페이지로
	    		nextPage = "login";
		        resultMap.put("loginMsg", "This user is not available.");
	    	}
	    	
	        resultMap.put("redirectUrl", nextPage);
	    } else {
	    	if ("400".equals(resultMap.get("resultCode"))) {
	    		// "구독이 만료되었습니다."
		        resultMap.put("loginMsg", "Your subscription has expired.");
	    	} else {
		        resultMap.put("loginMsg", "Please check your ID or password.");
	    	}
	        resultMap.put("redirectUrl", "login");
	    }
	    
	    return resultMap;
	}
	
	@RequestMapping(value = "/auth/adminLogin", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public HashMap<String, Object> selectAdminLoginInfo(@RequestBody AdamsLoginDTO adamsLoginDTO, HttpServletRequest request) throws Exception {
		
		// ADMIN LOGIN의 경우 CS_NO를 '999' 
		adamsLoginDTO.setCsNo(RsfPropertiesUtil.getProperty("Globals.sysadm.csno"));
		
	    HashMap<String, Object> resultMap = processLogin(adamsLoginDTO, request, false);
	    String nextPage = ""; 
	    		
	    // 로그인 성공 시 페이지 이동 정보 추가
	    if ("200".equals(resultMap.get("resultCode"))) {
	        
	    	AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
	    	
	    	// 로그인 성공시 로그린 이력 저장
	    	adamsLoginService.insertLoginHist(sAdamsLoginDTO, request);
	    	
	    	nextPage = "myPage"; // 로그인 후 이동할 기본 페이지 
	    	
	    	if ( "Y".equals(sAdamsLoginDTO.getPasswordInitYn()) ) {
	    		// 비밀번호 초기화 된 경우
	    		nextPage = "pwChange";
		        resultMap.put("loginMsg", "Your password has been reset. You will be taken to the reset screen.");
	    	} else if ( "9".equals(sAdamsLoginDTO.getStatDvCd()) ) {
	    		// 사용자의 상태가 종료인 경우 로그인 페이지로
	    		nextPage = "login";
		        resultMap.put("loginMsg", "This user is not available.");
	    	}
	    	
	        resultMap.put("redirectUrl", nextPage);
	    } else {
	    	if ("400".equals(resultMap.get("resultCode"))) {
	    		// "구독이 만료되었습니다."
		        resultMap.put("loginMsg", "Your subscription has expired.");
	    	} else {
		        resultMap.put("loginMsg", "Please check your ID or password.");
	    	}
	        resultMap.put("redirectUrl", "login");
	    }
	    
	    return resultMap;
	}
	
	private HashMap<String, Object> processLogin(AdamsLoginDTO adamsLoginDTO, HttpServletRequest request, boolean isJwtLogin) throws Exception {
		HashMap<String,Object>  resultMap         = new HashMap<String,Object>();
		List<AdamsMenuDTO>      adamsMenuDTOs     = new ArrayList<>();
		List<AdamsMenuDTO>      adamsMenuTreeDTOs = new ArrayList<>();
		String                  sChkCd            = "";

		// 1. 일반 로그인 처리
		AdamsLoginDTO adamsLoginResultDTO = adamsLoginService.selectLoginInfo(adamsLoginDTO);

		if (adamsLoginResultDTO != null && !StringUtil.isEmpty(adamsLoginResultDTO.getCsNo()) && !StringUtil.isEmpty(adamsLoginResultDTO.getUsrId())) {
			
			sChkCd = adamsLoginService.checkUserUse(adamsLoginResultDTO);
			
			if ( "1".equals(sChkCd) ) {
				resultMap.put("resultCode"   , "400");
				//resultMap.put("resultMessage", rsfMessageSource.getMessage("fail.common.login"));
				return resultMap;
			}
			
			/*
			 * form data의 csNo가 empty가 아니고 (어드민 로그인이면)
			 * 로그인 계정의 소속이 '999' 이면 form data의 csNo를 바탕으로 회사 정보 업데이트
			 * 로그인 계정의 소속이 '999' 가 아니면 form data의 csNo와 동일해야 정상 로그인 가능
			 */
			if (!StringUtil.isEmpty(adamsLoginDTO.getCsNo())) {
				if ("999".equals(adamsLoginResultDTO.getCsNo())) {
					AdamsCsNoDTO csDTO = adamsLoginService.getCsNmByCsNo(adamsLoginDTO.getCsNo());
					adamsLoginResultDTO.setCsNo(csDTO.getCsNo());
					adamsLoginResultDTO.setCompNm(csDTO.getCompNm());
					adamsLoginResultDTO.setCompNoDvCd(csDTO.getCompNoDvCd());
				} else {
					if (!adamsLoginResultDTO.getCsNo().equals(adamsLoginDTO.getCsNo())) {
						resultMap.put("resultCode"   , "300");
						//resultMap.put("resultMessage", rsfMessageSource.getMessage("fail.common.login"));
						return resultMap;
					}
				}
			}

			//log.debug("===>>> adamsLoginDTO.getCsNo()  = "+adamsLoginDTO.getCsNo());
			log.debug("===>>> adamsLoginDTO.getUsrId() = "+adamsLoginDTO.getUsrId());
			//log.debug("===>>> loginVO.getPassword() = "+loginVO.getPassword());
			
			String jwtToken = adamsJwtTokenUtil.generateToken(adamsLoginResultDTO);
			
			String username = adamsJwtTokenUtil.getUserSeFromToken(jwtToken);
	    	log.debug("Dec jwtToken username = "+username);
	    	
	    	adamsMenuDTOs     = adamsLoginService.selectMenuList(adamsLoginResultDTO);
	    	adamsMenuTreeDTOs = adamsLoginService.selectMenuTreeList(adamsMenuDTOs);
	    	 
	    	//서버사이드 권한 체크 통과를 위해 삽입
	    	//EgovUserDetailsHelper.isAuthenticated() 가 그 역할 수행. DB에 정보가 없으면 403을 돌려 줌. 로그인으로 튕기는 건 프론트 쪽에서 처리
	    	request.getSession().setAttribute(RsfConstant.SESSION_LOGIN_INFO		, adamsLoginResultDTO);
	    	request.getSession().setAttribute(RsfConstant.SESSION_MENU_FLATLIST	, adamsMenuDTOs);
	    	request.getSession().setAttribute(RsfConstant.SESSION_MENU_TREELIST	, adamsMenuTreeDTOs);
	    	//request.getSession().setAttribute("LoginVO", loginResultVO);
	    	
			//resultMap.put("resultVO"        , adamsLoginResultDTO);
			//resultMap.put("resultMenuVO"    , adamsMenuDTOs);
			//resultMap.put("resultMenuTreeVO", adamsMenuTreeDTOs);
			if (isJwtLogin) {
	            //resultMap.put("jToken", jwtToken);
	        }
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "성공 !!!");
		} else {
			//resultMap.put("resultVO"        , adamsLoginResultDTO);
			//resultMap.put("resultMenuVO"    , adamsMenuDTOs);
			//resultMap.put("resultMenuTreeVO", adamsMenuTreeDTOs);
			resultMap.put("resultCode"   , "300");
			//resultMap.put("resultMessage", rsfMessageSource.getMessage("fail.common.login"));
		}

		return resultMap;

	}

	@Operation(
			summary = "JWT 로그인",
			description = "JWT 로그인 처리",
			tags = {"AdamsLoginController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "로그인 성공"),
			@ApiResponse(responseCode = "300", description = "로그인 실패")
	})
	@PostMapping(value = "/auth/adamsLogin-jwt")
	public HashMap<String, Object> selectLoginJWTInfo(@RequestBody AdamsLoginDTO adamsLoginDTO, HttpServletRequest request, ModelMap model) throws Exception {
		HashMap<String, Object> resultMap = processLogin(adamsLoginDTO, request, true);
		String nextPage = ""; 
		
	    // 로그인 성공 시 페이지 이동 정보 추가
	    if ("200".equals(resultMap.get("resultCode"))) {

	    	AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
	    	
	    	// 로그인 성공시 로그린 이력 저장
	    	adamsLoginService.insertLoginHist(sAdamsLoginDTO, request);
	    	
	    	nextPage = "myPage"; // 로그인 후 이동할 기본 페이지 
	    	
	    	if ( "Y".equals(sAdamsLoginDTO.getPasswordInitYn()) ) {
	    		// 비밀번호 초기화 된 경우
	    		nextPage = "pwChange";
		        resultMap.put("loginMsg", "You just logged in with a temporary password, so you need to set up a new one.");
	    	} else if ( "9".equals(sAdamsLoginDTO.getStatDvCd()) ) {
	    		// 사용자의 상태가 종료인 경우 로그인 페이지로
	    		nextPage = "login";
		        resultMap.put("loginMsg", "This user is not available.");
	    	}
	    	
	        resultMap.put("redirectUrl", nextPage);
	    }
	    
	    return resultMap;
	}

	/**
	 * 로그아웃한다.
	 * @return resultVO
	 * @exception Exception
	 */
	@Operation(
			summary = "로그아웃",
			description = "로그아웃 처리(JWT,일반 관계 없이)",
			security = {@SecurityRequirement(name = "Authorization")},
			tags = {"AdamsLoginController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "로그아웃 성공"),
	})
	@RequestMapping(value = "/auth/adamsLogout", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE})
	public AdamsResultDTO actionLogoutJSON(@RequestBody AdamsLogoutDTO adamsLogoutDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdamsResultDTO adamsResultDTO = new AdamsResultDTO();

		new SecurityContextLogoutHandler().logout(request, response, null);

		adamsResultDTO.setResultCode(ResponseCode.SUCCESS.getCode());
		adamsResultDTO.setResultMessage(ResponseCode.SUCCESS.getMessage());
		
		return adamsResultDTO;
	}

	/**
	 * 고객 목록을 조회한다
	 * @param vo - 빈 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 고객 목록
	 * @exception Exception
	 */
	@Operation(
			summary = "고객 목록",
			description = "고객 목록 조회",
			tags = {"AdamsLoginController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "고객 조회 성공"),
			@ApiResponse(responseCode = "300", description = "고객 조회 실패")
	})
	@RequestMapping(value = "/auth/adamsLoginCs", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.TEXT_HTML_VALUE})
	public HashMap<String, Object> selectCsNoList(@RequestBody AdamsLoginDTO adamsLoginDTO, HttpServletRequest request) throws Exception {
	    HashMap<String, Object> resultMap = new HashMap<String,Object>();

		// 1. 일반 로그인 처리
	    List<AdamsCsNoDTO> adamsCsNoDTOs = adamsLoginService.selectCsNoList(adamsLoginDTO);

		if (adamsCsNoDTOs != null && adamsCsNoDTOs.get(0).getCsNo() != null && !adamsCsNoDTOs.get(0).getCsNo().equals("")) {

			resultMap.put("resultVO"    , adamsCsNoDTOs);
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "성공 !!!");
		} else {
			resultMap.put("resultVO"    , adamsCsNoDTOs);
			resultMap.put("resultCode"   , "300");
			//resultMap.put("resultMessage", rsfMessageSource.getMessage("fail.common.login"));
		}

		return resultMap;
	}

	/**
	 * 초기화된 비밀번호를 변경 한다
	 * @param vo - 빈 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 고객 목록
	 * @exception Exception
	 */
	@Operation(
			summary = "사용자 비밀번호",
			description = "사용자 비밀번호 변경",
			tags = {"AdamsLoginController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "비밀번호 변경 성공"),
			@ApiResponse(responseCode = "300", description = "비밀번호 변경 실패")
	})
	@RequestMapping(value = "/auth/adamsLoginPw", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.TEXT_HTML_VALUE})
	public HashMap<String, Object> updateUsrPassword(@RequestBody AdamsLoginDTO adamsLoginDTO, HttpServletRequest request) throws Exception {
	    HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;
	    String nextPage = ""; 
	    
	    // 1. 세션에서 사용자 정보 가져오기
	    AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);

	    if ( sAdamsLoginDTO != null && sAdamsLoginDTO.getUsrId() != null && !sAdamsLoginDTO.getUsrId().equals("")) {
		    // 2. 새 비밀번호 적용을 위한 세션정보 가져오기 
	    	adamsLoginDTO.setCsNo(sAdamsLoginDTO.getCsNo());
	    	adamsLoginDTO.setUsrId(sAdamsLoginDTO.getUsrId());
	    	adamsLoginDTO.setUsrPassword(sAdamsLoginDTO.getUsrPassword());
		    
			// 3. 비밀번호 변경 처리
		    bResult = adamsLoginService.updateUsrPassword(adamsLoginDTO);
	    }
	    
		if (bResult) {
			
			// 4. 세션정보 초기화
			HttpSession session = request.getSession(false); // 현재 세션을 가져옴, 없으면 null 반환
		    if (session != null) {
		        session.invalidate(); // 세션 무효화, 모든 세션 데이터를 제거하고 세션 종료
		        log.info("Session has been invalidated.");
		    } else {
		        log.info("No active session to clear.");
		    }
			
			// 사용자 재로그인 페이지로
    		nextPage = "login";
    		
	        resultMap.put("redirectUrl", nextPage);
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "성공 !!!");
		} else {
			resultMap.put("resultCode"   , "300");
			//resultMap.put("resultMessage", rsfMessageSource.getMessage("fail.common.login"));
		}

		return resultMap;
	}

	/**
	 * 초기화된 비밀번호를 변경 한다
	 * @param vo - 빈 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 고객 목록
	 * @exception Exception
	 */
	@Operation(
			summary = "사용자 비밀번호 찾기",
			description = "사용자 비밀번호 찾기",
			tags = {"AdamsLoginController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "비밀번호 찾기 성공"),
			@ApiResponse(responseCode = "300", description = "비밀번호 찾기 실패")
	})
	@RequestMapping(value = "/auth/adamsFindPw", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.TEXT_HTML_VALUE})
	public HashMap<String, Object> searchPassword(@RequestBody AdamsFindPwDTO adamsFindPwDTO, HttpServletRequest request) throws Exception {
	    HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;
	    String nextPage = ""; 
	    
	    // 1. 비밀번호 찾기 처리
		bResult = adamsLoginService.searchPassword(adamsFindPwDTO);
	    
		if (bResult) {
			
			// 사용자 재로그인 페이지로
    		nextPage = "login";
    		
	        resultMap.put("redirectUrl", nextPage);
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "성공 !!!");
		} else {
			resultMap.put("resultCode"   , "300");
			//resultMap.put("resultMessage", rsfMessageSource.getMessage("fail.common.login"));
		}

		return resultMap;
	}

	/**
	 * 신규 고객 신청 정보를 저장 한다
	 * @param vo - 빈 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 고객 목록
	 * @exception Exception
	 */
	@Operation(
			summary = "신규 고객 신청",
			description = "사신규 고객 신청 정보 저장",
			tags = {"AdamsLoginController"}
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "신규 고객 신청 성공"),
			@ApiResponse(responseCode = "300", description = "신규 고객 신청 실패")
	})
	@RequestMapping(value = "/auth/adamsNewCs", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.TEXT_HTML_VALUE})
	public HashMap<String, Object> insertNewCs(@RequestBody AdamsNewCsDTO adamsNewCsDTO, HttpServletRequest request) throws Exception {
	    HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;
	    String nextPage = ""; 
	    
	    // 1. 비밀번호 찾기 처리
		bResult = adamsLoginService.insertNewCs(adamsNewCsDTO);
	    
		if (bResult) {
			
			// 사용자 재로그인 페이지로
    		nextPage = "login";
    		
	        resultMap.put("redirectUrl", nextPage);
			resultMap.put("resultCode"   , "200");
			resultMap.put("resultMessage", "성공 !!!");
		} else {
			resultMap.put("resultCode"   , "300");
			//resultMap.put("resultMessage", rsfMessageSource.getMessage("fail.common.login"));
		}

		return resultMap;
	}


	
	/**
	 * 마이페이지에서 계정을 생성한 후 누적일 수를 조회한다
	 * @param 
	 * @return result - 마이페이지에서 계정을 생성한 후 누적일 수
	 * @exception Exception
	 */
	@RequestMapping(value = "/mypage/adamsRegDtmTotal", method=RequestMethod.POST, consumes="application/json")
	public AdamsRegDtmTotalDTO selectRegDtmTotal(@RequestBody AdamsMypageUsrIdDTO adamsMypageUsrIdDTO, HttpServletRequest request) throws Exception {
		
		// 1. 세션에서 사용자 정보 가져오기
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		
		adamsMypageUsrIdDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		adamsMypageUsrIdDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		
		// 2. 계정을 생성한 후 누적일 수 가져오기
		AdamsRegDtmTotalDTO adamsRegDtmTotalDTO = adamsLoginService.selectRegDtmTotal(adamsMypageUsrIdDTO);
		
		if(adamsRegDtmTotalDTO != null) {
			log.info(adamsRegDtmTotalDTO.toString());
		}else {
			log.info("adamsRegDtmTotalDTO is null");
		}
		return adamsRegDtmTotalDTO;
	}
	
	/**
	 * 마이페이지에서 오늘을 제외하고 가장 최근에 로그인 했던 날짜를 조회한다
	 * @param 
	 * @return result - 오늘을 제외하고 가장 최근에 로그인 했던 날짜
	 * @exception Exception
	 */	
	@RequestMapping(value = "/mypage/adamsLastLoginDtm", method=RequestMethod.POST, consumes="application/json")
	public AdamsLastLoginDtmDTO selectLastLoginDtm(@RequestBody AdamsMypageUsrIdDTO adamsMypageUsrIdDTO, HttpServletRequest request) throws Exception {
		
		// 1. 세션에서 사용자 정보 가져오기
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		adamsMypageUsrIdDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		adamsMypageUsrIdDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		
		// 2. 계정을 생성한 후 가장 최근에 로그인 했던 날짜 가져오기
		AdamsLastLoginDtmDTO adamsLastLoginDtmDTO = adamsLoginService.selectLastLoginDtm(adamsMypageUsrIdDTO);
		
		if(adamsLastLoginDtmDTO != null) {
			log.info(adamsLastLoginDtmDTO.toString());
		}else {
			log.info("adamsLastLoginDtmDTO is null");
		}
		return adamsLastLoginDtmDTO;
	}
	
	/**
	 * 마이페이지에서 로그인 총 횟수를 조회한다
	 * @param 
	 * @return result - 로그인 총 횟수
	 * @exception Exception
	 */	
	@RequestMapping(value = "/mypage/adamsLoginCntTotal", method=RequestMethod.POST, consumes="application/json")
	public AdamsLoginCntTotalDTO selectLoginCntTotal(@RequestBody AdamsMypageUsrIdDTO adamsMypageUsrIdDTO, HttpServletRequest request) throws Exception {
		
		// 1. 세션에서 사용자 정보 가져오기
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		adamsMypageUsrIdDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		adamsMypageUsrIdDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		
		// 2. 계정을 생성한 후 로그인 총 횟수 가져오기
		AdamsLoginCntTotalDTO adamsLoginCntTotalDTO = adamsLoginService.selectLoginCntTotal(adamsMypageUsrIdDTO);
		
		if(adamsLoginCntTotalDTO != null) {
			log.info(adamsLoginCntTotalDTO.toString());
		}else {
			log.info("adamsLoginCntTotalDTO is null");
		}
		return adamsLoginCntTotalDTO;
	}
	
	/**
	 * 마이페이지에서 (전날 기준)30일간 일별 로그인 횟수를 조회한다
	 * @param 
	 * @return result - (전날 기준)30일간 일별 로그인 횟수
	 * @exception Exception
	 */	
	@RequestMapping(value = "/mypage/adamsMonthLoginCnt", method=RequestMethod.POST, consumes="application/json")
	public List<AdamsMonthLoginCntDTO> selectMonthLoginCnt(@RequestBody AdamsMypageUsrIdDTO adamsMypageUsrIdDTO, HttpServletRequest request) throws Exception {
		
		// 1. 세션에서 사용자 정보 가져오기
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		adamsMypageUsrIdDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		adamsMypageUsrIdDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		
		// 2. 계정을 생성한 후 누적일 수 가져오기
		List<AdamsMonthLoginCntDTO> adamsMonthLoginCntDTOs = adamsLoginService.selectMonthLoginCnt(adamsMypageUsrIdDTO);
		
		if(adamsMonthLoginCntDTOs != null) {
			log.info(adamsMonthLoginCntDTOs.toString());
		}else {
			log.info("adamsMonthLoginCntDTO is null");
		}
		return adamsMonthLoginCntDTOs;
	}
	
	/**
	 * 마이페이지에서 오늘을 제외하고 가장 최근에 파일 업로드 했던 날짜를 조회한다
	 * @param 
	 * @return result - 오늘을 제외하고 가장 최근에 파일 업로드 했던 날짜
	 * @exception Exception
	 */	
	@RequestMapping(value = "/mypage/adamsLastUploadDtm", method=RequestMethod.POST, consumes="application/json")
	public AdamsLastUploadDtmDTO selectLastUploadDtm(@RequestBody AdamsMypageUsrIdDTO adamsMypageUsrIdDTO, HttpServletRequest request) throws Exception {
		
		// 1. 세션에서 사용자 정보 가져오기
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		adamsMypageUsrIdDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		adamsMypageUsrIdDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		
		// 2. 계정을 생성한 후 가장 최근에 파일 업로드 했던 날짜 가져오기
		AdamsLastUploadDtmDTO adamsLastUploadDtmDTO = adamsLoginService.selectLastUploadDtm(adamsMypageUsrIdDTO);
		
		if(adamsLastUploadDtmDTO != null) {
			log.info(adamsLastUploadDtmDTO.toString());
		}else {
			log.info("adamsLastUploadDtmDTO is null");
		}
		return adamsLastUploadDtmDTO;
	}
	
	/**
	 * 마이페이지에서 파일 업로드 총 횟수를 조회한다
	 * @param 
	 * @return result - 파일 업로드 총 횟수
	 * @exception Exception
	 */	
	@RequestMapping(value = "/mypage/adamsUploadCntTotal", method=RequestMethod.POST, consumes="application/json")
	public AdamsUploadCntTotalDTO selectUploadCntTotal(@RequestBody AdamsMypageUsrIdDTO adamsMypageUsrIdDTO, HttpServletRequest request) throws Exception {
		
		// 1. 세션에서 사용자 정보 가져오기
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		adamsMypageUsrIdDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		adamsMypageUsrIdDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		
		// 2. 계정을 생성한 후 파일 업로드 총 횟수 가져오기
		AdamsUploadCntTotalDTO adamsUploadCntTotalDTO = adamsLoginService.selectUploadCntTotal(adamsMypageUsrIdDTO);
		
		if(adamsUploadCntTotalDTO != null) {
			log.info(adamsUploadCntTotalDTO.toString());
		}else {
			log.info("adamsUploadCntTotalDTO is null");
		}
		return adamsUploadCntTotalDTO;
	}
	
	/**
	 * 마이페이지에서 (전날 기준)30일간 일별 파일 업로드 횟수를 조회한다
	 * @param 
	 * @return result - (전날 기준)30일간 일별 파일 업로드 횟수
	 * @exception Exception
	 */	
	@RequestMapping(value = "/mypage/adamsMonthUploadCnt", method=RequestMethod.POST, consumes="application/json")
	public List<AdamsMonthUploadCntDTO> selectMonthUploadCnt(@RequestBody AdamsMypageUsrIdDTO adamsMypageUsrIdDTO, HttpServletRequest request) throws Exception {
		
		// 1. 세션에서 사용자 정보 가져오기
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		adamsMypageUsrIdDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		adamsMypageUsrIdDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		
		// 2. 계정을 생성한 후 (전날 기준)30일간 일별 파일 업로드 수 가져오기
		List<AdamsMonthUploadCntDTO> adamsMonthUploadCntDTOs = adamsLoginService.selectMonthUploadCnt(adamsMypageUsrIdDTO);
		
		if(adamsMonthUploadCntDTOs != null) {
			log.info(adamsMonthUploadCntDTOs.toString());
		}else {
			log.info("adamsMonthUploadCntDTOs is null");
		}
		return adamsMonthUploadCntDTOs;
	}
	
	/**
	 * 마이페이지에서 오늘을 제외하고 가장 최근에 배치 실행 했던 날짜를 조회한다
	 * @param 
	 * @return result - 오늘을 제외하고 가장 최근에 배치실행 했던 날짜
	 * @exception Exception
	 */	
	@RequestMapping(value = "/mypage/adamsLastBatDtm", method=RequestMethod.POST, consumes="application/json")
	public AdamsLastBatDtmDTO selectLastBatDtm(@RequestBody AdamsMypageUsrIdDTO adamsMypageUsrIdDTO, HttpServletRequest request) throws Exception {
		
		// 1. 세션에서 사용자 정보 가져오기
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		adamsMypageUsrIdDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		adamsMypageUsrIdDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		
		// 2. 계정을 생성한 후 가장 최근에 배치를 실행 했던 날짜 가져오기
		AdamsLastBatDtmDTO adamsLastBatDtmDTO = adamsLoginService.selectLastBatDtm(adamsMypageUsrIdDTO);
		
		if(adamsLastBatDtmDTO != null) {
			log.info(adamsLastBatDtmDTO.toString());
		}else {
			log.info("adamsLastBatDtmDTO is null");
		}
		return adamsLastBatDtmDTO;
	}
	
	/**
	 * 마이페이지에서 배치 실행 총 횟수를 조회한다
	 * @param 
	 * @return result - 배치 실행 총 횟수
	 * @exception Exception
	 */	
	@RequestMapping(value = "/mypage/adamsBatCntTotal", method=RequestMethod.POST, consumes="application/json")
	public AdamsBatCntTotalDTO selectBatCntTotal(@RequestBody AdamsMypageUsrIdDTO adamsMypageUsrIdDTO, HttpServletRequest request) throws Exception {
		
		// 1. 세션에서 사용자 정보 가져오기
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		adamsMypageUsrIdDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		adamsMypageUsrIdDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		
		// 2. 계정을 생성한 후 배치 실행 총 횟수 가져오기
		AdamsBatCntTotalDTO adamsBatCntTotalDTO = adamsLoginService.selectBatCntTotal(adamsMypageUsrIdDTO);
		
		if(adamsBatCntTotalDTO != null) {
			log.info(adamsBatCntTotalDTO.toString());
		}else {
			log.info("adamsBatCntTotalDTO is null");
		}
		return adamsBatCntTotalDTO;
	}
	
	/**
	 * 마이페이지에서 (전날 기준)30일간 일별 배치를 실행한 횟수를 조회한다
	 * @param 
	 * @return result - (전날 기준)30일간 일별 배치를 실행한 횟수
	 * @exception Exception
	 */	
	@RequestMapping(value = "/mypage/adamsMonthBatCnt", method=RequestMethod.POST, consumes="application/json")
	public List<AdamsMonthBatCntDTO> selectMonthBatCnt(@RequestBody AdamsMypageUsrIdDTO adamsMypageUsrIdDTO, HttpServletRequest request) throws Exception {
		
		// 1. 세션에서 사용자 정보 가져오기
		AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
		adamsMypageUsrIdDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		adamsMypageUsrIdDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		
		// 2. 계정을 생성한 후 (전날 기준)30일간 일별 배치를 실행한 횟수 가져오기
		List<AdamsMonthBatCntDTO> adamsMonthBatCntDTOs = adamsLoginService.selectMonthBatCnt(adamsMypageUsrIdDTO);
		
		if(adamsMonthBatCntDTOs != null) {
			log.info(adamsMonthBatCntDTOs.toString());
		}else {
			log.info("adamsMonthBatCntDTOs is null");
		}
		return adamsMonthBatCntDTOs;
	}
	
	/**
	 * 마이페이지에서 사용자 정보를 변경 한다
	 * @param adamsUpdateAccountDTO - 변경된 사용자 정보가 담긴 AdamsUpdateAccountDTO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 고객 목록
	 * @exception Exception
	 */
	@RequestMapping(value = "/mypage/updateMyAccount", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.TEXT_HTML_VALUE})
	public HashMap<String, Object> updateAccount(@RequestBody AdamsUpdateAccountDTO adamsUpdateAccountDTO, HttpServletRequest request) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;
	    int checkResult = 0;
	    
	    // 1. 세션에서 사용자 정보 가져오기
	    AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
	    
	    try {
	    	if ( sAdamsLoginDTO != null && sAdamsLoginDTO.getUsrId() != null && !sAdamsLoginDTO.getUsrId().equals("")) {
			    // 2. 사용자 정보 수정을 세션정보 가져오기 
		    	adamsUpdateAccountDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		    	adamsUpdateAccountDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		    	
		    	// 3. 사용자 인증 - 사용자가 입력한 비밀번호가 저장된 비밀번호와 동일한지
		    	AdamsCheckUserParDTO adamsCheckUserParDTO = new AdamsCheckUserParDTO();
		    	adamsCheckUserParDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		    	adamsCheckUserParDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		    	adamsCheckUserParDTO.setUsrCurrentPassword(adamsUpdateAccountDTO.getUsrPassword());
		    	
		    	AdamsCheckUserResDTO adamsCheckUserResDTO = adamsLoginService.checkUsrPw(adamsCheckUserParDTO);
		    	checkResult = adamsCheckUserResDTO.getCheckUsrPwd(); /* 1: 사용자 인증 통과, 0: 사용자 인증 실패 */
		    			    
				// 4. 부서명, 전화번호 변경 처리
			    bResult = adamsLoginService.updateAccount(adamsUpdateAccountDTO);
			    
			    // 5. 변경된 사용자 정보 가져오기
			    AdamsUpdateLoginDTO newAdamsLoginDTO = new AdamsUpdateLoginDTO();
			    newAdamsLoginDTO.setCsNo(sAdamsLoginDTO.getCsNo());
			    newAdamsLoginDTO.setUsrId(sAdamsLoginDTO.getUsrId());
			    
			    AdamsLoginDTO newAdamsUpdateLoginDTO = adamsLoginService.actionUpdateLogin(newAdamsLoginDTO);
			    request.getSession().setAttribute(RsfConstant.SESSION_LOGIN_INFO, newAdamsUpdateLoginDTO);
		    }
		    
		    if (bResult) {
		    	log.info(adamsUpdateAccountDTO.toString());
				resultMap.put("resultCode"   , "200");
				resultMap.put("resultMessage", "Success");
			} else {
				if(checkResult == 0) {
					resultMap.put("resultCode"   , "400"); // 임의로 지정한 에러코드.
					resultMap.put("resultMessage", "Your current password does not match."); // 사용자가 현재 입력한 비밀번호가 DB와 일치하지 않습니다
				}else {
					resultMap.put("resultCode"   , "300");
					resultMap.put("resultMessage", "Sorry, Update Failed");
				}
			}
	    } catch (Exception e) {
	    	resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", "Error : " + e.getMessage());
	    }
	    
		return resultMap;
	}
	
	/**
	 * 마이페이지에서 사용자의 비밀번호를 변경 한다
	 * @param 
	 * @param 
	 * @return 
	 * @exception Exception
	 */
	@RequestMapping(value = "/mypage/changeMyPassword", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.TEXT_HTML_VALUE})
	public HashMap<String, Object> changeMyPassword(@RequestBody AdamsUpdatePwDTO adamsUpdatePwDTO, HttpServletRequest request) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
	    boolean  bResult = false;
	    int checkResult = 0;
	    
	    // 1. 세션에서 사용자 정보 가져오기
	    AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);

	    try {
	    	if ( sAdamsLoginDTO != null && sAdamsLoginDTO.getUsrId() != null && !sAdamsLoginDTO.getUsrId().equals("")) {
			    // 2. 사용자 정보 수정을 세션정보 가져오기 
		    	adamsUpdatePwDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		    	adamsUpdatePwDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		    	
		    	// 3. 사용자 인증 - 사용자가 입력한 비밀번호가 저장된 비밀번호와 동일한지
		    	AdamsCheckUserParDTO adamsCheckUserParDTO = new AdamsCheckUserParDTO();
		    	adamsCheckUserParDTO.setCsNo(sAdamsLoginDTO.getCsNo());
		    	adamsCheckUserParDTO.setUsrId(sAdamsLoginDTO.getUsrId());
		    	adamsCheckUserParDTO.setUsrCurrentPassword(adamsUpdatePwDTO.getUsrCurrentPassword());
		    	
		    	AdamsCheckUserResDTO adamsCheckUserResDTO = adamsLoginService.checkUsrPw(adamsCheckUserParDTO);
		    	checkResult = adamsCheckUserResDTO.getCheckUsrPwd(); /* 1: 사용자 인증 통과, 0: 사용자 인증 실패 */
		    			    
				// 4. 비밀번호 변경 처리
			    bResult = adamsLoginService.changeMyPassword(adamsUpdatePwDTO);
		    }
		    
		    if (bResult) {
		    	log.info(adamsUpdatePwDTO.toString());
				resultMap.put("resultCode"   , "200");
				resultMap.put("resultMessage", "Success");
			} else {
				if(checkResult == 0) {
					resultMap.put("resultCode"   , "400"); // 임의로 지정한 에러코드.
					resultMap.put("resultMessage", "Your current password does not match."); // 사용자가 현재 입력한 비밀번호가 DB와 일치하지 않습니다
				}else {
					resultMap.put("resultCode"   , "300");
					resultMap.put("resultMessage", "Sorry, Update Failed");
				}
			}
	    }catch (Exception e) {
	    	resultMap.put("resultCode"   , "300");
			resultMap.put("resultMessage", "Error : " + e.getMessage());
	    }
		
		return resultMap;
	}
	
}