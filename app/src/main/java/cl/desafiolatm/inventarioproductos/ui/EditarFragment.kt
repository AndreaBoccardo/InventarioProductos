package cl.desafiolatm.inventarioproductos.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cl.desafiolatm.inventarioproductos.R
import cl.desafiolatm.inventarioproductos.databinding.FragmentEditarBinding
import cl.desafiolatm.inventarioproductos.viewmodel.CompraViewModel

class EditarFragment : Fragment() {

    private lateinit var binding: FragmentEditarBinding
    val viewModel: CompraViewModel by activityViewModels<CompraViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditarBinding.inflate(inflater, container, false)

        viewModel.compraMutable.observe(viewLifecycleOwner, Observer {
            with(binding){
                val compra = it
                etNombreEditar.text = Editable.Factory.getInstance().newEditable(it.producto)
                etCantidadEditar.text = Editable.Factory.getInstance().newEditable(it.cantidad.toString())
                etValorEditar.text = Editable.Factory.getInstance().newEditable(it.precio.toString())

                btnModificar.setOnClickListener {
                    if (etNombreEditar.text.isNotEmpty()){
                        if (etCantidadEditar.text.toString().toInt() > 1){
                            if (etValorEditar.text.toString().toInt() > 0){
                                tvSubTotalEditar.text = (etCantidadEditar.text.toString()
                                    .toInt() * etValorEditar.text.toString().toInt()).toString()
                            } else {
                                Toast.makeText(context, "Debe ingresar un precio mayor a 0", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(context, "Debe ingresar una cantidad mayor a 1", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, "Debe ingresar un nombre", Toast.LENGTH_LONG).show()
                    }

                    val producto = etNombreEditar.text.toString()
                    val cantidad = etCantidadEditar.text.toString().toInt()
                    val precio = etValorEditar.text.toString().toInt()
                    viewModel.editar(compra.id, producto, cantidad, precio)
                    val alerta = AlertDialog.Builder(requireContext())
                    alerta.setTitle("Editado")
                    alerta.setMessage("Su compra fue editada exitosamente")
                    alerta.setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                        dialog.cancel()
                    })
                    alerta.show()
                }
            }
        })

        return binding.root
    }
}