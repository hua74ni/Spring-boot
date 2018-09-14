package com.landicorp.marketing.controller;

import com.alibaba.fastjson.JSONObject;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.constant.ResponseCode;
import com.landicorp.marketing.entities.ReceiveTicket;
import com.landicorp.marketing.service.ReceiveTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by huangdonghua on 28/03/2018.
 */
@Controller
@Scope("prototype")
@RequestMapping("/receiveTicket")
public class ReceiveTicketController extends BaseActionSupport{
    private final static Logger LOG = LoggerFactory.getLogger(ReceiveTicketController.class);

    @Autowired
    private ReceiveTicketService receiveTicketService;

    @RequestMapping(value = "queryReceiveTicket.action" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryReceiveTicket(HttpServletRequest httpRequest){
        LOG.info("查询领劵活动的数据");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ReceiveTicket receiveTicket = JSONObject.parseObject(paramsJson.toJSONString(),ReceiveTicket.class);
            int currentPage = Integer.parseInt(paramsJson.get("currentPage").toString());
            int pageSize = Integer.parseInt(paramsJson.get("pageSize").toString());
            String state = receiveTicket.getState();
            if(state != null && !state.equals("")){
                if(state.equals("all")){
                    state = "";
                }
                receiveTicket.setState(state);
            }

            TableData tableData = receiveTicketService.queryReceiveTicketTable(receiveTicket,currentPage,pageSize);
            if (tableData == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("查询领劵活动的数据成功", tableData, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;
    }

    @RequestMapping(value = "changeState.action" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateWelfare(HttpServletRequest httpRequest){
        LOG.info("请求修改领劵活动数据");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            ReceiveTicket receiveTicket = JSONObject.parseObject(paramsJson.toJSONString(),ReceiveTicket.class);

            Integer integer = receiveTicketService.updateReceiveTicket(receiveTicket);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("请求修改领劵活动数据成功", null, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;

    }

}
