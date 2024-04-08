# language: es
@Obtener
Requisito: Obtener un producto usando la api /api/v1/product

	Esquema del escenario: Obtener un producto creado previamente con éxito
	Dada una API válida con url "/api/v1/product/" y formato "application/json"
	* y un producto valido previamente creado
	Cuando se hace una petición "GET" a la API
	Entonces se recibe una respuesta "exitosa" con código <codigo>
	* y el mensaje "<mensaje>"
	
	@Exito
	Ejemplos:
	|codigo|mensaje|
	|200|El producto fue encontrado|
	
	
Esquema del escenario: No listar un producto
	Dada una API válida con url "/api/v1/product/" y formato "application/json"
	Cuando se hace una petición "GET" con el sku "<name>"
	* a la API
	Entonces se recibe una respuesta "de error" con código <codigo>


	@Fallo
	Ejemplos:
	|name|codigo|
	|qwert|400|