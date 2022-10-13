package com.line;

import com.line.domain.Hospital;
import com.line.parser.HospitalParser;
import com.line.parser.Parser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileController<T> {
    public FileController(Parser<T> parser) {
        this.parser = parser;
    }
    Parser<T> parser;
    boolean isRemoveColumnName = true;
    List<T> readLines(String filename) throws IOException {
        List<T> result = new ArrayList<>();
        BufferedReader br; //= new BufferedReader(new FileReader(filename));
        br = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
        String str;
        if(isRemoveColumnName){
            br.readLine();
        }
        while ((str = br.readLine()) != null) {
            result.add(parser.parse(str));
        }
        return result;
    }

    public void writeFile(List<Hospital> hps, String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write(getSqlInsertQuery());
        for (Hospital hp : hps) {
            bw.append(hp.getTupleString());
        }
        bw.write(";");
        bw.close();
    }

    public String getSqlInsertQuery() {
        String sqlLine = String.format("INSERT INTO `likelion-db`.`seoul_hospital`\n" +
                "(`id`,`address`,`district`,`category`,`emergency_room`,`name`,`subdivision`)\n" +
                "VALUES\n");
        return sqlLine;
    }

//    public void write(List<String> strs, String filename) {
//        File file = new File(filename);
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            for (String str : strs) {
//                writer.write(str);
//
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



}
