//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.10.05 at 04:46:17 PM ALMT 
//


package kz.gatewaysoap.requester;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kz.gatewaysoap.requester package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kz.gatewaysoap.requester
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMessageDataRequest }
     * 
     */
    public GetMessageDataRequest createGetMessageDataRequest() {
        return new GetMessageDataRequest();
    }

    /**
     * Create an instance of {@link GetResponseInfoResponse }
     * 
     */
    public GetResponseInfoResponse createGetResponseInfoResponse() {
        return new GetResponseInfoResponse();
    }

    /**
     * Create an instance of {@link RequesterOK }
     * 
     */
    public RequesterOK createRequesterOK() {
        return new RequesterOK();
    }

    /**
     * Create an instance of {@link RequesterERROR }
     * 
     */
    public RequesterERROR createRequesterERROR() {
        return new RequesterERROR();
    }

}
