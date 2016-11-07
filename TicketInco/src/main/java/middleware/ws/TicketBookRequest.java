package middleware.ws;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import middleware.DateAdapter;
import middleware.business.EventSchedule;

@XmlJavaTypeAdapter(DateAdapter.class)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketBookRequest {

	public long eventId;
	@XmlJavaTypeAdapter(DateAdapter.class)
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

	@Override
	public String toString() {
		return "TicketBookRequest [eventId=" + eventId + ", eventDate=" + eventDate + ", eventSchedules="
				+ eventSchedules + "]";
	}

}
