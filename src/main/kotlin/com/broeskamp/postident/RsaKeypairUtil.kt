package com.broeskamp.postident

import com.nimbusds.jose.util.StandardCharset
import org.slf4j.LoggerFactory
import java.security.*
import java.security.interfaces.RSAPrivateCrtKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAPrivateKeySpec
import java.security.spec.RSAPublicKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*


internal object RsaKeypairUtil {

    private val logger = LoggerFactory.getLogger(RsaKeypairUtil.javaClass)

    private fun doRsaKeysMatch(
        publicKey: PublicKey,
        privateKey: PrivateKey
    ): Boolean {
        val keyFactory = KeyFactory.getInstance("RSA")

        val publicKeySpec = keyFactory.getKeySpec(
            publicKey,
            RSAPublicKeySpec::class.java
        )
        val privateKeySpec = keyFactory.getKeySpec(
            privateKey,
            RSAPrivateKeySpec::class.java
        )
        return publicKeySpec.modulus == privateKeySpec.modulus
    }

    private fun decodePkcs8KeySpec(rsaPrivateKey: String): PKCS8EncodedKeySpec =
        PKCS8EncodedKeySpec(
            Base64.getDecoder().decode(stripKeyFromDelimiterAndWhitespaces(rsaPrivateKey))
        )

    private fun decodeX509KeySpec(rsaPublicKey: String): X509EncodedKeySpec =
        X509EncodedKeySpec(
            Base64.getDecoder().decode(stripKeyFromDelimiterAndWhitespaces(rsaPublicKey))
        )

    private val keyDelimiterRegex = Regex("-----[A-Za-z ]+?-----")

    private fun stripKeyFromDelimiterAndWhitespaces(key: String): String {
        return key
            .replace("\n", "")
            .replace("\r", "")
            .replace(keyDelimiterRegex, "")
            .replace(" ", "")

    }


    internal fun generateKeypair(
        givenPrivateKey: String?,
        givenPublicKey: String?
    ): KeyPair {

        if (givenPrivateKey == null && givenPublicKey != null) {
            throw IllegalArgumentException("A public key to encrypt but no private key to decrypt was given. Either provide both, only the private key or none.")
        }
        val keyfactory = KeyFactory.getInstance("RSA")
        if (givenPrivateKey != null) {
            val privatePkcs8KeySpec = decodePkcs8KeySpec(givenPrivateKey)
            val privateRsaKey = keyfactory.generatePrivate(privatePkcs8KeySpec) as RSAPrivateKey
            if (givenPublicKey != null) {
                val publicX509EncodedKeySpec = decodeX509KeySpec(givenPublicKey)
                val publicRsaKey =
                    keyfactory.generatePublic(publicX509EncodedKeySpec) as RSAPublicKey
                if (!doRsaKeysMatch(publicRsaKey, privateRsaKey)) {
                    throw IllegalArgumentException("Both, a public key for encryption, and a private key for decryption was given, but both don't fit together.")
                } else {
                    return KeyPair(
                        keyfactory.generatePublic(publicX509EncodedKeySpec),
                        keyfactory.generatePrivate(privatePkcs8KeySpec),
                    )
                }
            } else {
                logger.info("Provided private key for decryption, but no public key for encryption. Creating public key automatically.")
                val privateKeyCrtView = privateRsaKey as RSAPrivateCrtKey
                val newPublicKeySpec =
                    RSAPublicKeySpec(privateKeyCrtView.modulus, privateKeyCrtView.publicExponent)
                val publicKey = keyfactory.generatePublic(newPublicKeySpec)
                if (logger.isDebugEnabled) {
                    logger.debug(
                        "Generated public key\n  {}",
                        Base64.getEncoder().encodeToString(publicKey.encoded)
                    )
                }
                return KeyPair(publicKey, privateRsaKey)
            }
        }
        logger.info("No encryption keys provided. Creating automatically...")
        val generator = KeyPairGenerator.getInstance("RSA")
        generator.initialize(4096)
        val result = generator.genKeyPair()
        if (logger.isDebugEnabled) {
            logger.debug(
                "Generated keypair with public key\n  {}",
                Base64.getEncoder().encodeToString(result.public.encoded)
            )
        }
        return result
    }

    internal fun getPublicKeyEncoded(key: PublicKey): String {
        val binary = key.encoded
        return String(Base64.getEncoder().encode(binary), StandardCharset.UTF_8)
    }

}

