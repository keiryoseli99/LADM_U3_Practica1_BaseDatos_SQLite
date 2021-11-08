package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main11.*
import kotlinx.android.synthetic.main.activity_main9.*
import java.io.File
import java.io.FileWriter

class MainActivity11 : AppCompatActivity() {
    var NYears = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)

        btnExpTodosConductores.setOnClickListener {
            var arreglo = Exportaciones(this)
                arreglo.exportarTodosConductores(this)
        }

        btnExpTodosVehiculos.setOnClickListener {
            var arreglo = Exportaciones(this)
            arreglo.exportarTodosVehiculos(this)
        }

        btnExpConductorVehiculo.setOnClickListener {
            var arreglo = Exportaciones(this)
            arreglo.exportarConductorVehiculos(this)
        }

        btnExpLicenciaVencida.setOnClickListener {
            var arreglo = Exportaciones(this)
            arreglo.exportarConductorConLicenciaVencida(this)
        }

        btnExpConductoresSinVehiculos.setOnClickListener {
            var arreglo = Exportaciones(this)
            arreglo.exportarConductorSinVehiculos(this)
        }

        btnExpVehiculosNYearsUso.setOnClickListener {
            NYears = campoNYearExport.text.toString()
            var arreglo = Exportaciones(this)
            arreglo.exportarVehiculosConNYearsUso(this,NYears)
        }
    }
}