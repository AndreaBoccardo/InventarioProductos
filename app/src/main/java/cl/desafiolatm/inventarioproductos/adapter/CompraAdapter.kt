package cl.desafiolatm.inventarioproductos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatm.inventarioproductos.R
import cl.desafiolatm.inventarioproductos.databinding.ItemLayoutBinding
import cl.desafiolatm.inventarioproductos.model.Compra

class CompraAdapter: RecyclerView.Adapter<CompraAdapter.CustomViewHolder>() {

    private var lista:List<Compra> = ArrayList()
    private lateinit var listener: miOnClickListener

    class CustomViewHolder(itemView: View, var listener: miOnClickListener): RecyclerView.ViewHolder(itemView){

        private val binding = ItemLayoutBinding.bind(itemView)

        fun bindData(compra: Compra){
            with(binding){
                tvNombreItem.text = compra.producto
                tvCantidadItem.text = compra.cantidad.toString()
                tvPrecioItem.text = compra.precio.toString()
                btnEditar.setOnClickListener {
                    listener.onClickListener(compra)
                }
                btnBorrar.setOnClickListener {
                    listener.onDeleteListener(compra)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return CustomViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun updateData(lista:List<Compra>){
        this.lista = lista
        notifyDataSetChanged()
    }

    interface miOnClickListener{
        fun onClickListener(compra: Compra)
        fun onDeleteListener(compra: Compra)
    }

    fun setOnClickListener(listener: miOnClickListener){
        this.listener = listener
    }
}