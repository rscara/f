package middleware.ws;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import middleware.DateAdapter;
import middleware.business.EventSchedule;

@XmlJavaTypeAdapter(DateAdapter.class)
public class TicketBookRequest {

	public long eventId;
	public Date eventDate;
	public List<EventSchedule> eventSchedules;

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

	public List<EventSchedule> getEventSchedules() {
		return eventSchedules;
	}

	public void setEventSchedules(List<EventSchedule> eventSchedules) {
		this.eventSchedules = eventSchedules;
	}

}
