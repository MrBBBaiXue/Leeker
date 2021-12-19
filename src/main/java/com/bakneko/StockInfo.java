package com.bakneko;

import java.io.IOException;
import java.util.Date;

public class StockInfo {
    private String Name;
    private String Code;
    private StockData[] DayK;
    public StockInfo(String name, String code)
    {
        Name = name;
        Code = code;
    }
    public String GetName() { return Name; }
    public String GetCode() { return Code; }
    public void DownloadDayK(String startDate)
            throws IOException, InterruptedException {
        String param = "/stock/kline/day?code="+ Code +"&startDate=" + startDate;
        // e.g. https://api.doctorxiong.club/v1/stock/kline/day?code=sh600066&startDate=2021-11-01
        System.out.println(HttpUtil.GetHttpResponse(App.ApiUrl + param));
    }
}
