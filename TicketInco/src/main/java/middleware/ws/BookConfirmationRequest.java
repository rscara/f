package middleware.ws;

import java.util.Date;

public class BookConfirmationRequest {

	public long bookId;
	public long paymentModeId;
	public String creditCardNumber;
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

}
