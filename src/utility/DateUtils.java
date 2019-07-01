package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

	public static Date parse(String date) {
		try {
			return FORMAT.parse(date);
		} catch (ParseException e) {
		}
		return null;
	}

	public static Date parse(String date, String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
		}
		return null;
	}

	public static String format(Date date) {
		if (date == null)
			return null;
		String dateString = FORMAT.format(date);
		return dateString;
	}

	public static String returnDateFormat(Date date) {
		if (date == null)
			return null;
		String dateString = FORMAT.format(date);
		return dateString;
	}

	public static String format(Date date, String format) {
		if (date == null)
			return null;
		String dateString = new SimpleDateFormat(format).format(date);
		return dateString;
	}

}
