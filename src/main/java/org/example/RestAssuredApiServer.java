package org.example;

import io.restassured.mapper.ObjectMapperType;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.assertTrue;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class RestAssuredApiServer {
    @Test
    public void testApiGetR_ONE() {
        //Exercise (1,2) https://jsonplaceholder.typicode.com/posts/1
        // and verify that the status code is 200.
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when().get("/posts/1")
                .then().assertThat().statusCode(200)//get request validqtion
                .body("id", equalTo(1))
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
        //this is additional validation for body response
    }

    @Test
    public void testRestAssuredPathParameters()
    //Exercise: 3 Using Path Parameters
    // Task: Write a test that uses a path parameter to retrieve a specific user
    {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .pathParam("userId", 1)
                .when().get("/users/1")//GET request
                .then().assertThat().statusCode(200)//validation GET request
                .body("name", equalTo("Leanne Graham"))
                .body("email", equalTo("Sincere@april.biz"));
        //this is additional validation for body response
    }
    @Test
    public void testApiQueryParameter()
    //Exercise 4: Query Parameters
    // Task: Write a test that uses query parameters
    //to search for posts from a specific user a+Exercise 5,6
    {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .queryParam("userId",1)
                .when().get("/posts/")
                .then().assertThat().statusCode(200)
                .body("userId", everyItem(equalTo(1)));
    }
    @Test
    public void testApiPOST()
    {
        //Exercise 5: Test POST request Extend
        // by verifying that the response contains the data you sent in your POST request.
        var result = given().baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")
                .body("{" +
                        "\"id\":101" +  //  101*
                        ", \"userId\": 1" +
                        ", \"title\":\" My title" + "\"" +
                        ", \"body\":\" Only bones" + "\"" +
                        "}")
                .when().post("/posts/").then().assertThat()
                // Send a POST request to create a new post
                .statusCode(201).extract().path("id").equals(101);
        //status code 201 means new resource has been created
    }

    @Test
    public void testApiHandlingHeaders() //Exercise 7: Handling HeadersTask:
    // Write a test that includes a custom header in your request and verify
    // that it is sent correctly.
    {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .header("City", "Mumbai")
                .body("")
                .when().get("/posts/")
                .then().assertThat().statusCode(200);
    }
    @Test
    public void testApiPUT() //Exercise 8: Test PUT-RequestTask:
    // Write a test that performs a PUT request to update data for an existing resource
    {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")
                .body("{ \"title\": \"Beautiful world\", \"body\": \"Humanity is the biggest religion\" }")
                //this is the updated body
                .when().put("/posts/1")//PUT request
                .then().assertThat().statusCode(200)//validation of PUT request
                .body("title", equalTo("Beautiful world"))
                .body("body", equalTo("Humanity is the biggest religion"));
        //this is additional validation for body response
    }
    @Test
    public void testApiDelete() //Exercise 9: Test DELETE-RequestTask:
    // Write a test that executes a DELETE request and verifies that the
    // resource is deleted correctly.
    {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when().delete("/posts/101")//DELETE request
                .then().assertThat().statusCode(200);//validation of DELETE request
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when().get("/posts/101")
                .then().assertThat().statusCode(404);
        //validation code 404 ensures that posts 101 is not accessible
        //     it will not function for posts 1-100 because they are read only API server
        //     and because 101 we created server accepted but not save(display)
    }

}

