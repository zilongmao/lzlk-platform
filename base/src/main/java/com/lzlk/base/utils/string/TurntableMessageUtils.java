package com.lzlk.base.utils.string;

import java.text.MessageFormat;

/**
*  @author 邻座旅客
*  @Description TODO
*  @Date  2019年7月10日14:51:06
*  @Created by 湖南达联
*/

public class TurntableMessageUtils {

    private static final String TURNTABLE_PRIZE_MSG="恭喜{0}抽到了{1}~~";


    public  static String getTurntablePrizeMsg(Object... arguments){
        return messageFormat(TURNTABLE_PRIZE_MSG,arguments);
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
        System.out.println(getTurntablePrizeMsg("易成仙","大宝剑"));
    }
}
