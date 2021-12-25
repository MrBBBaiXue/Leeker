package com.bakneko;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class StockInfo {

    private final String Name;
    private final String Code;

    private List<KLine> KLines;
    // 把KLine数据存储在这里

    public StockInfo(String name, String code) {
        Name = name;
        Code = code;
    }

    public StockInfo(String name, String codeID, int codePrefix) {
        Name = name;
        Code = (codePrefix == 1 ? "SH" : "SZ") + codeID;
    }

    public String getName() {
        return this.Name;
    }

    public String getCode() {
        return this.Code;
    }

    public List<KLine> getKLines() {
        return this.KLines;
    }

    public void setKLines(List<KLine> kLines) {
        KLines = kLines;
    }

    public void DownloadDailyKLine(String startDate, String endDate) throws IOException {

        // 把正常的股票代码转换为东方财富的格式
        // e.g. 平安银行 SZ000001 -> 0.000001
        //      宇通客车 SH600066 -> 1.600066
        var prefix = Code.substring(0, 2);
        var id = Code.substring(2);
        String apiPrefix;
        switch (prefix) {
            case "SH":
                apiPrefix = "1";
                break;

            case "SZ":
            default:
                apiPrefix = "0";
                break;
        }
        var apiCode = apiPrefix + "." + id;

        // 从东方财富网清洗出来的API获取信息
        var param = "?fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61&klt=101&fqt=1&secid="
                + apiCode + "&beg=" + startDate + "&end=" + endDate;
        var url = new URL(App.GetDayKApiUrl + param);

        // 使用 Jackson JSON 获得信息
        var objectMapper = new ObjectMapper();
        var data = objectMapper.writeValueAsString(objectMapper.readTree(url).path("data"));
        var stockResponseData = objectMapper.readValue(data, StockResponseData.class);

        // 调用StockResponseData.GetKLineList, 清洗数据，获得<KLine>
        KLines = stockResponseData.GetKLineList();
    }

    public String ToJsonString() throws JsonProcessingException {
        //转化为字符串
        var objectMapper = new ObjectMapper();
        var string = objectMapper.writeValueAsString(this);
        return string;
    }

    public void SaveToFile(String filePath) {
        // 输出到文件
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(ToJsonString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}