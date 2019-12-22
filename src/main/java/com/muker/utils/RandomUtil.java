package com.muker.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * @program: CarSys
 * @description:
 * @author: Feri
 * @create: 2019-12-09 16:51
 */
public class RandomUtil {

    /**
     * @param groupcount 每组的人数
     * @param total 总人数
     * @return Map集合 每组对应的人的序号*/
    public static Map<Integer, ArrayList<Integer>>  createGroup(int groupcount,int total){
        //1、保证添加顺序
        LinkedHashMap<Integer,ArrayList<Integer>> map=new LinkedHashMap<>();
        int[] arr=new int[total];//记录已经分配的人的序号
        Random random=new Random();
        int len=total/groupcount;
        int lg=0;
        //循环每一组
        for(int i=1;i<=len;i++){
            map.put(i,new ArrayList<>());
            for(int j=1;j<=groupcount;j++){
               int c=random.nextInt(total)+1;
                while (indexOf(arr,c)>-1){
                    //已分配 重新生成
                    c=random.nextInt(total)+1;
                }
                arr[lg]=c;
                map.get(i).add(c);
                lg++;
            }
        }
        return map;
    }

    private static int indexOf(int[] arr,int c){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==c){
                return i;
            }
        }
        return -1;
    }
    /**
     * 随机生成指定格式的数字
     * @param len 随机数字的个数*/
    public static int createNum(int len){
        Random random=new Random();
        //4  1000-9999   0-8999 +1000
        //5 10000-99999
        //[0,num)
        //Math.pow(10,len)  10的len次幂
        int max=(int)(Math.pow(10,len)-Math.pow(10,len-1));
        return random.nextInt(max)+(int)Math.pow(10,len-1);
    }
    public static void main(String[] args) {
        System.out.println(createNum(4));
    }
}
