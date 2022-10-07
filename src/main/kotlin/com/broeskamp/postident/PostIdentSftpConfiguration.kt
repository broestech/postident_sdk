package com.broeskamp.postident

data class PostIdentSftpConfiguration(
    private val billingNumber: String,
    val host: String,
    val path: String,
    val publicKey: String,
    val privateKey: String,
    val keyPassword: String?,

    ) {
    fun getVideoZipFilename(username: String, caseId: String) =
        "VIDEOCHATRECORDING_${username}_${billingNumber}_${caseId}.zip"
}
