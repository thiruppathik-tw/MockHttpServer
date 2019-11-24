package com.thiru.MockHttpServer.Models

class Response(data: String) {

    /**
     * HTTP status code after processing, e.g. "200 OK", Status.OK
     */
    public var status: Status? = null

    /**
     * MIME type of content, e.g. "text/html"
     */
    public var mimeType: String? = null

    /**
     * Data of the response, may be null.
     */
    public var data: String? = null

    public var contentLength: Long = 0

    /**
     * Headers for the HTTP response. Use addHeader() to add lines. the
     * lowercase map is automatically kept up to date.
     */
    public var header: Map<String, String>? = null

    /**
     * The request method that spawned this response.
     */
    public var requestMethod: HttpMethod? = null

    public var encodeAsGzip: Boolean = false

    public var keepAlive: Boolean = false

    init {
        this.data = data
    }
    constructor(status: Status, mimeType: String, data: String) : this(data) {
        this.status = status
        this.mimeType = mimeType
    }
}