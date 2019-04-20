package com.stub.framework.csvreader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class EmployeeCsvReader {

    public String[] getData(String AccountID) {
        int flag = 0;
        String empid = null;
        String firstName = null;
        String lastName = null;
        String team = null;
        String[] data = new String[4];
        try {
            /*
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("EmployeeDetails.csv").getFile());
            Reader in = new FileReader(file);*/
            Reader in = new FileReader("EmployeeDetails.csv");
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {
                empid = record.get("empid");
                if (empid.equals(AccountID)) {
                    flag = 1;
                    firstName = record.get("f_name");
                    lastName = record.get("l_name");
                    team = record.get("team");
                    break;
                }

            }

        } catch (Exception e) {
            System.out.println("Exception e " + e);

        }

        if (flag == 0) {
            empid = "1";
            firstName = "Sam";
            lastName = "gordon";
            team = "manufacturing";
        }

        data[0] = AccountID;
        data[1] = firstName;
        data[2] = lastName;
        data[3] = team;

        return data;
    }

}
