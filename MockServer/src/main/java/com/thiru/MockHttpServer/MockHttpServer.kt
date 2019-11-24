package com.thiru.MockHttpServer

import com.fasterxml.jackson.databind.ObjectMapper
import fi.iki.elonen.NanoHTTPD


class MockHttpServer(mockServerController: Class<out Any>) : NanoHTTPD(8080) {
    var controllerClassObject: Class<out Any>

    init {
        this.controllerClassObject = mockServerController
        AnnotationUtils.parseAnnotation(this.controllerClassObject)
    }

    override fun serve(session: IHTTPSession?): Response {
        return processRequest(
            session?.method?.name!!,
            session?.uri.toString(),
            session?.parameters!!
        )
    }

    private fun processRequest(
        requestMethod: String,
        uri: String,
        queryParams: Map<String, List<String>>
    ): Response {
        var result =
            newFixedLengthResponse(Response.Status.OK, "text/html", "{\"result\":\"Not found\"}")
        val annotatedMethods = AnnotationUtils.getMethods(requestMethod, uri)
        for (declaredMethod in annotatedMethods) {
            try {
                val json: String = ObjectMapper().writeValueAsString(queryParams)
                val response =
                    declaredMethod.method.invoke(
                        controllerClassObject.newInstance(),
                        json
                    ) as com.thiru.MockHttpServer.Models.Response

                result = newFixedLengthResponse(
                    Response.Status.lookup(response?.status?.requestStatus!!),
                    response.mimeType,
                    response.data
                )
                break
            } catch (castException: ClassCastException) {
                System.out.println(castException.message)
            } catch (illegalArgException: IllegalArgumentException) {
                System.out.println(illegalArgException.message)
            }

        }

        return result
    }

}