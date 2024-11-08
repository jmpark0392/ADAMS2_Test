package com.rds.adams.web.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{
	private static final String UTF8_ENCODING = "UTF-8";
	private static final int UTF8_LENGTH_INFO_BYTE_LENGTH_ID = 0;
	private static final int UTF8_LENGTH_INFO_CURRENT_BYTE_ID = 1;
	private static final int UTF8_LENGTH_INFO_DISPLAY_LENGTH = 2;
	
	public static boolean isEmpty( String str )
	{
		if( str == null )
		{
			return true;
		}
		
		return str.trim().equals( "" );
	}
	
	public static boolean isEquals( String str, String value )
	{
		if( str == null )
		{
			return false;
		}
		
		return str.equals( value );
	}
	
	public static boolean isEqualsIgnoreCase( String str, String value )
	{
		if( str == null )
		{
			return false;
		}
		
		return str.equalsIgnoreCase( value );
	}
	
	public static String toNotNull( String str )
	{
		if( str == null )
		{
			return "";
		}
		
		return str;
	}
	
	public static String toExcel( String text )
	{
		if( isEmpty( text ) == true )
		{
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		
		int length = text.length();
		
		for( int i = 0; i < length; i++ )
		{
			char tmp = text.charAt( i );
			if( tmp != '\r' )
			{
				sb.append( tmp );
			}
		}
		
		return sb.toString();
	}
	
	public static int lineCount( String text )
	{
		if( isEmpty( text ) == true )
		{
			return 0;
		}
		
		int count = 1;
		
		int length = text.length();
		for( int i = 0; i < length; i++ )
		{
			char tmp = text.charAt( i );
			
			if( tmp == '\n' )
			{
				count++;
			}
		}
		
		return count;
	}
	
	private static int[] getUTF8LengthInfo( String str, int size, int utf8DisplaySize ) throws Exception
	{
		int byteLength = 0;
		int currentByte = 0;
		int displayLength = 0;
		
		byte[] byteArray = str.getBytes( UTF8_ENCODING );
		
		while( byteLength < byteArray.length )
		{
			byte tmp = byteArray[ byteLength ];
			
			if( tmp == 9 || tmp == 10 || ( tmp >= 32 && tmp <= 126 ) )
			{
				byteLength++;
				currentByte = 1;
				displayLength++;
			}
			else if( tmp >= -62 && tmp <= -33 )
			{
				byteLength += 2;
				currentByte = 2;
				displayLength += utf8DisplaySize;
			}
			else if( tmp >= -32 && tmp <= -17 )
			{
				byteLength += 3;
				currentByte = 3;
				displayLength += utf8DisplaySize;
			}
			else if( tmp >= -16 && tmp <= -9 )
			{
				byteLength += 4;
				currentByte = 4;
				displayLength += utf8DisplaySize;
			}
			else if( tmp >= -8 && tmp <= -5 )
			{
				byteLength += 5;
				currentByte = 5;
				displayLength += utf8DisplaySize;
			}
			else if( tmp >= -4 && tmp <= -3 )
			{
				byteLength += 6;
				currentByte = 6;
				displayLength += utf8DisplaySize;
			}
			else
			{
				byteLength++;
			}
			
			if( size > 0 && displayLength >= size )
			{
				break;
			}
		}
		
		int[] lengthInfoArray = new int[ 3 ];
		lengthInfoArray[ UTF8_LENGTH_INFO_BYTE_LENGTH_ID ] = byteLength;
		lengthInfoArray[ UTF8_LENGTH_INFO_CURRENT_BYTE_ID ] = currentByte;
		lengthInfoArray[ UTF8_LENGTH_INFO_DISPLAY_LENGTH ] = displayLength;
		
		byteArray = null;
		
		return lengthInfoArray;
	}
	
	public static String UTF8Substring( String str, int size, int utf8DisplaySize, String endStr ) throws Exception
	{
		int[] lengthInfoArray = getUTF8LengthInfo( str, size, utf8DisplaySize );
		int byteLength = lengthInfoArray[ UTF8_LENGTH_INFO_BYTE_LENGTH_ID ];
		int currentByte = lengthInfoArray[ UTF8_LENGTH_INFO_CURRENT_BYTE_ID ];
		int displayLength = lengthInfoArray[ UTF8_LENGTH_INFO_DISPLAY_LENGTH ];
		
		if( displayLength > size )
		{
			byteLength -= currentByte;
		}
		
		byte[] byteArray = str.getBytes( UTF8_ENCODING );
		byte[] newByte = new byte[ byteLength ];
		for( int i = 0; i < newByte.length; i++ )
		{
			newByte[ i ] = byteArray[ i ];
		}
		
		String resultStr = new String( newByte, UTF8_ENCODING );
		if( byteLength < byteArray.length )
		{
			resultStr += endStr;
		}
		
		byteArray = null;
		newByte = null;
		
		return resultStr;
	}
	
    /**
     * Replace substring of one string with another string and return altered string.
     * @param str Input string.
     * @param oldStr The substring section to replace.
     * @param newStr The new substring replacing old substring section.
     * @return Converted string
     */
    public static String replace( final String str,
                                  final String oldStr,
                                  final String newStr )
    {
        final StringBuffer sb = new StringBuffer();
        final int len = str.length();
        final int from_len = oldStr.length();

        int i = 0;
        int s;
        while( i < len )
        {
            s = str.indexOf( oldStr, i );
            if( s < 0 )
            {
                sb.append( str.substring( i ) );
                break;
            }
            else
            {
                sb.append( str.substring( i, s ) );
                sb.append( newStr );
                i = s + from_len;
            }
        }

        return sb.toString();
    }

    /**

     */
    public static String replaceString(String strSource, String strFrom, String strTo)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals(""))
        {
            return "";
        }
        if (strFrom.equals(""))
        {
            return strSource;
        }
//        StringBuffer strBuffer = new StringBuffer(strSource);
//        int idx = strSource.indexOf(strFrom);
//        while (idx != -1)
//        {
//            strBuffer.delete(idx, idx + strFrom.length());
//            strBuffer.insert(idx, strTo);
//            strSource = strBuffer.toString();
//            idx = strSource.indexOf(strFrom);
//        }
//        return strSource;
        return strSource.replaceAll(strFrom, strTo);
    }


    /**

     */
    public static String getSubString(String strSource, int idx, int cnt)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals(""))
        {
            return "";
        }
        int tot = strSource.length();
        if (idx >= tot)
        {
            return "";
        }
        if (idx + cnt > tot)
        {
            cnt = tot - idx;
        }
        return strSource.substring(idx, idx + cnt);
    }


    /**

     */
    public static String getLeftString(String strSource, int cnt)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals(""))
        {
            return "";
        }
        int len = strSource.length();
        if (cnt > len)
        {
            cnt = len;
        }
        return strSource.substring(0, cnt);
    }


    /**

     */
    public static String getRightString(String strSource, int cnt)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals(""))
        {
            return "";
        }
        int len = strSource.length();
        if (cnt > len)
        {
            cnt = len;
        }
        return strSource.substring(len - cnt);
    }


    /**

     */
    public static String deleteString(String strSource, String strFrom)
    {
        if (strSource == null || strSource.equals(""))
        {
            return strSource;
        }
        StringBuffer result = new StringBuffer(strSource);
        int idx = strSource.indexOf(strFrom);
        if (idx != -1)
        {
            result.delete(idx, idx + strFrom.length());
        }
        return result.toString();
    }


    /**
     */
    public static String cutString(String strSource, int cnt)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals(""))
        {
            return "";
        }
        StringBuffer sb = new StringBuffer("");
        int len = 0;
        for (int i = 0; i < strSource.length(); i++)
        {
            if (strSource.charAt(i) > 255)
            {
                len += 2;
            }
            else
            {
                len++;
            }
            if (len > cnt)
            {
                return sb.toString();
            }
            sb.append(strSource.charAt(i));
        }
        return sb.toString();
    }

    public static String rtrim(String source)
    {
        /*if(source == null) return null;
        for(int i=source.length()-1; i>=0; i--){
            // �뒪�럹�씠�뒪/2Byte�뒪�럹�씠�뒪/�꺆
        	if(source.charAt(i) == ' ' || source.charAt(i) == '���' || source.charAt(i) == '\t'){ 
                continue; 
            }else{
                return source.substring(0,i+1); 
            }
        }*/
        return null;
    }

    /**

     */
    public static int getByteLength(String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals(""))
        {
            return 0;
        }
        int len = 0;
        for (int i = 0; i < strSource.length(); i++)
        {
            if (strSource.charAt(i) > 255)
            {
                len += 2;
            }
            else
            {
                len++;
            }
        }
        return len;
    }


    /**

     */
    public static String removeCharacter(String strSource, char ch)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals(""))
        {
            return "";
        }
        StringBuffer result = new StringBuffer("");
        for (int i = 0; i < strSource.length(); i++)
        {
            if (strSource.charAt(i) != ch)
            {
                result.append(strSource.charAt(i));
            }
        }
        return result.toString();
    }
    
    /**
     */
    public static String removeComma(String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals(""))
        {
            return "0";
        }
        StringBuffer result = new StringBuffer("");
        for (int i = 0; i < strSource.length(); i++)
        {
            if (strSource.charAt(i) != ',')
            {
                result.append(strSource.charAt(i));
            }
        }
        return result.toString();
    }
    
    /**
     */
    public static String removeDesh(String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals(""))
        {
            return "";
        }
        StringBuffer result = new StringBuffer("");
        for (int i = 0; i < strSource.length(); i++)
        {
            if (strSource.charAt(i) != '-')
            {
                result.append(strSource.charAt(i));
            }
        }
        return result.toString();
    }


    /**
     */
    public static String nullToBlank(String strSource)
    {
        return (strSource == null) ? "" : strSource;
    }


    /**
     */
    public static String blankToNull(String strSource)
    {
        if (strSource == null) return null;
        return (strSource.trim().equals("")) ? null : strSource;
    }


    /**
     * 
     * @param int
     * @return String
     */
