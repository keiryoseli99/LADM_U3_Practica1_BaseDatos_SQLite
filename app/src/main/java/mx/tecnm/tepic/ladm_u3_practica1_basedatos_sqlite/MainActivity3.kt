package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.listaConductor
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    var idConductor = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        mostrarConductoresCapturados()

        btnRegresarIngresarVehiculo.setOnClickListener {
            finish()
        }
    }

    private fun mostrarConductoresCapturados() {
        var arregloConductor = Conductor(this).consultarConductor()

        listaConductor.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloConductor)
        idConductor.clear()
        idConductor = Conductor(this).obtenerIDsConductor()
        activarEvento(listaConductor)
    }

    private fun activarEvento(listaConductor: ListView) {
        listaConductor.setOnItemClickListener { adapterView, view, indiceSeleccionado, l ->

            val idSeleccionado = idConductor[indiceSeleccionado]
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("¿DESEA AGREGAR UN VEHICULO A ESTE CONDUCTOR?")
                .setPositiveButton("SI"){d,i-> ingresarVehiculo(idSeleccionado)}
                .setNegativeButton("NO"){d,i-> d.cancel() }
                .show()
        }
    }

    private fun ingresarVehiculo(idSeleccionado: Int) {
        val intento = Intent(this, MainActivity4::class.java)
        intento.putExtra("idConductor",idSeleccionado.toString())
        startActivity(intento)
    }
}