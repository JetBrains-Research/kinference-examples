package io.kinference.examples.utils

expect object ResourceLoader {
    suspend fun bytes(path: Path): ByteArray
}

class Path(parts: List<String>) {
    private val parts = parts.map { it.trim().trim('/') }.filter { it.isNotBlank() }

    constructor(vararg path: String): this(path.flatMap { it.split("/") })
    constructor(path: String): this(path.split("/"))
    constructor(path: Path, part: String) : this(path.parts + part)
    constructor(part: String, path: Path) : this(listOf(part) + path.parts)

    fun toRelativePath() = parts.joinToString(separator = "/")
    fun toAbsolutePath() = parts.joinToString(prefix = "/", separator = "/")
}