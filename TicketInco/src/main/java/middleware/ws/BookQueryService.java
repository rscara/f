package middleware.ws;

public interface BookQueryService {
	
	public BookQueryRequest queryBook(BookQueryResponse bookQueryResponse) throws Exception;

}
