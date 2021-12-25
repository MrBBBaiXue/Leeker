package com.bakneko;

public class KLine {

    private String Date = null;
    private double Open = 0.00;
    private double Close = 0.00;
    private double Max = 0.00;
    private double Min = 0.00;
    private double DealMount = 0.00;
    private double DealPrice = 0.00;
    private double Amplitude = 0.00;
    private double AmplRatio = 0.00;
    private double AmplPrice = 0.00;
    private double TradeRatio = 0.00;

    /*
    * Date: 时间
    * Open: 开盘
    * Close: 收盘
    * Max: 最高
    * Min: 最低
    * DealMount: 成交数
    * DealPrice: 成交额
    * Amplitude: 振幅
    * AmplRatio: 涨跌幅
    * AmplPrice: 涨跌额
    * TradeRatio: 换手率
    * */

    public KLine(String date, double open, double close, double max,
                 double min, double dealMount, double dealPrice,
                 double amplitude, double amplRatio, double amplPrice, double tradeRatio) {
        // 构造KLine对象
        Date = date;
        Open = open;
        Close = close;
        Max = max;
        Min = min;
        DealMount = dealMount;
        DealPrice = dealPrice;
        Amplitude = amplitude;
        AmplRatio = amplRatio;
        AmplPrice = amplPrice;
        TradeRatio = tradeRatio;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getOpen() {
        return Open;
    }

    public void setOpen(double open) {
        Open = open;
    }

    public double getClose() {
        return Close;
    }

    public void setClose(double close) {
        Close = close;
    }

    public double getMax() {
        return Max;
    }

    public void setMax(double max) {
        Max = max;
    }

    public double getMin() {
        return Min;
    }

    public void setMin(double min) {
        Min = min;
    }

    public double getDealMount() {
        return DealMount;
    }

    public void setDealMount(double dealMount) {
        DealMount = dealMount;
    }

    public double getDealPrice() {
        return DealPrice;
    }

    public void setDealPrice(double dealPrice) {
        DealPrice = dealPrice;
    }

    public double getAmplitude() { return Amplitude; }

    public void setAmplitude(double amplitude) {
        Amplitude = amplitude;
    }

    public double getAmplRatio() {
        return AmplRatio;
    }

    public void setAmplRatio(double amplRatio) {
        AmplRatio = amplRatio;
    }

    public double getAmplPrice() {
        return AmplPrice;
    }

    public void setAmplPrice(double amplPrice) {
        AmplPrice = amplPrice;
    }

    public double getTradeRatio() {
        return TradeRatio;
    }

    public void setTradeRatio(double tradeRatio) {
        TradeRatio = tradeRatio;
    }

}
