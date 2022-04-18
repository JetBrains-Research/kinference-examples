package io.kinference.examples.utils

import kotlinx.browser.window
import kotlinx.coroutines.await
import org.khronos.webgl.Int8Array

actual object ResourceLoader {
    actual suspend fun bytes(path: Path): ByteArray {
        val response = window.fetch(path.toRelativePath()).await()
        val buffer = response.arrayBuffer().await()

        return Int8Array(buffer).unsafeCast<ByteArray>()
    }
}