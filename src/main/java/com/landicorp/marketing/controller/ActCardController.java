package com.landicorp.marketing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.constant.ResponseCode;
import com.landicorp.marketing.entities.ActCard;
import com.landicorp.marketing.entities.CardProgram;
import com.landicorp.marketing.service.ActCardService;
import com.landicorp.marketing.utill.DateUtil;
import com.landicorp.marketing.utill.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by huangdonghua on 09/04/2018.
 */
@RestController
@Scope("prototype")
@RequestMapping("/actCard")
public class ActCardController extends BaseActionSupport {
    private final static Logger LOG = LoggerFactory.getLogger(ActCardController.class);

    @Autowired
    private ActCardService actCardService;

    @RequestMapping(value = "queryActCardTable.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryActCardTable(HttpServletRequest httpRequest){
        LOG.info("查询开卡活动的数据列表");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActCard actCard = JSON.parseObject(paramsJson.toJSONString(), ActCard.class);
            int currentPage = Integer.parseInt(paramsJson.get("currentPage").toString());
            int pageSize = Integer.parseInt(paramsJson.get("pageSize").toString());
            String state = actCard.getState();
            if(state != null && !state.equals("")){
                if(state.equals("all")){
                    state = "";
                }
                actCard.setState(state);
            }
            TableData tableData = actCardService.queryActCardTable(actCard,currentPage,pageSize);

            if (tableData == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("查询开卡活动的数据列表成功", tableData, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;
    }

    /**
     * 新增开卡活动
     *
     * @param httpRequest
     * @return
     */
    @RequestMapping(value = "insertActCard.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertActCard(HttpServletRequest httpRequest) {
        LOG.info("请求新增开卡活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActCard actCard = JSON.parseObject(paramsJson.toJSONString(), ActCard.class);

            if(actCard != null && StringUtils.isNotNullAndBlank(actCard.getNoticeStartDatetime())){
                actCard.setNoticeStartDatetime(DateUtil.convertTime(actCard.getNoticeStartDatetime()));
            }

            if(actCard != null && StringUtils.isNotNullAndBlank(actCard.getNoticeEndDatetime())){
                actCard.setNoticeEndDatetime(DateUtil.convertTime(actCard.getNoticeEndDatetime()));
            }

            actCard.setStartDate(DateUtil.convertDate(actCard.getStartDate()));
            actCard.setEndDate(DateUtil.convertDate(actCard.getEndDate()));
            actCard.setUpdateTime(new Date().getTime());
            Integer integer = actCardService.insertActCard(actCard);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("新增开卡活动成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }


    /**
     * 修改开卡活动
     *
     * @param httpRequest
     * @return
     */
    @RequestMapping(value = "updateActCard.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateActCard(HttpServletRequest httpRequest) {
        LOG.info("请求修改开卡活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActCard actCard = JSON.parseObject(paramsJson.toJSONString(), ActCard.class);

            if(StringUtils.isNotNullAndBlank(actCard.getNoticeStartDatetime())){
                actCard.setNoticeStartDatetime(DateUtil.convertTime(actCard.getNoticeStartDatetime()));
            }

            if(StringUtils.isNotNullAndBlank(actCard.getNoticeEndDatetime())){
                actCard.setNoticeEndDatetime(DateUtil.convertTime(actCard.getNoticeEndDatetime()));
            }

            if(StringUtils.isNotNullAndBlank(actCard.getStartDate())){
                actCard.setStartDate(DateUtil.convertTime(actCard.getStartDate()));
            }

            if(StringUtils.isNotNullAndBlank(actCard.getEndDate())){
                actCard.setEndDate(DateUtil.convertTime(actCard.getEndDate()));
            }

            actCard.setUpdateTime(new Date().getTime());
            Integer integer = actCardService.updateCard(actCard);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("修改开卡活动成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

    /**
     * 修改开卡活动
     *
     * @param httpRequest
     * @return
     */
    @RequestMapping(value = "changeState.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject changeState(HttpServletRequest httpRequest) {
        LOG.info("请求修改开卡活动状态");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ActCard actCard = JSON.parseObject(paramsJson.toJSONString(), ActCard.class);
            actCard.setUpdateTime(new Date().getTime());

            Integer integer = actCardService.changeState(actCard);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("修改开卡活动状态成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }


    // 根据Id查询对应的开卡活动 该数据中包括List<cardProgram> 该数据
    // 前端无需再获取 List<cardProgram>数据
    @RequestMapping(value = "getActCard.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getActCard(HttpServletRequest httpRequest) {
        LOG.info("请求根据id查询开卡活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            String cardId = paramsJson.getString("cardId");
            ActCard actCard = actCardService.getActCardById(cardId);
            if (actCard == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("根据id查询开卡活动成功", actCard, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

    @RequestMapping(value = "deleteActCard.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteActCard(HttpServletRequest httpRequest){
        LOG.info("请求删除开卡活动");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            List<String> deleteIds = (List<String>) paramsJson.get("deleteIds");

            Integer integer = actCardService.delActCardByIds(deleteIds);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("删除开卡活动成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

    @RequestMapping(value = "queryCardProgramByCardId.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCardProgramByCardId(HttpServletRequest httpRequest){
        LOG.info("请求开卡活动方案配置");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            String cardId = paramsJson.getString("cardId");
            int currentPage = Integer.parseInt(paramsJson.get("currentPage").toString());
            int pageSize = Integer.parseInt(paramsJson.get("pageSize").toString());

            TableData tableData = actCardService.queryCardProgramByCardId(cardId,currentPage,pageSize);

            if (tableData == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("查询开卡活动方案配置成功", tableData, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;

    }

    


}
