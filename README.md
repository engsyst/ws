# ws
Examples for building jaxws Web services

## LB2ParserDemo
EXamples for DOM, SAX, JAXB and XSLT processing

## LB3WSServerJAXWSExample
Simple example of jaxws.
Do not need any additional library.
Use **HelloServer.java** for publish Web service.

## LB3WSClientJAXWSExample
Example, how to call remote Web service.

All artifacts already generated as java files using:
```
#>wsimport -s src -keep http://localhost:9090/hello?wsdl -b bindings.xml
```
## LB3WSClientJAXWSExampleWithInheritance
How to make asynchronouse call to remote Web service

## LB3WSServerJAXWSExampleWithInheritance
Show how to be generated WSDL contract with class or interface inheritance

## [LB3WSServerJAXWSExampleAXIS2OnTomcat](https://github.com/engsyst/ws/tree/master/LB3WSServerJAXWSExampleAXIS2OnTomcat)
1. You **MUST** add [Axis2](https://github.com/engsyst/ws/tree/master/JARS/jaxws) facet to this project. Eclipse will add all needed libraries onto WebContent directory of project.
2. Unpack and put onto  [jstl library](https://github.com/engsyst/ws/tree/master/JARS/jstl)
3. Start project "On server"

## [LB3WSServerJAXWSExampleCXFOnTomcat](https://github.com/engsyst/ws/tree/master/LB3WSServerJAXWSExampleCXFOnTomcat)
1. You **MUST** add [CXF]() facet to this project. Eclipse will add all needed libraries onto classpath and WebContent directory of project.
2. Run Project "On Server"

See deployment descriptor: WEB-INF/cxf-servlet.xml

## LB3WSServerJAXWSExampleMetroOnTomcat
1. Unpack and put all .jar files of [jaxws-ri](https://github.com/engsyst/ws/tree/master/JARS/jaxws) onto lib
2. Run Project "On Server"

See deployment descriptor: WEB-INF/sun-jaxws.xml

## LB3WSServerJAXWSExampleOnTomcat
Documentation in progress

## LB3WSServerJAXWSWithInheritanceExample
Documentation in progress

## LB3WSServerRESTExampleOnTomcat

## LB4jaxwsBookService

## LB4jaxwsBookServiceWebClient

## LB5RESTServerJAXRSExample
