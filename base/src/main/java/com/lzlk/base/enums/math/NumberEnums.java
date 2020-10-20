package com.lzlk.base.enums.math;

import java.util.Arrays;

/**
 * @Classname 数字枚举类
 * @Created by 湖南达联
 * @Date 2019/8/1 18 14
 * @Description:
 * @Author: 资龙茂
 */
public enum NumberEnums {

    ZERO(0, "ZERO"),

    ONE(1, "ONE"),

    TWO(2, "TWO"),

    THREE(3, "THREE"),

    FOUR(4, "FOUR"),

    FIVE(5, "FIVE"),

    SIX(6, "SIX"),

    SEVEN(7, "SEVEN"),

    EIGHT(8, "EIGHT"),

    NINE(9, "NINE"),

    TEN(10, "TEN"),

    ELEVEN(11, "ELEVEN"),

    TWELVE(12, "TWELVE"),

    THIRTEEN(13, "THIRTEEN"),

    FOURTEEN(14, "FOURTEEN"),

    FIFTEEN(15, "FIFTEEN"),

    SIXTEEN(16, "SIXTEEN"),

    SEVENTEEN(17, "SEVENTEEN"),

    EIGHTEEN(18, "EIGHTEEN"),

    NINETEEN(19, "NINETEEN"),

    TWENTY(20, "TWENTY"),

    TWENTY_FIVE(25,"TWENTY_FIVE"),

    THIRTY(30, "THIRTY"),

    THIRTY_ONE(31, "THIRTY_ONE"),

    FORTY(40, "FORTY"),

    FIFTY(50, "FIFTY"),

    SIXTY(60, "SIXTY"),

    SEVENTY(70, "SEVENTY"),

    EIGHTY(80, "EIGHTY"),

    NINETY(90, "NINETY"),

    ONE_HUNDERD(100, "ONE_HUNDERD"),

    FIVE_HUNDERD(500,"FIVE_HUNDERD"),

    ONE_THOUSAND(1000, "ONE_THOUSAND"),

    TEN_THOUSAND(10000, "TEN_THOUSAND")
    ;

    private Integer number;

    private String english;


    NumberEnums(Integer number, String english) {
        this.number = number;
        this.english = english;
    }

    public Integer getNumber() {
        return number;
    }

    public String getEnglish() {
        return english;
    }

    public static NumberEnums eval(Integer number) {
        if (number == null) {
            return null;
        }
        return Arrays.stream(NumberEnums.values()).filter(input -> input.number.equals(number)).findFirst()
                .orElseGet(null);
    }
}
