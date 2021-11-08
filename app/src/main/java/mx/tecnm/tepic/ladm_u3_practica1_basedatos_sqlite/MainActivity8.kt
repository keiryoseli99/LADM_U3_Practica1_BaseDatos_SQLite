package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main4.*
import kotlinx.android.synthetic.main.activity_main4.btnInsertarVehiculo
import kotlinx.android.synthetic.main.activity_main8.*

class MainActivity8 : AppCompatActivity() {
    var idCond = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        val extra = intent.extras
        idCond = extra!!.getString("idConsultasConductor")!!

        mostrarVehiculosCapturados()

        btnRegresarAConductores.setOnClickListener {
            finish()
        }
    }

    private fun mostrarVehiculosCapturados() {
        var arregloVehiculos = Vehiculo(this).consultarVehiculoPorIDConductor(idCond)

        listaVehiculoPorConsultor.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloVehiculos)
    }

}