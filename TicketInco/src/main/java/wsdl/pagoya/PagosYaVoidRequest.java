
package wsdl.pagoya;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para pagosYaVoidRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="pagosYaVoidRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confirmationNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pagosYaVoidRequest", propOrder = {
    "confirmationNumber"
})
public class PagosYaVoidRequest {

    protected long confirmationNumber;

    /**
     * Obtiene el valor de la propiedad confirmationNumber.
     * 
     */
    public long getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * Define el valor de la propiedad confirmationNumber.
     * 
     */
    public void setConfirmationNumber(long value) {
        this.confirmationNumber = value;
    }

}
