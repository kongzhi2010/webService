package husd.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    private Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected void writeBackJson(HttpServletResponse response, String json) {
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            LOGGER.error("[base controller] error, json is : {} e is: {} ", json, e);
        }
    }
}
