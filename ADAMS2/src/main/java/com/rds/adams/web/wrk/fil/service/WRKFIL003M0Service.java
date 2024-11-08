package com.rds.adams.web.wrk.fil.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rds.adams.web.util.file.FileStore;
import com.rds.adams.web.util.file.dto.UploadFile;
import com.rds.adams.web.wrk.fil.dao.WRKFIL003M0DAO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL003M0P0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL003M0P1DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL003M0R0DTO;
import com.rds.adams.web.wrk.fil.dto.WRKFIL003M0R1DTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@Service
@RequiredArgsConstructor
public class WRKFIL003M0Service {
	
	@Value("${file.upload.path}")  // @Value는 Spring 꺼를 사용해야한다 , @Value를 통해 appilcation.* 에 지정한 값을 가져올 수 있다.
	private String uploadFileDir;  // 가져온 값은 여기에 저장됨.
	
	private final FileStore fileStore;
	
	@Autowired
	WRKFIL003M0DAO wRKFIL003M0DAO;
	
	public HashMap<String, Object> selectList(WRKFIL003M0P0DTO inVo) {
		
		Map<String, String>			mapParam		= new HashMap<String, String>();			// 동적 쿼리 Param용 Map
		Map<String, String>			mapGrdHead		= null;										// 동적 그리드 Head용 Map
		HashMap<String, Object>		mapResult		= new HashMap<String,Object>();				// 전체 결과용 HashMap
		List<Map<String, String>>	mapGrdHradList	= new ArrayList<>();						// 동적 그리드 Head 목록용 List Map
		List<Map<String, String>>	mapResultList	= null;										// 동적 쿼리 결과용 List Map
		
		List<WRKFIL003M0R0DTO>	wRKFIL003M0P0DTOList = wRKFIL003M0DAO.selectTableInfoList(inVo);	// 조회 대상 테이블 정보 DTO
		
		
		int	iCnt			= 0;			// 동적 Grid 컬럼 카운트
		String sColId		= "";			// 그리드 헤더 ID용 String
		String sItmNm		= "";			// 그리드 헤더 명용 String
		String sDbId		= "RDSDB";		// default : RDSDB
		String stblId		= "DUAL";		// default : DUAL
		String sUiSelYn		= "N";			// 화면조회여부 default : N
		String sQuery		= " SELECT ";	// 동적쿼리 생성용 String
		boolean bStdYymm	= false;		// 결산년월 컴럼 존재 여부 체크용
		
		/* 조회된 컬럼 수만큼 For문으로 동적 쿼리문 생성 */
		for ( WRKFIL003M0R0DTO wRKFIL003M0R0DTO : wRKFIL003M0P0DTOList ) {
			
			sColId = wRKFIL003M0R0DTO.getClmnId();  // 칼럼ID
			sItmNm = wRKFIL003M0R0DTO.getItmNm();   // 항목명
			
			mapGrdHead = new HashMap<String, String>();
			
			mapGrdHead.put("CD", sColId);   // 컬럼ID
			mapGrdHead.put("NM", sItmNm);   // 항목명
			
			mapGrdHradList.add(iCnt, mapGrdHead);
			
			iCnt++;
			
			if ( iCnt == 1) {
				sQuery = sQuery + "	   " + sColId + " ";
				
				sDbId	= wRKFIL003M0R0DTO.getDbId();	  // DB 스키마
				stblId	= wRKFIL003M0R0DTO.getTblId();	  // 테이블ID
				sUiSelYn = wRKFIL003M0R0DTO.getUiSelYn();   // 화면 조회 여부

			} else {
				sQuery   = sQuery + "	 , " + sColId + " ";
			}
			
			/* 테이블에 기준년월 존재여부 확인. 하단 조회조건 셋팅 시 기준년월이 없을경우 조회X */
			if ( "STD_YYMM".equals(sColId) ) {
				bStdYymm = true;
			}
			
		}
		
		/* 조회 컬럼중 등록/변경 정보 default Set */
		sQuery = sQuery + "  , FRST_REG_EMP_NO "  
						+ "  , CONVERT(VARCHAR, FRST_REG_DTM, 23) AS FRST_REG_DTM "
						+ "  , FNL_UPD_EMP_NO "
						+ "  , CONVERT(VARCHAR, FNL_UPD_DTM, 23) AS FNL_UPD_DTM "
						+ "  FROM " + sDbId + ".dbo." + stblId;
		
		/* UI 조회 대상인 경우 조회 */
		if ( "Y".equals(sUiSelYn) ) {
			sQuery = sQuery + " WHERE 1=1 "
							+ "   AND CS_NO = '" + inVo.getCsNo() + "' ";
		} else {
			sQuery = sQuery + " WHERE 1=2 ";
		}
		
		/* 결산년월 컬럼이 있고 Parameter로 정보가 있는 경우 조회  */
		if ( bStdYymm && !inVo.getStdYymm().isEmpty() ) {
			sQuery = sQuery + "   AND STD_YYMM = '" + inVo.getStdYymm() + "' ";
		} else if ( bStdYymm && inVo.getStdYymm().isEmpty() ) {
			sQuery = sQuery + "   AND 1=2 ";
		}

		sQuery = sQuery + " ORDER BY 1, 2, 3, 4, 5 ";

		mapParam.put("sQuery", sQuery);   // 동적 쿼리 문장 Param 셋팅
		
		mapResultList = wRKFIL003M0DAO.selectList(mapParam);   // 그리드 동적 쿼리로 데이터 조회

		mapResult.put("grdHead", mapGrdHradList);   // 그리드 동적 헤더 정보 결과 HashMapdp 적용
		mapResult.put("data"   , mapResultList);	// 그리드 동적 데이터 정보 결과 HashMapdp 적용
		
		log.info(" mapResult : " + mapResult.toString());
		
		return mapResult;
		
	}
	
