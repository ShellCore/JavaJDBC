package capadatos.pool

import org.apache.commons.dbcp2.BasicDataSource
import utils.getProperty
import javax.sql.DataSource

fun getMySQLConnection() = getDataSource().connection

private fun getDataSource() : DataSource
        = BasicDataSource().apply {
    driverClassName = getProperty("driverClassName")
    username = getProperty("userName")
    password = getProperty("password")
    url = getProperty("url")
    initialSize = 5
}