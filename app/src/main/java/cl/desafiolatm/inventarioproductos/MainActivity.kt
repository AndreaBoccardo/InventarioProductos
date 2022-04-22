package cl.desafiolatm.inventarioproductos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import cl.desafiolatm.inventarioproductos.databinding.ActivityMainBinding
import cl.desafiolatm.inventarioproductos.viewmodel.CompraViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: CompraViewModel by viewModels<CompraViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}