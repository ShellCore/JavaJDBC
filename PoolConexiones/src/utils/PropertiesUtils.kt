package utils

import java.io.FileInputStream
import java.io.IOException
import java.util.*

fun getProperty(key: String): String {

    var value = ""
    val prop = Properties()
    var input: FileInputStream? = null
    try {
        input = FileInputStream("resources/project.properties")
        prop.load(input)
        value = prop.getProperty(key)
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        input?.let {
            try {
                it.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    return value
}
