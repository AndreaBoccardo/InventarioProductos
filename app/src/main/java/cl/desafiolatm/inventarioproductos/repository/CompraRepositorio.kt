package cl.desafiolatm.inventarioproductos.repository

import android.content.Context
import androidx.lifecycle.LiveData
import cl.desafiolatm.inventarioproductos.model.Compra
import cl.desafiolatm.inventarioproductos.room.ProyectoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompraRepositorio(var contexto: Context) {

    private val db = ProyectoDatabase.getInstancia(contexto)

    fun agregar(compra: Compra){
        CoroutineScope(Dispatchers.IO).launch {
            db.compraDao().agregar(compra)
        }
    }

    fun eliminarProducto (compra: Compra){
        CoroutineScope(Dispatchers.IO).launch {
            db.compraDao().eliminarProducto(compra)
        }
    }

    fun editar (compra: Compra){
        CoroutineScope(Dispatchers.IO).launch {
            db.compraDao().editar(compra)
        }
    }

    fun listar() : LiveData<List<Compra>> {
        return db.compraDao().listar()
    }

    fun totalProductos(): Int{
        return db.compraDao().totalProductos()
    }

    fun totalCompra() : Int{
        return db.compraDao().totalCompra()
    }

    fun borrar(){
        return db.compraDao().borrar()
    }
}