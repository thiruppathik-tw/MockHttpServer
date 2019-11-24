package com.thiru.nanohttpd

import com.thiru.MockHttpServer.GET
import com.thiru.MockHttpServer.Models.Response
import com.thiru.MockHttpServer.Models.Status
import com.thiru.MockHttpServer.POST

class Controller {

    @GET("/test/1")
    fun sendTestResponse1(queryParameters: String): Response {
        return Response(
            Status.OK,
            "text/json",
            "{\"name\":\"Test Response From Annotation test/1\"}"
        )
    }

    @GET("/test/")
    fun sendTestResponse(): Response {
        return Response(
            Status.OK,
            "text/json",
            "{\"name\":\"Test Response From Annotation test/\"}"
        )
    }

    @GET("/test/\\d*")
    fun sendTestResponse(queryParameters: String): Response {
        //Business Logic
        return Response(
            Status.OK,
            "text/json",
            "{\"name\":\"Test Response From Annotation test/\"}"
        )
    }

    @POST("/test/\\d*")
    fun sendTestPostResponse(queryParameters: String): Response {
        //Business Logic
        return Response(
            Status.OK,
            "text/json",
            "{\"name\":\"Test Response From Annotation test/\"}"
        )
    }
}