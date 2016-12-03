package husd.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import husd.framework.model.EncodeEnum;

/**
 * Created by hushengdong on 16/9/5.
 */
public class HttpUtil {

    public static String getRequestURLWithParam(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String queryString = request.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            url.append("?");
            url.append(queryString);
        }
        return encodeURL(url.toString());
    }

    public static String encodeURL(String url) {
        try {
            url = URLEncoder.encode(url, EncodeEnum.UTF8.getName());
        } catch (UnsupportedEncodingException e) {
            // ignore
        }
        return url;
    }
}
