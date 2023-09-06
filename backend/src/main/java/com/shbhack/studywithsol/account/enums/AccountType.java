package com.shbhack.studywithsol.account.enums;

public enum AccountType {

    DEPOSIT("예적금"),
    FOREIGN("외화"),
    FUND("펀드"),
    TRUST("신탁");

    private final String message;

    AccountType(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
