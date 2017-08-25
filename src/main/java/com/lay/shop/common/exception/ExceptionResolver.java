package com.lay.shop.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;



/**
 * @author dianchao.song
 */
public class ExceptionResolver extends SimpleMappingExceptionResolver{

	private static final Logger	log	= LoggerFactory.getLogger(ExceptionResolver.class);

	@Autowired
	private MessageSource		messageSource;

	@Override
	protected ModelAndView getModelAndView(String viewName,Exception exception,HttpServletRequest request){
		ModelAndView mv = new ModelAndView(viewName);
		Map<String, Object> exceptionMap = new HashMap<String, Object>();
		Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>(1);
		if (exception instanceof BusinessException){
			BusinessException businessException = (BusinessException) exception;
			exception = encode(businessException);
			exceptionMap.put("statusCode", ((BusinessException) exception).getValue());
			exceptionMap.put("message", exception.getMessage());
			exceptionMap.put("linkedException", ((BusinessException) exception).getLinkedException());
			
			if(log.isDebugEnabled()){
				log.error("business exception!",exception);
			}
			
		}else if (exception instanceof ValidateException) {//如果是通用服务端验证的异常
			ValidateException ve = (ValidateException) exception;
			
			exceptionMap.put("statusCode", ErrorCodes.PARAMS_ERROR);
			String message=null;
			if(ve.getFullKey()!=null){
				Object[] args = ve.getArgs();
				message= messageSource.getMessage(ve.getFullKey(), args,  LocaleContextHolder.getLocale());
			}
			else{
				message =messageSource.getMessage(ve.getFieldNameKey(), null, LocaleContextHolder.getLocale());
				Object[] args = ve.getArgs();
				message+= messageSource.getMessage(ve.getErrorTipKey(), args,  LocaleContextHolder.getLocale());
			}
			exceptionMap.put("message", message);
			/*Writer w = new StringWriter();
			exception.printStackTrace(new PrintWriter(w));
			exceptionMap.put("stackTrace", w.toString());*/
			
			if(log.isDebugEnabled()){
				log.error("validation exception!",exception);
			}
		}
		else if (exception instanceof BindException) {
			BindException be = (BindException) exception;
			List<ObjectError> allErrors = be.getAllErrors();
			ObjectError err = allErrors.get(0);
			String msg = err.getDefaultMessage();
			exceptionMap.put("statusCode", ErrorCodes.DATA_BIND_EXCEPTION);
			exceptionMap.put("message", msg);
			Writer w = new StringWriter();
			exception.printStackTrace(new PrintWriter(w));
			exceptionMap.put("stackTrace", w.toString());
			
			if(log.isInfoEnabled()){
				log.error("bind exception!",exception);
			}
		}
		else{
			BusinessException runtimeException = encode(new BusinessException(ErrorCodes.SYSTEM_ERROR));
			exceptionMap.put("statusCode", runtimeException.getValue());
			exceptionMap.put("message", runtimeException.getMessage());
			Writer w = new StringWriter();
			exception.printStackTrace(new PrintWriter(w));
			exceptionMap.put("stackTrace", w.toString());
			
			if(log.isErrorEnabled()){
				log.error("runtime exception!",exception);
			}
		}
		if (request.getHeader("X-Requested-With") != null){
			mv.setView(new MappingJackson2JsonView());
		}
		result.put("exception", exceptionMap);
		mv.addAllObjects(result);
		
		return mv;
	}

	private BusinessException encode(BusinessException businessException){
		String errorCode = businessException.getValue();
		String message = null;
		Object[] args = businessException.getArgs();
		if(!ErrorCodes.SUCCESS.getValue().equals(errorCode)){
			String key = ErrorCodes.BUSINESS_EXCEPTION_PREFIX + errorCode;
			message = messageSource.getMessage(key, args, key, LocaleContextHolder.getLocale());
		}else{
			message = businessException.getMessage();
		}
		
		
		BusinessException result = new BusinessException(errorCode, message);
		result.setArgs(args);
		if (businessException.getLinkedException() != null){
			result.setLinkedException(encode(businessException.getLinkedException()));
		}
		return result;
	}
}
