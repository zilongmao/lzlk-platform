package com.lzlk.base.utils.string;


import java.text.MessageFormat;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/5/15 12:04
 * @Created by 湖南达联
 */
public class MessageFormatUtils {

    /**
     * 星粉榜消息模板
     */
    private static final String FANS_RANK_TOP_REWARD_MSG = "恭喜你,荣登昨日粉丝贡献榜TOP{0},奖励{1}积分已入账,请查收!";

    /**
     * 积分打榜弹幕模板
     */
    private static final String INTEGRAL_VOTE_MSG = "@{0}正在为@{1}疯狂打榜+{2}";

    /**
     * 爱心打榜弹幕模板
     */
    private static final String LOVE_VOTE_MSG = "@{0}正在为@{1}公益助力+{2}颗爱心";

    /**
     * 快乐币打榜弹幕
     */
    private static final String COIN_VOTE_MSG = "@{0}正在给@{1}比心+{2}";



    /**
     * 大波粉积分打榜弹幕模板
     */
    private static final String INTEGRAL_POPULATION_VOTE_MSG = "号外号外~一大波迷妹在为@{0}打榜！";

    /**
     * 大波粉快乐币打榜弹幕模板
     */
    private static final String COIN_POPULATION_VOTE_MSG="厉害了~粉丝们正在为@{0}比心！";

    /**
     * 大波粉召唤打榜弹幕模板
     */
    private static final String SHARE_POPULATION_VOTE_MSG="@{0}的粉丝们正在使用召唤技能！";

    /**
     * 大波粉打CALL打榜弹幕模板
     */
    private static final String CALL_POPULATION_VOTE_MSG="粉丝们正在给@{0}疯狂打CALL";


    private static final String LOVE_GAME_TEAM_VOTE_MSG="集合咯~@{0}爱心公益队伍正在发起召唤！";


    //评论标题
    private static  final  String COMMENT_TITLE="{0}评论了你的帖子";

    //点赞标题
    private static  final  String ADDLIKE_TITLE="{0}点赞了你的帖子";

    //打榜人气值弹幕
    private static  final  String POPULARITY_TITLE="{0}为{1}贡献了+{2}人气~~";


    //打CALL被官方置顶or删除
    private static  final  String CALL_OPERATE="你在{0}粉丝会发表的帖子被{1}";

    private static  final  String CALL_RECOVER="你申述的帖子已被恢复";

    private static  final  String  CALL_RECOMMEND="你发表的帖子被推荐到首页";

    private  static  final  String CALL_REFUSE="很遗憾，你的帖子申诉被拒绝";

    private  static  final  String STAR_GUARD="恭喜你，已成为{0}粉丝会守护大使";

    private  static  final  String STAR_MISS_GUARD="很遗憾，您在{0}粉丝会守护大使职位被撤销";

    private  static  final  String  STAR_HEAD_PORTRAIT="你在{0}粉丝会修改的爱豆头像审核通过";

    private  static  final  String  STAR_HEAD_PORTRAIT_REFUSE="你在{0}粉丝会修改的爱豆头像审核不通过";

    private  static  final  String  CALL_CONENT="{0}:{1}";








    /**
     * 获取星粉榜TOP奖励消息
     * @param arguments
     * @return
     */
    public static String getFansRankTopRewardMsg(Object... arguments){
        return messageFormat(FANS_RANK_TOP_REWARD_MSG,arguments);
    }


    /**
     * 获取积分打榜消息
     * @param arguments
     * @return
     */
    public static String getIntegralVoteMsg(Object... arguments){
        return messageFormat(INTEGRAL_VOTE_MSG,arguments);
    }


    /**
     * 获取快乐币打榜消息
     * @param arguments
     * @return
     */
    public static String getCoinVoteMsg(Object... arguments){
        return messageFormat(COIN_VOTE_MSG,arguments);
    }


    /**
     * 获取爱心打榜消息
     * @return
     */
    public static String getLoveVoteMsg(Object... arguments){
        return messageFormat(LOVE_VOTE_MSG,arguments);
    }


    /**
     * 获取积分打榜大波粉消息
     * */
    public static String getIntegralPopulationVoteMsg(Object... arguments){
        return messageFormat(INTEGRAL_POPULATION_VOTE_MSG,arguments);
    }

    /**
     * 获取快乐币打榜大波粉消息
     * */
    public static String  getCoinPopulationVoteMsg(Object... arguments){
        return messageFormat(COIN_POPULATION_VOTE_MSG,arguments);
    }

    /**
     * 获取召唤打榜大波粉消息
     * */
    public static String getSharePopulationVoteMsg(Object... arguments){
       return messageFormat(SHARE_POPULATION_VOTE_MSG,arguments);
    }

    /**
     * 获取打CALL打榜大波粉消息
     * */
    public static String getCallPopulationVoteMsg(Object... arguments){
        return messageFormat(CALL_POPULATION_VOTE_MSG,arguments);
    }

    /**
     * 获取爱心公益队伍前十次捐赠消息
     * */
    public static String getLoveGameTeamVoteMsg(Object... arguments){
        return messageFormat(LOVE_GAME_TEAM_VOTE_MSG,arguments);
    }

    /**
     * 获取评论标题
     * */
    public static String getCommentTitle(Object... arguments){
        return messageFormat(COMMENT_TITLE,arguments);
    }

    /**
     * 获取点赞标题
     * */
    public static String getAddlikeTitle(Object... arguments){
        return messageFormat(ADDLIKE_TITLE,arguments);
    }

    /**
     * 获取人气弹幕
     * */
    public static String getPopularityTitle(Object... arguments){
        return messageFormat(POPULARITY_TITLE,arguments);
    }

    //打CALL被操作
    public static String getCallOperate(Object... arguments){return  messageFormat(CALL_OPERATE,arguments);}

    //恢复申述打call
    public static String getCallRecover(Object... arguments){return  messageFormat( CALL_RECOVER,arguments);}

    //call被推荐到首页
    public static String getCallRecommend(Object... arguments){return  messageFormat( CALL_RECOMMEND,arguments);}

    //拒绝申述打call
    public static String getCallRefuse(Object... arguments){return  messageFormat( CALL_REFUSE,arguments);}

    // 成为守护大使
    public static String getStarGuard(Object... arguments){return  messageFormat( STAR_GUARD,arguments);}

    // 被撤销守护大使
    public static String getStarMissGuard(Object... arguments){return  messageFormat( STAR_MISS_GUARD,arguments);}

    //修改粉丝会头像成功
    public static String getStarHeadPortrait(Object... arguments){return  messageFormat( STAR_HEAD_PORTRAIT,arguments);}

    //修改粉丝会头像失败
    public static String getStarHeadPortraitRefuse(Object... arguments){return  messageFormat( STAR_HEAD_PORTRAIT_REFUSE,arguments);}


    public static  String getCallConent(Object...arg){
        return messageFormat(CALL_CONENT,arg);
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
        System.out.println(getFansRankTopRewardMsg(1, 100));
        System.out.println(getIntegralVoteMsg("蓝内裤","海尔兄弟",100));
        System.out.println(getCoinVoteMsg("蓝内裤","海尔兄弟",100));
        System.out.println(getLoveVoteMsg("蓝内裤","海尔兄弟",100));
    }
}
