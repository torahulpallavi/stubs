package com.stub.framework.csvreader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

public class getEmployeeCacheReader {

    int flag = 0;
    String empid = null;
    String firstName = null;
    String lastName = null;
    String team = null;
    String[] data = new String[4];

    HashMap<String, ArrayList> map = new HashMap<String, ArrayList>();

    public void fetchData() {
        try {
            System.out.println("loaded once");
            /*
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("AccountBalance.csv").getFile());
            Reader in = new FileReader(file);*/

            Reader in = new FileReader("AccountBalance.csv");
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {

                empid = record.get("empid");
                firstName = record.get("f_name");
                lastName = record.get("l_name");
                team = record.get("balance");

                ArrayList<String> r1 = new ArrayList<String>();

                r1.add(firstName);
                r1.add(lastName);
                r1.add(team);
                map.put(empid, r1);

            }

        } catch (Exception e) {
            System.out.println("Exception e " + e);

        }

    }

    public ArrayList<String> getData(String key) {

        ArrayList l1 = map.get(key);

        return l1;
    }

}
