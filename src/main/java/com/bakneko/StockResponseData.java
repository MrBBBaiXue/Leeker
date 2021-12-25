package com.bakneko;

public class StockResponseData {

    // 从JSON获得出的Data数据
    private int code = 0;
    private int market = 0;
    private String name = null;
    private int decimal = 0;
    private int dktotal = 0;
    private double preKPrice = 0.0;
    private String[] KLines = null;

    public int getCode() { return this.code; }
    public void setCode(int code) { this.code = code; }

    public int getMarket() { return this.market; }
    public void setMarket(int market) { this.market = market; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public int getDecimal() { return this.decimal; }
    public void setDecimal(int decimal) { this.decimal = decimal; }

    public int getDktotal() { return this.dktotal; }
    public void setDktotal(int dktotal) { this.dktotal = dktotal; }

    public double getPreKPrice() { return this.preKPrice; }
    public void setPreKPrice(int preKPrice) { this.preKPrice = preKPrice; }

    public String[] getKLines() { return this.KLines; }
    public void setKLines(String[] kLines) { this.KLines = kLines; }

}

/* [返回值示例]

{
  "rc": 0,
  "rt": 17,
  "svr": 182481159,
  "lt": 1,
  "full": 0,
  "data": {
    "code": 600066,
    "market": 1,
    "name": "宇通客车",
    "decimal": 2,
    "dktotal": 5884,
    "preKPrice": 11.04,
    "klines": [
      "2021-11-25,11.03,11.00,11.04,10.94,168758,185406924.00,0.91,-0.36,-0.04,0.76",
      "2021-12-23,10.83,10.96,11.01,10.79,204268,222973728.00,2.04,1.39,0.15,0.92",
      "2021-12-24,10.95,10.86,11.00,10.81,139973,152226117.00,1.73,-0.91,-0.10,0.63"
    ]
  }
}

 */
