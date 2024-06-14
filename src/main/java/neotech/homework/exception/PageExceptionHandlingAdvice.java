package neotech.homework.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.resource.NoResourceFoundException;

@ControllerAdvice
public class PageExceptionHandlingAdvice {

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNotFoundError(Model model) {
        model.addAttribute("errorCode", "404");
        model.addAttribute("message", "Page Not Found");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleInternalError(Model model) {
        model.addAttribute("errorCode", "500");
        model.addAttribute("message", "OOops! Something went wrong");
        return "error";
    }
    
}
