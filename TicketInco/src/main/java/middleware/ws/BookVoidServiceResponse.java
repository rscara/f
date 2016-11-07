package middleware.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BookVoidServiceResponse {
	
	private int voidPaymentConfirmationId;

	public int getVoidPaymentConfirmationId() {
		return voidPaymentConfirmationId;
	}

	public void setVoidPaymentConfirmationId(int voidPaymentConfirmationId) {
		this.voidPaymentConfirmationId = voidPaymentConfirmationId;
	}
}
