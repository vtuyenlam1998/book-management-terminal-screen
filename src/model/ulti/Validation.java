package model.ulti;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static Pattern pattern;
    private static final String USER_NAME_REGEX = "^[a-zA-Z0-9_]{4,}$";
    private static final String FULL_NAME_REGEX = "^[a-zA-Z\\sàáạã_-]{3,25}$";
    private static final String PHONE_NUMBER_REGEX = "^\\+?(?:\\d{2})?[0]\\d{9}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@])[A-Za-z\\d@]{8,}$";
    private static final String EMAIL_REGEX  = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String DATE_REGEX  = "^(19|20)\\d{2}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])\\s([01]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
    public static boolean validate(String regex, String regexPattern) {
        switch (regexPattern) {
            case "USER_NAME" -> pattern = Pattern.compile(USER_NAME_REGEX);
            case "FULL_NAME" -> pattern = Pattern.compile(FULL_NAME_REGEX);
            case "EMAIL" -> pattern = Pattern.compile(EMAIL_REGEX);
            case "PHONE_NUMBER" -> pattern = Pattern.compile(PHONE_NUMBER_REGEX);
            case "DATE" -> pattern = Pattern.compile(DATE_REGEX);
            case "PASSWORD" -> pattern = Pattern.compile(PASSWORD_REGEX);
        }
        Matcher matcher = pattern.matcher(regex);
        return !matcher.matches();
    }


}
