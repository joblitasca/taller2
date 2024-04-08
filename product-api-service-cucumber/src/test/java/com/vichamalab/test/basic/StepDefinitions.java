package com.vichamalab.test.basic;


import static io.restassured.RestAssured.given;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import com.vichamalab.test.dto.Product;
import com.vichamalab.test.dto.ProductRequest;
import com.vichamalab.test.dto.ProductResponse;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dada;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StepDefinitions {
	Response response;
	ProductRequest productRequest;
	String originalSku="";
	String currentMethod ="";
	String nombreEsperado="";
	String descripcionEsperada="";
	float precioEsperado=0;
	String currentUrl="";
			
	
	@Before
	public void SetupScenarios() {
		RestAssured.baseURI="http://localhost:8081";
		productRequest = new ProductRequest();
	}
	
	@After
	public void CleanScenarios() {
		//Limpiar BD, eliminar referencias
	}
	
	@BeforeStep
	public void BeforeStep() {
		//Chequeo de variables , logs
	}
	
	@AfterStep
	public void AfterStep() {
		//Chequeo de variables , logs
	}
	
	@Dada("una API válida con url {string} y formato {string}")
	public void una_api_válida_con_url_y_formato(String url, String contentType) {
		RestAssured.requestSpecification = new RequestSpecBuilder()
		        .setContentType(contentType)
		        .setAccept(ContentType.JSON)
		        .build();
		currentUrl= url;
		System.out.println("currentUrl: " + currentUrl);
	}
	
	@Cuando("se hace una petición {string} a la API")
    public void se_hace_una_peticion(String metodo) {
        currentMethod = metodo;
        
        if ("GET".equalsIgnoreCase(metodo)) {
        	 response = given()
                    .contentType(ContentType.JSON)
                .when()
                   .get(currentUrl + originalSku + "/");
        	 }
	}
	
	
	@Cuando("a la API")
	public void a_la_api() {
		if ("POST".equals(currentMethod.toUpperCase())) {
			response = given().contentType(ContentType.JSON)// Headers
					.body(productRequest).when().post(currentUrl)// Uri
					.then().extract().response();
		} else if ("PUT".equals(currentMethod.toUpperCase())) {
			nombreEsperado = productRequest.getName();
			descripcionEsperada = productRequest.getDescription();
			precioEsperado = productRequest.getPrice();
			response = given().contentType(ContentType.JSON)// Headers
					.body(productRequest).when().put(currentUrl + originalSku + "/")// Uri
					.then().extract().response();
		}else if ("DELETE".equals(currentMethod.toUpperCase())) {
			nombreEsperado = productRequest.getName();
			descripcionEsperada = productRequest.getDescription();
			precioEsperado = productRequest.getPrice();
			response = given().contentType(ContentType.JSON)// Headers
					.body(productRequest).when().delete(currentUrl + originalSku + "/")// Uri
					.then().extract().response();
		}else if ("GET".equals(currentMethod.toUpperCase())) {
			nombreEsperado = productRequest.getName();
			descripcionEsperada = productRequest.getDescription();
			precioEsperado = productRequest.getPrice();
			response = given().contentType(ContentType.JSON)// Headers
					.body(productRequest).when().get(currentUrl + originalSku + "/")// Uri
					.then().extract().response();
			System.out.println("nombreEsperado: " + nombreEsperado);
		}
	}
	
	
	
	
	@Cuando("se hace una petición con el nombre {string}")
	public void se_hace_una_petición_con_el_nombre(String nombre) {
		productRequest.setName(nombre);
	}
	
	@Cuando("se hace una petición {string} con el nombre {string}")
	public void se_hace_una_petición_con_el_nombre(String metodo, String nombre) {
		productRequest.setName(nombre);
		currentMethod=metodo;
	}
	

	@Dada("y un producto valido previamente creado")
	public void y_un_producto_valido_previamente_creado() {
		productRequest = new ProductRequest();
		productRequest.setName("Nombre Original");
		productRequest.setDescription("Descripcion Original");
		productRequest.setPrice(100);
	   	 response = given()
	   		.contentType(ContentType.JSON)//Headers
	   		.body(productRequest)
	   	.when()
	   		.post(currentUrl)//Uri
	   	.then()
	   		.extract()
	   		.response();
	   	 
	   	 originalSku = response.jsonPath().getString("sku");
	}
	
	@Cuando("y la descripción {string}")
	public void y_la_descripción(String description) {
		productRequest.setDescription(description);
	}
	
	@Cuando("y el precio {float}")
	public void y_el_precio(float price) {
		productRequest.setPrice(price);
	}

	
	@Entonces("se recibe una respuesta exitosa con código {int}")
	public void se_recibe_una_respuesta_exitosa_con_código(Integer statusCode) {
		Assertions.assertEquals(statusCode,response.getStatusCode());
	}
	
	@Entonces("se recibe una respuesta {string} con código {int}")
	public void se_recibe_una_respuesta_de_error_con_código(String responseType,Integer statusCode) {
		Assertions.assertEquals(statusCode,response.getStatusCode());
		System.out.println("Mensaje de error: " + statusCode);
	}
	
	@Entonces("y el mensaje {string}")
	public void y_el_mensaje(String message) {
		Assertions.assertEquals(response.jsonPath().getString("message"), message);
	}
	
	@Entonces("y se reciben los detalles del producto correctamente")
	public void y_se_reciben_los_detalles_del_producto_correctamente() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Mensaje de error: " + response.getBody().asString());
		
		
		
	    throw new io.cucumber.java.PendingException();
	}	
	
	
	@Entonces("con los campos actualizados correctamente")
	public void con_los_campos_actualizados_correctamente() {
	   	 ProductResponse response = given()
	   		.contentType(ContentType.JSON)//Headers
	   	.when()
	   		.get(currentUrl+originalSku+"/")//Uri
	   	.then()
	   		.statusCode(HttpStatus.SC_OK)
	   		.extract()
	   		.as(ProductResponse.class);
	   	 Product product = response.getProducts().get(0);
	   	 Assertions.assertEquals(nombreEsperado, product.getName());
	   	 Assertions.assertEquals(descripcionEsperada, product.getDescription());
	   	 Assertions.assertEquals(precioEsperado, product.getPrice());
	}
	
	@Dada("y un producto validado previamente creado {string}")
	public void y_un_producto_validado_previamente_creado(String malsku) {
		productRequest = new ProductRequest();
		productRequest.setName("Nombre Original");
		productRequest.setDescription("Descripcion Original");
		productRequest.setPrice(1000);
	   	 response = given()
	   		.contentType(ContentType.JSON)//Headers
	   		.body(productRequest)
	   	.when()
	   		.post(currentUrl)//Uri
	   	.then()
	   		.extract()
	   		.response();
	   
	   	 originalSku = response.jsonPath().getString("malsku"); //sku incorrecto
	 	System.out.println("Mensaje orinal sku: " + originalSku);
	 	System.out.println("Mensaje de body: " + response.getBody().asString());

	}
	@Cuando("se hace una petición {string} con el sku {string}")
	public void se_hace_una_petición_con_el_sku(String metodo, String sku) {
		productRequest.setName(sku);
		currentMethod=metodo;
	}
	

}