/**
 * 
 */
package com.rds.adams.web.common.menu.controller;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rds.rsf.core.common.RsfMessageSource;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
/**
 * @author JeongHyunseung
 *
 */
@Slf4j
@Controller
public class MenuLinkController {
	
	@Autowired
	RsfMessageSource rsfMessageSource;
	
	private void csrfEnroll(Model model, CsrfToken csrfToken) {
		if (csrfToken != null || model != null) {
			model.addAttribute("csrfParameterName", csrfToken.getParameterName());
			model.addAttribute("csrfHeaderName", csrfToken.getHeaderName());
		}
	}
	
	// 새로운 메소드: GetMapping("/") 경로로 접근 시 로그인 페이지로 CSRF 토큰 전달
	@GetMapping("/")
	public String showLoginPage(HttpServletRequest request, Model model) {
		
		// login.html로 이동
		return "login";
	}
	
	@GetMapping("/adminLogin")
	public String showAdminLoginPage(HttpServletRequest request, Model model) {
		
		// login.html로 이동
		return "adminLogin";
	}
	
	
	
	@GetMapping("/TokenRefresh")
	@ResponseBody
	public Map<String, String> refreshCsrfToken(HttpServletRequest request, Model model) {
		
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		csrfEnroll(model, csrfToken);
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("token", csrfToken.getToken());
		
		return result;
	}
	
	
	@PostMapping("/menuLink")
	public String goMenuPage(@RequestBody String pageName
						   , HttpServletRequest request
						   , Model model
			) {
		
		log.debug("##################### pageName : ["+pageName.split("=")[1]+"] #####################");
		
		String newPageName = pageName.split("&")[0].split("=")[1];
		
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
		
		ModelAndView modelAndView = new ModelAndView("error/error_cmm");
		modelAndView.addObject("errorTitle", rsfMessageSource.getMessage("authentic.fail.session.expire.title", Locale.ENGLISH));
		modelAndView.addObject("errorMessage", rsfMessageSource.getMessage("authentic.fail.session.expire.message", Locale.ENGLISH));
		
		return modelAndView;
		
	}
	
	@PostMapping("/FailCsrfCertificattion")
	public ModelAndView failCsrfCertificattion() {
		
		ModelAndView modelAndView = new ModelAndView("error/error_cmm");
		modelAndView.addObject("errorTitle", "Fail CSRF Certification!!");
		modelAndView.addObject("errorMessage", "CSRF authentication failed. Please contact the administrator.");
		
		return modelAndView;
		
	}
}