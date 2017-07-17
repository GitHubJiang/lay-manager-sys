package com.lay.shop.modules.sys.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.lay.shop.common.constants.SystemConstants;
import com.lay.shop.common.exception.BusinessException;
import com.lay.shop.common.exception.ErrorCodes;
import com.lay.shop.common.utils.StringEncrypt;
import com.lay.shop.common.utils.Validator;
import com.lay.shop.common.web.BaseController;
import com.lay.shop.common.web.Result;
import com.lay.shop.modules.sys.command.UserCommand;
import com.lay.shop.modules.sys.service.UserService;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 上午9:58:37
 * @since
 */
@Controller
@RequestMapping("/sys/user/")
public class UserController extends BaseController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value={"login"})
    @ResponseBody
    public Result<UserCommand> login(UserCommand userCommand,HttpServletRequest request) {
        Result<UserCommand> result = new Result<>();
        userCommand.setLoginPassword(StringEncrypt
            .Encrypt(userCommand.getLoginPassword(), ""));
        UserCommand checkUser = null;
        try {
            checkUser = userService.findUserByLoginNameAndEncryptedPassword(userCommand.getLoginName(), userCommand.getLoginPassword());
            if (Validator.isNullOrEmpty(checkUser)) {
                throw new BusinessException(ErrorCodes.USER_NO);
            } else if (SystemConstants.LIFECYCLE_DISABLE.equals(checkUser.getLifecycle())) {
                throw new BusinessException(ErrorCodes.USER_DISABLE);
            } else {
                request.getSession().setAttribute("user", checkUser);
                logger.info(JSON.toJSONString(request.getSession().getAttribute("user")));
                result.setData(checkUser);
            }
        }catch(BusinessException e){
            result.setCode(e.getValue());
            result.setMsg(e.getMessage());
        }catch (Exception e) {
            logger.error("UserController:{}",e.getMessage());
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }        
        return result;
    }
}
