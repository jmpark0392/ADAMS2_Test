package com.rds.adams.web.opn.srv.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.rds.adams.web.opn.srv.dto.OPNSRV003M0P0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV003M0R0DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV003M0P1DTO;
import com.rds.adams.web.opn.srv.dto.OPNSRV003M0R1DTO;

@Mapper
public interface OPNSRV003M0DAO {
    /**
	 * 고객사 목록 조회를 처리한다
	 * @param vo OPNSRV003M0P0DTO
	 * @return List<OPNSRV003M0R0DTO>
	 * @exception Exception
	 */
	
    public List<OPNSRV003M0R0DTO> getChartInfos(OPNSRV003M0P0DTO inVo);
	public List<OPNSRV003M0R1DTO> getGridData(OPNSRV003M0P1DTO inVo);
}
