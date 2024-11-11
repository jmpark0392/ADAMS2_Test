package com.rds.adams.web.adm.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rds.adams.web.adm.srv.dto.ADMSRV002M0P0DTO;
import com.rds.adams.web.adm.srv.dto.ADMSRV002M0R0DTO;
import com.rds.adams.web.adm.srv.service.ADMSRV002M0Service;
import com.rds.adams.web.common.login.dto.AdamsLoginDTO;
import com.rds.rsf.core.constant.RsfConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ADMSRV002M0Controller {
    
    @Autowired
    private ADMSRV002M0Service admSrv002M0Service;

     /**
      *  fetchUserUsageDataAndCost method will return the user usage data and cost by receiving the month date and yeaer
      * @param inputDto
      * @return 
     */

     @RequestMapping(value="/ADMSRV002M0GetUserDataAndCost", method=RequestMethod.POST)
     public ResponseEntity<List<ADMSRV002M0R0DTO>> fetchUserUsageDataAndCost(
          @RequestParam("year") String year
        , @RequestParam("month") String month
        , HttpServletRequest request
     ){
        try {
            //validate and format the year and month into  yyyy-MM format
            String yearMonth = year + (month.length() == 1 ? "0" + month : month);

            // get the user information from session
            AdamsLoginDTO sAdamsLoginDTO = (AdamsLoginDTO) request.getSession().getAttribute(RsfConstant.SESSION_LOGIN_INFO);
            
            // create a DTO to hold the input values
            ADMSRV002M0P0DTO inputDto = new ADMSRV002M0P0DTO();
            inputDto.setYearMonth(yearMonth);
            inputDto.setCsNo(sAdamsLoginDTO.getCsNo());

            // call the service to fetch the user usage data and cost
            List<ADMSRV002M0R0DTO> userUsageDataAndCost = admSrv002M0Service.fetchUserUsageDataAndCost(inputDto);
            return ResponseEntity.ok(userUsageDataAndCost);

        } catch (Exception e) {
            e.getStackTrace();
            return ResponseEntity.status(500).build();
        }
     }
}
