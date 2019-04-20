package com.stub.framework.stubs;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.stub.framework.csvreader.getEmployeeCacheReader;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class getEmployeeSalary {

    public void stubgetEmployeeSalary(WireMockServer wireMockServer, getEmployeeCacheReader ec)
    {

        wireMockServer.stubFor(get(urlPathEqualTo("/EmployeeSalary"))

               //.withQueryParam("EmployeeID", containing("WireMock"))
               // .withQueryParam("EmployeeID", matching("[0-9]{1}"))
                //.withHeader("EmployeeID", matching("[0-9]{1}"))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withHeader("name","balances")
                                .withHeader("Cache-Control", "no-cache")
                                .withTransformerParameter("val",ec)
                                .withTransformers("EmployeeSalaryTrf")
                ));

    }
}
