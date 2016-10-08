package middleware.ws;

import java.util.List;

public class TicketQueryResponse {
	
	public List<EventSchedule> events;

	public List<EventSchedule> getEvents() {
		return events;
	}

	public void setEvents(List<EventSchedule> events) {
		this.events = events;
	}

}
