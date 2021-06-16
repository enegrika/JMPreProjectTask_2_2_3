package org.springMVChibernateCRUD.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = {"/", "/index"})
public class WelcomeController {

    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    private static LocalDateTime time;

    //     WELCOME PAGE
    @Value("${msg.title}")
    private String titleString;

    @GetMapping
    public String index(Model model) {

        time = LocalDateTime.now();
        logger.info("welcome page started at {}", time);

        model.addAttribute("title", titleString);
// send parameters to logger message with {} marks
        logger.info("welcome() is executed, at time: {} with username : {}", time, "sergei");

        logger.trace("trace log test");
        logger.debug("debug level log test");
        logger.info("info level log test");
        logger.warn("warning log test");
        logger.error("This is TEST !!!!! Error message");

        logger.info("welcome page ended");

        return "users/index";
    }
}
