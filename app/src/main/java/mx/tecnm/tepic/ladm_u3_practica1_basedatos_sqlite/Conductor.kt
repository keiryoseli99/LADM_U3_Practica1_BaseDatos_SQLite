package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.widget.Toast
import mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite.BaseDatos
import java.io.File
import java.io.FileWriter
import java.lang.Exception
import java.time.Instant

class Conductor(p:Context) {
    var nombre = ""
    var domicilio = ""
    var nolicencia = ""
    var vence = ""
    val pnt = p

    fun insertarConductor() : Boolean {
        val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val datos = ContentValues()

        datos.put("nombre",nombre)
        datos.put("domicilio",domicilio)
        datos.put("nolicencia",nolicencia)
        datos.put("vence",vence)

        val resultado = tablaConductor.insert("CONDUCTOR",null,datos)
        if (resultado == -1L){
            return false
        }
        return true
    }

    fun consultarConductor() : ArrayList<String> {
        val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val resultado = ArrayList<String>()
        val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"),null,null,null,null,null)
        if (cursor.moveToFirst()) {
            do {
                val datos = "(${cursor.getInt(0)})"+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4)
                resultado.add(datos)
            } while (cursor.moveToNext())
        } else {
            resultado.add("NO SE ENCONTRO DATOS A MOSTRAR")
        }
        return resultado
    }

    fun consultarConductorPorID(idDelConductor: String) : ArrayList<String> {
        val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val resultado = ArrayList<String>()
        val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"),"IDCONDUCTOR=?", arrayOf(idDelConductor),null,null,null)
        //val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"),null,null,null,null,null)
        if (cursor.moveToFirst()) {
            do {
                val datos = "(${cursor.getInt(0)})"+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4)
                resultado.add(datos)
            } while (cursor.moveToNext())
        } else {
            resultado.add("NO SE ENCONTRO DATOS A MOSTRAR")
        }
        return resultado
    }

    fun obtenerIDsConductor() : ArrayList<Int> {
        val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"),null,null,null,null,null)
        if (cursor.moveToFirst()) {
            do {
                resultado.add(cursor.getInt(0))
            } while (cursor.moveToNext())
        }
        return resultado
    }

    fun eliminarConductor(idEliminar:Int) : Boolean {
        val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val resultado = tablaConductor.delete("CONDUCTOR", "IDCONDUCTOR=?", arrayOf(idEliminar.toString()))
        if (resultado == 0) return false
        return true
    }

    fun consultarConductor(idBuscar: String) : Conductor {
        val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).readableDatabase
        val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"),"IDCONDUCTOR=?", arrayOf(idBuscar),null,null,null)
        val conductor = Conductor(MainActivity2())
        if (cursor.moveToFirst()) {
            conductor.nombre = cursor.getString(1)
            conductor.domicilio = cursor.getString(2)
            conductor.nolicencia = cursor.getString(3)
            conductor.vence = cursor.getString(4)
        }
        return conductor
    }

    fun actualizarConductor(idActualizar: String): Boolean {
        val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val datos = ContentValues()

        datos.put("nombre",nombre)
        datos.put("domicilio",domicilio)
        datos.put("nolicencia",nolicencia)
        datos.put("vence",vence)

        val resultado = tablaConductor.update("CONDUCTOR",datos,"IDCONDUCTOR=?", arrayOf(idActualizar))
        if (resultado==0) return false
        return true
    }

    fun consultarConductorConLicenciaVencida() : ArrayList<String> {
        val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val resultado = ArrayList<String>()
        val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"),"VENCE<DATE('now')", null,null,null,null)
        if (cursor.moveToFirst()) {
            do {
                val datos = "(${cursor.getInt(0)})"+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4)
                resultado.add(datos)
            } while (cursor.moveToNext())
        } else {
            resultado.add("NO SE ENCONTRO DATOS A MOSTRAR")
        }
        return resultado
    }

    fun consultarConductorSinVehiculo() : ArrayList<String> {
        val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
        val resultado = ArrayList<String>()
        val cursor = tablaConductor.rawQuery("SELECT * FROM CONDUCTOR C LEFT JOIN VEHICULO V ON C.IDCONDUCTOR=V.IDCONDUCTOR WHERE V.IDCONDUCTOR IS NULL",null)
        if (cursor.moveToFirst()) {
            do {
                val datos = "(${cursor.getInt(0)})"+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4)
                resultado.add(datos)
            } while (cursor.moveToNext())
        } else {
            resultado.add("NO SE ENCONTRO DATOS A MOSTRAR")
        }
        return resultado
    }

}