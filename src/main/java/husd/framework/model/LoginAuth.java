package husd.framework.model;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

public class LoginAuth {

    private String username;
    private String loginSeq;
    private String loginToken;

    public LoginAuth() {}

    public LoginAuth(String username, String loginSeq, String loginToken) {
        this.username = username;
        this.loginSeq = loginSeq;
        this.loginToken = loginToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginSeq() {
        return loginSeq;
    }

    public void setLoginSeq(String loginSeq) {
        this.loginSeq = loginSeq;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public boolean equalsWithAnother(LoginAuth other) {
        if (other == null) {
            return false;
        }
        return StringUtils.isNotBlank(username) && StringUtils.isNotBlank(loginSeq)
                && StringUtils.isNotBlank(loginToken)
                && StringUtils.equals(username, other.getUsername())
                && StringUtils.equals(loginSeq, other.getLoginSeq())
                && StringUtils.equals(loginToken, other.getLoginToken());
    }

    public boolean isPasswordMayBeStolen(LoginAuth other) {
        if (other == null) {
            return false;
        }
        return StringUtils.isNotBlank(username) && StringUtils.isNotBlank(loginSeq)
                && StringUtils.isNotBlank(loginToken)
                && StringUtils.equals(username, other.getUsername())
                && StringUtils.equals(loginSeq, other.getLoginSeq())
                && !StringUtils.equals(loginToken, other.getLoginToken());
    }

}
