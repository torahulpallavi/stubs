package com.stub.framework.stubs;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class EmployeeDetails {

    public void stubEmployeeDetails(WireMockServer wireMockServer)
    {

        wireMockServer.stubFor(post(urlEqualTo("/EmployeeSummary"))
                //.atPriority(1)
                //.withHeader("Content-Type", equalTo("application/json"))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withHeader("Cache-Control", "no-cache")
                                .withTransformers("EmployeeDetailsTrf")
                ));

    }
}
