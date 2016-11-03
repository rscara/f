package middleware.dal;

import java.util.Date;

public class EventConfirmation {


	public long eventId;
	public Date eventDate;
	public Date hour;
	public String sector;
	public int quantity;
	public long bookId;
	public double price;

	public EventConfirmation(long eventId, Date eventDate, Date hour, String sector, int quantity, long bookId, double price) {
		super();
		this.eventId = eventId;
		this.eventDate = eventDate;
		this.hour = hour;
		this.sector = sector;
		this.quantity = quantity;
		this.bookId = bookId;
		this.price = price;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "EventConfirmation [eventId=" + eventId + ", eventDate=" + eventDate + ", hour=" + hour + ", sector="
				+ sector + ", quantity=" + quantity + ", bookId=" + bookId + ", price=" + price + "]";
	}

}
