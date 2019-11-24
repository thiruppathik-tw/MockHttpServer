package com.thiru.MockHttpServer.Models;

/**
     * HTTP Request methods, with the ability to decode a <code>String</code>
     * back to its enum value.
     */
    public enum HttpMethod {
        GET,
        PUT,
        POST,
        DELETE,
        HEAD,
        OPTIONS,
        TRACE,
        CONNECT,
        PATCH,
        PROPFIND,
        PROPPATCH,
        MKCOL,
        MOVE,
        COPY,
        LOCK,
        UNLOCK
    }