package com.alexeykorshun.skydictonary.network

/**
 * @author Alexei Korshun on 29.09.2020.
 */
class Request internal constructor(
    val url: String,
    val method: String
) {

    open class Builder {
        private var url: String? = null
        private var method: String

        init {
            this.method = "GET"
        }

        open fun url(url: String): Builder = apply {
            this.url = url
        }

        open fun get() = method("GET")

        open fun method(method: String): Builder = apply {
            require(method.isNotEmpty()) {
                "method.isEmpty() == true"
            }
            this.method = method
        }

        open fun build(): Request {
            return Request(
                checkNotNull(url) { "url must not be a null" },
                method
            )
        }
    }
}