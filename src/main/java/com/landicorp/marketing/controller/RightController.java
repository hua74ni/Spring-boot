package com.landicorp.marketing.controller;

import com.alibaba.fastjson.JSONObject;
import com.landicorp.marketing.constant.ResponseCode;
import com.landicorp.marketing.entities.RightTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限controller
 * Created by jiangjt on 2017/9/25.
 */
@Controller
@Scope("prototype")
@RequestMapping("/right")
public class RightController extends BaseActionSupport {
    private final static Logger LOG = LoggerFactory.getLogger(RightController.class);


    @RequestMapping(value = "queryMenuByUser.action", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMenuByUser(HttpServletRequest httpRequest) {
        LOG.info("请求根据用户查询菜单");
        JSONObject result = new JSONObject();
        try {
            JSONObject param = getRequestParamsJson(httpRequest);
            JSONObject paramsJson = (JSONObject) param.get("params");
//            List<RightTree> rightTreeList = rightService.queryRightTree(loginUser.getUserType(),CommonConstant.QUERY_RIGHTTREE_TYPE_ROLE);
            List<RightTree> rightTreeList = new ArrayList<RightTree>();
            List<RightTree> temp = new ArrayList<RightTree>();

            RightTree parentRightTree = new RightTree();
            RightTree chidrenRightTree = new RightTree();
            RightTree chidrenRightTree1 = new RightTree();
            RightTree FunctionRightTree1 = new RightTree();
            RightTree FunctionRightTree2 = new RightTree();
            RightTree FunctionRightTree3 = new RightTree();
            RightTree FunctionRightTree4 = new RightTree();
            RightTree FunctionRightTree5 = new RightTree();
            RightTree FunctionRightTree6 = new RightTree();
            RightTree FunctionRightTree7 = new RightTree();

            FunctionRightTree1.setRightId("a5d38b831a3c466589d00e3f303b5812");
            FunctionRightTree1.setExpand(true);
            FunctionRightTree1.setTitle("开卡活动");
            FunctionRightTree1.setRightIcon("ios-paper");
            FunctionRightTree1.setRightUrl("actCardManager");
            FunctionRightTree1.setOpen(true);
            FunctionRightTree1.setName("开卡活动");
            FunctionRightTree1.setChecked(true);

            FunctionRightTree2.setRightId("a5d38b831a3c466589d00e3f303b5832");
            FunctionRightTree2.setExpand(true);
            FunctionRightTree2.setTitle("消费活动");
            FunctionRightTree2.setRightIcon("ios-paper");
            FunctionRightTree2.setRightUrl("actConsumeManager");
            FunctionRightTree2.setOpen(true);
            FunctionRightTree2.setName("消费活动");
            FunctionRightTree2.setChecked(true);

            FunctionRightTree3.setRightId("a5d38b831a3c466589d00e3f303b5834");
            FunctionRightTree3.setExpand(true);
            FunctionRightTree3.setTitle("充值活动");
            FunctionRightTree3.setRightIcon("ios-paper");
            FunctionRightTree3.setRightUrl("actRechargeManager");
            FunctionRightTree3.setOpen(true);
            FunctionRightTree3.setName("充值活动");
            FunctionRightTree3.setChecked(true);

            FunctionRightTree4.setRightId("a5d38b831a3c466589d00e3f453b58ed");
            FunctionRightTree4.setExpand(true);
            FunctionRightTree4.setTitle("生日活动");
            FunctionRightTree4.setRightIcon("ios-paper");
            FunctionRightTree4.setRightUrl("actBirthdayManager");
            FunctionRightTree4.setOpen(true);
            FunctionRightTree4.setName("生日活动");
            FunctionRightTree4.setChecked(true);

            temp.add(FunctionRightTree1);
            temp.add(FunctionRightTree2);
            temp.add(FunctionRightTree3);
            temp.add(FunctionRightTree4);

            chidrenRightTree.setRightId("a5d38b831a3c466589d00e3f303b58ed");
            chidrenRightTree.setExpand(true);
            chidrenRightTree.setTitle("会员活动");
            chidrenRightTree.setRightIcon("ios-paper");
            chidrenRightTree.setRightUrl("会员活动");
            chidrenRightTree.setHasChild("1");
            chidrenRightTree.setOpen(true);
            chidrenRightTree.setName("会员活动");
            chidrenRightTree.setChecked(true);
            chidrenRightTree.setChildren(new ArrayList<>(temp));
            
            
            
            

            FunctionRightTree5.setRightId("a5d38b123a3c466589d00e3f303b5812");
            FunctionRightTree5.setExpand(true);
            FunctionRightTree5.setTitle("大转盘");
            FunctionRightTree5.setRightIcon("ios-paper");
            FunctionRightTree5.setRightUrl("turntableManager");
            FunctionRightTree5.setOpen(true);
            FunctionRightTree5.setName("大转盘");
            FunctionRightTree5.setChecked(true);

            FunctionRightTree6.setRightId("a5d38b83aedc466589d00e3f303b5832");
            FunctionRightTree6.setExpand(true);
            FunctionRightTree6.setTitle("福袋");
            FunctionRightTree6.setRightIcon("ios-paper");
            FunctionRightTree6.setRightUrl("welfareManager");
            FunctionRightTree6.setOpen(true);
            FunctionRightTree6.setName("福袋");
            FunctionRightTree6.setChecked(true);

            FunctionRightTree7.setRightId("a5d38b831a3c222589d00e3f303b5834");
            FunctionRightTree7.setExpand(true);
            FunctionRightTree7.setTitle("领劵活动");
            FunctionRightTree7.setRightIcon("ios-paper");
            FunctionRightTree7.setRightUrl("receiveTicketManager");
            FunctionRightTree7.setOpen(true);
            FunctionRightTree7.setName("领劵活动");
            FunctionRightTree7.setChecked(true);

            temp.clear();
            temp.add(FunctionRightTree5);
            temp.add(FunctionRightTree6);
            temp.add(FunctionRightTree7);

            chidrenRightTree1.setRightId("a5ae8bf31a3c456249d11e3f303b58ed");
            chidrenRightTree1.setExpand(true);
            chidrenRightTree1.setTitle("推广活动");
            chidrenRightTree1.setRightIcon("ios-paper");
            chidrenRightTree1.setRightUrl("推广活动");
            chidrenRightTree1.setHasChild("1");
            chidrenRightTree1.setOpen(true);
            chidrenRightTree1.setName("推广活动");
            chidrenRightTree1.setChecked(true);
            chidrenRightTree1.setChildren(new ArrayList<>(temp));

            temp = new ArrayList<>();
            temp.add(chidrenRightTree);
            temp.add(chidrenRightTree1);

            parentRightTree.setRightId("222da6b889494307894471e49c29704f");
            parentRightTree.setExpand(true);
            parentRightTree.setTitle("权限管理");
            parentRightTree.setHasChild("1");
            parentRightTree.setOpen(true);
            parentRightTree.setName("权限管理");
            parentRightTree.setChecked(true);
            parentRightTree.setChildren(new ArrayList<>(temp));


            rightTreeList.add(parentRightTree);
            
            if (rightTreeList == null) {
                //数据库操作异常
                buildErrorResponse(ResponseCode.DATE_ERROR, result);
                return result;
            }
            buildSuccessResponse("查询菜单成功", rightTreeList.get(0).getChildren(), result);
        } catch (Exception e) {
            buildExceptionResponse(e, result);
        }
        return result;
    }

}
