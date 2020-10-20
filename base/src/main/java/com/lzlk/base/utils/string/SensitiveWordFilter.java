package com.lzlk.base.utils.string;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/11 16:32
 * @Created by 湖南达联
 */
public enum SensitiveWordFilter {

    INSTANCE;

    @SuppressWarnings("rawtypes")
    private Map sensitiveWordMap = null;
    public static int minMatchTYpe = 1;      //最小匹配规则
    public static int maxMatchType = 2;      //最大匹配规则
    public static String replaceChar = "**";

    /**
     * 构造函数，初始化敏感词库
     */
    SensitiveWordFilter(){
        sensitiveWordMap = new SensitiveWordInit().initKeyWord();
    }


    /**
     * 判断文字是否包含敏感字符
     * @author chenming
     * @date 2014年4月20日 下午4:28:30
     * @param txt  文字
     * @param matchType  匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
     * @return 若包含返回true，否则返回false
     * @version 1.0
     */
    public boolean isContaintSensitiveWord(String txt,int matchType){
        boolean flag = false;
        for(int i = 0 ; i < txt.length() ; i++){
            int matchFlag = this.CheckSensitiveWord(txt, i, matchType); //判断是否包含敏感字符
            if(matchFlag > 0){    //大于0存在，返回true
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取文字中的敏感词
     * @author chenming
     * @date 2014年4月20日 下午5:10:52
     * @param txt 文字
     * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
     * @return
     * @version 1.0
     */
    public Set<String> getSensitiveWord(String txt , int matchType){
        Set<String> sensitiveWordList = new HashSet<String>();

        for(int i = 0 ; i < txt.length() ; i++){
            int length = CheckSensitiveWord(txt, i, matchType);    //判断是否包含敏感字符
            if(length > 0){    //存在,加入list中
                sensitiveWordList.add(txt.substring(i, i+length));
                i = i + length - 1;    //减1的原因，是因为for会自增
            }
        }

        return sensitiveWordList;
    }

    /**
     * 替换敏感字字符
     * @author chenming
     * @date 2014年4月20日 下午5:12:07
     * @param txt
     * @param matchType
     * @version 1.0
     */
    public String replaceSensitiveWord(String txt,int matchType){
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt, matchType);     //获取所有的敏感词
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(SensitiveWordFilter.replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }

        return resultTxt;
    }

    /**
     * 获取替换字符串
     * @author chenming
     * @date 2014年4月20日 下午5:21:19
     * @param replaceChar
     * @param length
     * @return
     * @version 1.0
     */
    private String getReplaceChars(String replaceChar,int length){
        String resultReplace = replaceChar;
        for(int i = 1 ; i < length ; i++){
            resultReplace += replaceChar;
        }

        return resultReplace;
    }

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：<br>
     * @author chenming
     * @date 2014年4月20日 下午4:31:03
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return，如果存在，则返回敏感词字符的长度，不存在返回0
     * @version 1.0
     */
    @SuppressWarnings({ "rawtypes"})
    public int CheckSensitiveWord(String txt,int beginIndex,int matchType){
        boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
        int matchFlag = 0;     //匹配标识数默认为0
        char word = 0;
        Map nowMap = sensitiveWordMap;
        for(int i = beginIndex; i < txt.length() ; i++){
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);     //获取指定key
            if(nowMap != null){     //存在，则判断是否为最后一个
                matchFlag++;     //找到相应key，匹配标识+1
                if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
                    flag = true;       //结束标志位为true
                    if(SensitiveWordFilter.minMatchTYpe == matchType){    //最小规则，直接返回,最大规则还需继续查找
                        break;
                    }
                }
            }
            else{     //不存在，直接返回
                break;
            }
        }
        if(matchFlag < 2 || !flag){        //长度必须大于等于1，为词
            matchFlag = 0;
        }
        return matchFlag;
    }

    public static void main(String[] args) {

//        SensitiveWordFilter.INSTANCE.sensitiveWordMap.size();
        System.out.println(SensitiveWordFilter.INSTANCE.sensitiveWordMap.size());
        String string = "资龙茂";
        System.out.println("待检测语句字数：" + string.length());
        long beginTime = System.currentTimeMillis();
        boolean flag =  SensitiveWordFilter.INSTANCE.isContaintSensitiveWord(string,SensitiveWordFilter.maxMatchType);
        long endTime = System.currentTimeMillis();
        System.out.println("语句中是否包含敏感词：" + flag);
        System.out.println("总共消耗时间为：" + (endTime - beginTime));
        Set<String> sensitiveWord = SensitiveWordFilter.INSTANCE.getSensitiveWord(string, SensitiveWordFilter.maxMatchType);
        System.out.println(sensitiveWord.size() +" ====== " + sensitiveWord);
        System.out.println(SensitiveWordFilter.INSTANCE.replaceSensitiveWord(string,SensitiveWordFilter.minMatchTYpe));

    }
}
