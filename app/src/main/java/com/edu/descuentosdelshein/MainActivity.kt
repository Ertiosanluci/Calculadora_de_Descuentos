package com.edu.descuentosdelshein

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button = findViewById<Button>(R.id.button)
        val importe = findViewById<EditText>(R.id.insertarimporte)
        val porcentaje = findViewById<EditText>(R.id.insertarporcentaje)
        val descuento = findViewById<EditText>(R.id.descuento)
        button.setOnClickListener {
            if (importe.text.isNotEmpty() && porcentaje.text.isNotEmpty()) {
                if (importe.text.toString().toDouble() < 0) {
                    Toast.makeText(this, "El importe debe ser mayor que 0", Toast.LENGTH_SHORT).show()
                }else {
                    if (porcentaje.text.toString().toDouble() > 100 || porcentaje.text.toString()
                            .toDouble() < 0
                    ) {
                        Toast.makeText(
                            this,
                            "El porcentaje debe estar entre 0 y 100",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        var dineroimporte = importe.text.toString().toDouble()
                        var dineroporcentaje = porcentaje.text.toString().toDouble()
                        var din = dineroimporte * dineroporcentaje / 100
                        var total = dineroimporte - din
                        descuento.setText(total.toString())
                    }
                }

            } else {
                descuento.setText("0.00")
                Toast.makeText(this, "Introduce importe y porcentaje", Toast.LENGTH_SHORT).show()
            }
        }
    }
}