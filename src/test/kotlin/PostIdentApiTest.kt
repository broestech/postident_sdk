import com.broeskamp.postident.PostIdentApi
import com.broeskamp.postident.PostIdentApiException
import com.broeskamp.postident.PostIdentConfiguration
import com.broeskamp.postident.dto.request.ProcessDataBuilder
import com.broeskamp.postident.dto.request.SigningCaseRequest
import com.broeskamp.postident.dto.request.SigningCaseRequestBuilder
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.net.http.HttpClient
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers
import java.time.LocalDate
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
    lateinit var response: HttpResponse<String>

    @Test
    fun `test throw PostIdentApiException on statusCode != 200`() {
        val postIdentConfiguration =
            PostIdentConfiguration(
                "user",
                "password",
                "clientId",
                "http://base.url",
                httpClient,
            )
        val postIdentApi = PostIdentApi(postIdentConfiguration, null)

        every { response.statusCode() } returns 404
        every { response.body() } returns ""
        every {
            httpClient.sendAsync(
                any(),
                eq(BodyHandlers.ofString())
            )
        } returns CompletableFuture.completedFuture(response)


        val signingCaseRequest = SigningCaseRequestBuilder().processData(
            ProcessDataBuilder().caseName("").validUntil(LocalDate.now()).build()
        )
            .documents(listOf()).signers(listOf()).build()

        assertThrows<ExecutionException>("Should have thrown ExecutionException") {
            postIdentApi.createSigningCase(signingCaseRequest).get()
        }.run { assertEquals(PostIdentApiException::class, cause!!::class) }
    }

    @Test
    fun `test throw IllegalArgumentException on sftpConfig == null`() {
        val postIdentConfiguration =
            PostIdentConfiguration(
                "user",
                "password",
                "clientId",
                "http://base.url",
                httpClient,
            )
        val postIdentApi = PostIdentApi(postIdentConfiguration, null)

        assertThrows<IllegalArgumentException> { postIdentApi.retrieveVideoIdentZip("anyId") }
    }
}