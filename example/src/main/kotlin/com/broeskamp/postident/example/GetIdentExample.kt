package com.broeskamp.postident.example


fun getIdent() {

    val api = createPostIdentApi()
    val ident = createNewIdent(api)

    val resultFuture = api.retrieveIdentCaseResult(ident.caseId)

    print("Getting ident result...")
    val result = resultFuture.get()
    println("done! Result: $result")


}

fun main() {
    getIdent()
}