package ru.MarkMoskvitin.NauJava;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import ru.MarkMoskvitin.NauJava.entity.Group;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupTest {
    private String name;

    private String URl = "http://localhost:8091/groups";

    private Response createGroup(){
        Group group= new Group();
        this.name =  UUID.randomUUID().toString();
        group.setName(this.name);
        return RestAssured
                .given()
                .auth().basic("admin", "admin")
                .body(group)
                .contentType(ContentType.JSON)
                .redirects().follow(true)
                .post(this.URl);
    }

    @Test
    public void createGroupTest()
    {
        Response response = createGroup();
        Group group = response
                .then()
                .statusCode(201)
                .extract()
                .as(Group.class);

        assertEquals(group.getName(),name);
    }

    @Test
    public void getGroupTest() {
        Response response = createGroup();
        Group group = response
                .then()
                .statusCode(201)
                .extract()
                .as(Group.class);

        Response getResponse = RestAssured
                .given()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .redirects().follow(true)
                .when()
                .get(this.URl + "/search/findByName?name=" + group.getName());

        Group retrievedGroup = getResponse
                .then().statusCode(200)
                .extract().as(Group.class);
        assertEquals(group.getName(), retrievedGroup.getName());
    }
}
