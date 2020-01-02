# MockHttpServer
MockHttpServer is a java library on top of NanoHttpd to create local webserver. NanoHttpd works as a local webserver, MockHttpServer serves a layer where we can customize the responses for the Http Requests.

Steps to Integrate :
1. Add MockHttpServer jar as a dependency in your project.
2. Create a controller class as like Controller.kt in the sample app. Controller is the class which handles the requests by using the annotated methods.
3. MockHttpServer will look for the matching annotations in Controller class while processing the webrequests. Post and Put menthods should have 2 string method parameters to receive path parameters and request Body from the webrequest.
4. Other request methods should have 1 string method parameter to receive path parameter from the webrequest.
5. Create an instance of MockHttpServer and pass the class instance of the controller.
         
         //Kotlin implementation
         private val mockHttpServer: MockHttpServer = MockHttpServer(Controller::class.java)
