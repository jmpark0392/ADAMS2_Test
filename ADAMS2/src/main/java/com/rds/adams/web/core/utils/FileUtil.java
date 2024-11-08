package com.rds.adams.web.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil
{
    // Make Dir
    public static boolean makeDir(String strPath)
    {
        try
        {
            return new File(strPath).mkdir();
        }
        catch (SecurityException e)
        {
            return false;
        }
    }


    // Delete Dir or File
    public static boolean deleteFile(String strFilename)
    {
        try
        {
            return new File(strFilename).delete();
        }
        catch (SecurityException e)
        {
            return false;
        }
    }


    // Exists Dir or File
    public static boolean existsFile(String strFilename)
    {
        try
        {
            return new File(strFilename).exists();
        }
        catch (SecurityException e)
        {
            return false;
        }
    }


    // Copy File
    public static boolean copyFile(String src, String dest)
    {
        try
        {
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try
            {
                fis = new FileInputStream(new File(src));
                fos = new FileOutputStream(new File(dest));
                byte buf[] = new byte[2048];
                int nread;
                while ((nread = fis.read(buf)) != -1)
                {
                    fos.write(buf, 0, nread);
                }
                return true;
            }
            finally
            {
                fis.close();
                fos.close();
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }

    // filename.subname.exe -> ext (확장자만 가져오기)
    public static String getFileExt(String strFilename)
    {
        if (strFilename == null)
        {
            return "";
        }
        if (strFilename.equals(""))
        {
            return "";
        }
        String arr[] = StringUtil.split(strFilename, '.', false);
        if (arr == null)
        {
            return "";
        }
        return arr[arr.length - 1];
    }


    public static boolean renameFile(String strOldFilename, String strNewFilename)
    {
        File oldFile = new File(strOldFilename);
        return oldFile.renameTo(new File(strNewFilename));
    }
    

}
