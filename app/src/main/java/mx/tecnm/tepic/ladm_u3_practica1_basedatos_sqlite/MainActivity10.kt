package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main10.*
import kotlinx.android.synthetic.main.activity_main8.*
import kotlinx.android.synthetic.main.activity_main8.listaVehiculoPorConsultor

class MainActivity10 : AppCompatActivity() {
    var idCond = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)

        val extra = intent.extras
        idCond = extra!!.getString("idConsultasVehiculo")!!

        mostrarConductorPorVehiculo()

        btnRegresarAVehiculos.setOnClickListener {
            finish()
        }
    }

    private fun mostrarConductorPorVehiculo() {
        var arregloVehiculos = Conductor(this).consultarConductorPorID(idCond)

        listaConductorPorVehiculo.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloVehiculos)
    }

}