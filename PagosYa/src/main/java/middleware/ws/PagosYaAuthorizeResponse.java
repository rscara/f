package middleware.ws;

public class PagosYaAuthorizeResponse {

	private boolean ok;
	private String errorDescription;
	private long confirmationNumber;

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public long getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(long confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

}
