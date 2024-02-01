import com.broeskamp.postident.*
import com.broeskamp.postident.dto.request.signing.SigningCaseProcessDataBuilder
import com.broeskamp.postident.dto.request.signing.SigningCaseRequestBuilder
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.mockkConstructor
import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.sftp.RemoteFile
import net.schmizz.sshj.sftp.RemoteFile.RemoteFileInputStream
import net.schmizz.sshj.sftp.SFTPClient
import net.schmizz.sshj.userauth.keyprovider.KeyProvider
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.net.http.HttpClient
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.time.LocalDate
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class PostIdentApiTest {

    @MockK
    lateinit var httpClient: HttpClient

    @MockK
    lateinit var response: HttpResponse<String>

    lateinit var privateKey: String
    lateinit var publicKey: String

    @BeforeTest
    fun setup() {
        val generator = KeyPairGenerator.getInstance("RSA")

        generator.initialize(2048, SecureRandom())
        val keypair = generator.genKeyPair()
        privateKey = Base64.getEncoder().encodeToString(keypair.private.encoded)
        publicKey = Base64.getEncoder().encodeToString(keypair.public.encoded)
    }

    @Test
    fun `test throw PostIdentApiException on statusCode != 200`() {
        val postIdentConfiguration =
            PostIdentConfiguration(
                "user",
                "password",
                "clientId",
                "http://base.url",
                "dataPassword",
                privateKey,
                publicKey,
                null,
                httpClient
            )
        val postIdentApi = PostIdentApi(postIdentConfiguration)

        every { response.statusCode() } returns 404
        every { response.body() } returns ""
        every {
            httpClient.sendAsync(
                any(),
                eq(BodyHandlers.ofString())
            )
        } returns CompletableFuture.completedFuture(response)


        val signingCaseRequest =
            SigningCaseRequestBuilder().processData(
                SigningCaseProcessDataBuilder().validUntil(LocalDate.now()).build()
            )
                .documents(listOf()).signers(listOf()).build()

        assertThrows<ExecutionException>("Should have thrown ExecutionException") {
            postIdentApi.createSigningCase(signingCaseRequest).get()
        }.run { assertEquals(PostIdentApiException::class, cause!!::class) }
    }

    @Test
    fun `test throw ConfigurationMissingException on sftpConfig == null`() {
        val postIdentConfiguration =
            PostIdentConfiguration(
                "user",
                "password",
                "clientId",
                "http://base.url",
                "dataPassword",
                privateKey,
                publicKey,
                null,
                httpClient
            )

        val postIdentApi = PostIdentApi(postIdentConfiguration)

        assertThrows<ConfigurationMissingException> { postIdentApi.retrieveVideoIdentZip("anyId") }
    }

    @Test
    fun `test retrieveVideoIdentZip`() {
        val sftpConfig = PostIdentSftpConfiguration(
            "billing",
            "host",
            "path",
            publicKey,
            privateKey,
            null,
        )

        val config =
            PostIdentConfiguration(
                "user",
                "password",
                "clientId",
                "http://base.url",
                "dataPassword",
                privateKey,
                publicKey,
                sftpConfig,
                httpClient
            )

        val caseId = "caseId"

        val postIdentApi = PostIdentApi(config)

        val sftpClient = mockk<SFTPClient>()
        val remoteFile = mockk<RemoteFile>()
        val keyProvider = mockk<KeyProvider>()

        mockkConstructor(SSHClient::class)
        mockkConstructor(RemoteFileInputStream::class)

        every { anyConstructed<SSHClient>().newSFTPClient() } returns sftpClient
        every { anyConstructed<SSHClient>().connect(sftpConfig.host) } returns Unit
        every {
            anyConstructed<SSHClient>().loadKeys(
                sftpConfig.privateKey,
                sftpConfig.publicKey,
                null
            )
        } returns keyProvider
        every {
            anyConstructed<SSHClient>().authPublickey(
                config.username,
                keyProvider
            )
        } returns Unit
        every { sftpClient.open(any()) } returns remoteFile

        val result = postIdentApi.retrieveVideoIdentZip(caseId)

        assertEquals(sftpConfig.getVideoZipFilename("user", caseId), result.name)

    }
}