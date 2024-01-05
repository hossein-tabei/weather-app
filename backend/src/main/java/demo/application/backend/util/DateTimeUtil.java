package demo.application.backend.util;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {

	public static String convertEpochToStringDate(long epochDate) {
		Instant inputDate = Instant.ofEpochMilli(epochDate).truncatedTo(ChronoUnit.DAYS);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		return formatter.format(Date.from(inputDate));
	}
	
	public static String getDayOfWeekFromEpochDate(long epochDate) {

		Instant inputDate = Instant.ofEpochMilli(epochDate).truncatedTo(ChronoUnit.DAYS);
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = inputDate.atZone(zoneId).toLocalDateTime();
		
		DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
		return 	dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US);
	}
	
	public static boolean isToday(long epochDate) {
		Instant inputDate = Instant.ofEpochMilli(epochDate).truncatedTo(ChronoUnit.DAYS);
		Instant today = Instant.now().truncatedTo(ChronoUnit.DAYS);
		return inputDate.equals(today);
	}
	
	public static boolean isTomorrow(long epochDate) {
		Instant inputDate = Instant.ofEpochMilli(epochDate).truncatedTo(ChronoUnit.DAYS);
		Instant tomorrow = Instant.now().truncatedTo(ChronoUnit.DAYS).plus(1, ChronoUnit.DAYS);
		return inputDate.equals(tomorrow);
	}
	
	public static long getEpochTimeFromToday(int offsetFromToday) {
		Instant today = Instant.now().truncatedTo(ChronoUnit.DAYS).plus(offsetFromToday, ChronoUnit.DAYS);
		return today.toEpochMilli();
	}
	
}
