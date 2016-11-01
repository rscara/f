
package wsdlgenerated;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PagosYaPaymentAuthorizorService", targetNamespace = "http://ticketincoesb.com/", wsdlLocation = "file:/home/jhagopian/git/f/TicketInco/src/main/resources/META-INF/paymentValidationService.wsdl")
public class PagosYaPaymentAuthorizorService
    extends Service
{

    private final static URL PAGOSYAPAYMENTAUTHORIZORSERVICE_WSDL_LOCATION;
    private final static WebServiceException PAGOSYAPAYMENTAUTHORIZORSERVICE_EXCEPTION;
    private final static QName PAGOSYAPAYMENTAUTHORIZORSERVICE_QNAME = new QName("http://ticketincoesb.com/", "PagosYaPaymentAuthorizorService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/home/jhagopian/git/f/TicketInco/src/main/resources/META-INF/paymentValidationService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PAGOSYAPAYMENTAUTHORIZORSERVICE_WSDL_LOCATION = url;
        PAGOSYAPAYMENTAUTHORIZORSERVICE_EXCEPTION = e;
    }

    public PagosYaPaymentAuthorizorService() {
        super(__getWsdlLocation(), PAGOSYAPAYMENTAUTHORIZORSERVICE_QNAME);
    }

    public PagosYaPaymentAuthorizorService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PAGOSYAPAYMENTAUTHORIZORSERVICE_QNAME, features);
    }

    public PagosYaPaymentAuthorizorService(URL wsdlLocation) {
        super(wsdlLocation, PAGOSYAPAYMENTAUTHORIZORSERVICE_QNAME);
    }

    public PagosYaPaymentAuthorizorService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PAGOSYAPAYMENTAUTHORIZORSERVICE_QNAME, features);
    }

    public PagosYaPaymentAuthorizorService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PagosYaPaymentAuthorizorService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PagosYaPaymentAuthorizor
     */
    @WebEndpoint(name = "PagosYaPaymentAuthorizorPort")
    public PagosYaPaymentAuthorizor getPagosYaPaymentAuthorizorPort() {
        return super.getPort(new QName("http://ticketincoesb.com/", "PagosYaPaymentAuthorizorPort"), PagosYaPaymentAuthorizor.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PagosYaPaymentAuthorizor
     */
    @WebEndpoint(name = "PagosYaPaymentAuthorizorPort")
    public PagosYaPaymentAuthorizor getPagosYaPaymentAuthorizorPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ticketincoesb.com/", "PagosYaPaymentAuthorizorPort"), PagosYaPaymentAuthorizor.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PAGOSYAPAYMENTAUTHORIZORSERVICE_EXCEPTION!= null) {
            throw PAGOSYAPAYMENTAUTHORIZORSERVICE_EXCEPTION;
        }
        return PAGOSYAPAYMENTAUTHORIZORSERVICE_WSDL_LOCATION;
    }

}
