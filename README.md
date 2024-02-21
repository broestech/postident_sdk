# PostIdent SDK

A Kotlin based SDK for the PostIdent and E-Signature service of Deutsche Post. Allows for creating,
managing and retrieving the results of signing and ident cases.

Table of Contents
=================

* [Features](#features)
* [How to use](#how-to-use)
    * [Include the dependency](#include-the-dependency)
    * [Configure an API-Object](#configure-an-api-object)
    * [Methods](#methods)
* [Encryption](#encryption)
    * [Generating KeyPairs](#generating-keypairs)
        * [PKCS8-Format](#pkcs8-format)
        * [OpenSSH-Format](#openssh-format)
* [Limitations](#limitations)

<!-- Created by https://github.com/ekalinin/github-markdown-toc -->

## Features

- Create SigningCase
- Retrieve SigningCaseData
- Retrieve IdentCaseData
- Retrieve Ident Video File

## How to use

### Include the dependency

Include the [JAR](https://mvnrepository.com/artifact/com.broeskamp/postident_sdk) in your classpath,
either
with maven

```xml

<dependency>
  <groupId>com.broeskamp</groupId>
  <artifactId>postident_sdk</artifactId>
  <version>${postidentsdk.version}</version>
</dependency>
```

or gradle

```groovy
implementation("com.broeskamp:postident_sdk:$postidentSdkVersion")
```

or any other build method of your likings.

### Configure an API-Object

Now create a `PostIdentConfiguration` object and pass it into a `PostIdentApi`.

```kotlin
  import com.broeskamp.postident.PostIdentConfiguration

/* ... */
PostIdentConfiguration(
    username = "foo",
    password = "bar",
    clientId = "1234",
    baseUrl = "https://postident-itu.deutschepost.de",
    dataPassword = "XYZABC123BC",
    privateKey = "-----BEGIN PRIVATE KEY----- /*...*/ -----END PRIVATE KEY-----",
    publicKey = "-----BEGIN PUBLIC KEY----- /*...*/ -----END PUBLIC KEY-----",
    sftpConfig = null
)
```

`username: String`: required. The PostIdent Username given by Deutsche Post.  
`password: String`: required. The PostIdent Password given by Deutsche Post.  
`clientId: String`: required. The Client ID given by Deutsche Post.  
`baseUrl: String`: required. The baseUrl of the PostIdent API. Used to target different environments
and might differ for your tenant.  
`dataPassword: String`: required. The password that is used to encrypt the transmitted ZIP
archives.  
`privateKey: String`: optional. A PKCS8 private key in PEM-Format to encrypt the transmitted data.
Might include the typical pre- and suffix for private keys and (unix) linebreaks.     
`publicKey: String`: optional. An X.509 public key in PEM-Format to encrypt the transmitted data.
Might include the typical pre- and suffix for public keys and (unix) linebreaks.

The keypair for encryption is optional and can be automatically generated if you leave them `null`.
You can also just provide a `privateKey` and omit `publicKey`. The public key will then be
generated. If you provide a `publicKey` you also have to provide a fitting `privateKey`.

Optionally you can provide a `PostIdentSftpConfiguration` if you want to actually download the
ident-files within the application. This is only used for the video files that are captured during
the identification process. This is not needed to download the signed documents.

```kotlin
  import com.broeskamp.postidentPostIdentSftpConfiguration

/* ... */
PostIdentSftpConfiguration(
    billingNumber = "123456",
    host = "ftps://ftp.postident.de",
    path = "/598",
    privateKey = "-----BEGIN RSA PRIVATE KEY----- /*...*/ -----END RSA PRIVATE KEY-----",
    publicKey = "ssh-rsa AAAAB3N /*...*/",
    keyPassword = null
)
```

`billingNumber: String`: required. The billing number given by Deutsche Post. It is used to locate
the correct files as they include the billing number.    
`host: String`: required. The domain to connect to via FTPS. Given by Deutsche Post.  
`path: String`: required. Your customized folder name / path on the FTP server. Connect once by hand
to find out this name.  
`privateKey: String`: required. A private key in openssh-Format to encrypt the transmitted data.
Might include the typical pre- and suffix for private keys in openssh-format and (unix)
linebreaks.     
`publicKey: String`: required. A public key in openssh-Format to encrypt the transmitted data. Might
include the typical pre- and suffix for public keys in openssh-format and (unix) linebreaks.  
`keyPassword: String`: optional. The password that was used to decrypt the private key. Can
be `null` if the private is not encrypted.

The keypair used for the SFTP-transfer might or might not be the same as used by
the `PostIdentConfiguration` object.

Afterwards you can create the API access object

```kotlin
val api = PostIdentApi(postIdentConfiguration)
```

### Methods

```kotlin
fun createSigningCase(signingCaseRequest: SigningCaseRequest): CompletableFuture<SigningCaseResponse>
```

Creates a **Signing Case** (and implicitly an **IdentCase**) (asynchronously). On success sends an
email with further instructions to the customer. The request contains the documents to sign, a list
of all signers, custom data (for case identification when retrieving an updating webhook) and
customization options like the webhook URL, name of the document, validity dates and label of the
signing button.

```kotlin
fun retrieveIdentCaseResult(identCaseId: String): CompletableFuture<IdentCaseResult>
```

Retrieves the current state of an **Ident Case**. This is not to be confused with a **Signing Case**
Uses the keypair provided in the configuration for encryption.

```kotlin
fun retrieveSigningCaseResult(caseId: String): CompletableFuture<SigningCaseResult>
```

Retrieves the current state of a **Signing Case**. This is not to be confused with an **Ident Case
**. Uses the keypair provided in the for encryption.

```kotlin
fun retrieveVideoIdentZip(caseId: String): PostIdentFile
```

Gets the file containing the video of the ident process. Needs the configuration to contain
an `PostIdentSftpConfiguration` or else it will throw a `ConfigurationMissingException`.

## Encryption

PostIdent requires encryption to be present when fetching data from SigningCases or IdentCases.
To use encryption you need to generate an RSA 3072 bit KeyPair and use the PKCS8 format for the
private key. Furthermore, to connect to the SFTP-Server you need to provide a public key via the
Post Dashboard
to authenticate via the corresponding private key.

### Generating KeyPairs

#### PKCS8-Format

- Generate private RSA key: `openssl genrsa -out private_key.pem 3072`
- Generate public key from private
  key: `openssl rsa -in private_key.pem -pubout -out public_key.pem`
- Convert private Key to PCKS8
  format: `openssl pkcs8 -topk8 -inform PEM -in private_key.pem -out private_key -nocrypt`

#### OpenSSH-Format

For the generation we use PuTTY Key Generator.

- At the bottom in the field 'Number of bits generated key' enter 3072
- Hit on Generate
- Afterwards the public key is shown in the OpenSSH format at the top
- To save the private key in the OpenSSH format go to Conversions -> Export OpenSSH key

You can test the connection and keys in FileZilla, which takes a .ppk and can be generated with the
button 'Save private key'

## Limitations

1. Currently, it is not possible to create an IdentCase on its own, without a SigningCase.
2. You have to provide keypairs for encryption, although those could be precomputed by the SDK.
   Future updates will contain this feature.