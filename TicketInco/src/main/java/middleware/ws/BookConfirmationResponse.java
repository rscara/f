package middleware.ws;

import java.util.List;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BookConfirmationResponse {

	private long bookId;
	@XmlMimeType("application/octet-stream")
	private List<DataHandler> images;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public List<DataHandler> getImages() {
		return images;
	}

	public void setImages(List<DataHandler> images) {
		this.images = images;
	}

}
