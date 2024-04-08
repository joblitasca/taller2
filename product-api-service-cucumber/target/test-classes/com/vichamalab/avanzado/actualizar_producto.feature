# language: es
@Actualizar
Requisito: Actualizar un producto usando la api /api/v1/product

	Esquema del escenario: Actualizar un producto creado previamente con éxito
	Dada una API válida con url "/api/v1/product/" y formato "application/json"
	* y un producto valido previamente creado
	Cuando se hace una petición "PUT" con el nombre "<nombre>"
	* y la descripción "<descripcion>"
	* y el precio <precio>
	* a la API
	Entonces se recibe una respuesta "exitosa" con código <codigo>
	* y el mensaje "<mensaje>"
	* con los campos actualizados correctamente

	@Exito
	Ejemplos:
	|nombre|descripcion|precio|codigo|mensaje|
	|Myphone 18|Descripcion Actualizada|999|200|El producto fue actualizado con éxito|

	
	Esquema del escenario: Actualizar un producto con campos invalidos
	Dada una API válida con url "/api/v1/product/" y formato "application/json"
	* y un producto valido previamente creado
	Cuando se hace una petición "PUT" con el nombre "<nombre>"
	* y la descripción "<descripcion>"
	* y el precio <precio>
	* a la API
	Entonces se recibe una respuesta "de error" con código <codigo>
	* y el mensaje "<mensaje>"

	@Fallo
	Ejemplos:
	|nombre|descripcion|precio|codigo|mensaje|
	||Descripcion Actualizada|999|400|El nuevo nombre no debe estar en blanco|
	|Myphone 12||999|400|La nueva descripción no debe estar en blanco|
	|Myphone 13|Descripcion Actualizada|0|400|El nuevo precio debe ser mayor a cero|