package cl.desafiolatm.inventarioproductos.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import cl.desafiolatm.inventarioproductos.model.Compra

@Dao
interface CompraDao {

    @Insert
    fun agregar(compra: Compra)

    @Delete
    fun eliminarProducto (compra: Compra)

    @Update
    fun editar (compra: Compra)

    @Query("select id, producto, cantidad, precio from compra_table")
    fun listar() : LiveData<List<Compra>>

    @Query("select Sum(cantidad) from compra_table")
    fun totalProductos(): Int

    @Query("select Sum(precio * cantidad) as total from compra_table")
    fun totalCompra() : Int

    @Query("delete from compra_table")
    fun borrar()
}