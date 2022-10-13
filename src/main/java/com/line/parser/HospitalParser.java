package com.line.parser;


import com.line.domain.Hospital;

public class HospitalParser implements Parser<Hospital>{

    private String replaceAllQuot(String str){
        return str.replaceAll("\"","");
    }

    @Override
    public Hospital parse(String str) {

        // 라인 받을때 부터 " 지우는 방법
        str = str.replaceAll("\"","");

        String[] splitted = str.split(",");

        return new Hospital(splitted[0],splitted[1],splitted[1],splitted[2],Integer.parseInt(splitted[6]),splitted[10],splitted[10]);
    }
}
