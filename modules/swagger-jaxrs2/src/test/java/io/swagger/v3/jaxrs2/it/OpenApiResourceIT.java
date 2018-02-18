package io.swagger.v3.jaxrs2.it;

import com.jayway.restassured.http.ContentType;

import io.swagger.v3.jaxrs2.annotations.AbstractAnnotationTest;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * <p>
 * An functional integration test that runs during maven's integration-test phase,
 * uses RestAssured to define REST API tests, and Jetty's Maven plugin to serve a simple
 * sample app just prior to the integration-test phase starting.
 */
public class OpenApiResourceIT extends AbstractAnnotationTest {
    private static final String EXPECTED_JSON = "{  \n" +
            "   \"openapi\":\"3.0.1\",\n" +
            "   \"paths\":{  \n" +
            "      \"/files/upload\":{  \n" +
            "         \"post\":{  \n" +
            "            \"operationId\":\"uploadFile\",\n" +
            "            \"requestBody\":{  \n" +
            "               \"content\":{  \n" +
            "                  \"multipart/form-data\":{  \n" +
            "                     \"schema\":{  \n" +
            "                        \"type\":\"string\"\n" +
            "                     }\n" +
            "                  }\n" +
            "               }\n" +
            "            },\n" +
            "            \"responses\":{  \n" +
            "               \"default\":{  \n" +
            "                  \"description\":\"default response\",\n" +
            "                  \"content\":{  \n" +
            "                     \"*/*\":{  \n" +
            "\n" +
            "                     }\n" +
            "                  }\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      \"/files/attach\":{  \n" +
            "         \"put\":{  \n" +
            "            \"operationId\":\"putFile\",\n" +
            "            \"parameters\":[  \n" +
            "               {  \n" +
            "                  \"name\":\"fileId\",\n" +
            "                  \"in\":\"form\",\n" +
            "                  \"schema\":{  \n" +
            "                     \"type\":\"string\"\n" +
            "                  }\n" +
            "               }\n" +
            "            ],\n" +
            "            \"responses\":{  \n" +
            "               \"default\":{  \n" +
            "                  \"description\":\"default response\",\n" +
            "                  \"content\":{  \n" +
            "                     \"application/octet-stream\":{  \n" +
            "\n" +
            "                     }\n" +
            "                  }\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      \"/users/add\":{  \n" +
            "         \"post\":{  \n" +
            "            \"operationId\":\"addUser\",\n" +
            "            \"parameters\":[  \n" +
            "               {  \n" +
            "                  \"name\":\"id\",\n" +
            "                  \"in\":\"form\",\n" +
            "                  \"schema\":{  \n" +
            "                     \"type\":\"string\"\n" +
            "                  }\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"name\":\"name\",\n" +
            "                  \"in\":\"form\",\n" +
            "                  \"schema\":{  \n" +
            "                     \"type\":\"string\"\n" +
            "                  }\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"name\":\"gender\",\n" +
            "                  \"in\":\"form\",\n" +
            "                  \"schema\":{  \n" +
            "                     \"type\":\"string\"\n" +
            "                  }\n" +
            "               }\n" +
            "            ],\n" +
            "            \"responses\":{  \n" +
            "               \"default\":{  \n" +
            "                  \"description\":\"default response\",\n" +
            "                  \"content\":{  \n" +
            "                     \"*/*\":{  \n" +
            "\n" +
            "                     }\n" +
            "                  }\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      \"/widgets/{widgetId}\":{  \n" +
            "         \"get\":{  \n" +
            "            \"tags\":[  \n" +
            "               \"widgets\"\n" +
            "            ],\n" +
            "            \"summary\":\"Find pet by ID\",\n" +
            "            \"description\":\"Returns a pet when ID <= 10.  ID > 10 or nonintegers will simulate API error conditions\",\n" +
            "            \"operationId\":\"getWidget\",\n" +
            "            \"parameters\":[  \n" +
            "               {  \n" +
            "                  \"name\":\"widgetId\",\n" +
            "                  \"in\":\"path\",\n" +
            "                  \"required\":true,\n" +
            "                  \"schema\":{  \n" +
            "                     \"type\":\"string\"\n" +
            "                  }\n" +
            "               }\n" +
            "            ],\n" +
            "            \"responses\":{  \n" +
            "               \"200\":{  \n" +
            "                  \"description\":\"Returns widget with matching id\",\n" +
            "                  \"content\":{  \n" +
            "                     \"application/json\":{  \n" +
            "                        \"schema\":{  \n" +
            "                           \"$ref\":\"#/components/schemas/Widget\"\n" +
            "                        }\n" +
            "                     }\n" +
            "                  }\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   },\n" +
            "   \"components\":{  \n" +
            "      \"schemas\":{  \n" +
            "         \"InputStream\":{  \n" +
            "            \"type\":\"object\"\n" +
            "         },\n" +
            "         \"Widget\":{  \n" +
            "            \"type\":\"object\",\n" +
            "            \"properties\":{  \n" +
            "               \"a\":{  \n" +
            "                  \"type\":\"string\"\n" +
            "               },\n" +
            "               \"b\":{  \n" +
            "                  \"type\":\"string\"\n" +
            "               },\n" +
            "               \"id\":{  \n" +
            "                  \"type\":\"string\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}";
    private static final String EXPECTED_YAML = "openapi: 3.0.1\n" +
            "paths:\n" +
            "  /files/upload:\n" +
            "    post:\n" +
            "      operationId: uploadFile\n" +
            "      requestBody:\n" +
            "        content:\n" +
            "          multipart/form-data:\n" +
            "            schema:\n" +
            "              type: string\n" +
            "      responses:\n" +
            "        default:\n" +
            "          description: default response\n" +
            "          content:\n" +
            "            '*/*': {}\n" +
            "  /files/attach:\n" +
            "    put:\n" +
            "      operationId: putFile\n" +
            "      parameters:\n" +
            "      - name: fileId\n" +
            "        in: form\n" +
            "        schema:\n" +
            "          type: string\n" +
            "      responses:\n" +
            "        default:\n" +
            "          description: default response\n" +
            "          content:\n" +
            "            application/octet-stream: {}\n" +
            "  /users/add:\n" +
            "    post:\n" +
            "      operationId: addUser\n" +
            "      parameters:\n" +
            "      - name: id\n" +
            "        in: form\n" +
            "        schema:\n" +
            "          type: string\n" +
            "      - name: name\n" +
            "        in: form\n" +
            "        schema:\n" +
            "          type: string\n" +
            "      - name: gender\n" +
            "        in: form\n" +
            "        schema:\n" +
            "          type: string\n" +
            "      responses:\n" +
            "        default:\n" +
            "          description: default response\n" +
            "          content:\n" +
            "            '*/*': {}\n" +
            "  /widgets/{widgetId}:\n" +
            "    get:\n" +
            "      tags:\n" +
            "      - widgets\n" +
            "      summary: Find pet by ID\n" +
            "      description: Returns a pet when ID <= 10.  ID > 10 or nonintegers will simulate\n" +
            "        API error conditions\n" +
            "      operationId: getWidget\n" +
            "      parameters:\n" +
            "      - name: widgetId\n" +
            "        in: path\n" +
            "        required: true\n" +
            "        schema:\n" +
            "          type: string\n" +
            "      responses:\n" +
            "        200:\n" +
            "          description: Returns widget with matching id\n" +
            "          content:\n" +
            "            application/json:\n" +
            "              schema:\n" +
            "                $ref: '#/components/schemas/Widget'\n" +
            "components:\n" +
            "  schemas:\n" +
            "    InputStream:\n" +
            "      type: object\n" +
            "    Widget:\n" +
            "      type: object\n" +
            "      properties:\n" +
            "        a:\n" +
            "          type: string\n" +
            "        b:\n" +
            "          type: string\n" +
            "        id:\n" +
            "          type: string\n";

