package com.broeskamp.postident.example

import com.broeskamp.postident.PostIdentApi
import com.broeskamp.postident.dto.request.ident.IdentCaseProcessData
import com.broeskamp.postident.dto.request.ident.IdentCaseRequest
import com.broeskamp.postident.dto.request.ident.IdentContactData
import com.broeskamp.postident.dto.request.ident.IdentDocumentData
import com.broeskamp.postident.dto.response.ident.IdentCaseResponse


fun createNewIdent(api: PostIdentApi = createPostIdentApi()): IdentCaseResponse {


    val case = IdentCaseRequest(
        identificationDocumentProvided = IdentDocumentData(
            null
        ),
        contactDataProvided = IdentContactData(),
        processData = IdentCaseProcessData(),
    )

    val caseResponse = api.createIdentCase(case)
    print("Creating Ident...")
    val result = caseResponse.get()
    println("done! Ident: $result")
    return result
}

fun main() {
    createNewIdent()
}