package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexp {

    public String findMatch(String input, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        boolean match = m.matches();
        if (match) {
            return m.group();
        }
        return "";
    }

}
