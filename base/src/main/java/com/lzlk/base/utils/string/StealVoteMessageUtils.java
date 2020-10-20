package com.lzlk.base.utils.string;

import java.text.MessageFormat;

public class StealVoteMessageUtils {


    private static final String STEAL_VOTE_TOAST_MEG="偷票成功,为{0}增加{1}人气值~";

    private static final String STEAL_VOTE_STAR_MEG="{0}去{1}粉丝会偷取{2}人气值";

    private static final String STOLEN_VOTE_STAR_MEG="{0}偷取了{1}人气值";

    private static final String VIP_STEAL_VOTE_STAR_MEG="{0}去{1}粉丝会偷取{2}人气值,vip周卡权益+{2}人气";

    private static final String VIP_STOLEN_VOTE_STAR_MEG="{0}偷取了{1}人气值,vip周卡权益+{2}人气";


    public  static String getStealVoteToastMeg(Object... arguments){
        return messageFormat(STEAL_VOTE_TOAST_MEG,arguments);
    }

    public  static String getStealVoteStarMeg(Object... arguments){
        return messageFormat(STEAL_VOTE_STAR_MEG,arguments);
    }

    public  static String getStolenVoteStarMeg(Object... arguments){
        return messageFormat(STOLEN_VOTE_STAR_MEG,arguments);
    }

    public  static String getVipStealVoteStarMeg(Object... arguments){
        return messageFormat(VIP_STEAL_VOTE_STAR_MEG,arguments);
    }

    public  static String getVipStolenVoteStarMeg(Object... arguments){
        return messageFormat(VIP_STOLEN_VOTE_STAR_MEG,arguments);
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

    public static void main(String[] args) {
        System.out.println(getStealVoteStarMeg("易成仙","海尔兄弟","7"));
        System.out.println(getStolenVoteStarMeg("易成仙","7"));
    }
}