	public void saveUploadFile(MultipartFile[] uploadFile, WRKFIL003M0P0DTO inVo, String csNo, String usrId) {
		
		UploadFile					atachFile			= null;
		OPCPackage					opcPackage			= null;
		XSSFWorkbook				wb					= null;
		Map<String, String>			mapColData			= null;										// 동적 그리드 Head용 Map
		Map<String, String>			mapHeadData			= null;										// 동적 그리드 Head용 Map
		Map<String, Object>			mapExcelData		= null;										// 동적 그리드 Head용 Map
		List<String>				colSelBasY			= new ArrayList<>();						// 적재기준여부=Y인 컬럼 List
		List<Map<String, String>>	mapExcelColList		= new ArrayList<>();						// 동적 그리드 Head 목록용 List Map
		List<Map<String, String>>	mapExcelHeadList	= new ArrayList<>();						// 동적 그리드 Head 목록용 List Map
		List<Map<String, Object>>	mapExcelDataList	= new ArrayList<>();						// 동적 그리드 Head 목록용 List Map
		
		WRKFIL003M0P1DTO wRKFIL003M0P1DTO = new WRKFIL003M0P1DTO();									// 이력 저장용 DTO
		
		inVo.setCsNo(csNo);
		
		List<WRKFIL003M0R0DTO>	wRKFIL003M0P0DTOList = wRKFIL003M0DAO.selectTableInfoList(inVo);	// 조회 대상 테이블 정보 DTO

		int		iCnt		= 0;					// 동적 Excel 컬럼 카운트
		int		iRowCnt		= 0;					// Excel ROW 카운트
		int		iColCnt		= 0;					// Excel Col 카운트
		String 	sCol		= "";					// Col String
		String 	sTblId		= "";					// 테이블 ID용 String
		String 	sColId		= "";					// 테이블 컬럼 ID용 String
		String 	sDbId		= "RDSDB";				// default : RDSDB
		String 	sSelBasYn	= "N";					// 적재기준여부 default : N
		String 	sBaseQuery	= " INSERT INTO ";		// 동적쿼리 생성용 String
		String 	sAddQuery	= "";					// 동적쿼리 생성용 String : Records
		String 	dBaseQuery	= " DELETE FROM ";		// 동적쿼리 생성용 String : DELETE
		String sStdYymm     = null;         		// 기준년월
		String sFileNm      = "";         			// 파일명
		String sFileUuidNm  = "";         			// 파일명(uuid)
		String sLoadSuccYn  = "N";         			// 성공여부
		
		/* 조회된 컬럼 수만큼 For문으로 동적 쿼리문 생성 및 적재기준여부가 Y인 컬럼저장 */
		for ( WRKFIL003M0R0DTO wRKFIL003M0R0DTO : wRKFIL003M0P0DTOList ) {
			
			sColId = wRKFIL003M0R0DTO.getClmnId();  // 칼럼ID
			
			mapColData = new HashMap<String, String>();
			
			mapColData.put("CD", sColId);   // 컬럼ID
			
			mapExcelColList.add(iCnt, mapColData);
			
			sSelBasYn = wRKFIL003M0R0DTO.getSelBasYn();
			if ( sSelBasYn.equals("Y") ) {
				colSelBasY.add(wRKFIL003M0R0DTO.getClmnId());
			};
			
			iCnt++;
			
			if ( iCnt == 1) {
				sTblId 		= wRKFIL003M0R0DTO.getTblId();		// 테이블ID
				sDbId		= wRKFIL003M0R0DTO.getDbId();		// DB 스키마
				
				wRKFIL003M0P1DTO.setDbId(sDbId);
				wRKFIL003M0P1DTO.setTblId(sTblId);
				
				sBaseQuery = sBaseQuery + "      " + sDbId + ".dbo." + sTblId + " "
										+ "   (  "
										+ "      CS_NO "
										+ "   , " + sColId + " ";
				
				dBaseQuery = dBaseQuery + "      " + sDbId + ".dbo." + sTblId + " ";
				
			} else {
				sBaseQuery = sBaseQuery + "   , " + sColId + " ";
			}
			
		}
		
		/* 공통 컬럼 정보 default Set */
		sBaseQuery = sBaseQuery + "   , FRST_REG_EMP_NO "
								+ "   , FRST_REG_DTM "
								+ "   , FNL_UPD_EMP_NO "
								+ "   , FNL_UPD_DTM "
								+ "   )  "
								+ " VALUES ";
		
		sAddQuery  = sAddQuery  + "   (  ";
		
		dBaseQuery = dBaseQuery + "   WHERE CS_NO = '" + csNo + "'";
		
		// 업로드 이력 Insert
		wRKFIL003M0P1DTO.setCsNo(csNo);
		wRKFIL003M0P1DTO.setUsrId(usrId);
		wRKFIL003M0DAO.insertUplHist(wRKFIL003M0P1DTO);
		
		log.info(" ====== wRKFIL003M0P1DTO : " + wRKFIL003M0P1DTO.toString());
		log.info(" ====== iSeqNo : " + wRKFIL003M0P1DTO.getSeqNo());
		
		/* 조회된 컬럼 수만큼 For문으로 동적 쿼리문 생성 */
		for ( int i=0; i < mapExcelColList.size(); i++ ) {
			
			sColId = mapExcelColList.get(i).get("CD");  // 칼럼ID
			
			//sColId = JdbcUtils.convertUnderscoreNameToPropertyName(sColId.toLowerCase());
			
			if ( i == 0) {
				/*sBaseQuery = sBaseQuery + "     #{item." + sColId + "} ";*/
				sAddQuery = sAddQuery + "     '" + csNo + "' "
				                      + "   , #{item." + sColId + "} ";
				
			} else {
				/* sBaseQuery = sBaseQuery + "   , #{item." + sColId + "} "; */
				sAddQuery = sAddQuery + "   , #{item." + sColId + "} ";
			}
			
		}
		
		/* 공통 컬럼 정보 default Set */
		sAddQuery   = sAddQuery + "   , '" + usrId + "' "	  // TO-DO : 세션 사용자 ID로 변경
								+ "   , GETDATE() "
								+ "   , '" + usrId + "' "	  // TO-DO : 세션 사용자 ID로 변경
								+ "   , GETDATE() "
								+ "   )";
		
		log.info(" =====> sBaseQuery : " + sBaseQuery);
		log.info(" =====> sAddQuery : "  + sAddQuery);
				
		for (MultipartFile multipartFile : uploadFile) {

			try {
				atachFile = fileStore.storeFile(multipartFile, usrId, csNo);
				
				sFileNm     = atachFile.getUploadFileName();
				sFileUuidNm = atachFile.getStoreFileName();
				opcPackage  = OPCPackage.open(atachFile.getStoreFilePath() + atachFile.getStoreFileName());
				wb		    = new XSSFWorkbook(opcPackage);
				
				int sheetNum = wb.getNumberOfSheets();
				
				for(int num = 0; num<sheetNum; num++) {

					XSSFSheet sheet = wb.getSheetAt(num);
					Iterator<Row> iterator = sheet.iterator();
					
					log.info(" ============= while(iterator.hasNext()) STRART =================");
					// Row
					while(iterator.hasNext()) {
						Row currentRow = iterator.next();
						Iterator<Cell> cellIterator = currentRow.iterator();
						iRowCnt++;
						iColCnt = 0;
						
						mapExcelData = new HashMap<String, Object>();
						// Cell
						while(cellIterator.hasNext()) {
							Cell currentCell = cellIterator.next();
							
							if( iRowCnt == 1 ) {

								mapHeadData = new HashMap<String, String>();
								
								mapHeadData.put("CD", currentCell.getStringCellValue());   // 컬럼ID
								
								mapExcelHeadList.add(iColCnt++, mapHeadData);
							} else {
								
								sCol = mapExcelHeadList.get(iColCnt++).get("CD");
								
								if (   sCol.indexOf("_NO") >= 0 || sCol.indexOf("_CD")  >= 0 || sCol.indexOf("_SECD") >= 0
									|| sCol.indexOf("_ID") >= 0 || sCol.indexOf("_YN")  >= 0 || sCol.indexOf("_TM")   >= 0
									|| sCol.indexOf("_NM") >= 0 || sCol.indexOf("_CTT") >= 0 || sCol.indexOf("_DESC") >= 0
									|| sCol.indexOf("_DT") >= 0 || sCol.indexOf("_YMD") >= 0 || sCol.indexOf("_YYMM") >= 0 ) {
									
									mapExcelData.put(sCol, returnStringValue(wb, currentCell));
									
								} else {
									mapExcelData.put(sCol, returnCellValue(wb, currentCell));
								}
							}
						}
						
						if( iRowCnt != 1 ) {
							mapExcelDataList = new ArrayList<>();
							
							mapExcelDataList.add(mapExcelData);
							
							if( iRowCnt == 2 ) {

								for ( String colSel : colSelBasY ) {
									dBaseQuery = dBaseQuery + " AND " + colSel + "   =   " + mapExcelData.get(colSel);
									if ( "STD_YYMM".equals(colSel) ) {
										sStdYymm = (String) mapExcelData.get(colSel);
									}
								}
								dBaseQuery = dBaseQuery + "   ;   ";
								wRKFIL003M0DAO.deleteList(dBaseQuery);
							}
							
							wRKFIL003M0DAO.insertDataList(sBaseQuery, sAddQuery, mapExcelDataList);
						}
						
						//log.info(); // Row를 구분해주기 위한 엔터
					}
					log.info(" =====> mapExcelDataList : " + mapExcelDataList.toString());
					log.info(" ============= while(iterator.hasNext()) END =================");
			  	}
				
				sLoadSuccYn = "Y";
			} catch (IOException ioe) {
				log.info("===> ioe.getMessage() : " + ioe.getMessage());
			} catch (Exception e) {
				log.info("===> e.getMessage() : " + e.getMessage());
			} finally {
				try {
					if ( opcPackage != null ) {
						opcPackage.close();
					}
					if ( wb != null ) {
						wb.close();
					}
				} catch (IOException ioe) {
					log.info("===> finally ioe.getMessage() : " + ioe.getMessage());
				} catch (Exception e) {
					log.info("===> finally e.getMessage() : " + e.getMessage());
				}
			}
		}
		
		wRKFIL003M0P1DTO.setStdYymm(sStdYymm);
		wRKFIL003M0P1DTO.setFileNm(sFileNm);
		wRKFIL003M0P1DTO.setUuid(sFileUuidNm);
		wRKFIL003M0P1DTO.setLoadSuccYn(sLoadSuccYn);
		wRKFIL003M0P1DTO.setLoadCnt(iRowCnt-1);
		// 업로드 이력 Update
		wRKFIL003M0DAO.updateUplHist(wRKFIL003M0P1DTO);
		
	}
	
