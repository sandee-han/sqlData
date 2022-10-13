package com.line;

import com.line.domain.Hospital;
import com.line.parser.HospitalParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileController<Hospital> FC = new FileController<>(new HospitalParser());
        String filename = "C:\\Users\\iwmis\\Downloads\\seoul_hospital_info.csv";
        List<Hospital> hospitals = FC.readLines(filename);


        FC.writeFile(hospitals, "./seoul_hospital_info.sql");

        System.out.println(hospitals.size());
//        for (Hospital hospital : hospitals) {
//            System.out.printf("%s,%s,%s,%s,%d,%s,%s",
//                    hospital.getId(),
//                    hospital.getAddress(),
//                    hospital.getDistrict(),
//                    hospital.getCategory(),
//                    hospital.getEmergencyRoom(),
//                    hospital.getName(),
//                    hospital.getSubdivision());
//        }
    }
}
