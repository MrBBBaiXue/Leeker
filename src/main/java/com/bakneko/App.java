package com.bakneko;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;


public class App
{
    public static String GetDayKApiUrl = "https://push2his.eastmoney.com/api/qt/stock/kline/get";

    // 数据存储
    public static List<StockInfo> StockInfos = new ArrayList<>();
    public static StockListData StockLists[];

    // 一次性抓取的股票数量
    public static String GetStockListUrl(int count) {
        return "https://10.push2.eastmoney.com/api/qt/clist/get?pn=1&pz=" + count + "&po=1&np=1&fltt=2&invt=2&fid=f3&fs=m:1+t:2,m:1+t:23&fields=f12,f13,f14";
    }

    // 时间信息
    public static Calendar Cdr = Calendar.getInstance();
    public static String CdrStart, CdrToday;

    // 程序路径
    public static String ProgramPath;
    public static String DataPath;
    public static String SavePath;

    public static void main( String[] args ) throws IOException {

        // 获取程序当前路径
        ProgramPath = Utility.getProgramPath();
        DataPath = ProgramPath + "/data/";
        File dir = new File(DataPath);
        if (!dir.exists()) { dir.mkdirs(); }
        SavePath = ProgramPath + "/save/";
        dir = new File(SavePath);
        if (!dir.exists()) { dir.mkdirs(); }

        // 获取今日时间
        Cdr.setTime(new Date());

        CdrToday = String.valueOf(Cdr.get(Calendar.YEAR)) +
                String.valueOf(Cdr.get(Calendar.MONTH) + 1) +
                String.valueOf(Cdr.get(Calendar.DATE));

        Cdr.add(Calendar.DATE,-30);
        CdrStart = String.valueOf(Cdr.get(Calendar.YEAR)) +
                String.valueOf(Cdr.get(Calendar.MONTH) + 1) +
                String.valueOf(Cdr.get(Calendar.DATE));

        // 初始输出
        System.out.println( "--------------------------------------------" );
        System.out.println( "股票数据蜘蛛" );
        System.out.println( "--------------------------------------------" );
        System.out.print("\n");

        while (true)
        {
            String mode = "";
            //要求输入模式和数量
            System.out.println( "选择模式：" );
            System.out.println( "A. 数据爬取" );
            System.out.println( "B. 韭菜模拟器" );
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            mode = sc.next();
            System.out.print("\n");

            switch (mode)
            {
                case "a":
                case "A":
                    AcquireData();
                    break;

                case "b":
                case "B":
                    JiuCaiSimulator();
                    break;

                default:
                    System.out.println("[!] 无法识别的输入，请重新输入！");
                    System.out.print("\n");
                    break;
            }
        }
    }

    public static void AcquireData() throws IOException {

        // 数据爬取

        // 默认爬10个
        var count = 10;

        // 获得输入数量
        //要求输入模式和数量
        System.out.println( "[* 数据爬取] 输入爬取数量: " );
        System.out.println( "(目前只支持上证)" );
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);
        count = sc.nextInt();
        System.out.print("\n");

        // 获取信息
        // 使用 Jackson JSON 获得列表 StockLists
        var objectMapper = new ObjectMapper();
        var stockListString = objectMapper.writeValueAsString(objectMapper.readTree(new URL(GetStockListUrl(count))).path("data").get("diff"));
        StockLists = objectMapper.readValue(stockListString, StockListData[].class);

        // 循环处理
        for (int i = 1; i <= count; i++)
        {
            // 获得信息
            var stockInfo = new StockInfo(StockLists[i - 1].getName(), StockLists[i - 1].getCodeID(), StockLists[i - 1].getCodePrefix());
            System.out.println("[*] [" + i + "/" + count + "] 名称: " + stockInfo.getName() + "  代码： " + stockInfo.getCode());

            // 下载信息并保存
            stockInfo.DownloadDailyKLine(CdrStart, CdrToday);
            var path = DataPath + stockInfo.getCode() + ".json";
            stockInfo.SaveToFile(path);
            System.out.println("[√] " + path);
            System.out.print("\n");

            // 保存到 StockInfos
            StockInfos.add(stockInfo);
        }
        return;
    }

    public static void JiuCaiSimulator() throws IOException {

        // 韭菜模拟器

        //
        System.out.println( "[$ 韭菜模拟器] 游戏说明: " );
        System.out.println( "   本来要写一个可以保存加载的“游戏大作”的，但是因为给资本家打工去了，就没写完。" );
        System.out.println( "简单做一个猜测股票涨跌的程序吧，游戏规则很简单，程序先给出你刚刚爬取的股票列表，" );
        System.out.println( "然后从第一天开始，你拥有10W元，你可以选择购买一只股票，然后你可以选择买入或卖出" );
        System.out.println( "避免在股票涨跌之间狗带。" );
        System.out.print("> 按回车继续");
        new BufferedReader(new InputStreamReader(System.in)).readLine();
        System.out.print("\n");



        return;
    }

}
