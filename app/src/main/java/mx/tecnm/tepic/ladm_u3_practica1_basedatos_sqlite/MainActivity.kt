package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnConductor.setOnClickListener {
            val intento1 = Intent(this, MainActivity2::class.java)
            startActivity(intento1)
        }

        btnVehiculo.setOnClickListener {
            val intento2 = Intent(this, MainActivity3::class.java)
            startActivity(intento2)
        }

        btnConsultasConductor.setOnClickListener {
            val intento3 = Intent(this, MainActivity7::class.java)
            startActivity(intento3)
        }

        btnConsultasVehiculos.setOnClickListener {
            val intento4 = Intent(this, MainActivity9::class.java)
            startActivity(intento4)
        }

        btnExportar.setOnClickListener {
            val intento5 = Intent(this, MainActivity11::class.java)
            startActivity(intento5)
        }

    }
}