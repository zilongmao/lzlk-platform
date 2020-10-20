package com.lzlk.base.utils.text;

import java.util.Random;

/**
  *  通用工具类
  *  @author  Longmao
  *
  */
public  class InvitationUtils  {

/**  自定义进制(O,I没有加入,容易与0,1混淆)  */
        private  static  final  char[]  r=new  char[]{'Q',  'W',  'E',  '8',  'A',  'S',  '2',  'D',  'Z',  'X',  '9',  'C',  '7',  'P',  '5',  '1',  'K',  '3',  'M',  'J',  'U',  'F',  'R',  '4',  'V',  'Y',  'L',  'T',  'N',  '6',  'B',  'G',  'H'};

        /**  (不能与自定义进制有重复)  */
        private  static  final  char  b='0';

        /**  进制长度  */
        private  static  final  int  binLen=r.length;

        /**  序列最小长度  */
        private  static  final  int  s=6;

        /**
          *  @Classname  邻座旅客
          *  @Description  TODO
          *  @Date  2019/4/13  14:41
          *  @Created  by  湖南达联
          */
public  static    String  getInvitationCode(int  id){
  char[]  buf=new  char[32];
                int  charPos=32;
                while((id  /  binLen)  >  0)  {
                        int  ind= id  %  binLen;
                        //  System.out.println(num  +  "-->"  +  ind);
                        buf[--charPos]=r[ind];
                        id  /=  binLen;
                }
                buf[--charPos]=r[id  %  binLen];
                //  System.out.println(num  +  "-->"  +  num  %  binLen);
                String  str=new  String(buf,  charPos,  (32  -  charPos));
                //  不够长度的自动随机补全
                if(str.length()  <  s)  {
                        StringBuilder  sb=new  StringBuilder();
                        sb.append(b);
                        Random  rnd  =  new  Random();
                        for(int  i=1;  i  <  s  -  str.length();  i++)  {
                                sb.append(r[rnd.nextInt(binLen)]);
                        }
                        str+=sb.toString();
                }
                return  str;
        }

        public  static  long  codeToId(String  code)  {
                char[] chs =code.toCharArray();
                long  res=0L;
                for(int  i=0;  i  <  chs.length;  i++)  {
                        int  ind=0;
                        for(int  j=0;  j  <  binLen;  j++)  {
                                if(chs[i]  ==  r[j])  {
                                        ind=j;
                                        break;
                                }
                        }
                        if(chs[i]  ==  b)  {
                                break;
                        }
                        if(i  >  0)  {
                                res=res  *  binLen  +  ind;
                        }  else  {
                                res=ind;
                        }
                        //  System.out.println(ind  +  "-->"  +  res);
                }
                return  res;
        }

    public static void main(String[] args) {
        System.out.println(codeToId("D48Q0L"));
    }
}