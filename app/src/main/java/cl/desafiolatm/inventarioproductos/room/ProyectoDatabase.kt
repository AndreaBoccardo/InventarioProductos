package cl.desafiolatm.inventarioproductos.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.desafiolatm.inventarioproductos.dao.CompraDao
import cl.desafiolatm.inventarioproductos.model.Compra

@Database(entities = [Compra::class], version = 1)
abstract class ProyectoDatabase : RoomDatabase() {

    abstract fun compraDao(): CompraDao

    companion object{
        @Volatile
        private var instancia: ProyectoDatabase? = null

        fun getInstancia(contexto: Context) : ProyectoDatabase
        {
            if(instancia == null)
            {
                synchronized(this)
                {
                    instancia = Room.databaseBuilder(contexto,
                        ProyectoDatabase::class.java,
                        "proyecto_db").build()
                }
            }
            return instancia!!
        }
    }
}