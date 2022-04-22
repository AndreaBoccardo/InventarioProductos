package cl.desafiolatm.inventarioproductos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.desafiolatm.inventarioproductos.model.Compra
import cl.desafiolatm.inventarioproductos.repository.CompraRepositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompraViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = CompraRepositorio(application.applicationContext)
    val lista: LiveData<List<Compra>> = repo.listar()
    val compraMutable = MutableLiveData<Compra>()
    val cantidadTotal = MutableLiveData<Int>()
    val valorTotal = MutableLiveData<Int>()
    val eliminarTodo = MutableLiveData<Unit>()

    fun agregar(producto:String, cantidad:Int, precio:Int){
        val c = Compra(producto,cantidad,precio)
        repo.agregar(c)
    }

    fun eliminarProducto(compra: Compra){
        repo.eliminarProducto(compra)
    }

    fun editar(id: Int, producto:String, cantidad:Int, precio:Int){
        val c = Compra(producto,cantidad,precio)
        c.id = id
        repo.editar(c)
    }

    fun totalProductos(){
        CoroutineScope(Dispatchers.IO).launch {
            cantidadTotal.postValue(repo.totalProductos())
        }
    }

    fun totalCompra(){
        CoroutineScope(Dispatchers.IO).launch {
            valorTotal.postValue(repo.totalCompra())
        }
    }

    fun borrar(){
        CoroutineScope(Dispatchers.IO).launch {
            eliminarTodo.postValue(repo.borrar())
        }
    }
}