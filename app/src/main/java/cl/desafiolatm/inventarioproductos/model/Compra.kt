package cl.desafiolatm.inventarioproductos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "compra_table")
data class Compra(
    @ColumnInfo(name = "producto")
    var producto: String,
    @ColumnInfo(name = "cantidad")
    var cantidad: Int,
    @ColumnInfo(name = "precio")
    var precio: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
