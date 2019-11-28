package com.thiru.MockHttpServer.Models

class Response(data: String) {

    /**
     * HTTP status code after processing, e.g. "200 OK", Status.OK
     */
    var status: Status? = null

    /**
     * MIME type of content, e.g. "text/html"
     */
    var mimeType: String? = null

    /**
     * Data of the response, may be null.
     */
    var data: String? = null

    var contentLength: Long = 0

    /**
     * Headers for the HTTP response. Use addHeader() to add lines. the
     * lowercase map is automatically kept up to date.
     */
    var header: Map<String, String>? = null

    /**
     * The request method that spawned this response.
     */
    var requestMethod: HttpMethod? = null

    var encodeAsGzip: Boolean = false

    var keepAlive: Boolean = false

    init {
        this.data = data
    }

    constructor(status: Status, mimeType: String, data: String) : this(data) {
        this.status = status
        this.mimeType = mimeType
    }
}