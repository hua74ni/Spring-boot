package com.landicorp.marketing;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landicorp.marketing.controller.ActCardController;
import com.landicorp.marketing.entities.*;
import com.landicorp.marketing.enums.GiftType;
import com.landicorp.marketing.enums.MarketingProgram;
import com.landicorp.marketing.mapper.ActCardGiftMapper;
import com.landicorp.marketing.mapper.ActCardMapper;
import com.landicorp.marketing.mapper.UserMoneyStreamMapper;
import com.landicorp.marketing.service.*;
import com.landicorp.marketing.utill.DateUtil;
import com.landicorp.marketing.utill.StringUtils;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@SpringBootTest 默认事物回滚 value = true 为了不让测试数据影响数据库
@Rollback(value = false)
public class MarketingApplicationTests {
    private final static Logger LOG = LoggerFactory.getLogger(MarketingApplicationTests.class);

	@Autowired
	private ActCardService actCardService;

	@Autowired
	private ActConsumeService actConsumeService;

	@Autowired
	private ActRechargeService actRechargeService;

	@Autowired
    private ActBirthdayService actBirthdayService;

	@Autowired
	private ActUserGiftService actUserGiftService;

	@Autowired
	private UserMoneyStreamMapper userMoneyStreamMapper;

	@Autowired
	private ActCardGiftMapper actCardGiftMapper;

	//在 storeId为00080001的商铺下的 所有会员用户
	private static final List<HashMap<String,Object>> userList = new ArrayList<HashMap<String,Object>>();

	static {
	    HashMap<String,Object> map = new HashMap<>();

	    //  storeId = 00080001 的会员用户
        map.put("openId","userId1");
        map.put("storeId","00080001");
        map.put("nemberGrade","低级会员");
        map.put("birthday","1995-12-24");
        userList.add(new HashMap<>(map));

        map.put("openId","userId2");
        map.put("storeId","00080001");
        map.put("nemberGrade","低级会员");
        map.put("birthday","1995-04-15");
        userList.add(new HashMap<>(map));

        map.put("openId","userId3");
        map.put("storeId","00080001");
        map.put("nemberGrade","低级会员");
        map.put("birthday","1995-04-16");
        userList.add(new HashMap<>(map));

        map.put("openId","userId4");
        map.put("storeId","00080001");
        map.put("nemberGrade","低级会员");
        map.put("birthday","1995-04-17");
        userList.add(new HashMap<>(map));

        map.put("openId","userId5");
        map.put("storeId","00080001");
        map.put("nemberGrade","低级会员");
        map.put("birthday","1995-04-18");
        userList.add(new HashMap<>(map));

        map.put("openId","userId6");
        map.put("storeId","00080001");
        map.put("nemberGrade","高级会员");
        map.put("birthday","1995-04-19");
        userList.add(new HashMap<>(map));

        map.put("openId","userId7");
        map.put("storeId","00080001");
        map.put("nemberGrade","高级会员");
        map.put("birthday","1995-04-20");
        userList.add(new HashMap<>(map));


        //  storeId = 00000001 的会员用户
        map.put("openId","userId8");
        map.put("storeId","00000001");
        map.put("nemberGrade","低级会员");
        map.put("birthday","1995-12-24");
        userList.add(new HashMap<>(map));

        map.put("openId","userId9");
        map.put("storeId","00000001");
        map.put("nemberGrade","低级会员");
        map.put("birthday","1995-04-15");
        userList.add(new HashMap<>(map));

        map.put("openId","userId10");
        map.put("storeId","00000001");
        map.put("nemberGrade","低级会员");
        map.put("birthday","1995-04-16");
        userList.add(new HashMap<>(map));

        map.put("openId","userId11");
        map.put("storeId","00000001");
        map.put("nemberGrade","低级会员");
        map.put("birthday","1995-04-17");
        userList.add(new HashMap<>(map));

        map.put("openId","userId12");
        map.put("storeId","00000001");
        map.put("nemberGrade","高级会员");
        map.put("birthday","1995-04-18");
        userList.add(new HashMap<>(map));

        map.put("openId","userId13");
        map.put("storeId","00000001");
        map.put("nemberGrade","高级会员");
        map.put("birthday","1995-04-19");
        userList.add(new HashMap<>(map));

        map.put("openId","userId14");
        map.put("storeId","00000001");
        map.put("nemberGrade","低级会员");
        map.put("birthday","1995-04-20");
        userList.add(new HashMap<>(map));

    }


