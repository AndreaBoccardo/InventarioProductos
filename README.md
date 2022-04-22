# InventarioProductos
Aplicacion que permite generar una lista dinámica de productos al realizar una compra

Requerimiento:
Un conocido le ha solicitado si puede crear una app que le ayude al momento de ir de compras al supermercado, la cual permita ingresar el nombre del producto, el precio y la cantidad. Dichos productos deben ser almacenados en la bd interna del teléfono, dándole al usuario la opción de eliminar o modificar los productos, registrados. Lo más importante que le solicitaron es que exista la opción de un botón que permita calcular el total a pagar según todos los productos registrados, además de la opción de limpiar todos los productos.

Se requiere registrar el nombre, precio y cantidad de productos, verificando que no se ingresen valores vacíos, que la cantidad registrada al menos sea 1 y que el precio puede ser 0 o mayor, debido que puede ser gratis o una oferta.
Al momento de eliminar un producto o de limpiar todos, debe solicitar la confirmación del usuario.
La opción de modificar los productos debe aplicar las mismas reglas que al momento de registrarlos por primera vez.
Puede calcular el total a pagar en tiempo real o al momento de presionar un botón o alguna forma que el usuario interactúe con el sistema.
Utilizar patrón MVVM junto a fragments o activities, viewBiding
