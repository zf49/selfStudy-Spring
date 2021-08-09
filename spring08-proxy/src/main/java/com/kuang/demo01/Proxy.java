package com.kuang.demo01;

public class Proxy implements Rent{

       private Host host;

    public Proxy(Host host) {
        this.host = host;
    }

    public Proxy() {
    }

    @Override
    public void rent() {
        host.rent();
        seeHouse();
        getAgencyFee();
        makeContract();
    }

    //看房
    public void seeHouse(){
        System.out.println("中介带你去看房");
    }

    //收中介费
    public void getAgencyFee(){
        System.out.println("收中介费");
    }

    //签合同
    public void makeContract(){
        System.out.println("签租赁合同");
    }
}
