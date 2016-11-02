
package wsdlgenerated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wsdlgenerated package. 
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

    private final static QName _LocalPaymentResponse_QNAME = new QName("http://ticketincoesb.com/", "localPaymentResponse");
    private final static QName _AuthorizePaymentResponse_QNAME = new QName("http://ticketincoesb.com/", "authorizePaymentResponse");
    private final static QName _LocalPaymentRequest_QNAME = new QName("http://ticketincoesb.com/", "localPaymentRequest");
    private final static QName _AuthorizePayment_QNAME = new QName("http://ticketincoesb.com/", "authorizePayment");
    private final static QName _VoidPayment_QNAME = new QName("http://ticketincoesb.com/", "voidPayment");
    private final static QName _PagosYaVoidRequest_QNAME = new QName("http://ticketincoesb.com/", "pagosYaVoidRequest");
    private final static QName _VoidPaymentResponse_QNAME = new QName("http://ticketincoesb.com/", "voidPaymentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wsdlgenerated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LocalPaymentRequest }
     * 
     */
    public LocalPaymentRequest createLocalPaymentRequest() {
        return new LocalPaymentRequest();
    }

    /**
     * Create an instance of {@link AuthorizePaymentResponse }
     * 
     */
    public AuthorizePaymentResponse createAuthorizePaymentResponse() {
        return new AuthorizePaymentResponse();
    }

    /**
     * Create an instance of {@link LocalPaymentResponse }
     * 
     */
    public LocalPaymentResponse createLocalPaymentResponse() {
        return new LocalPaymentResponse();
    }

    /**
     * Create an instance of {@link VoidPaymentResponse }
     * 
     */
    public VoidPaymentResponse createVoidPaymentResponse() {
        return new VoidPaymentResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalPaymentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ticketincoesb.com/", name = "localPaymentResponse")
    public JAXBElement<LocalPaymentResponse> createLocalPaymentResponse(LocalPaymentResponse value) {
        return new JAXBElement<LocalPaymentResponse>(_LocalPaymentResponse_QNAME, LocalPaymentResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalPaymentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ticketincoesb.com/", name = "localPaymentRequest")
    public JAXBElement<LocalPaymentRequest> createLocalPaymentRequest(LocalPaymentRequest value) {
        return new JAXBElement<LocalPaymentRequest>(_LocalPaymentRequest_QNAME, LocalPaymentRequest.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link VoidPaymentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ticketincoesb.com/", name = "voidPaymentResponse")
    public JAXBElement<VoidPaymentResponse> createVoidPaymentResponse(VoidPaymentResponse value) {
        return new JAXBElement<VoidPaymentResponse>(_VoidPaymentResponse_QNAME, VoidPaymentResponse.class, null, value);
    }

}
