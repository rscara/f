package middleware.ws;

public class PagosYaAuthorizeResponse {

	private long confirmationId;
	private boolean error;
	private String errorDescription;

	public long getConfirmationId() {
		return confirmationId;
	}

	public void setConfirmationId(long confirmationId) {
		this.confirmationId = confirmationId;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

}
