package com.example.demo.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

public class Util {
    public static String generateId(String entityName, int id) {
        String prefix = "";
        String suffix = "";
        switch(entityName) {
            case "Admin": prefix = "A"; break;
            case "Appointment": prefix = "AP"; break;
            case "BDCentre": prefix = "BD"; break;
            case "Campaign": prefix = "CA"; break;
            case "Donor": prefix = "D"; break;
            case "Record": prefix = "RC"; break;
            case "Registration": prefix = "RF"; break;
            case "Staff": prefix = "ST"; break;
        }

        for(int i = 0; i < (5-String.valueOf(id).length()); i++)
            suffix += "0";

        suffix += String.valueOf(id);

        return prefix + suffix;
    }

    public static Date setZeroTime(final Date d)
    {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        for(final int p : new int[] {Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND})
        {
            cal.set(p, cal.getMinimum(p));
        }
        return cal.getTime();
    }

    public static Date setMaxTime(final Date d)
    {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        for(final int p : new int[] {Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND})
        {
            cal.set(p, cal.getMaximum(p));
        }
        return cal.getTime();
    }



    public static String convertToHex(final byte[] data)
    {
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }


    public static String getStackTrace(final Throwable aThrowable)
    {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    public static <T> T[][] split(final T[] values, final int size, final T[][] stub)
    {
        final List<T[]> ret = new ArrayList<T[]>();
        if(values.length > size)
        {

            for(int i=0;i<values.length;i+=size)
            {
                final int index = i + size;
                final T[] parts = Arrays.copyOfRange(values, i, (index>=values.length) ? values.length : index);
                ret.add(parts);
            }
        }else
        {
            ret.add(values);
        }
        return ret.toArray(stub);
    }
}
