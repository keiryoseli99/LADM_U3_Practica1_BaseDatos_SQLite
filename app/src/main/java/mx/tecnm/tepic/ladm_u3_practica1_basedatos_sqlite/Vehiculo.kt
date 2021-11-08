package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.widget.Toast
import mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite.BaseDatos
import java.io.File
import java.io.FileWriter
import java.lang.Exception

class Vehiculo(p:Context) {
    var placa = ""
    var marca = ""
    var modelo = ""
    var year = ""
    var idConductor = ""
    val pnt = p

    fun insertarVehiculo() : Boolean {
        val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val datos = ContentValues()

        datos.put("placa",placa)
        datos.put("marca",marca)
        datos.put("modelo",modelo)
        datos.put("year",year)
        datos.put("idConductor",idConductor)

        val resultado = tablaVehiculo.insert("VEHICULO",null,datos)
        if (resultado == -1L){
            return false
        }
        return true
    }

    fun consultarVehiculo() : ArrayList<String> {
        val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val resultado = ArrayList<String>()
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),null,null,null,null,null)
        if (cursor.moveToFirst()) {
            do {
                var datos = "(${cursor.getInt(0)})"+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getInt(4)+"\n"+cursor.getInt(5)
                resultado.add(datos)
            } while (cursor.moveToNext())
        } else {
            resultado.add("NO SE ENCONTRO DATOS A MOSTRAR")
        }
        return resultado
    }

    fun consultarVehiculoPorIDConductor(idDelConductor: String) : ArrayList<String> {
        val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val resultado = ArrayList<String>()
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),"IDCONDUCTOR=?", arrayOf(idDelConductor),null,null,null)
        //val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),null,null,null,null,null)
        if (cursor.moveToFirst()) {
            do {
                var datos = "(${cursor.getInt(0)})"+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getInt(4)+"\n"+cursor.getInt(5)
                resultado.add(datos)
            } while (cursor.moveToNext())
        } else {
            resultado.add("NO SE ENCONTRO DATOS A MOSTRAR")
        }
        return resultado
    }

    fun obtenerIDsVehiculo() : ArrayList<Int> {
        val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),null,null,null,null,null)
        if (cursor.moveToFirst()) {
            do {
                resultado.add(cursor.getInt(0))
            } while (cursor.moveToNext())
        }
        return resultado
    }

    fun obtenerIDConductorVehiculo() : ArrayList<Int> {
        val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),null,null,null,null,null)
        if (cursor.moveToFirst()) {
            do {
                resultado.add(cursor.getInt(5))
            } while (cursor.moveToNext())
        }
        return resultado
    }

    fun eliminarVehiculo(idEliminar:Int) : Boolean {
        val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val resultado = tablaVehiculo.delete("VEHICULO", "IDVEHICULO=?", arrayOf(idEliminar.toString()))
        if (resultado == 0) return false
        return true
    }

    fun eliminarVehiculoIfConductorDelete(idEliminar:Int) : Boolean {
        val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val resultado = tablaVehiculo.delete("VEHICULO", "IDCONDUCTOR=?", arrayOf(idEliminar.toString()))
        if (resultado == 0) return false
        return true
    }

    fun consultarVehiculoPorIDVehiculo(idBuscar: String) : Vehiculo {
        val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).readableDatabase
        //val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),"IDVEHICULO=?", arrayOf(idBuscar),null,null,null)
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),"IDVEHICULO=?", arrayOf(idBuscar),null,null,null)
        val vehiculo = Vehiculo(MainActivity())
        if (cursor.moveToFirst()) {
            vehiculo.placa = cursor.getString(1)
            vehiculo.marca = cursor.getString(2)
            vehiculo.modelo = cursor.getString(3)
            vehiculo.year = cursor.getString(4)
        }
        return vehiculo
    }

    fun actualizarVehiculo(idActualizar: String): Boolean {
        val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val datos = ContentValues()

        datos.put("placa",placa)
        datos.put("marca",marca)
        datos.put("modelo",modelo)
        datos.put("year",year)

        val resultado = tablaVehiculo.update("VEHICULO",datos,"IDVEHICULO=?", arrayOf(idActualizar))
        if (resultado==0) return false
        return true
    }

    fun consultarVehiculosPorYears(N: String) : ArrayList<String> {
        val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val resultado = ArrayList<String>()
        val cursor = tablaVehiculo.rawQuery("SELECT * FROM VEHICULO WHERE 2021-YEAR=${N.toInt()}",null)
        if (cursor.moveToFirst()) {
            do {
                var datos = "(${cursor.getInt(0)})"+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getInt(4)+"\n"+cursor.getInt(5)
                resultado.add(datos)
            } while (cursor.moveToNext())
        } else {
            resultado.add("NO SE ENCONTRO DATOS A MOSTRAR")
        }
        return resultado
    }

}