package com.ingressou.Ingressou.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError() {
        ModelAndView modelAndView = new ModelAndView("error"); // O nome da view que você vai usar
        modelAndView.addObject("message", "Página não encontrada. Verifique a URL.");
        return modelAndView;
    }

    // Método removido, pois não é mais necessário no Spring Boot 2.3+
}

