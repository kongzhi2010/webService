package husd.framework.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class NumberUtil {

    static String regx = "[\\+\\-]?[\\d]+([\\.][\\d]*)?([Ee][+-]?[\\d]+)?";// 科学计数法正则表达式
    static Pattern pattern = Pattern.compile(regx);

    public static boolean isENum(String num) {
        if (StringUtils.isBlank(num)) {
            return false;
        }
        return pattern.matcher(num).matches();
    }
}
