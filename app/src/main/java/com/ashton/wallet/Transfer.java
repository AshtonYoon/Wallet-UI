package com.ashton.wallet;

public class Transfer {
    private String Date, Amount, Dollar;
    private boolean mType = false;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDollar() {
        return Dollar;
    }

    public void setDollar(String dollar) {
        Dollar = dollar;
    }

    public boolean ismType() {
        return mType;
    }

    public void SetType(boolean type){
        mType = type;
    }
}
