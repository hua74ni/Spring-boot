package com.landicorp.marketing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landicorp.marketing.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huangdonghua on 09/04/2018.
 */
@RestController
@Scope("prototype")
@RequestMapping("/user")
public class UserController extends BaseActionSupport {
    private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "login.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(HttpServletRequest httpRequest) {
        LOG.info("请求登陆");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            User user = JSON.parseObject(paramsJson.toJSONString(), User.class);
//            List<User> userList = userService.queryUserList(user);
//            if (CollectionUtil.isEmpty(userList)) {
//                buildErrorResponse(ResponseCode.LOGIN_ERROR, result);
//                return result;
//            }
//            HttpSession session = httpRequest.getSession();
//            session.setAttribute(CommonConstant.SESSION_LOGIN_USER, userList.get(0));
//            User loginUser = userList.get(0);
//            Role role = roleService.queryRoleByUser(loginUser);
//            if (role == null) {
//                //数据库操作异常
//                buildErrorResponse(ResponseCode.DATE_ERROR, result);
//                return result;
//            }
//            loginUser.setSessionId(session.getId());
//            loginUser.setRoleType(role.getRoleType());
            buildSuccessResponse("登陆成功", user, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

}
