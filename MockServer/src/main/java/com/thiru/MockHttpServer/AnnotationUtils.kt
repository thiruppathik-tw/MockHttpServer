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
            } else if (annotation is PATCH) {
                addMethods(annotation.uri, "PATCH", declaredMethod)
            }
        }

        fun addMethods(
            uri: String,
            requestMethod: String,
            declaredMethod: java.lang.reflect.Method
        ) {
            var methods = this.annotations.get(uri)
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
            annotations.put(uri, methods)
        }

        fun getMethods(
            requestMethod: String,
            uri: String
        ): List<Method> {
            val annotatedMethods =
                this.annotations.filter { it.key.toRegex().matches(uri) }.values.flatten()
                    .filter { it.requestMethod.equals(requestMethod) }

            var methodsMatchingSignature: List<Method>? = null

            if (requestMethod.equals("PUT") || requestMethod.equals("POST")) {
                methodsMatchingSignature = annotatedMethods
                    .filter {
                        it.method.parameterTypes.size == 2
                                && it.method.parameterTypes[0].isInstance(String::class.java)
                                && it.method.parameterTypes[1].isInstance(String::class.java)
                    }
            } else {
                methodsMatchingSignature = annotatedMethods
                    .filter {
                        it.method.parameterTypes.size == 1 && it.method.parameterTypes[0] == String::class.java
                    }
            }
            return methodsMatchingSignature
        }
    }
}