    private static final int jettyPort = System.getProperties().containsKey("jetty.port") ? Integer.parseInt(System.getProperty("jetty.port")): -1;

    @BeforeMethod
    public void checkJetty() {
        if (jettyPort == -1) {
            throw new SkipException("Jetty not configured");
        }
    }

    @Test
    public void testSwaggerJson() throws Exception {
        final String actualBody = given()
                .port(jettyPort)
                .log().all()
                .when()
                .get("/openapi.json")
                .then()
                .log().all()
                .assertThat()
                .statusCode(503)
                .contentType("text/html;charset=iso-8859-1")
                .extract()
                .response().body().asString();

        compareAsJson(actualBody, EXPECTED_JSON);
    }

    @Test
    public void testSwaggerJsonUsingAcceptHeader() throws Exception {
        final String actualBody = given()
                .port(jettyPort)
                .log().all()
                .accept(ContentType.JSON)
                .when()
                .get("/openapi")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response().body().asString();

        compareAsJson(actualBody, EXPECTED_JSON);
    }

    @Test
    public void testSwaggerYaml() throws Exception {
        final String actualBody = given()
                .port(jettyPort)
                .log().all()
                .when()
                .get("/openapi.yaml")
                .then()
                .log().all()
                .assertThat()
                .statusCode(503)
                .contentType("application/yaml")
                .extract().response().body().asString();

        compareAsYaml(actualBody, EXPECTED_YAML);
    }

    @Test
    public void testSwaggerYamlUsingAcceptHeader() throws Exception {
        final String actualBody = given()
                .port(jettyPort)
                .log().all()
                .accept("application/yaml")
                .when()
                .get("/openapi")
                .then()
                .log().all()
                .assertThat()
                .statusCode(503)
                .contentType("application/yaml")
                .extract().response().body().asString();

        compareAsYaml(actualBody, EXPECTED_YAML);
    }
}
