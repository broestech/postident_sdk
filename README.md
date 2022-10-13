# PostIdent SDK

Java SDK for POSTIDENT by Deutsche Post.

## Features

- Create SigningCase
- Retrieve SigningCaseData (encrypted)
- Retrieve IdentCaseData (encrypted)

# How to use

For a local build you need to publish this to you local maven repository

`gradle publishToMavelLocal`

Afterwards you can use it as a dependency, for example:

``` xml
<dependency>
  <groupId>com.broeskamp</groupId>
  <artifactId>postident_sdk</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

## Encryption

PostIdent requires encryption to be present when fetching data from SigningCases or IdentCases.
To use encryption you need to generate an RSA 3072 bit KeyPair and use the PCKS8 format for the
private key.

### Generate KeyPair

- Generate private RSA key: `openssl genrsa -out private_key.pem 3072`
- Generate public key from private
  key: `openssl rsa -in private_key.pem -pubout -out public_key.pem`
- Convert private Key to PCKS8
  format: `openssl pkcs8 -topk8 -inform PEM -in private_key.pem -out private_key -nocrypt`

## SFTP

PostIdent hosts a SFTP server which contains files related to the ident (e.g. video recording).
The authentication is done via an RSA 3072 bit KeyPair.
The public key is then loaded into the PostIdent administration portal.

### Generate KeyPair

For the generation we use PuTTY Key Generator.

- At the bottom in the field 'Number of bits generated key' enter 3072
- Hit on Generate
- Afterwards the public key is shown in the OpenSSH format at the top
- To save the private key in the OpenSSH format go to Conversions -> Export OpenSSH key

You can test the connection and keys in FileZilla, which takes a .ppk and can be generated with the
button 'Save private key'