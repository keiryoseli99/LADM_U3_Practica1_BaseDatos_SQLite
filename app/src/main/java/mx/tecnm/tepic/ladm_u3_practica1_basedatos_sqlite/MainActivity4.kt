package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main4.*

class MainActivity4 : AppCompatActivity() {
    var idVehiculo = ArrayList<Int>()
    var idCond = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val extra = intent.extras
        idCond = extra!!.getString("idConductor")!!

        mostrarVehiculosCapturados()
        btnInsertarVehiculo.setOnClickListener {
            val vehiculo = Vehiculo(this)

            vehiculo.placa = campoPlaca.text.toString()
            vehiculo.marca = campoMarca.text.toString()
            vehiculo.modelo = campoModelo.text.toString()
            vehiculo.year = campoYear.text.toString()
            vehiculo.idConductor = idCond

            val resultado = vehiculo.insertarVehiculo()
            if (resultado) {
                Toast.makeText(this, "EXITO! SE INSERTO", Toast.LENGTH_LONG).show()
                campoPlaca.text.clear()
                campoMarca.text.clear()
                campoModelo.text.clear()
                campoYear.text.clear()
                mostrarVehiculosCapturados()
            } else {
                Toast.makeText(this, "ERROR NO SE INSERTOR", Toast.LENGTH_LONG).show()
            }
        }

        btnRegresarVehiculo.setOnClickListener {
            finish()
        }
    }

    private fun mostrarVehiculosCapturados() {
        var arregloVehiculo = Vehiculo(this).consultarVehiculo()

        listaVehiculo.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloVehiculo)
        idVehiculo.clear()
        idVehiculo = Vehiculo(this).obtenerIDsVehiculo()
        activarEvento(listaVehiculo)
    }

    private fun activarEvento(listaVehiculo: ListView) {
        listaVehiculo.setOnItemClickListener { adapterView, view, indiceSeleccionado, l ->

            val idSeleccionado = idVehiculo[indiceSeleccionado]
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("¿QUE DESEA HACER CON EL Vehiculo?")
                .setPositiveButton("EDITAR"){d,i-> actualizar(idSeleccionado)}
                .setNegativeButton("ELIMINAR"){d,i-> eliminar(idSeleccionado)}
                .setNeutralButton("CANCELAR"){d,i-> d.cancel() }
                .show()
        }
    }

    private fun actualizar(idSeleccionado: Int) {
        val intento = Intent(this, MainActivity6::class.java)
        intento.putExtra("idActualizarVehiculo",idSeleccionado.toString())
        startActivity(intento)

        AlertDialog.Builder(this)
            .setMessage("¿DESEAS ACTUALIZAR LA LISTA?")
            .setPositiveButton("SI"){d,i-> mostrarVehiculosCapturados()}
            .setNegativeButton("NO"){d,i-> d.cancel()}
            .show()
    }

    private fun eliminar(idSeleccionado: Int) {
        AlertDialog.Builder(this)
            .setTitle("IMPORTANTE")
            .setMessage("¿SEGURO QUE DESEAS ELIMINAR ID ${idSeleccionado}?")
            .setPositiveButton("SI"){d,i->
                val resultado = Vehiculo(this).eliminarVehiculo(idSeleccionado)
                if (resultado) {
                    Toast.makeText(this, "SE ELIMINO CON EXITO", Toast.LENGTH_LONG).show()
                    mostrarVehiculosCapturados()
                } else {
                    Toast.makeText(this, "ERROR NO SE LOGRO ELIMINAR", Toast.LENGTH_LONG).show()
                }
            }
            .setNegativeButton("NO"){d,i-> d.cancel()}
            .show()
    }

}