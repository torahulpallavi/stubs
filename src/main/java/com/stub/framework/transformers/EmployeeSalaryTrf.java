package com.stub.framework.transformers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.QueryParameter;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import com.stub.framework.csvreader.*;

import java.util.ArrayList;

public class EmployeeSalaryTrf extends ResponseDefinitionTransformer {

    @Override
    public boolean applyGlobally() {
        return false;
    }

    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {
        //System.out.println("Employee Salary - Tramsformer");
        String AccountID= null;//request.getHeader("AccountID");
        String record[] = new String[4];
        EmployeeBalCsvReader read = new EmployeeBalCsvReader();
        String EmployeeID= null;
       // EmployeeID=request.getHeader("EmployeeID");
        String path = request.getAbsoluteUrl();
        //EmployeeID=parameters.getString("EmployeeID");



        String absoluteUrl = request.getAbsoluteUrl();
        String[] pairedValues = absoluteUrl.split("\\?")[1].split("&");
        String[] values = pairedValues[0].split("=");
        EmployeeID=values[1];

        record=read.getData(EmployeeID);
        getEmployeeCacheReader x = (getEmployeeCacheReader) parameters.get("val");
        ArrayList l = x.getData(EmployeeID);




       /* for (String pair : pairedValues) {

            System.out.println("val"+pair);
        }*/
       // System.out.println("path =  " + values[1]);

        if (EmployeeID == null){

            return new ResponseDefinitionBuilder()
                    .withHeader("MyHeader", "Transformed")
                    .withStatus(200)
                    .withBody("{\n" +
                            "  \"id\" : \"1\",\n" +
                            "  \"f_name\" : \"Adam\",\n" +
                            "  \"l_name\" : \"Smith\",\n" +
                            "  \"skills\" : [\n" +
                            "    \"web development\",\n" +
                            "    \"ux design\",\n" +
                            "    \"data science\"\n" +
                            "  ],\n" +
                            "  \"salary\": \"101$\"\n" +
                            "}")
                    .build();



        }
        else {
            return new ResponseDefinitionBuilder()
                    .withHeader("MyHeader", "Transformed")
                    .withStatus(200)
                    .withFixedDelay(50)
                    .withBody("{\n" +
                            "  \"id\" : \""+EmployeeID+"\",\n" +
                            "  \"f_name\" : \""+l.get(0)+"\",\n" +
                            "  \"l_name\" : \""+l.get(1)+"\",\n" +
                            "  \"skills\" : [\n" +
                            "    \"web development\",\n" +
                            "    \"ux design\",\n" +
                            "    \"data science\"\n" +
                            "  ],\n" +
                            "  \"balance\": \""+l.get(2)+"\"\n" +
                            "}")
                    .build();
        }
    }

    @Override
    public String getName() {
        return "EmployeeSalaryTrf";
    }
}
