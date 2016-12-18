package controllers;

import elasticsearch.ElasticsearchController;
import jsonEntities.JsonApps;
import jsonEntities.JsonStackTrace;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Александр on 27.11.2016.
 */
@Controller
public class ViewController {

    @RequestMapping(value = {"/", "/login", "/loginfailed"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/hello"}, method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "redirect:login";
    }

    @RequestMapping(value = "/topFiveApps", method = RequestMethod.GET)
    public ModelAndView topFiveApps() throws Exception {
        ElasticsearchController esc = new ElasticsearchController();
        List<JsonApps> list = esc.getTopBrokenApps(5);
        ModelAndView modAndView = new ModelAndView("/topFiveApps");
        modAndView.addObject("brokenApps",list);
        return modAndView;
    }

    @RequestMapping(value = "/topApps", method = RequestMethod.GET)
    public ModelAndView topApps() throws Exception {
        ElasticsearchController esc = new ElasticsearchController();
        List<JsonApps> list = esc.getTopBrokenApps();
        ModelAndView modAndView = new ModelAndView("/topApps");
        modAndView.addObject("brokenApps",list);
        return modAndView;
    }

    @RequestMapping(value = "/topFiveErrors", method = RequestMethod.GET)
    public ModelAndView topFiveErrors() throws Exception {
        ElasticsearchController esc = new ElasticsearchController();
        List<JsonStackTrace> list = esc.getTopErrors(5);
        ModelAndView modAndView = new ModelAndView("/topFiveErrors");
        modAndView.addObject("errors", list);
        return modAndView;
    }

    @RequestMapping(value = "/topErrors", method = RequestMethod.GET)
    public ModelAndView topErrors() throws Exception {
        ElasticsearchController esc = new ElasticsearchController();
        List<JsonStackTrace> list = esc.getTopErrors();
        ModelAndView modAndView = new ModelAndView("/topErrors");
        modAndView.addObject("errors", list);
        return modAndView;
    }

    @RequestMapping(value = "/searchResults", method = RequestMethod.GET)
    public ModelAndView searchResults(String kusok) throws Exception{
        ElasticsearchController esc = new ElasticsearchController();
        //List<JsonApps> list = esc.getAppByStacktraceSubstring(kusok);
        List<JsonApps> list = esc.getTopBrokenApps();
        ModelAndView modelAndView = new ModelAndView("/searchResults");
        modelAndView.addObject("searchResults", list);
        return modelAndView;
    }

}
