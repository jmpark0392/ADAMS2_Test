package com.rds.adams.web.core.utils;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
	public static long getTimeMilliSecond()
	{
		long mils = System.currentTimeMillis();
		
		return mils;
	}
	
    /**
     * 금일 날짜 2003-01-17 (if today = 2003-01-17)
     * 
     * @return String YYYY-MM-DD
     */
    public static String getToday()
    {
        return getString("YYYY-MM-DD"); // 2003-01-17
    }


    /**
     * 금일 현재 날짜 시각 2003-01-17 13:21:00 (if today = 2003-01-17 13:21:00)
     * 
     * @return String YYYY-MM-DD HH:NN:DD
     */
    public static String getNow()
    {
        return getString("DATETIME"); // 2003-01-17 13:21:00
    }


    /**
     * YYYY-MM-DD 형태로 만들어 리턴 정상적인 값을 리턴하는 입력 값은 다음과 같다. (변환불가할 때는 0000 또는 00으로 대체) YYYY-MM-DD, YYYY-MM, YYYY-M, YYYYMMDD, YYYYMM, YYYYM
     */
    private static String fillDate(String source)
    {
        String arr[] = StringUtil.split(source, '-', false);
        String YYYY = "0000";
        String MM = "00";
        String DD = "00";
        if (arr == null) return YYYY + "-" + MM + "-" + DD;
        if (arr.length > 0)
        {
            if ((isRight(arr[0])) && (arr[0].length() == 4))
            {
                YYYY = arr[0];
            }
            if (arr.length == 1)
            { // 이것은 날짜형식이 YYYYM 또는 YYYYMM 또는 YYYYMMDD 일 것이다.
                if (arr[0].length() == 5)
                {
                    YYYY = StringUtil.getLeftString(arr[0], 4);
                    MM = "0" + StringUtil.getRightString(arr[0], 1);
                }
                else if (arr[0].length() == 6)
                {
                    YYYY = StringUtil.getLeftString(arr[0], 4);
                    MM = StringUtil.getRightString(arr[0], 2);
                }
                else if (arr[0].length() == 8)
                {
                    YYYY = StringUtil.getLeftString(arr[0], 4);
                    MM = StringUtil.getSubString(arr[0], 4, 2);
                    DD = StringUtil.getSubString(arr[0], 6, 2);
                }
                return YYYY + "-" + MM + "-" + DD;
            }
        }
        if (arr.length > 1)
        {
            if ((isRight(arr[1])) && (arr[1].length() == 2))
            {
                MM = arr[1];
            }
            else if ((isRight(arr[1])) && (arr[1].length() == 1))
            {
                MM = "0" + arr[1];
            }
        }
        if (arr.length > 2)
        {
            if ((isRight(arr[2])) && (arr[2].length() == 2))
            {
                DD = arr[2];
            }
            else if ((isRight(arr[2])) && (arr[2].length() == 1))
            {
                DD = "0" + arr[2];
            }
        }
        return YYYY + "-" + MM + "-" + DD;
    }


    /**
     * 옳은 날짜형식인지 검사
     */
    private static boolean isRight(String source)
    {
        for (int i = 0; i < source.length(); i++)
        {
            char c = source.charAt(i);
            if ((c < '0' || c > '9') && (c != '-'))
            {
                return false;
            }
        }
        return true;
    }


    /**
     * 갭 만큼 월 수를 차감
     */
    private static String plusMonth(String source, int nGap)
    {
        int nYear = StringUtil.parseInt(StringUtil.getSubString(source, 0, 4));
        int nMonth = StringUtil.parseInt(StringUtil.getSubString(source, 5, 2));
        String sDate = StringUtil.getSubString(source, 8, 2);
        boolean isPrev = false;
        int hopping = nGap;
        if (nGap < 0)
        {
            isPrev = true;
            hopping *= -1;
        }
        for (int i = 0; i < hopping; i++)
        {
            if (isPrev)
            {
                nMonth--;
                if (nMonth < 1)
                {
                    nYear--;
                    nMonth = 12;
                }
            }
            else
            {
                nMonth++;
                if (nMonth > 12)
                {
                    nYear++;
                    nMonth = 1;
                }
            }
        }
        String sYear = StringUtil.parseString(nYear);
        String sMonth = StringUtil.parseString(nMonth);
        if (nMonth < 10)
        {
            sMonth = "0" + sMonth;
        }
        return sYear + "-" + sMonth + "-" + sDate;
    }

    /**
     * 갭 만큼 쿼터 수를 차감
     */
    private static String plusQuarter(String source, int nGap)
    {
//        return sYear + sQuarter;
    	return getYearMonth(source, nGap*3);
//    	return source;
    }

    /**
     * 금일 년도, 월 200301 (if today = 2003-01-17)
     * 
     * @return String YYYYMM
     */
    public static String getYearMonth()
    {
        return getString("YYYY") + getString("MM"); // 200301
    }


    /**
     * 금일 년도 2003 (if today = 2003-01-17)
     * 
     * @return String YYYY
     */
    public static String getYear()
    {
        return getString("YYYY"); // 2003
    }


    /**
     * 금일 월 01 (if today = 2003-01-17)
     * 
     * @return String MM
     */
    public static String getMonth()
    {
        return getString("MM"); // 01
    }


    /**
     * 금일 날짜(일) 17 (if today = 2003-01-17)
     * 
     * @return String DD
     */
    public static String getDate()
    {
        return getString("DD"); // 17
    }


    /**
     * 입력받은 날짜(String)에서 년도를 리턴
     * 
     * @param String
     *        YYYY-MM 또는 YYYYMM 또는 YYYY-MM-DD 또는 YYYYMMDD
     * @return String YYYY
     */
    public static String getYear(String source)
    {
        source = fillDate(source);
        return StringUtil.getLeftString(source, 4);
    }


    /**
     * 입력받은 날짜(String)에서 년도와 월을 리턴
     * 
     * @param String
     *        YYYY-MM 또는 YYYYMM 또는 YYYY-MM-DD 또는 YYYYMMDD
     * @return String YYYYMM
     */
    public static String getYearMonth(String source)
    {
        source = fillDate(source);
        return StringUtil.getLeftString(source, 4) + StringUtil.getSubString(source, 5, 2);
    }


    /**
     * 입력받은 날짜(String)에서 월을 리턴
     * 
     * @param String
     *        YYYY-MM 또는 YYYYMM 또는 YYYY-MM-DD 또는 YYYYMMDD
     * @return String MM
     */
    public static String getMonth(String source)
    {
        source = fillDate(source);
        return StringUtil.getSubString(source, 5, 2);
    }


    /**
     * 입력받은 날짜(String)에서 일을 리턴
     * 
     * @param String
     *        YYYY-MM 또는 YYYYMM 또는 YYYY-MM-DD 또는 YYYYMMDD
     * @return String DD
     */
    public static String getDate(String source)
    {
        source = fillDate(source);
        return StringUtil.getSubString(source, 8, 2);
    }


    /**
     * 입력받은 날짜(String)에서 n개월 가감 후 년도를 리턴
     * @param source - YYYY-MM 또는 YYYYMM 또는 YYYY-MM-DD 또는 YYYYMMDD
     * @param nGap - 증분(음의 정수 또는 양의 정수) 예: -2
     * @return String YYYYMM
     */
    public static String getYearMonth(String source, int nGap)
    {
        source = fillDate(source);
        source = plusMonth(source, nGap);
        return StringUtil.getLeftString(source, 4) + StringUtil.getSubString(source, 5, 2);
    }

    /**
     * 입력받은 쿼터에서 n쿼터 가감 후 쿼터를 리턴
     * @param source - YYYYQN
     * @param nGap - 증분(음의 정수 또는 양의 정수) 예: -2
     * @return YYYYQN
     */
    public static String getYearQuarter(String source, int nGap)
    {
        source = StringUtil.removeDesh(source);
        source = plusQuarter(source, nGap);
        return source;
    }
    

    /**
     * 입력받은 연도+결산기에서 n결산기 가감 후 연도+결산기를 리턴
     * @param termTypeCd - Monthly = M Quarterly = Q Half-Yearly = H
     * @param source - YYYYMM 또는 YYYYQN
     * @param nGap - 증분(음의 정수 또는 양의 정수) 예: -2
     * @return
     */
    public static String getYearTerm(String termTypeCd, String source, int nGap)
    {
    	if ( "M".equals(termTypeCd))
    	{
    		source = getYearMonth( source, nGap);
    	}
    	else if ( "Q".equals(termTypeCd))
    	{
    		source = getYearQuarter( source, nGap);
    	}
    	else if ( "H".equals(termTypeCd))
    	{
    		// to be defined.
    	}
    	return source;
    }
    
    
    /**
     * 입력받은 날짜(String)에서 n개월 가감 후 년도를 리턴
     * 
     * @param String
     *        YYYY-MM 또는 YYYYMM 또는 YYYY-MM-DD 또는 YYYYMMDD
     * @param int
     *        가감할 월 수 (Ex. 12 또는 -6 등등)
     * @return String YYYY-MM-DD
     */
    public static String getYearMonthDay(String source, int nGap)
    {
        source = fillDate(source);
        source = plusMonth(source, nGap);
        return source;
    }


    /**
     * 입력받은 날짜(String)에서 n개월 가감 후 년도를 리턴
     * 
     * @param String
     *        YYYY-MM 또는 YYYYMM 또는 YYYY-MM-DD 또는 YYYYMMDD
     * @param int
     *        가감할 월 수 (Ex. 12 또는 -6 등등)
     * @return String YYYY
     */
    public static String getYear(String source, int nGap)
    {
        source = fillDate(source);
        source = plusMonth(source, nGap);
        return StringUtil.getLeftString(source, 4);
    }


    /**
     * 입력받은 날짜(String)에서 n개월 가감 후 월을 리턴
     * 
     * @param String
     *        YYYY-MM 또는 YYYYMM 또는 YYYY-MM-DD 또는 YYYYMMDD
     * @param int
     *        가감할 월 수 (Ex. 12 또는 -6 등등)
     * @return String MM
     */
    public static String getMonth(String source, int nGap)
    {
        source = fillDate(source);
        source = plusMonth(source, nGap);
        return StringUtil.getSubString(source, 5, 2);
    }


    /**
     * 입력받은 날짜(String)에서 n개월 가감 후 일을 리턴(사실상 의미없는 메서드)
     * 
     * @param String
     *        YYYY-MM 또는 YYYYMM 또는 YYYY-MM-DD 또는 YYYYMMDD
     * @param int
     *        가감할 월 수 (Ex. 12 또는 -6 등등)
     * @return String DD
     */
    public static String getDate(String source, int nGap)
    {
        source = fillDate(source);
        source = plusMonth(source, nGap);
        return StringUtil.getSubString(source, 8, 2);
    }


    /**
     * long 형의 time 을 반환한다. (비교할 때 쓰자)
     */
    public static long getTime(String source)
    {
        source = fillDate(source);
        return parseDate(source).getTime();
    }


    /**
     * 다양한 형태의 현재 시각을 만든다.
     */
    public static String getString(String kind)
    {
        DecimalFormat DF = new DecimalFormat("00");
        Calendar now = Calendar.getInstance();
        String YYYY = Integer.toString(now.get(Calendar.YEAR));
        String MM = DF.format(now.get(Calendar.MONTH) + 1);
        String M = Integer.toString(now.get(Calendar.MONTH) + 1);
        String DD = DF.format(now.get(Calendar.DATE));
        String D = Integer.toString(now.get(Calendar.DATE));
        String HH = DF.format(now.get(Calendar.HOUR_OF_DAY));
        String NN = DF.format(now.get(Calendar.MINUTE));
        String SS = DF.format(now.get(Calendar.SECOND));
        kind = kind.toUpperCase();
        if (kind.equals("YYYY"))
        { // 2003
            return YYYY;
        }
        else if (kind.equals("MM"))
        { // 01
            return MM;
        }
        else if (kind.equals("M"))
        { // 1
            return M;
        }
        else if (kind.equals("DD"))
        { // 17
            return DD;
        }
        else if (kind.equals("D"))
        { // 17
            return D;
        }
        else if (kind.equals("YYYYMM"))
        { // 200301
            return YYYY + MM + DD;
        }
        else if (kind.equals("YYYY-MM"))
        { // 2003-01
            return YYYY + "-" + MM;
        }
        else if (kind.equals("YYYYMMDD"))
        { // 20030117
            return YYYY + MM + DD;
        }
        else if (kind.equals("YYYY-MM-DD"))
        { // 2003-01-17
            return YYYY + "-" + MM + "-" + DD;
        }
        else if (kind.equals("YYYY/MM/DD"))
        { // 2003-01-17
            return YYYY + "/" + MM + "/" + DD;
        }
        else if (kind.equals("HH:NN:SS"))
        { // 13:21:00
            return HH + ":" + NN + ":" + SS;
        }
        else if (kind.equals("DATE"))
        { // 2003-01-17
            return YYYY + "-" + MM + "-" + DD;
        }
        else if (kind.equals("TIME"))
        { // 13:21:00
            return HH + ":" + NN + ":" + SS;
        }
        else if (kind.equals("DATETIME"))
        { // 2003-01-17 13:21:00
            return YYYY + "-" + MM + "-" + DD + " " + HH + ":" + NN + ":" + SS;
        }
        else
        {
            return "";
        }
    }


    /**
     * date type -> String
     * 
     * @return 형식: 2002-01-17
     */
    public static String formatDate(Date dtDate)
    {
        return new SimpleDateFormat("yyyy-MM-dd").format(dtDate);
    }


    /**
     * String -> date type
     * 
     * @param 형식:
     *        2002-01-17
     */
    public static Date parseDate(String strDate)
    {
        return (Date) java.sql.Date.valueOf(strDate);
    }


    /**
     * date type -> String
     * 
     * @return 형식: 2002-01-17
     */
    public static String parseString(Date dtDate)
    {
        return formatDate(dtDate);
    }


    /**
     * 월말 날짜 구하기
     */
    public static String getLastDay(String strYear, String strMonth)
    {
        String businessdate = "";
        java.util.Date tmpDate = null;
        Calendar cal = Calendar.getInstance();
        tmpDate = new SimpleDateFormat("yyyyMMdd").parse(strYear + strMonth + "01", new ParsePosition(0));
        cal.setTime(tmpDate);
        int theLastdayOftheMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        tmpDate = new SimpleDateFormat("yyyyMMdd").parse(strYear + strMonth + theLastdayOftheMonth, new ParsePosition(0));
        cal.setTime(tmpDate);
        businessdate = new SimpleDateFormat("yyyy-MM-dd").format(tmpDate);
        return businessdate;
    }


    /**
     * 월말 날짜 구하기
     */
    public static String getLastDay(String strYearMonth)
    {
        if (strYearMonth == null || strYearMonth.length() < 6)
        {
            return "";
        }
        return getLastDay(strYearMonth.substring(0, 4), strYearMonth.substring(4, 6));
    }


    /**
     * 영업일(토,일이 아닌) 월말 날짜 구하기
     */
    public static String getLastWorkingDay(String strYear, String strMonth)
    {
        String businessdate = "";
        java.util.Date tmpDate = null;
        Calendar cal = Calendar.getInstance();
        tmpDate = new SimpleDateFormat("yyyyMMdd").parse(strYear + strMonth + "01", new ParsePosition(0));
        cal.setTime(tmpDate);
        int theLastdayOftheMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        tmpDate = new SimpleDateFormat("yyyyMMdd").parse(strYear + strMonth + theLastdayOftheMonth, new ParsePosition(0));
        cal.setTime(tmpDate);
        int DayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (DayOfWeek == 7)
        { // 토요일인 경우 -> 금요일로 분석
            businessdate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(tmpDate.getTime() - 86400000 * 1));
        }
        else if (DayOfWeek == 1)
        { // 일요일인 경우 -> 금요일로 분석
            businessdate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(tmpDate.getTime() - 86400000 * 2));
        }
        else
        {
            businessdate = new SimpleDateFormat("yyyy-MM-dd").format(tmpDate);
        }
        return businessdate;
    }


    /**
     * 1개월전 영업일
     */
    public static String getWorkingDayOfLastMonth()
    {
        String businessdate = "";
        java.util.Date tmpDate = null;
        Calendar cal = Calendar.getInstance();
        tmpDate = new Date(cal.getTime().getTime() - ((long) 86400000 * 30));
        cal.setTime(tmpDate);
        int DayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (DayOfWeek == 7)
        { // 토요일인 경우 -> 금요일로 분석
            businessdate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(tmpDate.getTime() - 86400000 * 1));
        }
        else if (DayOfWeek == 1)
        { // 일요일인 경우 -> 금요일로 분석
            businessdate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(tmpDate.getTime() - 86400000 * 2));
        }
        else
        {
            businessdate = new SimpleDateFormat("yyyy-MM-dd").format(tmpDate);
        }
        return businessdate;
    }


    /**
     * 날짜
     * 
     * @return YYYYMMDD
     */
    public static String getDateString()
    {
        Calendar now = Calendar.getInstance();
        DecimalFormat DF = new DecimalFormat("00");
        String YYYY = Integer.toString(now.get(Calendar.YEAR));
        String MM = DF.format(now.get(Calendar.MONTH) + 1);
        String DD = DF.format(now.get(Calendar.DATE));
        return YYYY + MM + DD;
    }


    /**
     * 날짜시각
     * 
     * @return YYYYMMDD_HHNNSS
     */
    public static String getDateTimeString()
    {
        Calendar now = Calendar.getInstance();
        DecimalFormat DF = new DecimalFormat("00");
        String YY = Integer.toString(now.get(Calendar.YEAR)).substring(2,4);
        String MM = DF.format(now.get(Calendar.MONTH) + 1);
        String DD = DF.format(now.get(Calendar.DATE));
        String HH = DF.format(now.get(Calendar.HOUR_OF_DAY));
        String NN = DF.format(now.get(Calendar.MINUTE));
        String SS = DF.format(now.get(Calendar.SECOND));
        return YY + MM + DD + "_" + HH + NN +SS;
    }
    

    /**
     * 시각
     * 
     * @return HHNNSS
     */
    public static String getTimeString()
    {
        Calendar now = Calendar.getInstance();
        DecimalFormat DF = new DecimalFormat("00");
        String HH = DF.format(now.get(Calendar.HOUR_OF_DAY));
        String NN = DF.format(now.get(Calendar.MINUTE));
        String SS = DF.format(now.get(Calendar.SECOND));
        return HH + NN +SS;
    }
    

    /**
     * 기준년도 + 1
     */
    public static String getNextYear(String thisYear)
    {
        String nextYear = StringUtil.parseString(StringUtil.parseInt(thisYear) + 1);
        return nextYear;
    }

    /**
     * 기준년월에 해당하는 회계연도 시작 기준년월
     * @param bascYm
     * @param clacMm
     * @return
     */
    public static String getAcStrtYm( String bascYm, String clacMm)
    {
    	String strtYm = null;
    	
    	//입력 데이터 체크
    	if ( bascYm != null && bascYm.length() == 6 && clacMm != null )
    	{
    		int iClacMm = StringUtil.parseInt(clacMm);
    		int iBascYm = StringUtil.parseInt(bascYm);
    		int year = iBascYm / 100;
    		int mm = iBascYm - year*100; 
    		int acYear = 0;
    		int strtMm = 0;
    		int iStrtYm = 0;
    		
    		//현재 월이 결산월 이후 이면 회계연도는 현재 연도, 아니면 회계연도는 이전연도
    		//또는 결산월이 12월 이면 회계연도는 현재 연도
    		if ( mm > iClacMm ) 
    		{
    			acYear = year;
    		}
    		else 
    		{
    			if ( iClacMm != 12 )
    			{
    				acYear = year -1;
    			}
    			else
    			{
    				acYear = year;
    			}
    		}
    		
    		strtMm = (iClacMm+1) % 12;
    		
    		iStrtYm = acYear*100 + strtMm;
    		
    		strtYm = StringUtil.parseString(iStrtYm);
    	}
    	
    	return strtYm;
    }

    /**
     * 기준년월에 해당하는 회계연도 종료 기준년월
     * @param bascYm
     * @param clacMm
     * @return
     */
    public static String getAcEndYm( String bascYm, String clacMm)
    {
    	String endYm = null;
    	
    	//입력 데이터 체크
    	if ( bascYm != null && bascYm.length() == 6 && clacMm != null )
    	{
    		int iClacMm = StringUtil.parseInt(clacMm);
    		int iBascYm = StringUtil.parseInt(bascYm);
    		int year = iBascYm / 100;
    		int mm = iBascYm - year*100; 
    		
    		int acYear = 0;
    		int endMm = 0;
    		int iEndYm = 0;
    		
    		//현재 월이 결산월 이후 이면 회계연도는 현재 연도, 아니면 회계연도는 이전연도
    		if ( mm > iClacMm ) 
    		{
    			acYear = year+1;
    		}
    		else 
    		{
    			acYear = year;
    		}
    		
    		endMm = iClacMm;
    		
    		iEndYm = acYear*100 + endMm;
    		
    		endYm = StringUtil.parseString(iEndYm);
    	}
    	
    	return endYm;
    }
    
    /**
     * 기준년월에 해당하는 회계연도
     * @param bascYm
     * @param clacMm
     * @return
     */
    public static String getAcYear( String bascYm, String clacMm)
    {
    	String acYear = null;
    	
    	//입력 데이터 체크
    	if ( bascYm != null && bascYm.length() != 6 && clacMm != null )
    	{
    		int iClacMm = StringUtil.parseInt(clacMm);
    		int iBascYm = StringUtil.parseInt(bascYm);
    		int year = iBascYm / 100;
    		int mm = iBascYm - year*100; 
    		
    		int iAcYear = 0;
    		
    		//현재 월이 결산월 이후 이면 회계연도는 현재 연도, 아니면 회계연도는 이전연도
    		if ( mm > iClacMm ) 
    		{
    			iAcYear = year+1;
    		}
    		else 
    		{
    			iAcYear = year;
    		}
    		
    		acYear = StringUtil.parseString(iAcYear);
    	}
    	
    	return acYear;
    }
    

    /**
     * 2003-10-28 추가 권기상 6자리 년월 문자열을 format한다.
     */
    public static String formatYYMM(String Str, String format)
    {
        if (Str.length() != 6) return Str;
        if (format.equals("YYYY-MM")) return Str.substring(0, 4) + "-" + Str.substring(4, 6);
        if (format.equals("YY-MM")) return Str.substring(2, 4) + "-" + Str.substring(4, 6);
        if (format.equals("YYYY.MM")) return Str.substring(0, 4) + "." + Str.substring(4, 6);
        if (format.equals("YY.MM")) return Str.substring(2, 4) + "." + Str.substring(4, 6);
        return Str;
    }

	/**
	 * 완료기준년월을 계산한다.
	 * - 내용연수 > 0 이면 발생기준년 + 내용연수 + 발생기준월 - 1
	 *                     년도가 바뀌는 경우를 고려해야 됨(ex: 201001 - 1 ==> 200912)
	 *   내용연수 = 0 이면 '999912' 로 셋팅
	 *                     토지의 경우는 상각을 하지 않기때문에 내용연수가 없다. 
	 *                     
	 * @param orgnBaseYm		발생기준년월
	 * @param svlf				내용연수
	 * @return
	 */
	public static String calcCpltBaseYm( String orgnBaseYm, int svlf )
	{
		String cpltBaseYm = null;
		
		if( svlf > 0 )
		{
			int orgnYy = Integer.parseInt( orgnBaseYm.substring( 0, 4 ) );
			int orgnMm = Integer.parseInt( orgnBaseYm.substring( 4, 6 ) );
			
			int cpltYy = orgnYy + svlf;
			int cpltMm = 0;
			if( orgnMm == 1 )
			{
				// 발생기준월이 1월이면 완료월을 12월로 하고 완료년도를 -1 해준다.
				cpltMm = 12;
				cpltYy -= 1;
			}
			else
			{
				cpltMm = orgnMm - 1;
			}
			
			cpltBaseYm = String.valueOf( cpltYy ) + ( cpltMm < 10 ? "0" + cpltMm : String.valueOf( cpltMm ) );
		}
		else
		{
			cpltBaseYm = "999912";
		}
		
		return cpltBaseYm;
	}
	
	/**
	 * 결산월에 따라 기준년월에 해당하는 분기시작 기준년월을 가져온다.
	 * 1)예를 들면 결산월(12월) 일 경우 1분기(1월~3월, 2분기(4~6)...
	 *  bascYm=200905, clacMm=12 ==> Return 200904
	 * @param bascYm : 기준년월
	 * @param clacMm : 결산월
	 * @return
	 */
	public static String getAcQrtStrtYm( String bascYm, String clacMm )
	{
		String retDt = "";
		//입력 데이터 체크
    	if ( bascYm != null && bascYm.length() == 6 && clacMm != null )
    	{
    		int bascYear = 0;
    		int bascMonth = 0;
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    		
    		bascYear = StringUtil.parseInt(bascYm) / 100;
    		bascMonth = StringUtil.parseInt(bascYm) - bascYear * 100;
    		
    		int startMonth = 0;	//분기시작월
    		startMonth = StringUtil.parseInt(clacMm)%12+1;
    		
    		int gap = startMonth % 3;
    		if(gap == 2) gap = 1;
    		else if(gap ==1) gap = 0;
    		else if(gap ==0) gap = 2;
    		
    		//현재 기준월에서 GAP 개월 뺀 기준월을 가져온다.
    		Calendar cl = Calendar.getInstance();
    		cl.set(bascYear, (bascMonth-1)-gap, 1); //기준일
    		bascYear = cl.get(Calendar.YEAR);
    		int rMonth = cl.get(Calendar.MONTH) + 1;

    		//차감월의 분기시작일
    		int sQrt = ((int)Math.ceil(rMonth / 3.0)-1) * 3 + 1;
    		cl.set(bascYear, sQrt-1, 1); //기준일
    		
    		cl.add(Calendar.MONTH, gap);
    		retDt = sdf.format(cl.getTime());
    	}
		return retDt;
	}
	
	public static String getTodayByDayOfWeek() {
		
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		String todayByDayOfWeek = "";
		
		switch(dayOfWeek) {
			case 1:
				todayByDayOfWeek = "SUN";
				break;
			case 2:
				todayByDayOfWeek = "MON";
				break;
			case 3:
				todayByDayOfWeek = "TUE";
				break;
			case 4:
				todayByDayOfWeek = "WED";
				break;
			case 5:
				todayByDayOfWeek = "THU";
				break;
			case 6:
				todayByDayOfWeek = "FRI";
				break;
			case 7:
				todayByDayOfWeek = "SAT";
				break;
		}
		
		return todayByDayOfWeek;
	}

}
