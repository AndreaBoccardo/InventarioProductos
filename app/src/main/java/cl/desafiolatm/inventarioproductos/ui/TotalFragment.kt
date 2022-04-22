package cl.desafiolatm.inventarioproductos.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cl.desafiolatm.inventarioproductos.R
import cl.desafiolatm.inventarioproductos.databinding.FragmentTotalBinding
import cl.desafiolatm.inventarioproductos.viewmodel.CompraViewModel

class TotalFragment : Fragment() {

    private lateinit var binding: FragmentTotalBinding
    val viewModel: CompraViewModel by activityViewModels<CompraViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTotalBinding.inflate(inflater, container, false)

        viewModel.totalProductos()
        viewModel.cantidadTotal.observe(viewLifecycleOwner, Observer {
            with(binding){
                it.let {
                    tvProductosTotal.text = it.toString()
                }
            }
        })

        viewModel.totalCompra()
        viewModel.valorTotal.observe(viewLifecycleOwner, Observer {
            with(binding){
                it.let {
                    tvValorTotal.text = it.toString()
                }
            }
        })

        viewModel.borrar()
        viewModel.eliminarTodo.observe(viewLifecycleOwner, Observer {
            with(binding){
                btnBorrarTodo.setOnClickListener {
                    val alerta = AlertDialog.Builder(requireContext())
                    alerta.setTitle("Eliminar Todo")
                    alerta.setMessage("¿Seguro desea eliminar la compra?")
                    alerta.setPositiveButton("Si", DialogInterface.OnClickListener { dialog, which ->
                        viewModel.borrar()
                    })
                    alerta.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                        dialog.cancel()
                    })
                    alerta.show()
                }
            }
        })

        return binding.root
    }
}