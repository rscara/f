package middleware.ws;

public class PagosYaVoidResponse {

	private long voidConfirmationId;
	private boolean error;
	private String errorDescription;

	public long getVoidConfirmationId() {
		return voidConfirmationId;
	}

	public void setVoidConfirmationId(long voidConfirmationId) {
		this.voidConfirmationId = voidConfirmationId;
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
