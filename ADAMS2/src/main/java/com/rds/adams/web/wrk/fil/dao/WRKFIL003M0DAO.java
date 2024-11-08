package com.rds.adams.web.wrk.fil.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.rds.adams.web.wrk.fil.dto.WRKFIL003M0P0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL003M0P1DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL003M0R0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL003M0R1DTO;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 6. 11.
 * @author : LEE CHANGGI
 * E-MAIL  : cg.lee@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-06-11 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */

@Mapper
public interface WRKFIL003M0DAO {
	
	public List<WRKFIL003M0R0DTO> selectTableInfoList(WRKFIL003M0P0DTO inVo);
		
	public List<Map<String, String>> selectList(Map<String, String> inVo);
	
	public void insertDataList(@Param("sQuery") String sQuery, @Param("sAddQuery") String sAddQuery, @Param("listMap") List<Map<String, Object>> inVo);

	public void deleteList(@Param("dQuery") String dBaseQuery);

	public List<WRKFIL003M0R1DTO> selectFileList(WRKFIL003M0P0DTO inVo);

	public int selectDbObjCnt(@Param("sDbId") String sDbId, @Param("sTblNm") String sTblNm);
	
	public int selectTblCnt(@Param("sQuery") String sQuery);
	
	public void insertUplHist(WRKFIL003M0P1DTO inVo);
	
	public void updateUplHist(WRKFIL003M0P1DTO inVo);

}