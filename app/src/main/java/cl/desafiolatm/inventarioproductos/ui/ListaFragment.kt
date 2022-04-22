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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cl.desafiolatm.inventarioproductos.R
import cl.desafiolatm.inventarioproductos.adapter.CompraAdapter
import cl.desafiolatm.inventarioproductos.databinding.FragmentListaBinding
import cl.desafiolatm.inventarioproductos.model.Compra
import cl.desafiolatm.inventarioproductos.viewmodel.CompraViewModel

class ListaFragment : Fragment() {

    private lateinit var binding: FragmentListaBinding
    val viewModel: CompraViewModel by activityViewModels<CompraViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaBinding.inflate(inflater, container, false)

        val adapter = CompraAdapter()
        val manager = LinearLayoutManager(context)

        adapter.setOnClickListener(object : CompraAdapter.miOnClickListener{
            override fun onClickListener(compra: Compra) {
                viewModel.compraMutable.value = compra
                Navigation.findNavController(requireView()).navigate(R.id.action_listaFragment_to_editarFragment)
            }

            override fun onDeleteListener(compra: Compra) {
                val alerta = AlertDialog.Builder(requireContext())
                alerta.setTitle(getString(R.string.eliminar))
                alerta.setMessage(getString(R.string.msg_eliminar))
                alerta.setPositiveButton(getString(R.string.si), DialogInterface.OnClickListener { dialog, which ->
                    viewModel.eliminarProducto(compra)
                })
                alerta.setNegativeButton(getString(R.string.no), DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })
                alerta.show()
            }
        })

        with(binding){
            rvLista.adapter = adapter
            rvLista.layoutManager = manager
            btnAgregar.setOnClickListener {
                Navigation.findNavController(requireView()).navigate(R.id.action_listaFragment_to_agregarFragment)
            }
            btnTotal.setOnClickListener {
                Navigation.findNavController(requireView()).navigate(R.id.action_listaFragment_to_totalFragment)
            }
        }

        viewModel.lista.observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })

        return binding.root
    }
}