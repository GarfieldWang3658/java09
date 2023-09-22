package com.hspedu.houserent.view;

import com.hspedu.houserent.domain.House;
import com.hspedu.houserent.service.HouseService;
import com.hspedu.houserent.utils.Utility;


//显示主菜单
//编写listHouse（）显示房屋列表
public class HouseView {
    private boolean loop = true;//控制显示菜单
    private char key = ' ';//用户输入
    private HouseService houseService = new HouseService(2);
    //实现完成修改房屋信息的功能

    //修改房源信息
    public void updateHouse(){
        System.out.println("==========修 改 房 源==========");
        System.out.println("请输入要修改的房源编号(-1表示退出)");
        int updateId= Utility.readInt();
        if (updateId==-1 ){
            System.out.println("==========退 出 修 改==========");
            return;

        }
        //返回引用类型，就是数组的元素
        //因此后面的house.setxxx（）就会修改HouseService中的数组元素！！！
        House house = houseService.findById(updateId);

        if (house == null){
            System.out.println("==========要修改房源的ID不存在==========");
            return;
        }
        System.out.println("姓名("+house.getName()+"):");
        String name = Utility.readString(8,"");
        //如果用户直接回车，表示不修改该信息，默认""；
        if (!"".equals(name)){//修改
            house.setName(name);
        }
        System.out.println("电话("+house.getPhone()+"):");
        String phone = Utility.readString(12,"");
        //如果用户直接回车，表示不修改该信息，默认""；
        if (!"".equals(phone)){//修改
            house.setPhone(phone);
        }
        System.out.println("地址("+house.getAddress()+"):");
        String address = Utility.readString(20,"");
        //如果用户直接回车，表示不修改该信息，默认""；
        if (!"".equals(address)){//修改
            house.setAddress(address);
        }
        System.out.println("租金("+house.getRent()+"):");
        int rent = Utility.readInt(-1);
        //如果用户直接回车，表示不修改该信息，默认""；
        if (rent !=-1){//修改
            house.setRent(rent);
        }
        System.out.println("状态("+house.getState()+"):");
        String state = Utility.readString(5,"");
        //如果用户直接回车，表示不修改该信息，默认""；
        if (!"".equals(state)){//修改
            house.setState(state);
        }
        System.out.println("==========修改房源信息成功==========");

    }


    //实现根据id查找房屋信息的功能
    public void findHouse(){
        System.out.println("==========查 找 房 源==========");
        System.out.println("请输入要查找的房源编号：");
        int findId= Utility.readInt();
        House house = houseService.findById(findId);
        if (house != null ){
            System.out.println( house );
        }else{
            System.out.println("==========查找房源的ID不存在==========");
        }
    }

    //完成退出确认
    public void exit(){
        char c = Utility.readConfirmSelection();
        if (c=='Y'){
            loop=false;
        }
    }

    public void delHouse(){//接收输入的ID，调用service的del方法
        System.out.println("==========删 除 房 源==========");
        System.out.println("请输入待删除房源的编号（-1退出）：");
        int delId = Utility.readInt();
        if (delId==-1){
            System.out.println("你放弃了删除Id");
            return;
        }

        char choice = Utility.readConfirmSelection();//该方法本身自带循环判断逻辑，必须输入Y或者N
        if (houseService.del(delId)){
            System.out.println("==========删除房源成功==========");
        }else{
            System.out.println("=====房源编号不存在，删除失败=====");
        }
    }

    public void addHouse() {//接收用户的输入，创建house对象，调用add方法。
        System.out.println("==========新 增 房 源==========");
        System.out.println("姓名：");
        String name = Utility.readString(10);
        System.out.println("电话：");
        String phone = Utility.readString(12);
        System.out.println("地址：");
        String addres = Utility.readString(20);
        System.out.println("月租：");
        int rent = Utility.readInt();
        System.out.println("状态：");
        String state = Utility.readString(5);
        //注意ID是系统分配的，用户不能输入
        House newHouse = new House(0, name, phone, addres, rent, state);
        if (houseService.add(newHouse)) {
            System.out.println("==========添加房源成功==========");
        } else {
            System.out.println("==========添加房源失败==========");
        }
    }

    public void listHouse() {
        System.out.println("==========房屋列表==========");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseService.list();//得到所有房屋信息
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null) {//如果为null，就不要再显示了。
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("==========房屋列表显示完毕==========");

    }

    public void mainMenu() {
        do {
            System.out.println("==========房屋出租系统菜单==========");
            System.out.println("\t\t\t1、新 增 房 源");
            System.out.println("\t\t\t2、查 找 房 源");
            System.out.println("\t\t\t3、删 除 房 源");
            System.out.println("\t\t\t4、修 改 房 源");
            System.out.println("\t\t\t5、房 源 列 表");
            System.out.println("\t\t\t6、退 出 系 统");
            System.out.println("请输入你的选择（1-6）：");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    updateHouse();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    exit();
                    break;
            }

        } while (loop);
    }
}
