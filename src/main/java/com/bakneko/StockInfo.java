package com.bakneko;

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
    public void DownloadDayK(Date startDate)
    {
        // e.g. https://api.doctorxiong.club/v1/stock/kline/day?code=sh600066&startDate=2021-11-01

    }
}
