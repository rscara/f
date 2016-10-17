package middleware.dal;

import java.util.Date;

public class EventConfirmation {

	public long eventId;
	public Date eventDate;
	public Date hour;
	public String sector;
	public int quantity;
	public long confirmationId;

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

	public long getConfirmationId() {
		return confirmationId;
	}

	public void setConfirmationId(long confirmationId) {
		this.confirmationId = confirmationId;
	}

}
