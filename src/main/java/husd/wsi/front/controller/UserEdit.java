package husd.wsi.front.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import husd.wsi.back.pojo.User;
import husd.wsi.back.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserEdit {

	@Resource
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/userEdit")
	public ModelAndView initForm(ModelMap map) {
		//绑定user到页面上的Form表单，参数都通过user传递过来
		User user = new User();
		return new ModelAndView("/user/userEdit").addObject(user);
	}

	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	public String addUserForm(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user/userEdit";
		}
		userService.saveUser(user);
		//处理POST请求之后重定向到结果展示页面，防止表单重复提交。
		return "redirect:" + user.getUsername();
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showUserProfile(@PathVariable String username, Model model) {
		User user = userService.findUser(username);
		if(user==null){
			user = new User();
		}
		model.addAttribute(user);
		return "user/userProfile";
	}

	@RequestMapping(value="verifyName",method=RequestMethod.GET)
	public String verifyIsNameExist(String username,Model model){
		User user = userService.findUser(username);
		if(user == null){
			return "error";
		}
		return "success";
	}
}
