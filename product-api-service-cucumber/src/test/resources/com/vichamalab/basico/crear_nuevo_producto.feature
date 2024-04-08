# language: es
Requisito: Crear un nuevo producto usando la api /api/v1/product

  Escenario: Crear un producto con éxito
    Dada una API válida con formato "application/json" 
    Cuando se hace una petición con el nombre "Myphone 15"
    * y la descripción "Smartphone de la compania MyApple"
    * y el precio 1500
    * a la url "/api/v1/product/"
    Entonces se recibe una respuesta exitosa con código 201
    * y el mensaje "El producto fue creado con éxito!"