package com.thiru.nanohttpd

import com.thiru.MockHttpServer.GET
import com.thiru.MockHttpServer.Models.Response
import com.thiru.MockHttpServer.Models.Status
import com.thiru.MockHttpServer.POST
import org.json.JSONObject

class Controller {

    @SuppressWarnings("unused")
    @GET("/test/1")
    fun sendTestResponse1(pathParamsJson: String, requestBodyJson: String?): Response {
        //Business Logic
        return Response(
            Status.OK,
            "text/json",
            "{\"name\":\"Test Response From Annotation test/1\"}"
        )
    }

    @SuppressWarnings("unused")
    @GET("/test/1")
    fun sendTestResponse1(pathParamsJson: String): Response {
        //Business Logic
        return Response(
            Status.OK,
            "text/json",
            "{\"name\":\"Test Response From Annotation test/1\"}"
        )
    }

    @SuppressWarnings("unused")
    @GET("/test/")
    fun sendTestResponse(): Response {
        //Business Logic
        return Response(
            Status.OK,
            "text/json",
            "{\"name\":\"Test Response From Annotation test/\"}"
        )
    }

    @SuppressWarnings("unused")
    @GET("/test/\\d*")
    fun sendTestResponse(queryParamsJson: String, requestBodyJson: String?): Response {
        //Business Logic
        return Response(
            Status.OK,
            "text/json",
            "{\"name\":\"Test Response From Annotation test/\"}"
        )
    }

    @SuppressWarnings("unused")
    @GET("/test/\\d*")
    fun sendTestResponse(queryParamsJson: String): Response {
        //Business Logic
        return Response(
            Status.OK,
            "text/json",
            "{\"name\":\"Test Response From Annotation test/\"}"
        )
    }

    @SuppressWarnings("unused")
    @POST("/test/\\d*")
    fun sendTestPostResponse(queryParamsJson: String, requestBodyJson: String?): Response {
        //Business Logic
        return Response(
            Status.OK,
            "text/json",
            if (JSONObject(requestBodyJson).has("name")) "{\"message\":\"Request body found : $requestBodyJson\"}" else "{\"message\":\"No request body found\"}"
        )
    }

    @SuppressWarnings("unused")
    @POST("/test/\\d*")
    fun sendTestPostResponse(queryParamsJson: String): Response {
        //Business Logic
        return Response(
            Status.OK,
            "text/json",
            "{\"message\":\"No request body found\"}"
        )
    }
}