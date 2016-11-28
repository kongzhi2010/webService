package husd.framework.model;

public enum CookieEnum {

    USER_COOKIE("_uc", "判断是否登陆的cookie");

    private CookieEnum(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    private String name;

    private String msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
