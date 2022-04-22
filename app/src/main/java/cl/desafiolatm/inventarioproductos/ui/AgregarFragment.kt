package cl.desafiolatm.inventarioproductos.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import cl.desafiolatm.inventarioproductos.R
import cl.desafiolatm.inventarioproductos.databinding.FragmentAgregarBinding
import cl.desafiolatm.inventarioproductos.viewmodel.CompraViewModel

class AgregarFragment : Fragment() {

    private lateinit var binding: FragmentAgregarBinding
    val viewModel: CompraViewModel by activityViewModels<CompraViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAgregarBinding.inflate(inflater, container, false)

        with(binding){
            btnModificar.setOnClickListener {
                if (etNombreAgregar.text.isNotEmpty()){
                    if (etCantidadAgregar.text.toString().toInt() > 1){
                        if (etValorAgregar.text.toString().toInt() > 0){
                            tvSubTotal.text = (etCantidadAgregar.text.toString()
                                .toInt() * etValorAgregar.text.toString().toInt()).toString()
                        } else {
                            Toast.makeText(context, getString(R.string.error_precio), Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, getString(R.string.error_cantidad), Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, getString(R.string.error_nombre), Toast.LENGTH_LONG).show()
                }
            }

            val producto = etNombreAgregar.text.toString()
            val cantidad = etCantidadAgregar.text.toString().toInt()
            val precio = etValorAgregar.text.toString().toInt()
            viewModel.agregar(producto, cantidad, precio)

            val alerta = AlertDialog.Builder(requireContext())
            alerta.setTitle(getString(R.string.agregada))
            alerta.setMessage("Su compra fue agregada exitosamente")
            alerta.setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            alerta.show()

            etNombreAgregar.text.clear()
            etCantidadAgregar.text.clear()
            etValorAgregar.text.clear()
        }

        return binding.root
    }
}