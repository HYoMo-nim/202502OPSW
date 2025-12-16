package com.pethealth.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CsvUtils {

    // CSV 한 줄을 쉼표(,)로 쪼개는 기능 (따옴표 안의 쉼표는 무시)
    public static String[] parseLine(String line) {
        // 복잡한 정규식: 쉼표로 나누되, 따옴표(")로 감싸진 쉼표는 건드리지 않음
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
            return LocalDateTime.now(); // 에러나면 현재 시간
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