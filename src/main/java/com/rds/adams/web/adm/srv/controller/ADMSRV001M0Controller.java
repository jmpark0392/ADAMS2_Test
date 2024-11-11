package com.rds.adams.web.adm.srv.controller;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.adm.srv.dto.ADMSRV001M0P0DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0P1DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0P2DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0R0DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV001M0R3DTO;
import com.rds.adams.web.adm.srv.service.ADMSRV001M0Service;
import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ADMSRV001M0Controller {

    @Autowired
    private ADMSRV001M0Service admSrv001M0Service;

    /**
     * Retrieves a list of subscription options available to customers
     * @param inVo
     * @return Map containing the options and the subscription info
     */
     @RequestMapping(value="/ADMSRV001SelectSubcriptionList", method=RequestMethod.GET) 
     public ResponseEntity<Map<String, Object>>loadSubcriptionPage(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            // get the user information from session
            AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
            if(sAdamsLoginDTO == null){
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("message", "User not logged in");
                return ResponseEntity.status(401).body(errorResponse);
            }

          
            String csNo = sAdamsLoginDTO.getCsNo();

            // fetch the all options;
            List<ADMSRV001M0P2DTO> options = admSrv001M0Service.selectAllOptions();

            // fetch the user's content subscription info
            ADMSRV001M0P0DTO inVo = new ADMSRV001M0P0DTO();
            inVo.setCsNo(csNo);
            List<ADMSRV001M0R0DTO> subscriptionInfo = admSrv001M0Service.selectOptionInfoByCustomer(inVo);

            response.put("options", options);
            response.put("subscriptionInfo", subscriptionInfo);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "User not logged in");
            return ResponseEntity.status(401).body(errorResponse);
        }
     }

     /**
     * Retrieves a all the options
     * @return list of the options 
     */
    @RequestMapping(value="/ADMSRV001M0SelectAllOptions", method=RequestMethod.POST) 
    public ResponseEntity <List<ADMSRV001M0P2DTO>> selectAllOptions() {
        try {
            List<ADMSRV001M0P2DTO> result = admSrv001M0Service.selectAllOptions();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Collections.emptyList());
        }
    }

    /**
     *  Inserts a new service option or updates an existing one for a customer.
     * @param inVo
     * @return  
     */
     @PostMapping(
         value="/ADMSRV001M0MergeServiceOption", 
         consumes = "application/json",  
         produces = "application/json"
    )
     public Map<String, Object> mergeServiceOptions(@RequestBody ADMSRV001M0P1DTO inVo, HttpServletRequest request,  HttpServletResponse response){
        Map<String, Object> result = new HashMap<>();
        try {
            // Log the session ID and attributes
            HttpSession session = request.getSession(false);
            if (session != null) {
                log.debug("Session ID: {}", session.getId());
                Enumeration<String> attributeNames = session.getAttributeNames();
                while (attributeNames.hasMoreElements()) {
                    String attributeName = attributeNames.nextElement();
                    Object attributeValue = session.getAttribute(attributeName);
                    log.debug("Session attribute [{}]: {}", attributeName, attributeValue);
                }
            } else {
                log.warn("No session found.");
            }
  

            // get the user info from the session 
            AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
            if(sAdamsLoginDTO == null){
                // Map<String, Object> errorResponse = new HashMap<>();
                result.put("message", "User not logged in");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return result;
            }

            String csNo = sAdamsLoginDTO.getCsNo();
            String userId = sAdamsLoginDTO.getUsrId();
        
            // setting the customer number and user id
            inVo.setCsNo(csNo);
            inVo.setFnlUpdEmpNo(userId);
            inVo.setFrstRegEmpNo(userId);

            // inVo.setSrvcCd(srvcCd);

            if(inVo.getSrvcCd() == null){
                log.warn("Service code is null");
                //result.put("status", "error");
                //result.put("message", "Service code is required");
    			result.put("resultCode"   , "300");
    			result.put("resultMessage", "Service code is required");
                //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return result;
            }
            
            String sChkCd = admSrv001M0Service.selectUsrCntChk(inVo);
            
            if ( "1".equals(sChkCd) ) {
                log.warn("Excess Users");
                //result.put("status", "error");
                //result.put("message", "Excess Users");
    			result.put("resultCode"   , "300");
    			result.put("resultMessage", "Excess Users");
                //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return result;
            }
            
            // udpate the service info table with the service subcription code
            admSrv001M0Service.mergeServiceOption(inVo);

            // update the option history if needed
            if(inVo.getOptCd() != null && inVo.getOptDtlsCd() != null) {
                admSrv001M0Service.updateCustomerOptionHistory(inVo);
            }

			result.put("resultCode"   , "200");
			result.put("resultMessage", "Success !!!");
            log.debug("Service option merged successfully: {}", result);
            return result;

        } catch (Exception e) {
            log.error("Error merging service option", e);
            //result.put("status", "error");
            //result.put("message", e.getMessage());
			result.put("resultCode"   , "300");
			result.put("resultMessage", e.getMessage());
            //response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log.debug("Service option merge failed: {}", result);
            return result;
        }
     }
     /**
      * Wil update the option selection to the user that is subcribed.
      * @param inVo
      * @return Returns a map that contains 
      */
      @PostMapping(
        value = "/ADMSRV001M0UpdateOptionDetails",
        consumes = "application/json",
        produces = "application/json"
      )
      public Map<String, Object> mergeOptionDetails(@RequestBody ADMSRV001M0P1DTO inVo, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        try {
            AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
            if(sAdamsLoginDTO == null){
                result.put("message", "User not logged in");
                // result.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return result;
            }

            String csNo = sAdamsLoginDTO.getCsNo();
            String userId = sAdamsLoginDTO.getUsrId();

            inVo.setCsNo(csNo);
            inVo.setFnlUpdEmpNo(userId);
            inVo.setFrstRegEmpNo(userId);

            if (inVo.getOptCd() == null || inVo.getOptDtlsCd() == null) {
                //result.put("status", "error");
                //result.put("message", "Option Code and Option Details Code are required.");
    			result.put("resultCode"   , "300");
    			result.put("resultMessage", "Option Code and Option Details Code are required.");
                //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return result;
            }
            
            String sChkCd = admSrv001M0Service.selectUsrCntChk(inVo);
            
            if ( "1".equals(sChkCd) ) {
                log.warn("Excess Users");
                //result.put("status", "error");
                //result.put("message", "Excess Users");
    			result.put("resultCode"   , "300");
    			result.put("resultMessage", "Excess Users.");
                //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return result;
            }
            
            // update the option history first  
            admSrv001M0Service.updateCustomerOptionHistory(inVo);

            // then update the service option table
            admSrv001M0Service.mergeOptionDetails(inVo);

            //result.put("status","success");
			result.put("resultCode"   , "200");
			result.put("resultMessage", "Success !!!");
            log.debug("Service Option Adding sunccessfully done: {}", result);
            return result;
        }
        catch(Exception e) {
            log.error("Error merging the service option");
            //result.put("status", "error");
            //result.put("message", e.getMessage());
			result.put("resultCode"   , "300");
			result.put("resultMessage", e.getMessage());
            return result;
        }
      }

     /**
      *  Updates the service history for a customer, effectively recording changes over time.
      * @param inVo
      * @return 
      */
      @RequestMapping(value="/ADMSRV001M0UpdateCustomerServiceHistory", method=RequestMethod.POST, consumes = "application/json")
      public void updateCustomerServiceHistory(@RequestBody ADMSRV001M0P1DTO inVo){
        log.info(inVo.toString());

        admSrv001M0Service.updateCustomerServiceHistory(inVo);
      }

      /**
       * Updates the option history for a customer, tracking changes to their options
       * @param inVo
       * @return 
       */
      @PostMapping(value="/ADMSRV001M0UpdateCustomerOptionHistory", consumes = "application/json")
      public @ResponseBody Map<String, Object> updateCustomerOptionHistory(@RequestBody ADMSRV001M0P1DTO inVo, HttpServletResponse response) {

        Map<String, Object> result = new HashMap<>();
        try {
            log.info(inVo.toString());

            admSrv001M0Service.updateCustomerOptionHistory(inVo);

            //result.put("status", "success");
			result.put("resultCode"   , "200");
			result.put("resultMessage", "Success !!!");
            return result;
        } catch (Exception e) {
            log.error("Error updating customer option history", e);
            //result.put("status", "error");
            //result.put("message", e.getMessage());
			result.put("resultCode"   , "300");
			result.put("resultMessage", e.getMessage());
            //response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return result;
        }
      }

  /**
   * Fetches the user's current suncsription info.
   * @param request HttpServletRequest to get session information.
   * @return User's subscription info.
   */

