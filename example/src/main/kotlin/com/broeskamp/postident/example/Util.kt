package com.broeskamp.postident.example

import com.broeskamp.postident.PostIdentApi
import com.broeskamp.postident.PostIdentConfiguration
import io.github.cdimascio.dotenv.Dotenv
import java.net.http.HttpClient

private const val ENV_PREFIX = "POSTIDENT_"
private var dotEnv: Dotenv = Dotenv.load()

private fun systemEnv(key: String): String {

    val fullKey = ENV_PREFIX + key
    val result = dotEnv[fullKey]
        ?: throw IllegalStateException("Environment variable $fullKey not set, but necessary.")
    return result
}

fun createPostIdentApi(): PostIdentApi {
    val configuration = PostIdentConfiguration(
        username = systemEnv("USERNAME"),
        password = systemEnv("PASSWORD"),
        clientId = systemEnv("CLIENT_ID"),
        baseUrl = systemEnv("BASE_URL"),
        dataPassword = systemEnv("DATA_PASSWORD"),
        // Create keypair automatically
        privateKey = null,
        publicKey = null,
        // Omit SFTP-Configuration for this example
        sftpConfig = null,
        httpClient = HttpClient.newHttpClient()
    )
    return PostIdentApi(configuration)
}