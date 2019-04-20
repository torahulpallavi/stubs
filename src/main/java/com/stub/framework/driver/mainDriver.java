package com.stub.framework.driver;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.stub.framework.csvreader.getEmployeeCacheReader;
import com.stub.framework.stubs.*;
import com.stub.framework.transformers.EmployeeDetailsTrf;
import com.stub.framework.transformers.EmployeeSalaryTrf;

public class mainDriver {

        public static void main(String args[]) {

                System.out.println("server started on port = " + System.getProperty("portNumer"));
                /* String portNumber="9090"; */
                String portNumber = System.getProperty("portNumer", "9098");
                int portNumberConvert = Integer.parseInt(portNumber);
                System.out.println("server started =" + portNumberConvert);

                WireMockServer wireMockServer = new WireMockServer(options().port(portNumberConvert)
                                .httpsPort(portNumberConvert + 1).containerThreads(200).jettyAcceptors(10)

                                .jettyAcceptQueueSize(100000)
                                .extensions(new EmployeeDetailsTrf()).extensions(new EmployeeSalaryTrf())

                );// No-args constructor will start on port 8080, no HTTPS

                /* start the server */
                wireMockServer.start();

                /* register Stubs */

                getEmployeeCacheReader EC = new getEmployeeCacheReader();
                EC.fetchData();
                new EmployeeDetails().stubEmployeeDetails(wireMockServer);
                new getEmployeeSalary().stubgetEmployeeSalary(wireMockServer, EC);

                wireMockServer.stubFor(any(urlEqualTo("/testStub"))
                                // .atPriority(1)
                                // .withHeader("Content-Type", equalTo("application/json"))
                                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                                                .withHeader("Cache-Control", "no-cache").withFixedDelay(100)
                                                .withBody("{\n" + "  \"id\" : \"1\",\n" + "  \"f_name\" : \"Adam\",\n"
                                                                + "  \"l_name\" : \"Smith\",\n" + "  \"skills\" : [\n"
                                                                + "    \"web development\",\n" + "    \"ux design\",\n"
                                                                + "    \"data science\"\n" + "  ],\n"
                                                                + "  \"team\": \"engineering\"\n" + "}")));

        }
}
