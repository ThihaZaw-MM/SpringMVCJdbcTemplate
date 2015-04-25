package com.mahar.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppUtility {
	
	private static Date getSystemDateTime(){
		return new Date();
	}
	
	public static String timeToString(){
		return "";
	}
	public static String dateToString(){
		return dateToString(getSystemDateTime());
	}
	public static String dateToString(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String year = String.format("%04d", calendar.get(Calendar.YEAR));
		String month = String.format("%02d", calendar.get(Calendar.MONTH) + 1);
		String day = String.format("%02d", calendar.get(Calendar.DATE));
		
		return year + month + day;
	}
	
	public static String dateToString(String date){
		if(date.equals("")) return "";
		if(date == null || date.length() == 0){
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date l_date =  format.parse(date);
			return dateToString(l_date);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String stringToDate(){
		return stringToDate(getSystemDateTime());
	}
	public static String stringToDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String year = String.format("%04d", calendar.get(Calendar.YEAR));
		String month = String.format("%02d", calendar.get(Calendar.MONTH) + 1);
		String day = String.format("%02d", calendar.get(Calendar.DATE));
		return day + "/" + month + "/" + year; 
	}
	public static String stringToDate(String date){
		if(date.equals("")) return "";
		if(date == null || date.length() == 0){
			return "";
		}
		if(date.length() < 8){
			return "";
		}
		String day = date.substring(6, 8);
		String month = date.substring(4, 6);
		String year = date.substring(0, 4);
		return day + "/" + month + "/" + year;
	}
	
	public static String formatDate(String date){
		try {
			if (date == null) return "";
			//System.out.println("in formatDate Method " + date);
			Date l_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			return stringToDate(l_date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static java.sql.Timestamp formatSqlDate(String date){
		try {
			//System.out.println("in formatSqlDate Method " + date);
			if (date == null) return null;
			Date l_date = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			java.sql.Timestamp timestamp = new java.sql.Timestamp(l_date.getTime());
			//System.out.println(timestamp.toString());
			return timestamp;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static java.sql.Timestamp formatSqlDate(){
		Date l_date = new Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(l_date.getTime());
		return timestamp;
	}
}
