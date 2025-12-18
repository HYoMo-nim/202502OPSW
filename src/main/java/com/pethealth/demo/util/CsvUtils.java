package com.pethealth.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CsvUtils {

    // CSV 한 줄을 쉼표(,)로 쪼개는 기능 (따옴표 안의 쉼표는 무시)
    public static String[] parseLine(String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    }

    // 날짜+시간 변환 (yyyy-MM-dd HH:mm:ss)
    public static LocalDateTime parseDateTime(String text) {
        if (text == null || text.isBlank()) return null;
        String cleanText = removeQuotes(text);
        try {
            // 날짜 형식에 따라 유연하게 처리
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(cleanText, formatter);
        } catch (Exception e) {
            return LocalDateTime.now();
        }
    }

    // 날짜 변환 (yyyy-MM-dd)
    public static LocalDate parseDate(String text) {
        if (text == null || text.isBlank()) return null;
        String cleanText = removeQuotes(text);
        try {
            return LocalDate.parse(cleanText);
        } catch (Exception e) {
            return LocalDate.now();
        }
    }

    // 따옴표 제거 ("강아지" -> 강아지)
    public static String removeQuotes(String text) {
        if (text == null) return "";
        return text.replace("\"", "").trim();
    }
}