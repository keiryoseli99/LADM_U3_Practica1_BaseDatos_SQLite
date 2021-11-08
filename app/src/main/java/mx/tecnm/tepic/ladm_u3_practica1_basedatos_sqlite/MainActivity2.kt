package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main5.*

class MainActivity2 : AppCompatActivity() {
    var idConductor = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mostrarConductoresCapturados()
        btnInsertarConductor.setOnClickListener {
            val conductor = Conductor(this)

            conductor.nombre = campoNombre.text.toString()
            conductor.domicilio = campoDomicilio.text.toString()
            conductor.nolicencia = campoNoLicencia.text.toString()
            conductor.vence = campoVence.text.toString()

            val resultado = conductor.insertarConductor()
            if (resultado) {
                Toast.makeText(this, "EXITO! SE INSERTO", Toast.LENGTH_LONG).show()
                campoNombre.text.clear()
                campoDomicilio.text.clear()
                campoNoLicencia.text.clear()
                campoVence.text.clear()
                mostrarConductoresCapturados()
            } else {
                Toast.makeText(this, "ERROR NO SE INSERTOR", Toast.LENGTH_LONG).show()
            }
        }

        btnInsertarConductorRegresar.setOnClickListener {
            finish()
        }
    }

    private fun mostrarConductoresCapturados() {
        val arregloConductor = Conductor(this).consultarConductor()

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
                .setMessage("¿QUE DESEA HACER CON EL CONDUCTOR?")
                .setPositiveButton("EDITAR"){d,i-> actualizar(idSeleccionado)}
                .setNegativeButton("ELIMINAR"){d,i-> eliminar(idSeleccionado)}
                .setNeutralButton("CANCELAR"){d,i-> d.cancel() }
                .show()
        }
    }

    private fun actualizar(idSeleccionado: Int) {
        val intento = Intent(this, MainActivity5::class.java)
        intento.putExtra("idActualizar", idSeleccionado.toString())
        startActivity(intento)

        AlertDialog.Builder(this)
            .setMessage("¿DESEAS ACTUALIZAR LA LISTA?")
            .setPositiveButton("SI"){d,i-> mostrarConductoresCapturados()}
            .setNegativeButton("NO"){d,i-> d.cancel()}
            .show()
    }


    private fun eliminar(idSeleccionado: Int) {
        AlertDialog.Builder(this)
            .setTitle("IMPORTANTE")
            .setMessage("¿SEGURO QUE DESEAS ELIMINAR ID ${idSeleccionado}?")
            .setPositiveButton("SI"){d,i->
                val resultado = Conductor(this).eliminarConductor(idSeleccionado)
                val resultado2 = Vehiculo(this).eliminarVehiculoIfConductorDelete(idSeleccionado)
                if (resultado && resultado2) {
                    Toast.makeText(this, "SE ELIMINO CON EXITO", Toast.LENGTH_LONG).show()
                    mostrarConductoresCapturados()
                } else {
                    Toast.makeText(this, "ERROR NO SE LOGRO ELIMINAR", Toast.LENGTH_LONG).show()
                }
            }
            .setNegativeButton("NO"){d,i-> d.cancel()}
            .show()
    }
}