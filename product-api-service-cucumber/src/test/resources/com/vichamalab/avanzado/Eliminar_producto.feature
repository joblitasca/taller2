# language: es
@Eliminar
Requisito: Eliminar un producto usando la api /api/v1/product

	Esquema del escenario: Eliminar un producto creado previamente con éxito
	Dada una API válida con url "/api/v1/product/" y formato "application/json"  
	* y un producto valido previamente creado 
	Cuando se hace una petición "DELETE" con el nombre "<nombre>"
	* y la descripción "<descripcion>"
	* a la API
	Entonces se recibe una respuesta "exitosa" con código <codigo>
	* y el mensaje "<mensaje>"
	
	@Exito
	Ejemplos:
	|nombre|descripcion|precio|codigo|mensaje|
	|Myphone 100|Un smartphone MyPhone|5500|200|El producto fue eliminado con éxito|

	
	Esquema del escenario: Eliminar un producto con campos invalidos
	Dada una API válida con url "/api/v1/product/" y formato "application/json"
	* y un producto validado previamente creado "123456"
	Cuando se hace una petición "DELETE" con el nombre "<nombre>"
	* y la descripción "<descripcion>"
	* y el precio <precio>
	* a la API
	Entonces se recibe una respuesta "de error" con código <codigo>
	* y el mensaje "<mensaje>"
	
	@Fallo
	Ejemplos:
	|nombre|descripcion|precio|codigo|mensaje|
	|Myphone 100|calidad|1000|400|El producto no fue encontrado|