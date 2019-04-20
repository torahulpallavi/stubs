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

public class EmployeeDetailsTrf extends ResponseDefinitionTransformer {

    @Override
    public boolean applyGlobally() {
        return false;
    }

    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {
        String AccountID= null;//request.getHeader("AccountID");
        String record[] = new String[4];
        EmployeeCsvReader read = new EmployeeCsvReader();
        String requestBody=request.getBodyAsString();

        JSONObject obj;
        try {
            obj = new JSONObject(requestBody);
             AccountID = obj.getJSONObject("Employee").getString("EmployeeID");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        record=read.getData(AccountID);


        //System.out.println("string = "+requestBody + "account id " + AccountID);


        if (AccountID == null){

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
                            "  \"team\": \"engineering\"\n" +
                            "}")
                    .build();



        }
        else {
            return new ResponseDefinitionBuilder()
                    .withHeader("MyHeader", "Transformed")
                    .withStatus(200)
                    .withFixedDelay(20)
                    .withBody("{\n" +
                            "  \"id\" : \""+record[0]+"\",\n" +
                            "  \"f_name\" : \""+record[1]+"\",\n" +
                            "  \"l_name\" : \""+record[2]+"\",\n" +
                            "  \"skills\" : [\n" +
                            "    \"web development\",\n" +
                            "    \"ux design\",\n" +
                            "    \"data science\"\n" +
                            "  ],\n" +
                            "  \"team\": \""+record[3]+"\"\n" +
                            "}")
                    .build();
        }
    }

    @Override
    public String getName() {
        return "EmployeeDetailsTrf";
    }
}
