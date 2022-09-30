package com.broeskamp.postident

import java.io.InputStream

data class PostIdentFile(
    val name: String,
    val inputStream: InputStream,
)
