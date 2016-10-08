package middleware.ws;

public class BookQueryResponse {
	
	//0 cancelado
	//1 pendiente
	//2 confirmado
	private int bookState;

	public int getBookState() {
		return bookState;
	}

	public void setBookState(int bookState) {
		this.bookState = bookState;
	}
}
