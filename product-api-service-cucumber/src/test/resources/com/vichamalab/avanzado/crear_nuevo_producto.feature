# language: es
@Crear
Requisito: Crear un nuevo producto usando la api /api/v1/product

	Esquema del escenario: Crear un producto con éxito
	Dada una API válida con url "/api/v1/product/" y formato "application/json"
	Cuando se hace una petición "POST" con el nombre "<nombre>"
	* y la descripción "<descripcion>"
	* y el precio <precio>
	* a la API
	Entonces se recibe una respuesta "exitosa" con código <codigo>
	* y el mensaje "<mensaje>"

	@Exito
	Ejemplos:
	|nombre|descripcion|precio|codigo|mensaje|
	|Myphone 11|Un smartphone MyPhone|1100|201|El producto fue creado con éxito!|
	|Myphone 12|Un smartphone MyPhone|1200|201|El producto fue creado con éxito!|
	|Myphone 13|Un smartphone MyPhone|1300|201|El producto fue creado con éxito!|
	|Myphone 14|Un smartphone MyPhone|1400|201|El producto fue creado con éxito!|
	|Myphone 15|Un smartphone MyPhone|1500|201|El producto fue creado con éxito!|
  
	Esquema del escenario: Crear un producto con campos vacios
    Dada una API válida con url "/api/v1/product/" y formato "application/json"
    Cuando se hace una petición "POST" con el nombre "<nombre>"
    * y la descripción "<descripcion>"
    * y el precio <precio>
    * a la API
    Entonces se recibe una respuesta "de error" con código <codigo>
    * y el mensaje "<mensaje>"
    
    @Fallo
	Ejemplos:
	|nombre|descripcion|precio|codigo|mensaje|
	||Un smartphone de la empresa MyPhone|1100|400|El nombre del producto no fue proporcionado|
	|Myphone 12||1200|400|La descripción del producto no fue proporcionada|
	|Myphone 13|Un smartphone de la empresa MyPhone|0|400|El precio del producto no fue proporcionado|