	private static String returnCellValue (XSSFWorkbook workbook, Cell cell) { 	
		CellType cellType = cell.getCellType();
		//log.info("===> cellType() : " + cellType.toString());
		switch (cellType) {
			case NUMERIC:
				double doubleVal = cell.getNumericCellValue();
				if (DateUtil.isValidExcelDate(doubleVal)) {
					SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
					return String.valueOf(dateFormatter.format(cell.getDateCellValue()));
				} else {
					return String.valueOf(doubleVal);
				}
			case STRING:
				return cell.getStringCellValue();
			case ERROR:
				return String.valueOf(cell.getErrorCellValue());
			case BLANK:
				return "";
			case FORMULA:
				XSSFFormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
				DataFormatter dataFormatter = new DataFormatter();
				// log.info(cell.getCellFormula());
				// 수식 그대로
				return dataFormatter.formatCellValue(evaluator.evaluateInCell(cell));
				// 수식 결과	        		        
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			default:
				return "";
		}
	}
	
	private static String returnStringValue (XSSFWorkbook workbook, Cell cell) { 	
		CellType cellType = cell.getCellType();
		//log.info("===> cellType() : " + cellType.toString());
		switch (cellType) {
			case NUMERIC:
				int iVal = (int) cell.getNumericCellValue();
				return String.valueOf(iVal);
			case STRING:
				return cell.getStringCellValue();
			case ERROR:
				return String.valueOf(cell.getErrorCellValue());
			case BLANK:
				return "";
			case FORMULA:
				XSSFFormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
				DataFormatter dataFormatter = new DataFormatter();
				// log.info(cell.getCellFormula());
				// 수식 그대로
				return dataFormatter.formatCellValue(evaluator.evaluateInCell(cell));
				// 수식 결과	        		        
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			default:
				return "";
		}
	}

