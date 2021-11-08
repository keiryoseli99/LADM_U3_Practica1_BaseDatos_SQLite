package mx.tecnm.tepic.ladm_u3_practica1_basedatos_sqlite

import android.content.Context
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileWriter
import java.lang.Exception

class Exportaciones(p: Context) {
    val pnt = p

    //Exportar Tabla con todos los conductores
    fun exportarTodosConductores(context: Context){
        val folder = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)}/SQLite") //SQLite = nombre del folder getExternalStorageDirectory

        if (!folder.exists()) folder.mkdir()

        //file name
        val csvFileName = "TablaConductores.csv"
        //file name and path
        val fileNameAndPath = "$folder/$csvFileName"

        try {
            //Escribir el archivo
            val fw = FileWriter(fileNameAndPath)
            //Base de datos
            val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
            val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"),null,null,null,null,null)

            if (cursor.moveToFirst()) {
                do {
                    fw.append("${cursor.getInt(0)}")
                    fw.append(",")
                    fw.append("${cursor.getString(1)}")
                    fw.append(",")
                    fw.append("${cursor.getString(2)}")
                    fw.append(",")
                    fw.append("${cursor.getString(3)}")
                    fw.append(",")
                    fw.append("${cursor.getString(4)}")
                    fw.append("\n")
                } while (cursor.moveToNext())
            }
            tablaConductor.close()
            fw.close()
            Toast.makeText(context, "SE EXPORTO CON EXITO!!!", Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Toast.makeText(context, "ERROR AL EXPORTAR", Toast.LENGTH_LONG).show()
        }
    }

    //Exportar Tabla con todos los vehiculos
    fun exportarTodosVehiculos(context: Context){
        val folder = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)}/SQLite") //SQLite = nombre del folder getExternalStorageDirectory

        if (!folder.exists()) folder.mkdir()

        //file name
        val csvFileName = "TablaVehiculos.csv"
        //file name and path
        val fileNameAndPath = "$folder/$csvFileName"

        try {
            //Escribir el archivo
            val fw = FileWriter(fileNameAndPath)
            //Base de datos
            val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
            val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),null,null,null,null,null)

            if (cursor.moveToFirst()) {
                do {
                    fw.append("${cursor.getInt(0)}")
                    fw.append(",")
                    fw.append("${cursor.getString(1)}")
                    fw.append(",")
                    fw.append("${cursor.getString(2)}")
                    fw.append(",")
                    fw.append("${cursor.getString(3)}")
                    fw.append(",")
                    fw.append("${cursor.getInt(4)}")
                    fw.append(",")
                    fw.append("${cursor.getInt(5)}")
                    fw.append("\n")
                } while (cursor.moveToNext())
            }
            tablaVehiculo.close()
            fw.close()
            Toast.makeText(context, "SE EXPORTO CON EXITO!!!", Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Toast.makeText(context, "ERROR AL EXPORTAR", Toast.LENGTH_LONG).show()
        }
    }

    //Exportar Tabla con todos los conductores con sus vehiculos asignados
    fun exportarConductorVehiculos(context: Context){
        val folder = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)}/SQLite") //SQLite = nombre del folder getExternalStorageDirectory

        if (!folder.exists()) folder.mkdir()

        //file name
        val csvFileName = "TablaConductoresVehiculos.csv"
        //file name and path
        val fileNameAndPath = "$folder/$csvFileName"

        try {
            //Escribir el archivo
            val fw = FileWriter(fileNameAndPath)
            //Base de datos
            val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
            val cursor = tablaConductor.rawQuery("SELECT * FROM CONDUCTOR C JOIN VEHICULO V ON C.IDCONDUCTOR=V.IDCONDUCTOR",null)

            if (cursor.moveToFirst()) {
                do {
                    //Conductor
                    fw.append("${cursor.getInt(0)}")
                    fw.append(",")
                    fw.append("${cursor.getString(1)}")
                    fw.append(",")
                    fw.append("${cursor.getString(2)}")
                    fw.append(",")
                    fw.append("${cursor.getString(3)}")
                    fw.append(",")
                    fw.append("${cursor.getString(4)}")
                    //Vehiculo
                    fw.append(",")
                    fw.append("${cursor.getInt(5)}")
                    fw.append(",")
                    fw.append("${cursor.getString(6)}")
                    fw.append(",")
                    fw.append("${cursor.getString(7)}")
                    fw.append(",")
                    fw.append("${cursor.getString(8)}")
                    fw.append(",")
                    fw.append("${cursor.getInt(9)}")
                    fw.append(",")
                    fw.append("${cursor.getInt(10)}")
                    fw.append("\n")
                } while (cursor.moveToNext())
            }
            tablaConductor.close()
            fw.close()
            Toast.makeText(context, "SE EXPORTO CON EXITO!!!", Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Toast.makeText(context, "ERROR AL EXPORTAR", Toast.LENGTH_LONG).show()
        }
    }

    //Exportar Tabla con todos los conductores con licencia vencida
    fun exportarConductorConLicenciaVencida(context: Context){
        val folder = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)}/SQLite") //SQLite = nombre del folder getExternalStorageDirectory

        if (!folder.exists()) folder.mkdir()

        //file name
        val csvFileName = "TablaConductoresConLicenciaVencida.csv"
        //file name and path
        val fileNameAndPath = "$folder/$csvFileName"

        try {
            //Escribir el archivo
            val fw = FileWriter(fileNameAndPath)
            //Base de datos
            val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
            val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"),"VENCE<DATE('now')", null,null,null,null)

            if (cursor.moveToFirst()) {
                do {
                    fw.append("${cursor.getInt(0)}")
                    fw.append(",")
                    fw.append("${cursor.getString(1)}")
                    fw.append(",")
                    fw.append("${cursor.getString(2)}")
                    fw.append(",")
                    fw.append("${cursor.getString(3)}")
                    fw.append(",")
                    fw.append("${cursor.getString(4)}")
                    fw.append("\n")
                } while (cursor.moveToNext())
            }
            tablaConductor.close()
            fw.close()
            Toast.makeText(context, "SE EXPORTO CON EXITO!!!", Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Toast.makeText(context, "ERROR AL EXPORTAR", Toast.LENGTH_LONG).show()
        }
    }

    //Exportar Tabla con todos los conductores sin vehiculos asignados
    fun exportarConductorSinVehiculos(context: Context){
        val folder = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)}/SQLite") //SQLite = nombre del folder getExternalStorageDirectory

        if (!folder.exists()) folder.mkdir()

        //file name
        val csvFileName = "TablaConductoresSinVehiculos.csv"
        //file name and path
        val fileNameAndPath = "$folder/$csvFileName"

        try {
            //Escribir el archivo
            val fw = FileWriter(fileNameAndPath)
            //Base de datos
            val tablaConductor = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
            val cursor = tablaConductor.rawQuery("SELECT * FROM CONDUCTOR C LEFT JOIN VEHICULO V ON C.IDCONDUCTOR=V.IDCONDUCTOR WHERE V.IDCONDUCTOR IS NULL",null)

            if (cursor.moveToFirst()) {
                do {
                    fw.append("${cursor.getInt(0)}")
                    fw.append(",")
                    fw.append("${cursor.getString(1)}")
                    fw.append(",")
                    fw.append("${cursor.getString(2)}")
                    fw.append(",")
                    fw.append("${cursor.getString(3)}")
                    fw.append(",")
                    fw.append("${cursor.getString(4)}")
                    fw.append("\n")
                } while (cursor.moveToNext())
            }
            tablaConductor.close()
            fw.close()
            Toast.makeText(context, "SE EXPORTO CON EXITO!!!", Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Toast.makeText(context, "ERROR AL EXPORTAR", Toast.LENGTH_LONG).show()
        }
    }

    //Exportar Tabla con todos los vehiculos con N cantidad de años de uso
    fun exportarVehiculosConNYearsUso(context: Context,N: String){
        val folder = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)}/SQLite") //SQLite = nombre del folder getExternalStorageDirectory

        if (!folder.exists()) folder.mkdir()

        //file name
        val csvFileName = "TablaVehiculosNYearsUso.csv"
        //file name and path
        val fileNameAndPath = "$folder/$csvFileName"

        try {
            //Escribir el archivo
            val fw = FileWriter(fileNameAndPath)
            //Base de datos
            val tablaVehiculo = BaseDatos(pnt,"EmpresaX",null,1).writableDatabase
            val cursor = tablaVehiculo.rawQuery("SELECT * FROM VEHICULO WHERE 2021-YEAR=${N.toInt()}",null)

            if (cursor.moveToFirst()) {
                do {
                    fw.append("${cursor.getInt(0)}")
                    fw.append(",")
                    fw.append("${cursor.getString(1)}")
                    fw.append(",")
                    fw.append("${cursor.getString(2)}")
                    fw.append(",")
                    fw.append("${cursor.getString(3)}")
                    fw.append(",")
                    fw.append("${cursor.getInt(4)}")
                    fw.append(",")
                    fw.append("${cursor.getInt(5)}")
                    fw.append("\n")
                } while (cursor.moveToNext())
            }
            tablaVehiculo.close()
            fw.close()
            Toast.makeText(context, "SE EXPORTO CON EXITO!!!", Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Toast.makeText(context, "ERROR AL EXPORTAR", Toast.LENGTH_LONG).show()
        }
    }

}