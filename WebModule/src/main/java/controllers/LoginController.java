package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Александр on 27.11.2016.
 */
@Controller
public class LoginController {

    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model){
        model.addAttribute("message", "Say welcome!");
        return "hello";
    }*/

    @RequestMapping(value = {"/", "/login", "/loginfailed", "/logout"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