@RequestMapping(value="/getUserSubscriptionInfo", method=RequestMethod.POST)
public ResponseEntity<List<ADMSRV001M0R0DTO>> getUserSubscriptionInfo(HttpServletRequest request) {
    try {
        // get the user info from the session
        AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
        if(sAdamsLoginDTO == null){
            log.warn("User not logged in, returning 401");
            return ResponseEntity.status(401).build();
        }
    
        String csNo = sAdamsLoginDTO.getCsNo();
        log.debug("Retrieved csNo from session: {}", csNo);

        ADMSRV001M0P0DTO inVo = new ADMSRV001M0P0DTO();
        inVo.setCsNo(csNo);
        // 
        List<ADMSRV001M0R0DTO> subscriptionInfo = admSrv001M0Service.selectOptionInfoByCustomer(inVo);
        return ResponseEntity.ok(subscriptionInfo);

    } catch (Exception e) {
        e.getStackTrace();
        log.error("Error in getUserSubscriptionInfo", e);
        return ResponseEntity.status(500).build();
    }
        // return ResponseEntity.status(500).build();
    }    
      
   /**
   * Fetches the user's current subscription info list
   * @param inVo User's subscription info.
   * @return User's subscription info list.
   */
    @RequestMapping(value="/selectSubscriptionList", method=RequestMethod.POST) 
    public ResponseEntity<List<ADMSRV001M0R0DTO>> selectSubscriptionList(HttpServletRequest request) {
        try {
            // get the user info from the session
            AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
            if(sAdamsLoginDTO == null){
                log.warn("User not logged in, returning 401");
                return ResponseEntity.status(401).build();
            }
        
            String csNo = sAdamsLoginDTO.getCsNo();
            log.debug("Retrieved csNo from session: {}", csNo);

            ADMSRV001M0P0DTO inVo = new ADMSRV001M0P0DTO();
            inVo.setCsNo(csNo);
            // 
            List<ADMSRV001M0R0DTO> subscriptionInfo = admSrv001M0Service.selectOptionInfoByCustomer(inVo);
            return ResponseEntity.ok(subscriptionInfo);

        } catch (Exception e) {
            e.getStackTrace();
            log.error("Error in getUserSubscriptionInfo", e);
            return ResponseEntity.status(500).build();
        }
            // return ResponseEntity.status(500).build();
        }
    /**
     * Search for prices for the services you will use
     * @param inVo
     * @return Map containing the options and the subscription info
     */
     @RequestMapping(value="/ADMSRV001M0SelectSrvcUprcInfo", method=RequestMethod.POST) 
     public ResponseEntity<Map<String, Object>>selectSrvcUprcInfo(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            // get the user information from session
            AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
            if(sAdamsLoginDTO == null){
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("message", "User not logged in");
                return ResponseEntity.status(401).body(errorResponse);
            }

    
            String csNo = sAdamsLoginDTO.getCsNo();

            // fetch the user's content subscription info
            ADMSRV001M0P0DTO inVo = new ADMSRV001M0P0DTO();
            inVo.setCsNo(csNo);
            
            List<ADMSRV001M0R3DTO> srvcUprcInfo = admSrv001M0Service.selectSrvcUprcInfo(inVo);

            response.put("data", srvcUprcInfo);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "User not logged in");
            return ResponseEntity.status(401).body(errorResponse);
        }
     }
    
}

