package com.bakneko;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        var stockYuTong = new StockInfo("宇通客车", "sh600066");
        System.out.println(stockYuTong.GetCode() + " " + stockYuTong.GetName());
    }
}
