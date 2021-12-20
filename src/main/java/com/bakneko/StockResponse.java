package com.bakneko;

import com.alibaba.fastjson.annotation.JSONField;

public class StockResponse {
    @JSONField(name = "code")
    private int Code;
    public int getCode() { return Code; }

    @JSONField(name = "message")
    private String Message;
    public String getMessage() { return Message; }

    @JSONField(name = "data")
    private StockData[] Data;
    public StockData[] getData() { return Data; }
    public void setData(StockData[] data) { Data = data; }

    public StockResponse(int code, String message, StockData[] data)
    {
        Code = code;
        Message = message;
        Data = data;
    }
}
