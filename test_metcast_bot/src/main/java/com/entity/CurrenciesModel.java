package com.entity;

import java.util.Objects;

public class CurrenciesModel {
    private String name;
    private String eurAsk;
    private String eurBid;
    private String rubAsk;
    private String rubBid;
    private String usdAsk;
    private String usdBid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEurAsk() {
        return eurAsk;
    }

    public void setEurAsk(String eurAsk) {
        this.eurAsk = eurAsk;
    }

    public String getEurBid() {
        return eurBid;
    }

    public void setEurBid(String eurBid) {
        this.eurBid = eurBid;
    }

    public String getRubAsk() {
        return rubAsk;
    }

    public void setRubAsk(String rubAsk) {
        this.rubAsk = rubAsk;
    }

    public String getRubBid() {
        return rubBid;
    }

    public void setRubBid(String rubBid) {
        this.rubBid = rubBid;
    }

    public String getUsdAsk() {
        return usdAsk;
    }

    public void setUsdAsk(String usdAsk) {
        this.usdAsk = usdAsk;
    }

    public String getUsdBid() {
        return usdBid;
    }

    public void setUsdBid(String usdBid) {
        this.usdBid = usdBid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrenciesModel that = (CurrenciesModel) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(eurAsk, that.eurAsk) &&
                Objects.equals(eurBid, that.eurBid) &&
                Objects.equals(rubAsk, that.rubAsk) &&
                Objects.equals(rubBid, that.rubBid) &&
                Objects.equals(usdAsk, that.usdAsk) &&
                Objects.equals(usdBid, that.usdBid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, eurAsk, eurBid, rubAsk, rubBid, usdAsk, usdBid);
    }
}
