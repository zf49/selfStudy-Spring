package com.kuang.demo01;

public class Client {
    public static void main(String[] args) {
        //房东要出租房子
        Host host = new Host();
        //代理 ，中介帮房东租房子，但是代理一般会有一些附属操作y
        Proxy proxy = new Proxy(host);

        //我不用面对房东，直接找中介
        proxy.rent();
    }
}
