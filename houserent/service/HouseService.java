package com.hspedu.houserent.service;

import com.hspedu.houserent.domain.House;
import com.hspedu.houserent.utils.Utility;

//
public class HouseService {
    private House[] houses;//保存hose 对象
    private int houseNums = 1;//记录当前有多少个房屋信息
    private int idCount = 1;//记录id当前增长到哪个值了

    //构造器

    public HouseService(int size) {//当创建HoseService对象，指定数组大小。
        houses = new House[size];

        //创建一个初始化对象。
        houses[0] = new House(1, "jack", "123", "御代田", 2000, "未出租");

    }

    public House findById(int findId) {
        for (int i = 0; i < houseNums; i++) {
            if (findId == houses[i].getId()) {
                return houses[i];
            }
        }
                return null;


    }


    //del删除一个房屋信息。
    public boolean del(int delId){
        //应当先找到删除的房屋信息对应的下标。
        //下标和房屋的编号不是一回事
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId==houses[i].getId()){//要删除的房屋对应的id，在数组下标为i的元素
                index = i;//使用index记录i
            }
        }
        if (index==-1){
            //说明delId在数组中不存在
            return false;
        }


        for (int i = index; i < houseNums-1; i++) {
            houses[i]=houses[i+1];

        }
        houses[--houseNums]=null;//把当前存在的房屋信息的最后一个置空，
        //houseNums--;
        return true;

    }

    //add方法，添加新对象，返回boolean
    public boolean add (House newHouse){
        if (houseNums == houses.length ){
            System.out.println("数组已满不能再添加了。。。");
            return false;
        }
        //把newhouse对象加入到数组的最后。
        houses[houseNums++]=newHouse;//先赋值后自增
       // houseNum++;//新增加一个房屋信息
        //需要设计一个ID增长的机制,然后更新newHouse的id
        //idCount++;
        newHouse.setId(++idCount);//先自增后set
        return true;

    }

    //list方法，返回houses
    public House[]list(){
        return houses;
    }
}
