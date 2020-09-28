package br.com.teste.api;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiTest {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void deveRetornarTarefas() {
		
		RestAssured.given()
					.when()
						.get("/todo")
					.then()
						.statusCode(200);
	}
	
	@Test
	public void deveAddTarefaComSucessoTarefas() {
		
		RestAssured.given()
					.body("{\"task\":\"teste2\", \"dueDate\":\"2020-12-30\"}")
					.contentType(ContentType.JSON)
					.when()
						.post("/todo")
					.then()
						.statusCode(201);
	}
	
	@Test
	public void naoDeveAdicionarTarefaInvalida() {
		
		RestAssured.given()
					.body("{\"task\":\"teste2\", \"dueDate\":\"2010-12-30\"}")
					.contentType(ContentType.JSON)
					.when()
						.post("/todo")
					.then()
						.statusCode(400)
						.and()
						.body("message", CoreMatchers.is("Due date must not be in past"));
	}
	
	@Test
	public void deveRemoverUmaTask() {
		
		//Add
		Integer id = RestAssured.given()
					.body("{\"task\":\"teste2\", \"dueDate\":\"2020-12-30\"}")
					.contentType(ContentType.JSON)
					.when()
						.post("/todo")
					.then()
						.statusCode(201).extract().path("id");
		
		RestAssured.given()
					.when()
						.delete(String.format("/todo/%d", id))
					.then()
						.statusCode(204);
	}
}
