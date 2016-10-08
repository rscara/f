package middleware.ws;

import java.util.Date;
import java.util.List;

public class TicketBookRequest {

	public int eventId;
	public Date eventDate;
	public List<EventAvailability> availability;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public List<EventAvailability> getAvailability() {
		return availability;
	}

	public void setAvailability(List<EventAvailability> availability) {
		this.availability = availability;
	}

}
