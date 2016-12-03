package husd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 这个类主要用来处理用户账号管理等信息，例如注册、忘记密码、修改密码等。
 * 
 * @author hushengdong
 *
 */
@Controller
@RequestMapping("/account/manage")
public class AccountController extends BaseController {

    @RequestMapping("register/toPage")
    public String toRegisterPage() {
        return "account/register";
    }

    @RequestMapping("forget/password/toPage")
    public String toForgetPasswordPage() {
        return "account/forget_password";
    }

    @RequestMapping("reset/password/toPage")
    public String toResetPasswordPage() {
        return "account/reset_password";
    }

    @RequestMapping("register")
    @ResponseBody
    public String register() {
        return "account/reset_password";
    }

    @RequestMapping("reset/password")
    @ResponseBody
    public String resetPassword() {
        return "account/reset_password";
    }
}
