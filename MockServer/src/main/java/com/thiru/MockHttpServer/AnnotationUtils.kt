package com.thiru.MockHttpServer

import com.thiru.MockHttpServer.Models.Method

class AnnotationUtils {
    companion object {
        var annotations: MutableMap<String, ArrayList<Method>> =
            HashMap()

        fun parseAnnotation(controllerClassObject: Class<out Any>) {
            for (declaredMethod in controllerClassObject.declaredMethods) {
                for (annotation in declaredMethod.declaredAnnotations) {
                    cacheAnnotation(annotation, declaredMethod)
                }
            }
        }

        fun cacheAnnotation(
            annotation: Annotation,
            declaredMethod: java.lang.reflect.Method
        ) {
            if (annotation is GET) {
                addMethods(annotation.uri, "GET", declaredMethod)
            } else if (annotation is PUT) {
                addMethods(annotation.uri, "PUT", declaredMethod)
            } else if (annotation is POST) {
                addMethods(annotation.uri, "POST", declaredMethod)
            } else if (annotation is DELETE) {
                addMethods(annotation.uri, "DELETE", declaredMethod)
            } else if (annotation is HEAD) {
                addMethods(annotation.uri, "HEAD", declaredMethod)
            } else if (annotation is OPTIONS) {
                addMethods(annotation.uri, "OPTIONS", declaredMethod)
            } else if (annotation is TRACE) {
                addMethods(annotation.uri, "TRACE", declaredMethod)
            } else if (annotation is CONNECT) {
                addMethods(annotation.uri, "CONNECT", declaredMethod)
            } else if (annotation is PATCH) {
                addMethods(annotation.uri, "PATCH", declaredMethod)
            } else if (annotation is PROPFIND) {
                addMethods(annotation.uri, "PROPFIND", declaredMethod)
            } else if (annotation is PROPPATCH) {
                addMethods(annotation.uri, "PROPPATCH", declaredMethod)
            } else if (annotation is MKCOL) {
                addMethods(annotation.uri, "MKCOL", declaredMethod)
            } else if (annotation is MOVE) {
                addMethods(annotation.uri, "MOVE", declaredMethod)
            } else if (annotation is COPY) {
                addMethods(annotation.uri, "COPY", declaredMethod)
            } else if (annotation is LOCK) {
                addMethods(annotation.uri, "LOCK", declaredMethod)
            } else if (annotation is UNLOCK) {
                addMethods(annotation.uri, "UNLOCK", declaredMethod)
            } else {

            }
        }

        fun addMethods(
            uri: String?,
            requestMethod: String,
            declaredMethod: java.lang.reflect.Method
        ) {
            var methods = this.annotations.get(uri!!)
            if (methods != null) {
                methods.add(
                    Method(
                        requestMethod,
                        declaredMethod
                    )
                )
            } else {
                methods = arrayListOf(
                    Method(
                        requestMethod,
                        declaredMethod
                    )
                )
            }
            annotations.put(uri!!, methods)
        }

        fun getMethods(
            requestMethod: String,
            uri: String
        ): List<Method> {
            return this.annotations.get(uri)!!.filter { it.requestMethod == requestMethod }
        }
    }
}