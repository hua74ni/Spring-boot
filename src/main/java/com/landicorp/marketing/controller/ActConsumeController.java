package com.landicorp.marketing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.constant.ResponseCode;
import com.landicorp.marketing.entities.ActCard;
import com.landicorp.marketing.entities.ActConsume;
import com.landicorp.marketing.service.ActConsumeService;
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
@RequestMapping("/actConsume")
public class ActConsumeController extends BaseActionSupport{
    private final static Logger LOG = LoggerFactory.getLogger(ActConsumeController.class);

    
    @Autowired
    private ActConsumeService actConsumeService;

    @RequestMapping(value = "queryActConsumeTable.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryActConsumeTable(HttpServletRequest httpRequest){
        LOG.info("查询消费活动的数据列表");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActConsume actConsume = JSON.parseObject(paramsJson.toJSONString(), ActConsume.class);
            int currentPage = Integer.parseInt(paramsJson.get("currentPage").toString());
            int pageSize = Integer.parseInt(paramsJson.get("pageSize").toString());
            String state = actConsume.getState();
            if(state != null && !state.equals("")){
                if(state.equals("all")){
                    state = "";
                }
                actConsume.setState(state);
            }
            TableData tableData = actConsumeService.queryActConsumeTable(actConsume,currentPage,pageSize);

            if (tableData == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("查询消费活动的数据列表成功", tableData, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;
    }

    /**
     * 新增消费活动
     *
     * @param httpRequest
     * @return
     */
    @RequestMapping(value = "insertActConsume.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertActConsume(HttpServletRequest httpRequest) {
        LOG.info("请求新增消费活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActConsume actConsume = JSON.parseObject(paramsJson.toJSONString(), ActConsume.class);

            if(actConsume != null && StringUtils.isNotNullAndBlank(actConsume.getNoticeStartDatetime())){
                actConsume.setNoticeStartDatetime(DateUtil.convertTime(actConsume.getNoticeStartDatetime()));
            }

            if(actConsume != null && StringUtils.isNotNullAndBlank(actConsume.getNoticeEndDatetime())){
                actConsume.setNoticeEndDatetime(DateUtil.convertTime(actConsume.getNoticeEndDatetime()));
            }

            actConsume.setStartDate(DateUtil.convertDate(actConsume.getStartDate()));
            actConsume.setEndDate(DateUtil.convertDate(actConsume.getEndDate()));
            actConsume.setUpdateTime(new Date().getTime());
            Integer integer = actConsumeService.insertActConsume(actConsume);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("新增消费活动成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }


    // 根据Id查询对应的消费活动 该数据中包括List<SpecificProgram> 该数据
    // 前端无需再获取 List<SpecificProgram>数据
    @RequestMapping(value = "getActConsume.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getActConsume(HttpServletRequest httpRequest) {
        LOG.info("请求根据id查询消费活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            String consumeId = paramsJson.getString("consumeId");
            ActConsume actConsume = actConsumeService.getActConsumeById(consumeId);
            if (actConsume == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("根据id查询消费活动成功", actConsume, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

    @RequestMapping(value = "deleteActConsume.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteActConsume(HttpServletRequest httpRequest){
        LOG.info("请求删除消费活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            List<String> deleteIds = (List<String>) paramsJson.get("deleteIds");

            Integer integer = actConsumeService.delActConsumeByIds(deleteIds);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("删除消费活动成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

    @RequestMapping(value = "queryProgramByConsumeId.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryProgramByConsumeId(HttpServletRequest httpRequest){
        LOG.info("请求消费活动方案配置");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            String consumeId = paramsJson.getString("consumeId");
            int currentPage = Integer.parseInt(paramsJson.get("currentPage").toString());
            int pageSize = Integer.parseInt(paramsJson.get("pageSize").toString());

            TableData tableData = actConsumeService.queryProgramByConsumeId(consumeId,currentPage,pageSize);

            if (tableData == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("查询消费活动方案配置成功", tableData, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;

    }


    /**
     * 修改消费活动状态
     *
     * @param httpRequest
     * @return
     */
    @RequestMapping(value = "changeState.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject changeState(HttpServletRequest httpRequest) {
        LOG.info("请求修改消费活动状态");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActConsume actConsume = JSON.parseObject(paramsJson.toJSONString(), ActConsume.class);
            actConsume.setUpdateTime(new Date().getTime());

            Integer integer = actConsumeService.changeState(actConsume);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("修改消费活动状态成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }



}
