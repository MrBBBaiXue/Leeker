package com.bakneko;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class App
{
    public static String GetDayKApiUrl = "https://push2his.eastmoney.com/api/qt/stock/kline/get";
    // 一次性抓取的股票数量
    private static final int stockCount = 1000;
    public static String GetStockListUrl = "https://10.push2.eastmoney.com/api/qt/clist/get?pn=1&pz=" + stockCount + "&po=1&np=1&fltt=2&invt=2&fid=f3&fs=m:1+t:2,m:1+t:23&fields=f12,f13,f14";
    // 数据存储
    public static List<StockInfo> StockInfos = new ArrayList<>();
    public static StockListData StockLists[];


    public static void main( String[] args ) throws IOException {

        // 获取程序当前路径
        var programPath = Utility.getProgramPath();
        var dataPath = programPath + "/data/";
        File dir = new File(dataPath);
        if (!dir.exists()) { dir.mkdirs(); }

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
        System.out.println( "股票数据蜘蛛" );
        System.out.println( "--------------------------------------------" );
        System.out.println("当前时间: " + cdrToday + "  起始时间: " + cdrStart);
        System.out.println("股票抓取数: " + stockCount);
        System.out.println( "--------------------------------------------" );

        // 获取信息
        // 使用 Jackson JSON 获得列表 StockLists
        var objectMapper = new ObjectMapper();
        var stockListString = objectMapper.writeValueAsString(objectMapper.readTree(new URL(GetStockListUrl)).path("data").get("diff"));
        StockLists = objectMapper.readValue(stockListString, StockListData[].class);

        // 循环处理
        for (int i = 1; i <= stockCount; i++)
        {
            // 获得信息
            var stockInfo = new StockInfo(StockLists[i - 1].getName(), StockLists[i - 1].getCodeID(), StockLists[i - 1].getCodePrefix());
            System.out.println("[爬取数据][" + i + "/" + stockCount + "] 名称: " + stockInfo.getName() + "  代码： " + stockInfo.getCode());

            // 下载信息并保存
            stockInfo.DownloadDailyKLine(cdrStart, cdrToday);
            var path = dataPath + stockInfo.getCode() + ".json";
            stockInfo.SaveToFile(path);
            System.out.println("> " + path);

            // 保存到 StockInfos
            StockInfos.add(stockInfo);
        }

        return;
    }
}
