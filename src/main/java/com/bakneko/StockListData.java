package com.bakneko;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockListData {

    @JsonProperty("f12")
    private String CodeID = null;

    @JsonProperty("f13")
    private int CodePrefix = 0;

    @JsonProperty("f14")
    private String Name = null;

    public String getCodeID() {
        return CodeID;
    }

    public void setCodeID(String codeID) {
        CodeID = codeID;
    }

    public int getCodePrefix() {
        return CodePrefix;
    }

    public void setCodePrefix(int codePrefix) {
        CodePrefix = codePrefix;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
