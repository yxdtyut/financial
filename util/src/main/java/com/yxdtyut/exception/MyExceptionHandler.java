package com.yxdtyut.exception;

import com.yxdtyut.result.CodeMsg;
import com.yxdtyut.result.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :   异常处理
 * @Date : 上午10:42 2018/6/7
 */
@ControllerAdvice
public class MyExceptionHandler {

//    @ExceptionHandler(GlobleException.class)
//    @ResponseBody
//    public Result<String> handler(Exception e) {
//        if (e instanceof GlobleException) {
//            GlobleException exception = (GlobleException) e;
//            CodeMsg cm = exception.getCm();
//            return Result.error(cm);
//        } else {
//            return Result.error(CodeMsg.SERVER_ERROR);
//        }
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public Result<String> handlerNotValid(Exception e) {
//        if (e instanceof MethodArgumentNotValidException) {
//            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
//            BindingResult bindingResult = exception.getBindingResult();
//            FieldError fieldError = bindingResult.getFieldError();
//            CodeMsg codeMsg = CodeMsg.PARAM_ERROR;
//            codeMsg.setMsg(fieldError.getField() + fieldError.getDefaultMessage());
//            return Result.error(codeMsg);
//        }
//        return Result.error(CodeMsg.SERVER_ERROR);
//    }

    @ExceptionHandler(GlobleException.class)
    public String handler(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        if (e instanceof GlobleException) {
            GlobleException exception = (GlobleException) e;
            CodeMsg cm = exception.getCm();
            map.put("errorMessage", cm);
        } else {
            map.put("errorMessage", CodeMsg.SERVER_ERROR);
        }
        request.setAttribute("ext", map);
        request.setAttribute("javax.servlet.error.status_code",555);
        return "forward:/error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handlerNotValid(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
        BindingResult bindingResult = exception.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        CodeMsg codeMsg = CodeMsg.PARAM_ERROR;
        codeMsg.setMsg(fieldError.getField() + fieldError.getDefaultMessage());
        map.put("errorMessage", codeMsg);
        request.setAttribute("ext", map);
        request.setAttribute("javax.servlet.error.status_code",444);
        return "forward:/error";

    }
}