	public List<WRKFIL003M0R1DTO> selectFileList(WRKFIL003M0P0DTO inVo) {
		
		int						iCnt	= 0;										// 테이블 여부 카운트
		int						iDtCnt	= 0;										// 테이블 데이터 카운트
		String 					sDbId	= "";										// DB ID
		String 					sTblNm	= "";										// TABLE NAME
		String 					sQuery	= "";										// 동적쿼리 생성용 String
		List<WRKFIL003M0R1DTO>	result	= new ArrayList<WRKFIL003M0R1DTO>();		// 결과 DTO LIST
		
		List<WRKFIL003M0R1DTO>	wRKFIL003M0R1DTOList = wRKFIL003M0DAO.selectFileList(inVo);	// 조회 대상 테이블 정보 DTO
		
		/* 조회된 컬럼 수만큼 For문으로 동적 쿼리문 생성 */
		for ( WRKFIL003M0R1DTO wRKFIL003M0R1DTO : wRKFIL003M0R1DTOList ) {
			iCnt   = 0;
			iDtCnt = -1;
			
			sDbId  = wRKFIL003M0R1DTO.getDbId();
			sTblNm = wRKFIL003M0R1DTO.getTblId();
			
			try {
				iCnt = wRKFIL003M0DAO.selectDbObjCnt(sDbId, sTblNm);
			} catch (Exception e) {
				log.info(" Insufficient permissions on Database  [ " + sDbId + " ] ");
				continue;
			}
			
			try {
				
				sQuery = " SELECT COUNT(1) AS CNT FROM [" + sDbId + "].dbo." + sTblNm + "  ";
				
				iDtCnt = wRKFIL003M0DAO.selectTblCnt(sQuery);
			} catch (Exception e) {
				log.info(" Insufficient permissions on Table  [ " + sTblNm + " ] ");
				continue;
			}
			
			if ( iCnt > 0 && iDtCnt >= 0) {
				result.add(wRKFIL003M0R1DTO);
			}
		}
		
		log.info(" mapResult : " + result.toString());
		
		return result;
	}
	

}