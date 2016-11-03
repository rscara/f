
package wsdl.pagoya;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wsdl.pagoya package. 
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

    private final static QName _AuthorizePaymentResponse_QNAME = new QName("http://ticketincoesb.com/", "authorizePaymentResponse");
    private final static QName _PagosYaPaymentRequest_QNAME = new QName("http://ticketincoesb.com/", "pagosYaPaymentRequest");
    private final static QName _AuthorizePayment_QNAME = new QName("http://ticketincoesb.com/", "authorizePayment");
    private final static QName _VoidPayment_QNAME = new QName("http://ticketincoesb.com/", "voidPayment");
    private final static QName _PagosYaVoidRequest_QNAME = new QName("http://ticketincoesb.com/", "pagosYaVoidRequest");
    private final static QName _PagosYaVoidResponse_QNAME = new QName("http://ticketincoesb.com/", "pagosYaVoidResponse");
    private final static QName _VoidPaymentResponse_QNAME = new QName("http://ticketincoesb.com/", "voidPaymentResponse");
    private final static QName _PagosYaPaymentResponse_QNAME = new QName("http://ticketincoesb.com/", "pagosYaPaymentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wsdl.pagoya
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthorizePaymentResponse }
     * 
     */
    public AuthorizePaymentResponse createAuthorizePaymentResponse() {
        return new AuthorizePaymentResponse();
    }

    /**
     * Create an instance of {@link PagosYaPaymentRequest }
     * 
     */
    public PagosYaPaymentRequest createPagosYaPaymentRequest() {
        return new PagosYaPaymentRequest();
    }

    /**
     * Create an instance of {@link VoidPaymentResponse }
     * 
     */
    public VoidPaymentResponse createVoidPaymentResponse() {
        return new VoidPaymentResponse();
    }

    /**
     * Create an instance of {@link PagosYaVoidResponse }
     * 
     */
    public PagosYaVoidResponse createPagosYaVoidResponse() {
        return new PagosYaVoidResponse();
    }

    /**
     * Create an instance of {@link PagosYaPaymentResponse }
     * 
     */
    public PagosYaPaymentResponse createPagosYaPaymentResponse() {
        return new PagosYaPaymentResponse();
    }

    /**
     * Create an instance of {@link VoidPayment }
     * 
     */
    public VoidPayment createVoidPayment() {
        return new VoidPayment();
    }

    /**
     * Create an instance of {@link AuthorizePayment }
     * 
     */
    public AuthorizePayment createAuthorizePayment() {
        return new AuthorizePayment();
    }

    /**
     * Create an instance of {@link PagosYaVoidRequest }
     * 
     */
    public PagosYaVoidRequest createPagosYaVoidRequest() {
        return new PagosYaVoidRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthorizePaymentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ticketincoesb.com/", name = "authorizePaymentResponse")
    public JAXBElement<AuthorizePaymentResponse> createAuthorizePaymentResponse(AuthorizePaymentResponse value) {
        return new JAXBElement<AuthorizePaymentResponse>(_AuthorizePaymentResponse_QNAME, AuthorizePaymentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagosYaPaymentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ticketincoesb.com/", name = "pagosYaPaymentRequest")
    public JAXBElement<PagosYaPaymentRequest> createPagosYaPaymentRequest(PagosYaPaymentRequest value) {
        return new JAXBElement<PagosYaPaymentRequest>(_PagosYaPaymentRequest_QNAME, PagosYaPaymentRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthorizePayment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ticketincoesb.com/", name = "authorizePayment")
    public JAXBElement<AuthorizePayment> createAuthorizePayment(AuthorizePayment value) {
        return new JAXBElement<AuthorizePayment>(_AuthorizePayment_QNAME, AuthorizePayment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoidPayment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ticketincoesb.com/", name = "voidPayment")
    public JAXBElement<VoidPayment> createVoidPayment(VoidPayment value) {
        return new JAXBElement<VoidPayment>(_VoidPayment_QNAME, VoidPayment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagosYaVoidRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ticketincoesb.com/", name = "pagosYaVoidRequest")
    public JAXBElement<PagosYaVoidRequest> createPagosYaVoidRequest(PagosYaVoidRequest value) {
        return new JAXBElement<PagosYaVoidRequest>(_PagosYaVoidRequest_QNAME, PagosYaVoidRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagosYaVoidResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ticketincoesb.com/", name = "pagosYaVoidResponse")
    public JAXBElement<PagosYaVoidResponse> createPagosYaVoidResponse(PagosYaVoidResponse value) {
        return new JAXBElement<PagosYaVoidResponse>(_PagosYaVoidResponse_QNAME, PagosYaVoidResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoidPaymentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ticketincoesb.com/", name = "voidPaymentResponse")
    public JAXBElement<VoidPaymentResponse> createVoidPaymentResponse(VoidPaymentResponse value) {
        return new JAXBElement<VoidPaymentResponse>(_VoidPaymentResponse_QNAME, VoidPaymentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagosYaPaymentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ticketincoesb.com/", name = "pagosYaPaymentResponse")
    public JAXBElement<PagosYaPaymentResponse> createPagosYaPaymentResponse(PagosYaPaymentResponse value) {
        return new JAXBElement<PagosYaPaymentResponse>(_PagosYaPaymentResponse_QNAME, PagosYaPaymentResponse.class, null, value);
    }

}
