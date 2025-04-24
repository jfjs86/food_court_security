package com.pragma.foodcourt.security.domain.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public final class DomainUtils {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    private DomainUtils() {
    }

    public static boolean isValidPhone(String phone, int maxLength) {
        if (isNullOrEmpty(phone)) {
            return false;
        }

        String trimmed = phone.trim();
        return trimmed.length() <= maxLength
                && PHONE_PATTERN.matcher(trimmed).matches()
                && (trimmed.indexOf('+') <= 0);
    }

    public static boolean isAdult(LocalDate birthdate, int minimumAge) {
        if (birthdate == null) {
            return false;
        }

        return Period.between(birthdate, LocalDate.now()).getYears() >= minimumAge;
    }

    public static boolean validateEmail(String email) {
        return !isNullOrEmpty(email) && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isNumber(String number) {
        return number != null && NUMBER_PATTERN.matcher(number).matches();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
