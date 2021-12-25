package com.bakneko;

public class StockResponseData {
    // 返回的JSON Metadata
    // 应当使用 Jackson进行解析
    private String brand = null;
    private int doors = 0;

    public String getBrand() { return this.brand; }
    public void   setBrand(String brand){ this.brand = brand;}

    public int  getDoors() { return this.doors; }
    public void setDoors (int doors) { this.doors = doors; }
}
