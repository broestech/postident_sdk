import com.broeskamp.postident.PostIdentApi
import com.broeskamp.postident.PostIdentApiException
import com.broeskamp.postident.PostIdentConfiguration
import com.broeskamp.postident.dto.request.SigningCaseRequest
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.net.http.HttpClient
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class PostIdentApiTest {

    @MockK
    lateinit var httpClient: HttpClient

    @MockK
    lateinit var signingCaseRequest: SigningCaseRequest

    @MockK
    lateinit var mapper: ObjectMapper

    @MockK
    lateinit var response: HttpResponse<String>

    @Test
    fun `test throw PostIdentApiException on statusCode != 200`() {
        val postIdentConfiguration =
            PostIdentConfiguration("user", "password", "clientId", "http://base.url", httpClient)
        val postIdentApi = PostIdentApi(postIdentConfiguration, mapper)

        every { mapper.writeValueAsString(any()) } returns ""
        every { response.statusCode() } returns 404
        every { response.body() } returns ""
        every {
            httpClient.sendAsync(
                any(),
                eq(BodyHandlers.ofString())
            )
        } returns CompletableFuture.completedFuture(response)
        
        assertThrows<ExecutionException>("") {
            postIdentApi.createSigningCase(signingCaseRequest).get()
        }.run { assertEquals(PostIdentApiException::class, cause!!::class) }

    }
}