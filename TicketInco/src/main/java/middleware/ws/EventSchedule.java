package middleware.ws;

import java.util.Date;
import java.util.List;

public class EventSchedule {
	
	public Date eventDate;
	public List<EventAvailability> availability;

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