//    public static String parseString(int nSource)
//    {
////        Integer result = new Integer(nSource);
////        return result.toString();
//        return new DecimalFormat("0").format(nSource);
//    }


    /**
     * 
     * @param int
     * @return String
     */
    public static String parseString(BigDecimal bigdecimal)
    {
//        Integer result = new Integer(nSource);
//        return result.toString();
        return new DecimalFormat("0.##").format(bigdecimal);
    }


    /**

     * @param double
     * @return String
     */
    public static String parseString(double dSource)
    {
//        Double result = new Double(dSource);
//        return result.toString(arg0) .toString();
//        return Double.toString(dSource);
        return new DecimalFormat("0.##########").format(dSource);
    }


    /**

     * @return String
     * @param int
     */
    public static int parseInt(String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        int result = Integer.parseInt(removeComma(strSource));
        return result;
    }


    /**

     * @return String
     * @param int
     */
    public static int parseInt(String strSource, int nDefault)
    {
        strSource = (strSource == null) ? "" : strSource;
        try
        {
            return Integer.parseInt(removeComma(strSource));
        }
        catch (NumberFormatException e)
        {
            return nDefault;
        }
    }


    /**

     * @return String
     * @param double
     */
    public static double parseDouble(String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        return Double.parseDouble(removeComma(strSource));
    }


    /**

     * @return String
     * @param double
     */
    public static double parseDouble(String strSource, double dDefault)
    {
        strSource = (strSource == null) ? "" : strSource;
        try
        {
            return Double.parseDouble(removeComma(strSource));
        }
        catch (NumberFormatException e)
        {
            return dDefault;
        }
    }


    /**
     */
    public static String encodeKorean(String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        try
        {
            return (strSource.equals("")) ? "" : new String(strSource.getBytes("8859_1"), "KSC5601");
        }
        catch (UnsupportedEncodingException e)
        {
            return strSource;
        }
    }


    /**
     */
    public static String[] encodeKorean(String strSource[])
    {
        if (strSource == null)
        {
            return null;
        }
        for (int i = 0; i < strSource.length; i++)
        {
            strSource[i] = (strSource[i] == null) ? "" : strSource[i];
            try
            {
                strSource[i] = new String(strSource[i].getBytes("8859_1"), "KSC5601");
            }
            catch (UnsupportedEncodingException e)
            {
            }
        }
        return strSource;
    }


    /**
     */
    public static String decodeKorean(String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        try
        {
            return (strSource.equals("")) ? "" : new String(strSource.getBytes("KSC5601"), "8859_1");
        }
        catch (UnsupportedEncodingException e)
        {
            return strSource;
        }
    }


    /**
     */
    public static String encodeUrl(String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        try
        {
            return (strSource.equals("")) ? "" : java.net.URLEncoder.encode(strSource, "KSC5601");
        }
        catch (UnsupportedEncodingException e)
        {
            return strSource;
        }
    }


    /**
     */
    public static String decodeUrl(String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals(""))
        {
            return "";
        }
        ByteArrayOutputStream result = new ByteArrayOutputStream(strSource.length());
        for (int i = 0; i < strSource.length(); i++)
        {
            int c = (int) strSource.charAt(i);
            if (c == '+')
            {
                result.write(' ');
            }
            else if (c == '%')
            {
                int c1 = Character.digit(strSource.charAt(++i), 16);
                int c2 = Character.digit(strSource.charAt(++i), 16);
                result.write((char) (c1 * 16 + c2));
            }
            else
            {
                result.write(c);
            }
        }
        return result.toString();
    }


    /**
     */
    public static double formatDouble(double d, int point) {
        double result = -1;
        int n = (int)(d * Math.pow(10, point));

        if ( (n % 10) >= 5 ) {
            int nn = (n / 10 + 1) * 10;
            result =  (double)nn / Math.pow(10, point);
        }
        else {
            int nn = n / 10;
            result = (double)nn / Math.pow(10, point - 1);
        }
        return result;
    }    
    
    /**
     */
    public static String formatDouble(String pattern, double dSource)
    {
        return new DecimalFormat(pattern).format(dSource);
    }


    /**
     */
    public static String formatDouble(String pattern, String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals("") || strSource.equals("NaN"))
        {
            return "";
        }
        return new DecimalFormat(pattern).format(parseDouble(strSource));
    }


    /**
     * @param double
     */
    public static String formatKrw(double dSource)
    {
        return new DecimalFormat("#,##0").format(dSource);
    }


    /**
     * @param double
     */
    public static String formatKrw(String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals("") || strSource.equals("NaN"))
        {
            return "";
        }
        return new DecimalFormat("#,##0").format(parseDouble(strSource));
    }


    /**
     * @param double
     * @param double
     *        �떒�쐞
     */
    public static String formatKrw(double dSource, double divide)
    {
        return new DecimalFormat("#,##0").format(dSource / divide);
    }


    /**
     * @param double
     * @param double
     *        �떒�쐞
     */
    public static String formatKrw(String strSource, double divide)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals("") || strSource.equals("NaN"))
        {
            return "";
        }
        return new DecimalFormat("#,##0").format(parseDouble(strSource) / divide);
    }


    /**
     * @param String
     * @return String
     */
    public static String formatPercent(String strSource)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals("") || strSource.equals("NaN"))
        {
            return "";
        }
        return new DecimalFormat("#,##0.00").format(parseDouble(strSource));
    }


    /**

     * @param double
     * @return String
     */
    public static String formatPercent(double dSource)
    {
        return formatPercent(parseString(dSource));
    }


    /**

     * @param String
     * @return String
     */
    public static String formatPercent(String strSource, double dMulti)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals("") || strSource.equals("NaN"))
        {
            return "";
        }
        return new DecimalFormat("#,##0.00").format(parseDouble(strSource) * dMulti);
    }


    /**
     * @param double
     * @return String
     */
    public static String formatPercent(double dSource, double dMulti)
    {
        return formatPercent(parseString(dSource), dMulti);
    }


    /**
     *
     */
    public static String divString(String strSource, double divide)
    {
        strSource = (strSource == null) ? "" : strSource;
        if (strSource.equals("") || strSource.equals("NaN"))
        {
            return "";
        }
        return parseString(parseDouble(strSource) / divide);
    }


    /**

     * @param String
     *        source
     * @param char
     *        terminal character
     * @return String[]
     */
    public static String[] split(String str, char chr)
    {
        return split(str, chr, false);
    }


    /**

     * @param String
     *        source
     * @param char
     *        terminal character
     * @param boolean
     *        hasTrailingCharacter
     * @return String[]
     */
    public static String[] split(String strRow, char chr, boolean trailing)
    {
        if (strRow == null)
        {
            return null;
        }
        int intPos;
        if (!trailing)
        {
            strRow += chr;
        }
        List<String> list = new ArrayList<String>();
        while ((intPos = strRow.indexOf(chr)) >= 0)
        {
            list.add(strRow.substring(0, intPos));
            strRow = strRow.substring(intPos + 1);
        }
        String as[] = new String[list.size()];
		return (String[]) list.toArray(as);
    }


    /**

     * @param String
     * @param String
     * @param boolean
     *        hasTrailingCharacter
     * @return String[]
     */
    public static String[] split(String strRow, String str, boolean trailing)
    {
        if (strRow == null)
        {
            return null;
        }
        int intPos, intLen = str.length();
        if (!trailing)
        {
            strRow += str;
        }
        List<String> list = new ArrayList<String>();
        while ((intPos = strRow.indexOf(str)) >= 0)
        {
            list.add(strRow.substring(0, intPos));
            strRow = strRow.substring(intPos + intLen);
        }
        String[] arrRow = new String[list.size()];
        for (int i = 0; i < arrRow.length; i++)
        {
            arrRow[i] = (String) list.get(i);
        }
        return arrRow;
    }


    /**
     * extractFactor
     * 
     * @param strRow
     *        "name=xxx value=yyy"
     * @param strPattern
     *        "name=? value=?"
     * @return array of factors
     */
    public static String[] extractFactor(String strRow, String strPattern)
    {
        try
        {
            String buf = strRow;
            List<String> list = new ArrayList<String>();
            String[] arrReg = split(strPattern, '?');
            int intLoop, intPos, intLen;
            intLoop = arrReg.length;
            for (int i = 0; i < intLoop; i++)
            {
                intPos = buf.indexOf(arrReg[i]);
                intLen = arrReg[i].length();
                if (intLen < 1 && i == arrReg.length - 1)
                {
                    intPos = buf.length();
                }
                if (i > 0)
                {
                    list.add(buf.substring(0, intPos));
                }
                buf = buf.substring(intPos + intLen);
            }
            String[] arrRow = new String[list.size()];
            for (int i = 0; i < arrRow.length; i++)
            {
                arrRow[i] = (String) list.get(i);
            }
            return arrRow;
        }
        catch (Exception e)
        {
            return null;
        }
    }


    /**

     * @return String
     */
    public static String fillSpace(String strSource, int max)
    {
        strSource = (strSource == null) ? "" : strSource;
        for (int i = strSource.length(); i < max; i++)
        {
            strSource += " ";
        }
        return strSource;
    }

    /**

     * @return String
     */
    public static String fillZero(String strSource, int max)
    {
        strSource = (strSource == null) ? "" : strSource;
        for (int i = strSource.length(); i < max; i++)
        {
            strSource += "0";
        }
        return strSource;
    }


    /**

     * @return String
     */
    public static String fillFrontZero(String strSource, int max)
    {
        strSource = (strSource == null) ? "" : strSource;
        for (int i = strSource.length(); i < max; i++)
        {
            strSource = "0"+strSource;
        }
        return strSource;
    }
    
    
    /**

     */
    public static String getObjectName(Object obj)
    {
        return (obj.getClass()).getName();
    }


    /**

     */
    public static String redirctByScript(String strUrl)
    {
        String result = "";
        result += "<SCRIPT languasge=\"javascript\">\n";
        result += "<!--\n";
        result = result + "\tlocation.href=\"" + strUrl + "\";\n";
        result += "-->\n";
        result += "</SCRIPT>";
        return result;
    }


    // ljy's CMCommon
    public static void sendData(OutputStreamWriter writer, String str) throws IOException
    {
        writer.write(new DecimalFormat("0000").format(str.length()) + str);
        writer.flush();
    }


    // ljy's CMCommon
    private static String recvN(InputStreamReader reader, int Count) throws IOException
    {
        String str = "";
        for (int i = 0; i < Count; i++)
        {
            str = str + (char) reader.read();
        }
        return str;
    }


    // ljy's CMCommon
    public static String receiveData(InputStreamReader reader) throws IOException
    {
        int len = Integer.parseInt(recvN(reader, 4));
        return recvN(reader, len);
    }



    // 20030728 -> 2003-07-28
    public static String formatDate(String strDate)
    {
        if (strDate == null || strDate.equals(""))
        {
            return strDate;
        }
        String tmp = strDate;
        tmp = replaceString(tmp, " ", "");
        tmp = replaceString(tmp, "/", "");
        tmp = replaceString(tmp, "-", "");
        tmp = replaceString(tmp, ":", "");
        if (tmp.length() == 8)
        {
            return tmp.substring(0, 4) + "-" + tmp.substring(4, 6) + "-" + tmp.substring(6, 8);
        }
        return strDate;
    }

    public static String formatDate(String strDate , String kind)
    {
        if (strDate == null || strDate.equals(""))
        {
            return strDate;
        }
        String tmp = strDate;
        tmp = replaceString(tmp, " ", "");
        tmp = replaceString(tmp, "/", "");
        tmp = replaceString(tmp, "-", "");
        tmp = replaceString(tmp, ":", "");
        if (tmp.length() == 8)
        {
        	if (kind.equals("/"))
        	{
                return tmp.substring(0, 4) + "/" + tmp.substring(4, 6) + "/" + tmp.substring(6, 8);
        	}
        	else
        	{
                return tmp.substring(0, 4) + "-" + tmp.substring(4, 6) + "-" + tmp.substring(6, 8);        		
        	}
        }
        return strDate;
    }



    // 20030728123059 -> 2003-07-28 12:30:59
    public static String formatDateTime(String strDateTime)
    {
        if (strDateTime == null || strDateTime.equals(""))
        {
            return strDateTime;
        }
        String tmp = strDateTime;
        tmp = replaceString(tmp, " ", "");
        tmp = replaceString(tmp, "/", "");
        tmp = replaceString(tmp, "-", "");
        tmp = replaceString(tmp, ":", "");
        if (tmp.length() == 14)
        {
            return tmp.substring(0, 4) + "-" + tmp.substring(4, 6) + "-" + tmp.substring(6, 8) + " " + tmp.substring(8, 10) + ":" + tmp.substring(10, 12) + ":" + tmp.substring(12, 14);
        }
        return strDateTime;
    }


    // 0175767008 -> 017-576-7008
    public static String formatPhoneNumber(String strNumber)
    {
        if (strNumber == null || strNumber.equals(""))
        {
            return strNumber;
        }
        String tmp = strNumber;
        tmp = replaceString(tmp, " ", "");
        tmp = replaceString(tmp, "/", "");
        tmp = replaceString(tmp, "-", "");
        tmp = replaceString(tmp, ":", "");
        if (tmp.length() == 7)
        {
            return tmp.substring(0, 3) + "-" + tmp.substring(3, 7);
        }
        else if (tmp.length() == 8)
        {
            return tmp.substring(0, 4) + "-" + tmp.substring(4, 8);
        }
        else if (tmp.length() == 9)
        {
            return tmp.substring(0, 2) + "-" + tmp.substring(2, 5) + "-" + tmp.substring(5, 9);
        }
        else if (tmp.length() == 10)
        {
            if (tmp.substring(0, 2).equals("02"))
            {
                return tmp.substring(0, 2) + "-" + tmp.substring(2, 6) + "-" + tmp.substring(6, 10);
            }
            else
            {
                return tmp.substring(0, 3) + "-" + tmp.substring(3, 6) + "-" + tmp.substring(6, 10);
            }
        }
        else if (tmp.length() == 11)
        {
            return tmp.substring(0, 3) + "-" + tmp.substring(3, 7) + "-" + tmp.substring(7, 11);
        }
        else if (tmp.length() == 12)
        {
            return tmp.substring(0, 4) + "-" + tmp.substring(4, 8) + "-" + tmp.substring(8, 12);
        }
        return strNumber;
    }



    public static String chkNull(String strVal, String init)
    {
        if(strVal != null)
        {
            if(strVal.equals(""))
                return init;
            else
                return strVal;
        } else
        {
            return init;
        }
    }    
    
    /**
     * @deprecated Use round()
     * @param val
     * @param point
     * @return
     */
    public static double pRound( double val, int point )
    {
    	double retVal = 0;
    	
    	double p = Math.pow( 10, point );
    	retVal = Math.round( val * p ) / p;
    	
    	return retVal;
    }
    
    public static double round( double val, int scale )
    {
    	return BigDecimal.valueOf( val ).setScale( scale, BigDecimal.ROUND_HALF_UP ).doubleValue();
    }
    
    public static double getCurrencyAmtRound( String currency, double amt )
    {
    	double retVal = 0;
    	
    	if ( currency != null )
    	{
    		if ( currency.equals( "KRW" ) )
    		{
    			retVal = pRound( amt, 0);
    		}
    		else
    		{
    			retVal = pRound( amt, 2);
    		}
    	}
    	
    	return retVal;
    }

    /**

     * @return
     */
    public static String concatFilePath(String path1, String path2)
    {
    	return StringUtil.getSystemFilePath(new StringBuilder().append(path1).append(System.getProperty("file.separator")).append(path2).toString());
    }
    
    /**

     * @return
     */
    public static String getSystemFilePath(String path)
    {
		String fs = System.getProperty("file.separator");
		if ("\\".equals(fs))
		{
			fs = "\\\\";
		}
		return path.replaceAll("(\\\\)+", "/").replaceAll("(/)+", fs);
   }
    
    /**

     */
	public static String getCamelString(String columnName)
	{
		StringBuffer sb = new StringBuffer();
		Matcher m = Pattern.compile("[_][a-z]").matcher(columnName.toLowerCase());
		while (m.find())
		{
			m.appendReplacement(sb, m.group().substring(1).toUpperCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**

	 */
	public static String getContraryCamelString(String javaVariable)
	{
		StringBuffer sb = new StringBuffer();
		Matcher m = Pattern.compile("[A-Z]").matcher(javaVariable);
		while (m.find())
		{
			if (m.start() == 0)
			{
				m.appendReplacement(sb, m.group());
			}
			else
			{
				m.appendReplacement(sb, "_" + m.group());
			}
		}
		m.appendTail(sb);
		return sb.toString().toUpperCase();
	}

	/**
	 * @param templetSource
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String getMappedTemplet(String templetSource, Map<String, String> map) throws Exception
	{
        List<String> listVariable = new ArrayList<String>();
           
        Matcher m = Pattern.compile("\\$\\{(\\w+)*}").matcher(templetSource);
        while(m.find())
	    {
        	String variable = m.group().substring(2,m.group().length()-1);
        	if (listVariable.indexOf(variable) < 0)
        	{
        		listVariable.add(variable);
        	}
	    }
        for (int i=0; i<listVariable.size(); i++)
        {
        	String variable = (String) listVariable.get(i);
        	String value = (String) map.get(variable);
        	if (value == null)
        	{
        		throw new Exception("No such variable: " + variable);
        	}
        	templetSource = templetSource.replaceAll("\\$\\{"+ variable +"\\}", value.replace("\\", "\\\\"));
        }
		return templetSource;
	}


    public static void main(String[] args) throws Exception
    {
//    	System.out.println(StringUtil.concatFilePath("c:\\dk\\\\jfs/dlkfs//sldk///////////fjsd\\dlfkjsdf//dlfkjsdf\\\\\\\\\\fsdfsd/dfsdklj\\","/WEB-INF"));
    	
//    	String columnName = "SYSTEM_TYPE_DIV_CD";
//    	System.out.println(columnName +" -> "+ StringUtil.getCamelString(columnName));
//
//    	String javaVariable = "systemTypeDivCd";
//    	System.out.println(javaVariable +" -> "+ StringUtil.getContraryCamelString(javaVariable));
    	
//    	//test getMappedTemplet()
//    	String templet = "/m2/samImptVirtlHttp.do?filePath=${filePath}";
//    	String filePath = "D:\\DEV\\ECM\\HANA\\sam\\Archive\\200912_00_9.�떆�궛�몴.dat";
//    	Map map = new HashMap();
//    	map.put("filePath", filePath);
//    	String b = getMappedTemplet(templet, map);
//    	System.out.println(b);
    	
    	// test split()
    	String a = "a,b,c,d";
    	String b[] = split(a, ',');
    	System.out.println(b.length);
    }


}
