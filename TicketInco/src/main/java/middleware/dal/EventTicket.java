package middleware.dal;

import java.util.Date;

public class EventTicket {
	
	public long eventId;
	public Date eventDate;
	public Date hour;
	public String sector;
	public double price;
	public int quantity;

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		return "EventTicket [eventId=" + eventId + ", eventDate=" + eventDate + ", hour=" + hour + ", sector=" + sector
				+ ", price=" + price + ", quantity=" + quantity + "]";
	}

}
