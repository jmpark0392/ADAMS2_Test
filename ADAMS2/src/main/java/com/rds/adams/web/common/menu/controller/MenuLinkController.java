/**
 * 
 */
package com.rds.adams.web.common.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rds.rsf.core.interceptor.CSRFTokenManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JeongHyunseung
 *
 */
@Slf4j
@Controller
public class MenuLinkController {
	
	private static String CSRF_PARAMETER_NAME = "CSRF_TOKEN";
	private static String CSRF_HEADER_NAME = "X-CSRF-Token";
	
	private void csrfEnroll(Model model, String csrfToken) {
		if (csrfToken != null || model != null) {
			model.addAttribute("csrfToken", csrfToken);
			model.addAttribute("csrfParameterName", CSRF_PARAMETER_NAME);
			model.addAttribute("csrfHeaderName", CSRF_HEADER_NAME);
		}
	}
	
	private String csrfGenerate(HttpSession session) {
		return CSRFTokenManager.getTokenForSession(session, true);
	}
	
	// 새로운 메소드: GetMapping("/") 경로로 접근 시 로그인 페이지로 CSRF 토큰 전달
	@GetMapping("/")
	public String showLoginPage(HttpServletRequest request, Model model) {
		// CSRF 토큰을 세션에서 가져오거나 없으면 생성
		HttpSession session = request.getSession(true);
		String _token = csrfGenerate(session);
		csrfEnroll(model, _token);
		
		// login.html로 이동
		return "login";
	}
	
	@GetMapping("/adminLogin")
	public String showAdminLoginPage(HttpServletRequest request, Model model) {
		// CSRF 토큰을 세션에서 가져오거나 없으면 생성
		HttpSession session = request.getSession(true);
		String _token = csrfGenerate(session);
		csrfEnroll(model, _token);
		
		// login.html로 이동
		return "adminLogin";
	}
	
	
	@GetMapping("/TokenRefresh")
	@ResponseBody
	public String refreshCsrfToken(HttpServletRequest request, Model model) {
		
		// 새로운 세션 생성
		HttpSession newSession = request.getSession(true);  // 새로운 세션을 강제로 생성
		// 새로운 CSRF 토큰을 발급하여 세션에 추가
		String newCsrfToken = csrfGenerate(newSession);
		csrfEnroll(model, newCsrfToken);
		
		// 새로 발급된 CSRF 토큰을 클라이언트로 반환
		return newCsrfToken;
	}
	
	@PostMapping("/menuLink")
	public String goMenuPage(@RequestBody String pageName
						   , HttpServletRequest request
						   , Model model
			) {
		
		log.debug("##################### pageName : ["+pageName.split("=")[1]+"] #####################");
		
		String newPageName = pageName.split("&")[0].split("=")[1];
		HttpSession session = request.getSession(true); 
		String token = CSRFTokenManager.getTokenForSession(session, false);
		csrfEnroll(model, token);
		
		if ( "myPage".equals(newPageName) ) {
			return "views/user/myPage";
		}
		if ( "pwChange".equals(newPageName) ) {
			return "views/user/newPassword";
		}
		if ( "newReq".equals(newPageName) ) {
			return "views/user/newCompany";
		}
		if ( "pwReset".equals(newPageName) ) {
			return "views/user/passwordReset";
		}
		if ( "login".equals(newPageName) ) {
			return "login";
		}
		
		return newPageName.replaceAll("%2F", "/");
		
	}
	
	@PostMapping("/FailAuthentic")
	public ModelAndView failAuthentic() {
		
		ModelAndView modelAndView = new ModelAndView("error/error_auth");
		modelAndView.addObject("errorTitle", "Session Expired!");
		modelAndView.addObject("errorMessage", "We're sorry, but your session has expired. Please log in again to continue.");
		
		return modelAndView;
		
	}
	
	@PostMapping("/FailCsrfCertificattion")
	public ModelAndView failCsrfCertificattion() {
		
		ModelAndView modelAndView = new ModelAndView("error/error_auth");
		modelAndView.addObject("errorTitle", "Fail CSRF Certification!!");
		modelAndView.addObject("errorMessage", "CSRF authentication failed. Please contact the administrator.");
		
		return modelAndView;
		
	}

}