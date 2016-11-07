package middleware.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BookVoidRequest {

	private long paymentConfirmationId;
	private long paymentMode;
	

	public long getPaymentConfirmationId() {
		return paymentConfirmationId;
	}

	public void setPaymentConfirmationId(long paymentConfirmationId) {
		this.paymentConfirmationId = paymentConfirmationId;
	}

	public long getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(long paymentMode) {
		this.paymentMode = paymentMode;
	}

}
