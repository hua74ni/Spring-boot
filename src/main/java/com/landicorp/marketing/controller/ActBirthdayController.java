package com.landicorp.marketing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.constant.ResponseCode;
import com.landicorp.marketing.entities.ActBirthday;
import com.landicorp.marketing.entities.ActBirthday;
import com.landicorp.marketing.service.ActBirthdayService;
import com.landicorp.marketing.utill.DateUtil;
import com.landicorp.marketing.utill.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by huangdonghua on 11/04/2018.
 */
@RestController
@Scope("prototype")
@RequestMapping("/actBirthday")
public class ActBirthdayController extends BaseActionSupport{
    private final static Logger LOG = LoggerFactory.getLogger(ActBirthdayController.class);

    @Autowired
    private ActBirthdayService actBirthdayService;

    @RequestMapping(value = "queryActBirthdayTable.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryActBirthdayTable(HttpServletRequest httpRequest){
        LOG.info("查询生日活动的数据列表");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActBirthday actBirthday = JSON.parseObject(paramsJson.toJSONString(), ActBirthday.class);
            int currentPage = Integer.parseInt(paramsJson.get("currentPage").toString());
            int pageSize = Integer.parseInt(paramsJson.get("pageSize").toString());
            String state = actBirthday.getState();
            if(state != null && !state.equals("")){
                if(state.equals("all")){
                    state = "";
                }
                actBirthday.setState(state);
            }
            TableData tableData = actBirthdayService.queryActBirthdayTable(actBirthday,currentPage,pageSize);

            if (tableData == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("查询生日活动的数据列表成功", tableData, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;
    }

    /**
     * 新增生日活动
     *
     * @param httpRequest
     * @return
     */
    @RequestMapping(value = "insertActBirthday.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertActBirthday(HttpServletRequest httpRequest) {
        LOG.info("请求新增生日活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActBirthday actBirthday = JSON.parseObject(paramsJson.toJSONString(), ActBirthday.class);

            if(actBirthday != null && StringUtils.isNotNullAndBlank(actBirthday.getNoticeStartDatetime())){
                actBirthday.setNoticeStartDatetime(DateUtil.convertTime(actBirthday.getNoticeStartDatetime()));
            }

            if(actBirthday != null && StringUtils.isNotNullAndBlank(actBirthday.getNoticeEndDatetime())){
                actBirthday.setNoticeEndDatetime(DateUtil.convertTime(actBirthday.getNoticeEndDatetime()));
            }

            actBirthday.setUpdateTime(new Date().getTime());
            Integer integer = actBirthdayService.insertActBirthday(actBirthday);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("新增生日活动成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }


    // 根据Id查询对应的生日活动 该数据中包括List<SpecificProgram> 该数据
    // 前端无需再获取 List<SpecificProgram>数据
    @RequestMapping(value = "getActBirthday.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getActBirthday(HttpServletRequest httpRequest) {
        LOG.info("请求根据id查询生日活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            String birthdayId = paramsJson.getString("birthdayId");
            ActBirthday actBirthday = actBirthdayService.getActBirthdayById(birthdayId);
            if (actBirthday == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("根据id查询生日活动成功", actBirthday, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

    @RequestMapping(value = "deleteActBirthday.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteActBirthday(HttpServletRequest httpRequest){
        LOG.info("请求删除生日活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            List<String> deleteIds = (List<String>) paramsJson.get("deleteIds");

            Integer integer = actBirthdayService.delActBirthdayByIds(deleteIds);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("删除生日活动成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

    @RequestMapping(value = "queryProgramByBirthdayId.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryProgramByBirthdayId(HttpServletRequest httpRequest){
        LOG.info("请求生日活动方案配置");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            String birthdayId = paramsJson.getString("birthdayId");
            int currentPage = Integer.parseInt(paramsJson.get("currentPage").toString());
            int pageSize = Integer.parseInt(paramsJson.get("pageSize").toString());

            TableData tableData = actBirthdayService.queryProgramByBirthdayId(birthdayId,currentPage,pageSize);

            if (tableData == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("查询生日活动方案配置成功", tableData, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;

    }

    /**
     * 修改生日活动
     *
     * @param httpRequest
     * @return
     */
    @RequestMapping(value = "changeState.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject changeState(HttpServletRequest httpRequest) {
        LOG.info("请求修改生日活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActBirthday actBirthday = JSON.parseObject(paramsJson.toJSONString(), ActBirthday.class);
            actBirthday.setUpdateTime(new Date().getTime());

            Integer integer = actBirthdayService.changeState(actBirthday);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("修改生日活动成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }


}
