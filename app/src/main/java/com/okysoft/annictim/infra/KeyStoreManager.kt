package com.okysoft.annictim.infra

import android.content.Context
import android.security.KeyPairGeneratorSpec
import com.okysoft.annictim.BuildConfig
import java.math.BigInteger
import java.security.*
import java.util.*
import javax.crypto.Cipher
import javax.security.auth.x500.X500Principal

class KeyStoreManager(private val context: Context) {

    private val KEY_PROVIDER = "AndroidKeyStore"
    private val KEY_STORE_ALIAS = "${BuildConfig.APPLICATION_ID}_KEY"
    private val KEY_STORE_ALGORITHM = "RSA"
    private val CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding"

    private val keyStore: KeyStore

    init {
        keyStore = KeyStore.getInstance(KEY_PROVIDER)
        keyStore.load(null)
    }

    private fun createKeyPairGeneratorSpec(): KeyPairGeneratorSpec {
        val start = Calendar.getInstance()
        val end = Calendar.getInstance()
        end.add(Calendar.YEAR, 100)

        val spec = KeyPairGeneratorSpec.Builder(context)
                .setAlias(KEY_STORE_ALIAS)
                .setSubject(X500Principal("CN=$KEY_STORE_ALIAS"))
                .setSerialNumber(BigInteger.valueOf(100000))
                .setStartDate(start.time)
                .setEndDate(end.time)
                .build()
        return spec
    }

    private fun createKeyPair(): KeyPair {
        val kpg = KeyPairGenerator.getInstance(KEY_STORE_ALGORITHM, KEY_PROVIDER).apply {
            initialize(createKeyPairGeneratorSpec())
        }
        return kpg.generateKeyPair()
    }

    private fun getPublicKey(): PublicKey
            = if (keyStore.containsAlias(KEY_STORE_ALIAS)) {
        keyStore.getCertificate(KEY_STORE_ALIAS).publicKey
    } else {
        createKeyPair().public
    }

    private fun getPrivateKey(): PrivateKey
            = if (keyStore.containsAlias(KEY_STORE_ALIAS)) {
        keyStore.getKey(KEY_STORE_ALIAS, null) as PrivateKey
    } else {
        createKeyPair().private
    }

    fun encrypt(bytes: ByteArray): ByteArray {
        if (bytes.isEmpty()) { return bytes }
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey())
        return cipher.doFinal(bytes)
    }

    fun decrypt(bytes: ByteArray): ByteArray {
        if (bytes.isEmpty()) { return bytes }
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey())
        return cipher.doFinal(bytes)
    }

}