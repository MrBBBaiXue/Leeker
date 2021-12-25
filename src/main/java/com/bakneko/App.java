package com.bakneko;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class App
{
    public static String GetDayKApiUrl = "https://push2his.eastmoney.com/api/qt/stock/kline/get";
    public static void main( String[] args ) throws IOException {

        // 获取程序当前路径
        //var programPath = System.getProperty("usr.dir");

        // 获取今日时间
        Calendar cdr = Calendar.getInstance();
        cdr.setTime(new Date());

        String cdrToday = String.valueOf(cdr.get(Calendar.YEAR)) +
                String.valueOf(cdr.get(Calendar.MONTH) + 1) +
                String.valueOf(cdr.get(Calendar.DATE));

        cdr.add(Calendar.DATE,-30);
        String cdrStart = String.valueOf(cdr.get(Calendar.YEAR)) +
                String.valueOf(cdr.get(Calendar.MONTH) + 1) +
                String.valueOf(cdr.get(Calendar.DATE));

        // 初始输出
        System.out.println( "--------------------------------------------" );
        System.out.println( "股票数据蜘蛛 & 韭菜模拟器" );
        System.out.println( "--------------------------------------------" );
        System.out.println("当前时间: " + cdrToday + "  起始时间: " + cdrStart);
        System.out.println( "--------------------------------------------" );

        // 测试获取信息
        var stockYuTong = new StockInfo("宇通客车", "SH600066");
        System.out.println(stockYuTong.getCode() + " " + stockYuTong.getName());
        stockYuTong.DownloadDailyKLine(cdrStart, cdrToday);

        var path = stockYuTong.getCode() + ".json";
        stockYuTong.SaveToFile(path);
    }
}
