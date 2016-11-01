
package wsdlgenerated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para pagosYaPaymentRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="pagosYaPaymentRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creditCardNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="expiration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="checkDigit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pagosYaPaymentRequest", propOrder = {
    "creditCardNumber",
    "expiration",
    "checkDigit",
    "amount"
})
public class PagosYaPaymentRequest {

    protected long creditCardNumber;
    protected String expiration;
    protected int checkDigit;
    protected double amount;

    /**
     * Obtiene el valor de la propiedad creditCardNumber.
     * 
     */
    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * Define el valor de la propiedad creditCardNumber.
     * 
     */
    public void setCreditCardNumber(long value) {
        this.creditCardNumber = value;
    }

    /**
     * Obtiene el valor de la propiedad expiration.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpiration() {
        return expiration;
    }

    /**
     * Define el valor de la propiedad expiration.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiration(String value) {
        this.expiration = value;
    }

    /**
     * Obtiene el valor de la propiedad checkDigit.
     * 
     */
    public int getCheckDigit() {
        return checkDigit;
    }

    /**
     * Define el valor de la propiedad checkDigit.
     * 
     */
    public void setCheckDigit(int value) {
        this.checkDigit = value;
    }

    /**
     * Obtiene el valor de la propiedad amount.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Define el valor de la propiedad amount.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

}
