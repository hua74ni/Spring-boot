package com.landicorp.marketing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landicorp.marketing.constant.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class BaseActionSupport {

    protected Logger mLogger = LoggerFactory.getLogger(this.getClass());

    protected HttpServletResponse response;

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    protected JSONObject getRequestParamsJson(HttpServletRequest httpRequest) {
        try {
            Enumeration<String> keys = httpRequest.getParameterNames();
            if(keys.hasMoreElements()){
                String key = keys.nextElement();
                return JSONObject.parseObject(key);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回成功响应结果给前端
     *
     * @param message 信息
     * @param data    数据
     * @return
     */
    protected void buildSuccessResponse(String message, Object data, JSONObject result) {
        try {
            mLogger.info("请求信息成功 message=" + message + ",data=" + JSON.toJSONString(data));
            result.put("code", ResponseCode.SUCCESS);
            result.put("message", message);
            result.put("handle", "");
            result.put("data", data);
        } catch (Exception e) {
            mLogger.error("拼接成功请求返回值异常", e);
        }
    }

    /**
     * 拼接错误信息给前端
     *
     * @param code 错误码
     * @return
     */
    protected void buildErrorResponse(int code, JSONObject errResult) {
        try {
            mLogger.info("请求信息失败 code=" + code);
            errResult.put("code", code);
            switch (code) {
                case ResponseCode.PARAM_ERROR:
                    errResult.put("message", "参数错误");
                    errResult.put("handle", "请重新确认参数！");
                    break;
                case ResponseCode.DATE_ERROR:
                    errResult.put("message", "数据库操作异常");
                    errResult.put("handle", "请重新操作或者联系管理员!");
                    break;
                case ResponseCode.LOGIN_ERROR:
                    errResult.put("message", "用户名或密码不正确");
                    errResult.put("handle", "请重新登陆！");
                    break;
                case ResponseCode.PASSWORD_ERROR:
                    errResult.put("message", "密码错误");
                    errResult.put("handle", "请重新输入密码！");
                    break;
                default:
                    mLogger.error("错误码不存在");
                    break;
            }
            errResult.put("data", null);
        } catch (Exception e) {
            mLogger.error("拼接失败请求返回值异常", e);
        }
    }

    /**
     * 拼接异常信息给前端
     *
     * @param e         异常
     * @param errResult
     */
    protected void buildExceptionResponse(Exception e, JSONObject errResult) {
        try {
            if ("session is null".equals(e.getMessage())) {
                errResult.put("code", ResponseCode.SESSION_ERROR);
                errResult.put("message", "系统未登陆或登录过期");
                errResult.put("handle", "请重新登陆！");
                mLogger.info("系统未登陆");
            } else {
                mLogger.error("请求信息失败", e);
                errResult.put("code", ResponseCode.EXCEPTION);
                errResult.put("message", "系统异常");
                errResult.put("handle", "请联系管理员");
            }
            errResult.put("data", null);
        } catch (Exception e2) {
            mLogger.error("拼接失败请求返回值异常", e2);
        }
    }

    /**
     * 根据sessionid获取session
     *
     * @param sessionId
     * @return
     */
//    protected HttpSession getSession(String sessionId) throws SinopecException {
//        if (StringUtil.isEmpty(sessionId)) {
//            throw new SinopecException("session is null");
//        }
//        HttpSession session = SessionContext.getInstance().getSession(sessionId);
//        return session;
//    }
//
//    protected User getLoginUser(String sessionId) throws SinopecException {
//        HttpSession session = getSession(sessionId);
//        if (session == null) {
//            throw new SinopecException("session is null");
//        }
//        User user = (User) session.getAttribute(CommonConstant.SESSION_LOGIN_USER);
//        return user;
//    }
}
