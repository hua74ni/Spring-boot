package com.landicorp.marketing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.constant.ResponseCode;
import com.landicorp.marketing.entities.Turntable;
import com.landicorp.marketing.entities.User;
import com.landicorp.marketing.service.TurntableService;
import com.landicorp.marketing.utill.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by huangdonghua on 2018/3/21.
 */
@Controller
@Scope("prototype")
@RequestMapping("/turntable")
public class TurntableController extends BaseActionSupport{
    private final static Logger LOG = LoggerFactory.getLogger(ReceiveTicketController.class);

    @Autowired
    private TurntableService turntableService;

    @RequestMapping(value = "queryTurnTable.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTurnTable(HttpServletRequest httpRequest){
        LOG.info("查询大转盘的数据");
        JSONObject result = new JSONObject();

        try{
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            Turntable turntable = JSON.parseObject(paramsJson.toJSONString(), Turntable.class);
            int currentPage = Integer.parseInt(paramsJson.get("currentPage").toString());
            int pageSize = Integer.parseInt(paramsJson.get("pageSize").toString());
            String state = turntable.getState();
            if(state != null && !state.equals("")){
                if(state.equals("all")){
                    state = "";
                }
                turntable.setState(state);
            }
            TableData tableData = turntableService.queryTurntableTable(turntable,currentPage,pageSize);

            if (tableData == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("查询大转盘表格成功", tableData, result);
        }catch (Exception e){
            buildExceptionResponse(e, result);
        }

        return result;
    }

    /**
     * 新增大转盘
     *
     * @param httpRequest
     * @return
     */
    @RequestMapping(value = "insertTurntable.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertTurntable(HttpServletRequest httpRequest,@RequestParam(value = "image1",required = false) MultipartFile image1,@RequestParam(value = "image2",required = false) MultipartFile image2) {
        LOG.info("请求新增大转盘");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            Turntable turntable = JSON.parseObject(paramsJson.toJSONString(), Turntable.class);

            turntable.setPreheatingTime(DateUtil.convertTime(turntable.getPreheatingTime()));
            turntable.setStartDate(DateUtil.convertDate(turntable.getStartDate()));
            turntable.setEndDate(DateUtil.convertDate(turntable.getEndDate()));
            turntable.setCreateTime(DateUtil.formatDateTime(new Date()));
            turntable.setUpdateTime(new Date().getTime());
            Integer integer = turntableService.insertTurntable(turntable);
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

    /**
     * 修改大转盘
     *
     * @param httpRequest
     * @return
     */
    @RequestMapping(value = "updateTurntable.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateTurntable(HttpServletRequest httpRequest) {
        LOG.info("请求修改大转盘");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            Turntable turntable = JSON.parseObject(paramsJson.toJSONString(), Turntable.class);

            turntable.setPreheatingTime(DateUtil.convertTime(turntable.getPreheatingTime()));
            turntable.setStartDate(DateUtil.convertDate(turntable.getStartDate()));
            turntable.setEndDate(DateUtil.convertDate(turntable.getEndDate()));
            turntable.setCreateTime(DateUtil.formatDateTime(new Date()));
            turntable.setUpdateTime(new Date().getTime());
            Integer integer = turntableService.updateTurntable(turntable);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("修改大转盘成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }


    @RequestMapping(value = "queryTurntableList.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTurntableList(HttpServletRequest httpRequest) {
        LOG.info("请求根据id查询大转盘");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            Turntable turntable = JSON.parseObject(paramsJson.toJSONString(), Turntable.class);
            List<Turntable> roleList = turntableService.queryturntableList(turntable);
            if (roleList == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("根据id查询大转盘成功", roleList, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

    @RequestMapping(value = "deleteTurntable.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteTurntable(HttpServletRequest httpRequest){
        LOG.info("请求删除大转盘");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            String sessionId = (String) paramsJson.get("sessionId");
            List<Integer> deleteIds = (List<Integer>) paramsJson.get("deleteIds");

            Integer integer = turntableService.delTurntable(deleteIds);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("删除大转盘成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }


    @RequestMapping(value = "changeState.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject changeState(HttpServletRequest httpRequest){
        LOG.info("请求改变大转盘状态");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
            Turntable turntable = JSON.parseObject(paramsJson.toJSONString(),Turntable.class);

            Integer integer = turntableService.updateTurntable(turntable);
            if (integer == 0) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("请求改变大转盘状态成功", null, result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }


}
