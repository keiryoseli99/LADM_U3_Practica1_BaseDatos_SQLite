package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main5.*

class MainActivity5 : AppCompatActivity() {
    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        val extra = intent.extras
        id = extra!!.getString("idActualizar")!!

        //RECUPERAR DATA
        val conductor = Conductor(this).consultarConductor(id)
        actualizarNombre.setText(conductor.nombre)
        actualizarDomicilio.setText(conductor.domicilio)
        actualizarNoLicencia.setText(conductor.nolicencia)
        actualizarVence.setText(conductor.vence)

        btnActualizarConductor.setOnClickListener {
            val conductorActualizar = Conductor(this)
            conductorActualizar.nombre = actualizarNombre.text.toString()
            conductorActualizar.domicilio = actualizarDomicilio.text.toString()
            conductorActualizar.nolicencia = actualizarNoLicencia.text.toString()
            conductorActualizar.vence = actualizarVence.text.toString()

            val resultado = conductorActualizar.actualizarConductor(id)
            if (resultado) {
                Toast.makeText(this, "EXITO SE ACTUALIZO", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "ERROR! NO SE LOGRO ACTUALIZAR", Toast.LENGTH_LONG).show()
            }
        }

        btnRegresarConductor.setOnClickListener {
            finish()
        }
    }
}