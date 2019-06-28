package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

    public static Date parse(String date) throws ParseException {
        return FORMAT.parse(date);
    }

    public static Date parse(String date, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }

    public static String format(Date date) {
        if(date == null)
            throw new RuntimeException("Date is null");
        String dateString = FORMAT.format(date);
        return dateString;
    }
    
    public static String returnDateFormat(Date date) {
        if(date == null)
            return "Not Returned yet";
        String dateString = FORMAT.format(date);
        return dateString;
    }

    public static String format(Date date, String format) {
        if(date == null)
            throw new RuntimeException("Date is null");
        String dateString =  new SimpleDateFormat(format).format(date);
        return dateString;
    }   

}
