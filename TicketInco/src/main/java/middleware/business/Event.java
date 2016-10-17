package middleware.business;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import middleware.DateAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {

	public long eventId;
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date eventDate;
	public List<EventSchedule> eventSchedules;

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public List<EventSchedule> getEventSchedules() {
		return eventSchedules;
	}

	public void setEventSchedules(List<EventSchedule> eventSchedules) {
		this.eventSchedules = eventSchedules;
	}

}
