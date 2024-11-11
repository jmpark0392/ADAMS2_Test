package com.rds.adams.web.core.utils;

import java.text.DecimalFormat;

public class ValidUtil {

	/**
	 * 금액의 소수점 이하 자릿수 체크
	 * 0,123 ==> True, 1,123.12 ==> false
	 * @param point : 소수점 이하 자릿수
	 * @param amt : 금액
	 * @return (TRUE : 정상, False : 비정상)
	 */
	public static boolean chkCurAmt(int point, double amt) 
	{
		//지수형태의 double 는 BigDecimal로 받아서 Numeric 형태로 넘긴다.
		DecimalFormat df = new DecimalFormat("#.#"); 
		
		return chkCurAmt(point,df.format(amt));
	}
	
	public static boolean chkCurAmt(int point, String amt)
	{
		boolean ret = false;
		String strAmt = "";
		int tmpPoint = 0;

		strAmt = amt;
		if(strAmt.split("\\.").length == 1) tmpPoint = 0;
		else tmpPoint = strAmt.split("\\.")[1].length();
		
		if(point >= tmpPoint) ret = true;
		return ret;
	}
}
