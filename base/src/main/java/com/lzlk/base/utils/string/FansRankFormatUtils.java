package com.lzlk.base.utils.string;


import java.text.MessageFormat;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019年5月17日
 * @Created by 湖南达联
 */
public class FansRankFormatUtils {

    public static final String REWARD_DAY_REASON = "粉丝贡献日榜TOP{0}-奖励";

    public static final String REWARD_DAY_TOP_NUM_TITLE = "粉丝贡献日榜TOP{0}奖励发放";

    public static final String REWARD_DAY_PUSH_CONTENT = "恭喜你，荣登昨日粉丝贡献榜TOP{0}，获得奖励{1}积分已入账，请查收！";

    public static final String REWARD_MONTH_REASON = "粉丝贡献月榜TOP{0}-奖励";

    public static final String REWARD_MONTH_TOP_NUM_TITLE = "粉丝贡献月榜TOP{0}奖励发放";

    public static final String REWARD_MONTH_PUSH_CONTENT = "恭喜你，荣登上月星粉榜TOP{0}，获得奖励{1}积分已入账，请查收！";

   /**
    * 奖励原因
    * */
    public static String getRewardDayReason(Object... arguments){
        return messageFormat(REWARD_DAY_REASON,arguments);
    }

    /**
     * 奖励标题
     * */
    public static String getRewardDayTopNumTitle(Object... arguments){
        return  messageFormat(REWARD_DAY_TOP_NUM_TITLE,arguments);
    }

    public static String getRewardDayPushContent(Object... arguments){
        return  messageFormat(REWARD_DAY_PUSH_CONTENT,arguments);
    }


    /**
     * 奖励原因
     * */
    public static String getRewardMonthReason(Object... arguments){
        return messageFormat(REWARD_MONTH_REASON,arguments);
    }

    /**
     * 奖励标题
     * */
    public static String getRewardMonthTopNumTitle(Object... arguments){
        return  messageFormat(REWARD_MONTH_TOP_NUM_TITLE,arguments);
    }

    public static String getRewardMonthPushContent(Object... arguments){
        return  messageFormat(REWARD_MONTH_PUSH_CONTENT,arguments);
    }
    /**
     * 格式化消息体
     * @param pattern
     * @param arguments
     * @return
     */
    private static String messageFormat(String pattern,Object... arguments){
        return  MessageFormat.format(pattern,arguments);
    }


}
