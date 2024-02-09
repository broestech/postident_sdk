package com.broeskamp.postident

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.security.Key
import java.security.KeyFactory
import java.security.spec.EncodedKeySpec
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*


class RsaKeypairUtilTest {


    @Test
    fun `when matching keys are provided, return those`() {
        val private = getPrivateKeyA()
        val public = getPublicKeyA()

        val keyPair = RsaKeypairUtil.generateKeypair(private, public)

        val encodedActualPublicKey = encodeKey(
            keyPair.public,
            X509EncodedKeySpec::class.java
        )
        val encodedActualPrivateKey = encodeKey(
            keyPair.private,
            PKCS8EncodedKeySpec::class.java
        )

        assertTrue(
            stripKeyFromDelimiterAndWhitespaces(public).contains(
                stripKeyFromDelimiterAndWhitespaces(
                    encodedActualPublicKey
                )
            ),
            "Assumed \"$public\" to contain \"$encodedActualPublicKey\", but wasn't."
        )
        assertTrue(
            stripKeyFromDelimiterAndWhitespaces(private).contains(
                stripKeyFromDelimiterAndWhitespaces(
                    encodedActualPrivateKey
                )
            ),
            "Assumed \"$private\" to contain \"$encodedActualPrivateKey\", but wasn't."
        )
    }

    @Test
    fun `when non-matching keys are provided, throw error`() {
        val private = getPrivateKeyA()
        val public = getPublicKeyB()

        assertThrows<IllegalArgumentException> {
            RsaKeypairUtil.generateKeypair(private, public)
        }
    }

    @Test
    fun `when only public key is provided, throw error`() {
        val public = getPublicKeyB()

        assertThrows<IllegalArgumentException> {
            RsaKeypairUtil.generateKeypair(null, public)
        }
    }

    @Test
    fun `when only private key is provided, generate matching public key`() {

        val private = getPrivateKeyA()

        val keyPair = RsaKeypairUtil.generateKeypair(private, null)


        val encodedActualPrivateKey = encodeKey(
            keyPair.private,
            PKCS8EncodedKeySpec::class.java
        )
        assertTrue(
            stripKeyFromDelimiterAndWhitespaces(private).contains(
                stripKeyFromDelimiterAndWhitespaces(
                    encodedActualPrivateKey
                )
            ),
            "Assumed \"$private\" to contain \"$encodedActualPrivateKey\", but wasn't."

        )
        assertTrue(RsaKeypairUtil.doRsaKeysMatch(keyPair.public, keyPair.private))

    }

    @Test
    fun `when no keys are provided, generate matching pair`() {
        val keyPair = RsaKeypairUtil.generateKeypair(null, null)

        assertTrue(RsaKeypairUtil.doRsaKeysMatch(keyPair.public, keyPair.private))
    }

    @Test
    fun `when keys match, return true`() {
        val private = getPrivateKeyA()
        val public = getPublicKeyA()
        val keypair = RsaKeypairUtil.generateKeypair(private, public)

        assertTrue(RsaKeypairUtil.doRsaKeysMatch(keypair.public, keypair.private))
    }

    @Test
    fun `when keys don't match, return false`() {
        val privateA = getPrivateKeyA()
        val publicA = getPublicKeyA()

        val privateB = getPrivateKeyB()
        val publicB = getPublicKeyB()

        val keypairA = RsaKeypairUtil.generateKeypair(privateA, publicA)
        val keypairB = RsaKeypairUtil.generateKeypair(privateB, publicB)

        assertFalse(RsaKeypairUtil.doRsaKeysMatch(keypairA.public, keypairB.private))
        assertFalse(RsaKeypairUtil.doRsaKeysMatch(keypairB.public, keypairA.private))
    }

    private fun getKey(file: String): String =
        this::class.java.getResource(file)!!.readText()

    private fun getPrivateKeyA() = getKey("/NOT_A_SECRET/test-private-key-A.pem")

    private fun getPublicKeyA() = getKey("/NOT_A_SECRET/test-public-key-A.pem")

    private fun getPrivateKeyB() = getKey("/NOT_A_SECRET/test-private-key-B.pem")

    private fun getPublicKeyB() = getKey("/NOT_A_SECRET/test-public-key-B.pem")

    private fun encodeKey(key: Key, specification: Class<out EncodedKeySpec>): String {
        val kf = KeyFactory.getInstance("RSA")
        val spec = kf.getKeySpec(key, specification)
        val encodedBytes = spec.encoded
        return Base64.getMimeEncoder().encodeToString(encodedBytes)
    }

    private fun stripKeyFromDelimiterAndWhitespaces(key: String): String {
        return key
            .replace("\n", "")
            .replace("\r", "")

            .replace(" ", "")

    }

}