	@Test
	public void userConsume(){
		ActConsume("openId", 500.00, "00080001");
	}

    @Test
    public void userRecharge(){
        ActRecharge("openId",500.00,"00080001");
    }

    @Test
    public void userCard(){
        ActCard("openId","00080001");
    }

    @Test
    public void userBirthday(){
        /*

            模拟定时任务 触发某商铺生日活动
                假如 该生日活动触发时间在 00:00
                每天在这个时间点触发 查询该生日活动有哪些商铺参与
                再查询该商铺下 有哪些会员用户 满足条件及是该会员用户生日时间
                    此时需要对会员生日时间进行说明: 由于活动设置有提前触发天数，因此在该会员用户生日的前几天就已经触发该活动及通知用户
                                                该功能完成完成的部分就是 通知用户及将该福利发送到会员用户的手里 设置如果用户未使用该福利到7天后自动过期可再设置定时任务

                模拟功能说明：从该生日活动已经触发 到对应的商铺开始模拟
                            由于没有数据 因此只模拟一个商铺下有多个会员用户

        */
        actBirthday();
    }


    // 生日活动
    public void actBirthday(){

        //活动有效的生日活动
        List<ActBirthday> actBirthdayList = actBirthdayService.queryActCardByValid();

        for(int i = 0;i < actBirthdayList.size();i++){

            ActBirthday actBirthday = actBirthdayList.get(i);
            String storeIds = actBirthday.getStoreIds();
            List<BirthdayProgram> birthdayProgramList = actBirthday.getBirthdayProgramList();

            // 活动触发提醒日期 = 触发当日 + 配置的提前触发天数
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, actBirthday.getAdvanceTriggerDay());
            String triggerDate = DateUtil.formatDay(calendar.getTime()).substring(5);

            for (BirthdayProgram birthdayProgram:
                    birthdayProgramList) {
                // 给满足条件的用户分配 生日赠品
                distributeBirthdayGift(birthdayProgram,triggerDate,storeIds);

            }

        }

    }


    /**
     * 给满足条件的用户分配 生日赠品
     * @param birthdayProgram
     * @param triggerDate
     * @param storeIds
     */
    private void distributeBirthdayGift(BirthdayProgram birthdayProgram,String triggerDate,String storeIds){

        // 模拟 所有的会员用户信息
        for(int j = 0;j < userList.size();j++){
            Map<String,Object> userInfo = userList.get(j);

            String userId = (String) userInfo.get("openId");
            String userStoreId = (String) userInfo.get("storeId");
            String userGrade = (String) userInfo.get("nemberGrade");
            String userBirthdayDate = (String) userInfo.get("birthday");
            userBirthdayDate = userBirthdayDate.substring(5);

            // 该用户未在此商铺下有活动
            if(storeIds.indexOf(userStoreId) == -1){
                continue;
            }
            // 会员等级限制
            if(StringUtils.isNotNullAndBlank(birthdayProgram.getMembershipLevel())
                    && birthdayProgram.getMembershipLevel().indexOf(userGrade) == -1){
                continue;
            }
            // 活动触发提醒日期 非会员用户的生日
            if(!triggerDate.equals(userBirthdayDate)){
                continue;
            }

            LOG.info("该用户被触发生日活动 该用户的信息为 " + JSON.toJSONString(userInfo));
            LOG.info("所赠送的福利为");

            switch (GiftType.match(birthdayProgram.getGiftType())){
                case INTEGRAL:

                    LOG.info(birthdayProgram.getGiftAmount() + " " + birthdayProgram.getGiftType() );
                    //TODO 给用户分配 积分

                    break;

                case GIFT_MONEY:

                    LOG.info(birthdayProgram.getGiftAmount() + " " + birthdayProgram.getGiftType() );
                    //TODO 给用户分配 赠送金

                    break;

                case COUPON:

                    LOG.info(birthdayProgram.getGiftType() + " 【" + birthdayProgram.getGiftItemLabel() + "】" + birthdayProgram.getGiftAmount() );
                    //TODO 给用户分配 优惠卷

                    break;

                case GIFT_PACKAGE:

                    LOG.info(birthdayProgram.getGiftType() + " 【" + birthdayProgram.getGiftItemLabel() + "】" + birthdayProgram.getGiftAmount() );
                    //TODO 给用户分配 赠送金

                    break;

                case GOODS:

                    LOG.info(birthdayProgram.getGiftType() + " 【" + birthdayProgram.getGiftItemLabel() + "】" + birthdayProgram.getGiftAmount() );
                    //TODO 给用户分配 物件

                    break;


                case GRADE:


                    LOG.info(birthdayProgram.getGiftType() + " 【" + birthdayProgram.getGiftItemLabel() + "】" );
                    //TODO 给用户分配 物件

                    break;
            }

        }

    }


    //开卡活动
    public void ActCard(String openId,String storeId){

        List<ActCard> actCardList = actCardService.queryActCardByValid(storeId);

        //  通过 openId 该会员的等级		这里直接默认等级 只有三级  低级 中级  高级
        String membershipGrade = "低级会员";

        try {

            // 由于 开卡活动无特定条件 因此只要该活动在有效期即可 享受活动福利
            for(int i = 0;i < actCardList.size();i++){
                LOG.info(actCardList.get(i).toString());
                List<CardProgram> cardProgramList = actCardList.get(i).getCardProgramList();

                for(int j = 0; j < cardProgramList.size();j++){
                    CardProgram cardProgram = cardProgramList.get(j);
                    // 分配给用户开卡活动的赠品
                    distributeCardGift(cardProgram,openId,storeId);
                }

            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException(e);
        }

    }


    /**
     *  分配给用户开卡活动的赠品
     * @param cardProgram
     * @param openId
     * @param storeId
     */
    private void distributeCardGift(CardProgram cardProgram,String openId,String storeId){

        ActCardGift actCardGift = new ActCardGift();
        actCardGift.setCardGiftId(UUID.randomUUID().toString());
        actCardGift.setOpenId(openId);
        actCardGift.setActCardId(cardProgram.getCardId());
        actCardGift.setCardProgramId(cardProgram.getProgramId());
        actCardGift.setGiftType(cardProgram.getGiftType());

        LOG.info("openId = " + openId +" 用户在 " + "storeId="+storeId + "的商铺开卡，对应开卡活动为");

        if(GiftType.match(cardProgram.getGiftType()) != null){

            actCardGift.setGiftType(cardProgram.getGiftType());
            actCardGift.setGiftItem(cardProgram.getGiftItem());
            actCardGift.setGiftItemLabel(cardProgram.getGiftItemLabel());
            actCardGift.setGiftAmount(cardProgram.getGiftAmount());

            actCardGiftMapper.insertSelective(actCardGift);
        }


//        switch (GiftType.match(cardProgram.getGiftType())){
//            case INTEGRAL:
//
//                LOG.info(cardProgram.getGiftAmount() + " " + cardProgram.getGiftType() );
//
//                break;
//
//            case GIFT_MONEY:
//
//                LOG.info(cardProgram.getGiftAmount() + " " + cardProgram.getGiftType() );
//
//
//                break;
//
//            case COUPON:
//
//                LOG.info(cardProgram.getGiftType() + " 【" + cardProgram.getGiftItemLabel() + "】" + cardProgram.getGiftAmount() );
//                //TODO 给用户分配 赠送金
//
//                break;
//
//            case GIFT_PACKAGE:
//
//                LOG.info(cardProgram.getGiftType() + " 【" + cardProgram.getGiftItemLabel() + "】" + cardProgram.getGiftAmount() );
//                //TODO 给用户分配 礼包
//
//                break;
//
//            case GOODS:
//
//                LOG.info(cardProgram.getGiftType() + " 【" + cardProgram.getGiftItemLabel() + "】" + cardProgram.getGiftAmount() );
//                //TODO 给用户分配 物件
//
//                break;
//
//
//            case GRADE:
//
//
//                LOG.info(cardProgram.getGiftType() + " 【" + cardProgram.getGiftItemLabel() + "】" );
//                //TODO 给用户分配 等级
//
//                break;
//        }

    }



    // 充值活动     由于充值活动类似与消费活动 这里就不进行注解喵描述
    public void ActRecharge(String openId,double money,String storeId){

        List<ActRecharge> actRechargeList = actRechargeService.queryActRechargeByValid(storeId);

        //  通过 openId 该会员的等级		这里直接默认等级 只有三级  低级 中级  高级
        String membershipGrade = "低级会员";

        try{

            UserMoneyStream userMoneyStream = new UserMoneyStream();
            userMoneyStream.setMoneyStreamId(UUID.randomUUID().toString());
            userMoneyStream.setMoney(money);
            userMoneyStream.setOpenId(openId);
            userMoneyStream.setStoreId(storeId);
            userMoneyStream.setOperateType("充值");

            userMoneyStreamMapper.insertSelective(userMoneyStream);

            for(int i = 0; i < actRechargeList.size();i++){
                ActRecharge actRecharge = actRechargeList.get(i);
                LOG.info(actRechargeList.get(i).toString());
                List<SpecificProgram> specificProgramList = actRechargeList.get(i).getSpecificProgramList();
                for(int j = 0; j < specificProgramList.size();j++){
                    SpecificProgram specificProgram = specificProgramList.get(j);
                    //会员限制
                    if(StringUtils.isNotNullAndBlank(specificProgram.getMembershipLevel()) &&
                            specificProgram.getMembershipLevel().indexOf(membershipGrade) == -1){
                        continue;
                    }
                    Integer actTotalLimitAmount = specificProgram.getActTotalLimitAmount();
                    Integer everybodyLimitAmount = specificProgram.getEverybodyLimitAmount();

                    // 次数限制
                    if(actTotalLimitAmount != null && actTotalLimitAmount != 0
                            && !actUserGiftService.getActLimitedByAct(null,specificProgram)){
                        continue;
                    }
                    if(everybodyLimitAmount != null && everybodyLimitAmount != 0
                            && !actUserGiftService.getActLimitedByAct(openId,specificProgram)){
                        continue;
                    }

                    for(int k = 0;k < specificProgram.getSpecificGiftList().size();k++){
                        SpecificGift specificGift = specificProgram.getSpecificGiftList().get(k);

                        // 用户活动礼品
                        ActUserGift actUserGift = distributeSpecificGift(actRecharge.getStartDate() + " "+ actRecharge.getStartTime()
                                                                            ,specificProgram,specificGift,money,userMoneyStream);
                        actUserGift.setUserGiftId(UUID.randomUUID().toString());
                        actUserGift.setOpenId(openId);
                        actUserGift.setStoreId(storeId);
                        actUserGift.setActProgramId(specificProgram.getProgramId());
                        actUserGift.setActIdType("actConsume");
                        actUserGift.setActId(actRecharge.getRechargeId());
                        actUserGift.setMoneyStreamId(userMoneyStream.getMoneyStreamId());
                        actUserGift.setSpecificType(specificProgram.getSpecificType());

                        if(StringUtils.isNullOrBlank(actUserGift.getGiftType())){
                            continue;
                        }
                        actUserGiftService.insertActUserGift(actUserGift);

                    }

                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException(e);
        }

    }



	// 消费活动
	public void ActConsume(String openId,double money,String storeId){

		List<ActConsume> actConsumeList = actConsumeService.queryActConsumeByValid(storeId);

		//  通过 openId 该会员的等级		这里直接默认等级 只有三级  低级 中级  高级
		String membershipGrade = "低级会员";

		try{

		    // 用户现金流水
			UserMoneyStream userMoneyStream = new UserMoneyStream();
			userMoneyStream.setMoneyStreamId(UUID.randomUUID().toString());
			userMoneyStream.setMoney(money);
			userMoneyStream.setOpenId(openId);
			userMoneyStream.setStoreId(storeId);
			userMoneyStream.setOperateType("消费");

			userMoneyStreamMapper.insertSelective(userMoneyStream);

			for(int i = 0; i < actConsumeList.size();i++){
                ActConsume actConsume = actConsumeList.get(i);
				LOG.info(actConsumeList.get(i).toString());
				List<SpecificProgram> specificProgramList = actConsumeList.get(i).getSpecificProgramList();
				for(int j = 0; j < specificProgramList.size();j++){
					SpecificProgram specificProgram = specificProgramList.get(j);
					//会员限制
					if(StringUtils.isNotNullAndBlank(specificProgram.getMembershipLevel()) &&
                            specificProgram.getMembershipLevel().indexOf(membershipGrade) == -1){
						continue;
					}
					Integer actTotalLimitAmount = specificProgram.getActTotalLimitAmount();
					Integer everybodyLimitAmount = specificProgram.getEverybodyLimitAmount();

					// 活动(每日/每周/每yeu )总次数限制
					if(actTotalLimitAmount != null && actTotalLimitAmount != 0
							&& !actUserGiftService.getActLimitedByAct(null,specificProgram)){
						continue;
					}
                    // 活动每个人(每日/每周/每日)次数限制
					if(everybodyLimitAmount != null && everybodyLimitAmount != 0
							&& !actUserGiftService.getActLimitedByAct(openId,specificProgram)){
						continue;
					}

					for(int k = 0;k < specificProgram.getSpecificGiftList().size();k++){
                        SpecificGift specificGift = specificProgram.getSpecificGiftList().get(k);

                        // 用户活动礼品
						ActUserGift actUserGift = distributeSpecificGift(actConsume.getStartDate() + " "+ actConsume.getStartTime()
                                                                            ,specificProgram,specificGift,money,userMoneyStream);

						actUserGift.setUserGiftId(UUID.randomUUID().toString());
						actUserGift.setOpenId(openId);
						actUserGift.setStoreId(storeId);
						actUserGift.setActProgramId(specificProgram.getProgramId());
						actUserGift.setActIdType("actConsume");
						actUserGift.setActId(actConsume.getConsumeId());
						actUserGift.setMoneyStreamId(userMoneyStream.getMoneyStreamId());
						actUserGift.setSpecificType(specificProgram.getSpecificType());

                        if(StringUtils.isNullOrBlank(actUserGift.getGiftType())){
                            continue;
                        }
						actUserGiftService.insertActUserGift(actUserGift);

					}


				}
			}
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}

	}


	public ActUserGift distributeSpecificGift(String dateTime,SpecificProgram specificProgram,SpecificGift specificGift,
                                       double money,UserMoneyStream userMoneyStream){

        ActUserGift actUserGift = new ActUserGift();

        switch (MarketingProgram.match(specificProgram.getSpecificType())){
            case UNCONDITIONAL_GIFT:

                actUserGift.setSpecialCondition(specificGift.getSpecialCondition());
                actUserGift.setGiftType(specificGift.getGiftType());
                actUserGift.setGiftItem(specificGift.getGiftItem());
                actUserGift.setGiftItemLabel(specificGift.getGiftItemLabel());
                actUserGift.setGiftAmount(specificGift.getGiftAmount());
                break;

            case EVERY_FULL_GIFT:

                Integer maxGiftAmount = new Double(money / specificGift.getSpecialCondition()).intValue();

                if(maxGiftAmount > specificGift.getMaxGiftAmount()){
                    maxGiftAmount = specificGift.getMaxGiftAmount();
                }

                // 每次最大赠送数量
                if(maxGiftAmount > 0){
                    actUserGift.setSpecialCondition(specificGift.getSpecialCondition());
                    actUserGift.setGiftType(specificGift.getGiftType());
                    actUserGift.setGiftItem(specificGift.getGiftItem());
                    actUserGift.setGiftItemLabel(specificGift.getGiftItemLabel());
                    actUserGift.setGiftAmount(specificGift.getGiftAmount());

                    if(actUserGift.getGiftAmount() != null && actUserGift.getGiftAmount() != 0){
                        actUserGift.setGiftAmount(actUserGift.getGiftAmount()*maxGiftAmount);
                    }else{
                        actUserGift.setGiftAmount(maxGiftAmount);
                    }
                }
                break;

            case FULL_GIFT:

                if(money >= specificGift.getSpecialCondition()){
                    actUserGift.setSpecialCondition(specificGift.getSpecialCondition());
                    actUserGift.setGiftType(specificGift.getGiftType());
                    actUserGift.setGiftItem(specificGift.getGiftItem());
                    actUserGift.setGiftItemLabel(specificGift.getGiftItemLabel());
                    actUserGift.setGiftAmount(specificGift.getGiftAmount());
                }
                break;

            case ACCUMULATION_GIFT:

                //该消费活动开始 时间 到现在 该用户花了多少钱
                userMoneyStream.setCreateTime(dateTime);
                Double totalMoney = userMoneyStreamMapper.getTotalMoney(userMoneyStream);
                //  可参与活动次数  =  totalMoney 除以 活动特定条件金额
                Integer actNum = new Double(totalMoney / specificGift.getSpecialCondition()).intValue();

                // 当前已参与该活动的次数
                Integer currentNum = actUserGiftService.getActTotalNum(actUserGift);

                // 可参与活动次数 大于 当前已参与该活动的次数 即可参与活动
                if(actNum > currentNum){
                    actUserGift.setSpecialCondition(specificGift.getSpecialCondition());
                    actUserGift.setGiftType(specificGift.getGiftType());
                    actUserGift.setGiftItem(specificGift.getGiftItem());
                    actUserGift.setGiftItemLabel(specificGift.getGiftItemLabel());
                    actUserGift.setGiftAmount(specificGift.getGiftAmount());
                }

                break;
        }

        return actUserGift;

    }




}
