package com.landicorp.marketing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.constant.ResponseCode;
import com.landicorp.marketing.entities.Welfare;
import com.landicorp.marketing.service.WelfareService;
import com.landicorp.marketing.utill.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by huangdonghua on 28/03/2018.
 */
@Controller
@Scope("prototype")
@RequestMapping("/welfare")
public class WelfareController extends BaseActionSupport{
    private final static Logger LOG = LoggerFactory.getLogger(WelfareController.class);

    @Autowired
    private WelfareService welfareService;

    @RequestMapping(value = "queryWelfare.action" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryWelfare(HttpServletRequest httpRequest){
        LOG.info("请求查询福袋的数据");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            Welfare welfare = JSONObject.parseObject(paramsJson.toJSONString(),Welfare.class);
            int currentPage = Integer.parseInt(paramsJson.get("currentPage").toString());
            int pageSize = Integer.parseInt(paramsJson.get("pageSize").toString());
            String state = welfare.getState();
            if(state != null && !state.equals("")){
                if(state.equals("all")){
                    state = "";
                }
                welfare.setState(state);
            }

            TableData tableData = welfareService.queryWelfareTable(welfare,currentPage,pageSize);
            if (tableData == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("请求查询福袋的数据成功", tableData, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;
    }

    @RequestMapping(value = "updateWelfare.action" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateWelfare(HttpServletRequest httpRequest){
        LOG.info("请求修改福袋数据");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            Welfare welfare = JSONObject.parseObject(paramsJson.toJSONString(),Welfare.class);

            Integer integer = welfareService.updateWelfare(welfare);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("请求修改福袋数据成功", null, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;
    }

    @RequestMapping(value = "insertWelfare.action" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertWelfare(HttpServletRequest httpRequest){
        LOG.info("请求新增福袋");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            Welfare welfare = JSON.parseObject(paramsJson.toJSONString(), Welfare.class);

            welfare.setStartDate(DateUtil.convertDate(welfare.getStartDate()));
            welfare.setEndDate(DateUtil.convertDate(welfare.getEndDate()));
            welfare.setCreateTime(DateUtil.formatDateTime(new Date()));
            welfare.setUpdateTime(new Date().getTime());
            Integer integer = welfareService.insertWelfare(welfare);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("新增福袋成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }


    @RequestMapping(value = "queryWelfareList.action" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryWelfareList(HttpServletRequest httpRequest){

        LOG.info("请求根据id查询大转盘");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            Welfare welfare = JSON.parseObject(paramsJson.toJSONString(), Welfare.class);
            List<Welfare> welfareList = welfareService.queryWelfareList(welfare);
            if (welfareList == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("根据id查询大转盘成功", welfareList, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;

    }

}
