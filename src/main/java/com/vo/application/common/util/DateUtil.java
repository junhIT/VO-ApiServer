package com.vo.application.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 날짜 관련 공통 
 * @author chatGPT
 */
public class DateUtil {
	 public static final DateTimeFormatter DATE_FORMAT_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
	 public static final DateTimeFormatter DATE_FORMAT_yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    
	 //  주어진 문자열이 지정된 날짜 형식에 맞는지 확인하는 메서드
	 public static boolean isValidFormat(String dateString, DateTimeFormatter dtf) {
		 try {
			 LocalDate.parse(dateString, dtf);
	         return true;
		 } catch (DateTimeParseException e) {
			 return false;
		 }
	 }
	 
	 // 주어진 문자열을 지정된 날짜 형식으로 파싱하여 LocalDate 객체로 변환하는 메서드
	 public static LocalDate parseDate(String dateStr, DateTimeFormatter dtf) {
		 return LocalDate.parse(dateStr, dtf);
	 }

	 // 주어진 LocalDate 객체를 지정된 날짜 형식으로 포맷팅하여 문자열로 변환하는 메서드
	 public static String formatDate(LocalDate date, DateTimeFormatter dtf) {
		 return dtf.format(date);
	 }
	 
//	 public static String getCurrentDate_yyyyMMdd() {
//		 return formatDate(LocalDate.now(), DateUtil.DATE_FORMAT_yyyyMMdd);
//	 }
//	 
//	 public static LocalDate getCurrentDate_yyyy_MM_dd() {
//		return parseDate(getCurrentDate_yyyyMMdd(), DateUtil.DATE_FORMAT_yyyyMMdd);
//	 }
}