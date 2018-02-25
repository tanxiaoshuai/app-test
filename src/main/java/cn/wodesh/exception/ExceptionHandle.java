package cn.wodesh.exception;
import cn.wodesh.config.ResultInfo;
import cn.wodesh.util.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常最终处理类
 * Created by TS on 2017/7/9.
 */
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = Logger.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handle(Exception e){
        logger.error(exceptionInfo(e));
        if(e instanceof FinalException){
            FinalException ex = (FinalException) e;
            return ResultUtil.error(ex.getCode() , ex.getMessage());
        }else {
            return ResultUtil.error(ResultInfo.EXCEPTION_CODE , e.toString());
        }
    }

    public String exceptionInfo(Exception e){
        StringBuffer sb = new StringBuffer();
        sb.append(e.toString());
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement traceElement : trace)
            sb.append("\n\tat ").append(traceElement.toString());
        return sb.toString();
    }

}
