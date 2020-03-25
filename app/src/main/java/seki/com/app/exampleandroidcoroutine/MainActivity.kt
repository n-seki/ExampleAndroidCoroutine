package seki.com.app.exampleandroidcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import seki.com.app.exampleandroidcoroutine.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private val viewModelModel: MainViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).component.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            viewModelModel.start()
        }

        binding.button2.setOnClickListener {
            viewModelModel.startAndBlockThread()
        }

        viewModelModel.result.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.textView.text = "Loading"
                }
                is Resource.Success -> {
                    binding.textView.text = it.value
                }
                is Resource.Error -> {
                    binding.textView.text = "Error"
                    Toast.makeText(this, it.throwable.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
