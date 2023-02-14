package ch.sbb

import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/v1")
class ExampleResource {

    val text =
        "Lorem ipsum dolor sit amet."


    @GET
    @Path("/memory/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    fun memory(@RestPath number: Int): List<String> {
        fun fib(input: String, cnt: Int): List<String> {
            Thread.sleep(100)
            return if (cnt < number) listOf(input) + fib(input+input, cnt + 1);
            else listOf(input)
        }
        return fib(text,0).map { it.toString() }
    }

    @GET
    @Path("/cpu/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    fun fibonacci(@RestPath number: Int): List<String> {
        var t1: ULong = 0u
        var t2: ULong = 1u
        val serie = mutableListOf<String>()

        for (i in 1..number) {
            repeat(100) { Thread.sleep(1) }

            serie.add(t1.toString())

            val sum = t1 + t2
            t1 = t2
            t2 = sum
        }
        return serie
    }
}