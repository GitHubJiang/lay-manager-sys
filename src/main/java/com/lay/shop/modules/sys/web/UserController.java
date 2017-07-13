package com.lay.shop.modules.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.shop.common.constants.SystemConstants;
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
    public Result<UserCommand> login(UserCommand userCommand) {
        Result<UserCommand> result = new Result<>();
        userCommand.setLoginPassword(StringEncrypt
            .Encrypt(userCommand.getLoginPassword(), ""));
        UserCommand checkUser = null;
        try {
            checkUser = userService.findUserByLoginNameAndEncryptedPassword(userCommand.getLoginName(), userCommand.getLoginPassword());
            if (Validator.isNullOrEmpty(checkUser)) {
                result.setCode(ErrorCodes.USER_NO.getValue());
                result.setMsg(ErrorCodes.USER_NO.getMsg());
            } else if (SystemConstants.LIFECYCLE_DISABLE.equals(checkUser.getLifecycle())) {
                result.setCode(ErrorCodes.USER_DISABLE.getValue());
                result.setMsg(ErrorCodes.USER_DISABLE.getMsg());
            } else {
                result.setData(checkUser);
            }
        } catch (Exception e) {
            result.setCode(ErrorCodes.RESULT_NO.getValue());
            result.setMsg(ErrorCodes.RESULT_NO.getMsg());
        }        
        return result;
    }
}
