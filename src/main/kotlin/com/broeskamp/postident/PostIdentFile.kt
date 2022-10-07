package com.broeskamp.postident

import com.thinkinglogic.builder.annotation.Builder
import java.io.InputStream

@Builder
data class PostIdentFile(
    val name: String,
    val inputStream: InputStream,
)
