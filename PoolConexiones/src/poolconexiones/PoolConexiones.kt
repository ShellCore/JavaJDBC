package poolconexiones

import capadatos.pool.getMySQLConnection
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

fun main(args: Array<String>) {
    var conn : Connection? = null
    var stmt : PreparedStatement? = null
    var rs : ResultSet? = null

    try {
        conn = getMySQLConnection()
        println("Utilizamos el pool de conexiones de MySQL")
        stmt = conn!!.prepareStatement("SELECT * FROM persona")
        rs = stmt!!.executeQuery()
        while (rs!!.next()) {
            print("Id: ${rs.getInt(1)}")
            print(", Nombre: ${rs.getString(2)}")
            println(", Apellido: ${rs.getString(3)}")
        }
        conn.close()
    } catch (e : SQLException) {
        e.printStackTrace()
    }
}