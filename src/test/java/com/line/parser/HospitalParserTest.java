package com.line.parser;

import com.line.FileController;
import com.line.domain.Hospital;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HospitalParserTest {
    // alt + insert
    String line1 = "\"A1120837\",\"서울특별시 금천구 벚꽃로 286 삼성리더스타워 111~114호 (가산동)\",\"C\",\"의원\",\"G099\",\"응급의료기관 이외\",\"2\",\"외과: 상시진료 내과는 당분간 휴진\",\"서울시 송파구 문정동 장지동 법조단지 위례 가락동 가락시장역 위치 삼성서울병원 외래교수 출신 구강외과 전문의 진료 진료과목 - 임플란트 치조골 뼈이식 수술 매복 사랑니 발치 턱관절 악관절 질환의 치료 교정 치료 및 기타 보존 보철(크라운 브릿지 인레이) 신경치료\",\"방이역 1번출구 바로옆 굿모닝 신한증권 뒷건물\",\"가산기대찬의원\",\"02-6267-2580\",\"02-920-5374\",\"1930\",\"1930\",\"1930\",\"1930\",\"1930\",\"1500\",\"1500\",\"1500\",\"0900\",\"0900\",\"0900\",\"0900\",\"0900\",\"0900\",\"1000\",\"1000\",\"085\",\"11\",\"126.88412249700781\",\"37.4803938036867\",\"2022-04-07 14:55:00.0\"";
    String line2 = "A1104130,서울특별시 강남구 도산대로 118 (논현동 신사빌딩 2층),C,의원,G099,응급의료기관 이외,2,비만클리닉,365일 진료 국민건강보험공단 검진 지정병원 점심시간 13~14시,신사역 1번 출구 인근,365엠씨의원,02-516-3650,02-920-5374,2000,2000,1900,2000,2000,1600,1400,1400,1100,1100,1100,1100,1100,1100,900,900,60,38,127.0218085,37.5166042,54:49.0";
    String line3 = "A1117873,서울특별시 관악구 신원로 38 5층 (신림동 청암빌딩),N,치과의원,G099,응급의료기관 이외,2,대표번호1 지역번호 추가20170118150453,서울시 송파구 문정동 장지동 법조단지 위례 가락동 가락시장역 위치 삼성서울병원 외래교수 출신 구강외과 전문의 진료 진료과목 - 임플란트 치조골 뼈이식 수술 매복 사랑니 발치 턱관절 악관절 질환의 치료 교정 치료 및 기타 보존 보철(크라운 브릿지 인레이) 신경치료,서월치안센터 인근 청암빌딩 5층,가로수치과의원,02-882-2750,02-920-5374,1900,2100,1900,2100,1900,1400,1500,1500,1000,1000,930,1400,1000,1000,1000,1000,87,76,126.9293767,37.48191799,54:55.0";

    // test code 추상화
    private void assertHospital(Hospital hospital, String eId, String eAddress, String eDistrict, String eCategory, Integer eEmergencyRoom, String eName, String eSubdivision) {

        String address = "서울특별시 금천구 벚꽃로 286 삼성리더스타워 111~114호 (가산동)";

        Assertions.assertEquals(eId, hospital.getId());

        // 주소가 잘 파싱되는지 테스트 추가
        Assertions.assertEquals(eAddress, hospital.getAddress());

        // District Parsing test
        Assertions.assertEquals(eDistrict, hospital.getDistrict());

        Assertions.assertEquals(eCategory, hospital.getCategory());

        Assertions.assertEquals(eEmergencyRoom, hospital.getEmergencyRoom());

        Assertions.assertEquals(eName, hospital.getName());

        Assertions.assertEquals(eSubdivision, hospital.getSubdivision());
    }

    // annotation
    @Test
    @DisplayName("Parsing doing well")
    void hospitalParsing() {
        HospitalParser hospitalParser = new HospitalParser();
        // ctrl + tab
        Hospital hospital = hospitalParser.parse(this.line1);

        String address = "서울특별시 금천구 벚꽃로 286 삼성리더스타워 111~114호 (가산동)";

        assertHospital(hospitalParser.parse(this.line3),
                "A1117873",
                "서울특별시 관악구 신원로 38 5층 (신림동 청암빌딩)",
                "서울특별시 관악구",
                "N",
                2,
                "가로수치과의원",
                "치과"
        );
    }

    @Test
    @DisplayName("insert쿼리를 잘 만드는지 test")
    void makeSqlQueryTest() {

        HospitalParser hospitalParser = new HospitalParser();
        Hospital hospital = hospitalParser.parse(this.line1);
        String sql = "INSERT INTO `likelion-db`.`seoul_hospital`\n" +
                "(`id`,`address`,`district`,`category`,`emergency_room`,`name`,`subdivision`)\n" +
                "VALUES\n" +
                "(\"A1120837\",\n" +
                "\"서울특별시 금천구 벚꽃로 286 삼성리더스타워 111~114호 (가산동)\",\n" +
                "\"서울특별시 금천구\",\n" +
                "\"C\",\n" +
                "2,\n" +
                "\"가산기대찬의원\",\n" +
                "\"\");";
        Assertions.assertEquals(sql, new FileController<Hospital>(new HospitalParser()).getHospitalSqlInsertQuery() + hospital.getTupleString());
    }
}
