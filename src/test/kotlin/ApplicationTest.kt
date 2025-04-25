import common.test
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testRoot() = test {
        val response = client.get("/status")
        assertEquals(response.status, HttpStatusCode.OK)
    }
}