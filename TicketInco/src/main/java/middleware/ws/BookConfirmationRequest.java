package middleware.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import middleware.DateAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BookConfirmationRequest {

	public long bookId;
	// 1 -> pagosYa
	// 2 -> local
	public long paymentModeId;
	public String creditCardNumber;
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date creditCardExpiration;
	public int creditCardCheckDigit;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(long paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Date getCreditCardExpiration() {
		return creditCardExpiration;
	}

	public void setCreditCardExpiration(Date creditCardExpiration) {
		this.creditCardExpiration = creditCardExpiration;
	}

	public int getCreditCardCheckDigit() {
		return creditCardCheckDigit;
	}

	public void setCreditCardCheckDigit(int creditCardCheckDigit) {
		this.creditCardCheckDigit = creditCardCheckDigit;
	}

	@Override
	public String toString() {
		return "BookConfirmationRequest [bookId=" + bookId + ", paymentModeId=" + paymentModeId + ", creditCardNumber="
				+ creditCardNumber + ", creditCardExpiration=" + creditCardExpiration + ", creditCardCheckDigit="
				+ creditCardCheckDigit + "]";
	}

}
