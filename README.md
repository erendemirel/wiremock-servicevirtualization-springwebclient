This is a sample test project to demonstrate service virtualization and API mocking with WireMock and Spring WebClient. *http://dummy.restapiexample.com/* demo site's */api/v1/employees* service is mocked using WireMock and tested with requests sent through client built using Spring WebClient

#### Running the Tests 
- There's only one test class, MyClientTest.java. It can be run under src/test/java/apimockingtest/  
  <br/>
### Notes
https://github.com/JensPiegsa/wiremock-extension is used to enable Junit 5 annotations to be used together with WireMock
