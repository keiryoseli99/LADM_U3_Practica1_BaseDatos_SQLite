package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main7.*

class MainActivity7 : AppCompatActivity() {
    var idConductor = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        btnTodosLosConductores.setOnClickListener {
            mostrarConductoresCapturados()
        }

        btnLicenciaVencida.setOnClickListener {
            mostrarConductoresConLicenciaVencida()
        }

        btnConductoresSinVehiculos.setOnClickListener {
            mostrarConductoresSinVehiculos()
        }

        btnRegresarConsultasConductor.setOnClickListener {
            finish()
        }
    }

    private fun mostrarConductoresSinVehiculos() {
        var arregloConductor = Conductor(this).consultarConductorSinVehiculo()

        listaConsultasConductor.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloConductor)
        //idConductor.clear()
        //idConductor = Conductor(this).obtenerIDsConductor()
        //activarEvento(listaConsultasConductor)
    }

    private fun mostrarConductoresConLicenciaVencida() {
        var arregloConductor = Conductor(this).consultarConductorConLicenciaVencida()

        listaConsultasConductor.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloConductor)
        //idConductor.clear()
        //idConductor = Conductor(this).obtenerIDsConductor()
        //activarEvento(listaConsultasConductor)
    }

    private fun mostrarConductoresCapturados() {
        var arregloConductor = Conductor(this).consultarConductor()

        listaConsultasConductor.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloConductor)
        idConductor.clear()
        idConductor = Conductor(this).obtenerIDsConductor()
        activarEvento(listaConsultasConductor)
    }

    private fun activarEvento(listaConsultasConductor: ListView) {
        listaConsultasConductor.setOnItemClickListener { adapterView, view, indiceSeleccionado, l ->

            val idSeleccionado = idConductor[indiceSeleccionado]
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("¿DESEA CONSULTAR LOS VEHICULOS DE ESTE CONDUCTOR?")
                .setPositiveButton("SI"){d,i-> consultarVehiculo(idSeleccionado)}
                .setNegativeButton("NO"){d,i-> d.cancel() }
                .show()
        }
    }

    private fun consultarVehiculo(idSeleccionado: Int) {
        val intento = Intent(this, MainActivity8::class.java)
        intento.putExtra("idConsultasConductor",idSeleccionado.toString())
        startActivity(intento)
    }

}