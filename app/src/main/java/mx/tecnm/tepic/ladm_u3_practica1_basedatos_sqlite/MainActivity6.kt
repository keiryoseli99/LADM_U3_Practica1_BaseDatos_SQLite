package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main6.*

class MainActivity6 : AppCompatActivity() {
    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        var extra = intent.extras
        id = extra!!.getString("idActualizarVehiculo")!!

        //RECUPERAR DATA
        val vehiculo = Vehiculo(this).consultarVehiculoPorIDVehiculo(id)
        actualizarPlaca.setText(vehiculo.placa)
        actualizarMarca.setText(vehiculo.marca)
        actualizarModelo.setText(vehiculo.modelo)
        actualizarYear.setText(vehiculo.year)

        btnActualizarVehiculo.setOnClickListener {
            val vehiculoActualizar = Vehiculo(this)
            vehiculoActualizar.placa = actualizarPlaca.text.toString()
            vehiculoActualizar.marca = actualizarMarca.text.toString()
            vehiculoActualizar.modelo = actualizarModelo.text.toString()
            vehiculoActualizar.year = actualizarYear.text.toString()

            val resultado = vehiculoActualizar.actualizarVehiculo(id)
            if (resultado) {
                Toast.makeText(this, "EXITO SE ACTUALIZO", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "ERROR! NO SE LOGRO ACTUALIZAR", Toast.LENGTH_LONG).show()
            }
        }

        btnRegresarActVehiculo.setOnClickListener {
            finish()
        }
    }
}