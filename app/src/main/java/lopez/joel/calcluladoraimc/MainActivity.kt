package lopez.joel.calcluladoraimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val weight: EditText = findViewById(R.id.weight)
        val height: EditText = findViewById(R.id.height)
        val calculate: Button = findViewById(R.id.calculate)
        val imc: TextView = findViewById(R.id.imc)
        val range: TextView = findViewById(R.id.range)

        calculate.setOnClickListener {
            val weightText = weight.text.toString()
            val heightText = height.text.toString()

            if (weightText.isNotEmpty() && heightText.isNotEmpty()) {
                val weightValue = weightText.toFloat()
                val heightValue = heightText.toFloat()

                // Calcular el IMC
                val imcValue = weightValue / (heightValue * heightValue)

                imc.text = "%.2f".format(imcValue)

                val (category,color)= when {
                    imcValue < 18.5 -> "Bajo peso" to R.color.brown
                    imcValue in 18.5..24.9 -> "Normal" to R.color.green
                    imcValue in 25.0..29.9 -> "Sobrepeso" to R.color.greenish
                    imcValue in 30.0..34.9 -> "Obesidad grado 1" to R.color.yellow
                    imcValue in 35.0..39.9 -> "Obesidad grado 2" to R.color.orange
                    else -> "Obesidad grado 3" to R.color.red // Para 40 o más
                }

                // Mostrar la categoría en el TextView
                range.text = category

                range.setBackgroundResource(color)
            } else {
                    imc.text = "Ingresa valores válidos"
                    range.text = ""

                    // Restaurar fondo de range por defecto
                    range.setBackgroundResource(R.color.white)
                }

            }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}