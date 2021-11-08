package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main4.*
import kotlinx.android.synthetic.main.activity_main4.listaVehiculo
import kotlinx.android.synthetic.main.activity_main9.*

class MainActivity9 : AppCompatActivity() {
    var idConductor = ArrayList<Int>()
    var NYears = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        btnTodosLosVehiculos.setOnClickListener {
            mostrarVehiculosCapturados()
        }

        btnVehiculoConNYearDeUso.setOnClickListener {
            mostrarVehiculosConNYearsDeUso()
        }
    }

    private fun mostrarVehiculosConNYearsDeUso() {
        NYears = campoNYear.text.toString()
        var arregloVehiculo = Vehiculo(this).consultarVehiculosPorYears(NYears)

        listaConsultasVehiculo.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloVehiculo)
    }

    private fun mostrarVehiculosCapturados() {
        var arregloVehiculo = Vehiculo(this).consultarVehiculo()

        listaConsultasVehiculo.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloVehiculo)
        idConductor.clear()
        idConductor = Vehiculo(this).obtenerIDConductorVehiculo()
        activarEvento(listaConsultasVehiculo)
    }

    private fun activarEvento(listaConsultasVehiculo: ListView) {
        listaConsultasVehiculo.setOnItemClickListener { adapterView, view, indiceSeleccionado, l ->

            val idSeleccionado = idConductor[indiceSeleccionado]
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("¿DESEA CONSULTAR El CONDUCTOR DE ESTE VEHICULO?")
                .setPositiveButton("SI"){d,i-> consultarConductor(idSeleccionado)}
                .setNegativeButton("NO"){d,i-> d.cancel()}
                .show()
        }
    }

    private fun consultarConductor(idSeleccionado: Int) {
        val intento = Intent(this, MainActivity10::class.java)
        intento.putExtra("idConsultasVehiculo",idSeleccionado.toString())
        startActivity(intento)
    }

}