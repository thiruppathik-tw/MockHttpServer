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
        val requestBody = HashMap<String, String>()
        val requestMethod = session!!.method.name
        if (requestMethod.equals("PUT") || requestMethod.equals("POST"))
            session.parseBody(requestBody)
        return processRequest(
            requestMethod,
            session.uri.toString(),
            session.parameters!!,
            requestBody
        )
    }

    private fun processRequest(
        requestMethod: String,
        uri: String,
        pathParams: Map<String, List<String>>,
        requestBody: Map<String, String>
    ): Response {
        var result =
            newFixedLengthResponse(Response.Status.OK, "text/html", "{\"result\":\"Not found\"}")
        val annotatedMethods = AnnotationUtils.getMethods(requestMethod, uri)
        for (declaredMethod in annotatedMethods) {
            try {
                val pathParamsjson: String = ObjectMapper().writeValueAsString(pathParams)
                val requestBodyJson: String? = requestBody.get("postData")
                var response: com.thiru.MockHttpServer.Models.Response
                if (requestMethod.equals("PUT") || requestMethod.equals("POST")) {
                    response = declaredMethod.method.invoke(
                        controllerClassObject.newInstance(),
                        pathParamsjson,
                        requestBodyJson
                    ) as com.thiru.MockHttpServer.Models.Response
                } else {
                    response = declaredMethod.method.invoke(
                        controllerClassObject.newInstance(),
                        pathParamsjson
                    ) as com.thiru.MockHttpServer.Models.Response
                }

                result = newFixedLengthResponse(
                    Response.Status.lookup(response.status?.requestStatus!!),
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