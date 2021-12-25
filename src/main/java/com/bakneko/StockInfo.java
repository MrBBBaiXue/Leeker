package com.bakneko;

import java.io.IOException;

public class StockInfo {

    private String Name;
    private String Code;
    private StockKLineData[] DayKLine;

    public StockInfo(String name, String code)
    {
        Name = name;
        Code = code;
    }

    public String getName() { return Name; }

    public String getCode() { return Code; }

    public void DownloadDayK(String startDate, String endDate)
            throws IOException, InterruptedException {

        // 把正常的股票代码转换为东方财富的格式
        // e.g. 平安银行 SZ000001 -> 0.000001
        //      宇通客车 SH600066 -> 1.600066

        var prefix = Code.substring(0,2);
        var id = Code.substring(2);
        String apiPrefix = prefix == "SZ" ? "0" : "1";
        var apiCode = apiPrefix + "." + id;

        // 从东方财富网清洗出来的API获取信息
        var param = "?fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61&klt=101&fqt=1&secid="
                + apiCode + "&beg=" + startDate + "&end=" + endDate;

        var url = App.GetDayKApiUrl + param;
        var json = HttpUtil.GetHttpResponse(url);


        // 输出数据
        System.out.println(json);
    }

}
