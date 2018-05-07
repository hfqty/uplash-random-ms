package me.ning.pro.handler;

import me.ning.pro.common.dto.OutputDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public OutputDTO exceptionHandler(HttpServletRequest req, Exception e){
        OutputDTO outputDTO = new OutputDTO();
        outputDTO.setMsg("系统出现异常");
        outputDTO.setCode("-1");
        return outputDTO;
    }
}
