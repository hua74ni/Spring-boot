package com.landicorp.marketing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.constant.ResponseCode;
import com.landicorp.marketing.entities.ActRecharge;
import com.landicorp.marketing.entities.ActRecharge;
import com.landicorp.marketing.service.ActRechargeService;
import com.landicorp.marketing.service.ActRechargeService;
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
@RequestMapping("/actRecharge")
public class ActRechargeController extends BaseActionSupport{
    private final static Logger LOG = LoggerFactory.getLogger(ActRechargeController.class);


    @Autowired
    private ActRechargeService actRechargeService;

    @RequestMapping(value = "queryActRechargeTable.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryActRechargeTable(HttpServletRequest httpRequest){
        LOG.info("查询充值活动的数据列表");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActRecharge actRecharge = JSON.parseObject(paramsJson.toJSONString(), ActRecharge.class);
            int currentPage = Integer.parseInt(paramsJson.get("currentPage").toString());
            int pageSize = Integer.parseInt(paramsJson.get("pageSize").toString());
            String state = actRecharge.getState();
            if(state != null && !state.equals("")){
                if(state.equals("all")){
                    state = "";
                }
                actRecharge.setState(state);
            }
            TableData tableData = actRechargeService.queryActRechargeTable(actRecharge,currentPage,pageSize);

            if (tableData == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("查询充值活动的数据列表成功", tableData, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;
    }

    /**
     * 新增充值活动
     *
     * @param httpRequest
     * @return
     */
    @RequestMapping(value = "insertActRecharge.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertActRecharge(HttpServletRequest httpRequest) {
        LOG.info("请求新增充值活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActRecharge actRecharge = JSON.parseObject(paramsJson.toJSONString(), ActRecharge.class);

            if(actRecharge != null && StringUtils.isNotNullAndBlank(actRecharge.getNoticeStartDatetime())){
                actRecharge.setNoticeStartDatetime(DateUtil.convertTime(actRecharge.getNoticeStartDatetime()));
            }

            if(actRecharge != null && StringUtils.isNotNullAndBlank(actRecharge.getNoticeEndDatetime())){
                actRecharge.setNoticeEndDatetime(DateUtil.convertTime(actRecharge.getNoticeEndDatetime()));
            }

            actRecharge.setStartDate(DateUtil.convertDate(actRecharge.getStartDate()));
            actRecharge.setEndDate(DateUtil.convertDate(actRecharge.getEndDate()));
            actRecharge.setUpdateTime(new Date().getTime());
            Integer integer = actRechargeService.insertActRecharge(actRecharge);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("新增充值活动成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }


    // 根据Id查询对应的充值活动 该数据中包括List<SpecificProgram> 该数据
    // 前端无需再获取 List<SpecificProgram>数据
    @RequestMapping(value = "getActRecharge.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getActRecharge(HttpServletRequest httpRequest) {
        LOG.info("请求根据id查询充值活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            String rechargeId = paramsJson.getString("rechargeId");
            ActRecharge actRecharge = actRechargeService.getActRechargeById(rechargeId);
            if (actRecharge == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("根据id查询充值活动成功", actRecharge, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

    @RequestMapping(value = "deleteActRecharge.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteActRecharge(HttpServletRequest httpRequest){
        LOG.info("请求删除充值活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            List<String> deleteIds = (List<String>) paramsJson.get("deleteIds");

            Integer integer = actRechargeService.delActRechargeByIds(deleteIds);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("删除充值活动成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

    @RequestMapping(value = "queryProgramByRechargeId.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryProgramByRechargeId(HttpServletRequest httpRequest){
        LOG.info("请求充值活动方案配置");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            String rechargeId = paramsJson.getString("rechargeId");
            int currentPage = Integer.parseInt(paramsJson.get("currentPage").toString());
            int pageSize = Integer.parseInt(paramsJson.get("pageSize").toString());

            TableData tableData = actRechargeService.queryProgramByRechargeId(rechargeId,currentPage,pageSize);

            if (tableData == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("查询充值活动方案配置成功", tableData, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;

    }

    /**
     * 修改充值活动
     *
     * @param httpRequest
     * @return
     */
    @RequestMapping(value = "changeState.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject changeState(HttpServletRequest httpRequest) {
        LOG.info("请求修改充值活动状态");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActRecharge actRecharge = JSON.parseObject(paramsJson.toJSONString(), ActRecharge.class);
            actRecharge.setUpdateTime(new Date().getTime());

            Integer integer = actRechargeService.changeState(actRecharge);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("修改充值活动状态成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }


}