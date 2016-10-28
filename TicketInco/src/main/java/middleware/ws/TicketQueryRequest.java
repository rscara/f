package middleware.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import middleware.DateAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketQueryRequest {

	private long eventID;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date eventDate;

	public long getEventID() {
		return eventID;
	}

	public void setEventID(long eventID) {
		this.eventID = eventID;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		return "TicketQueryRequest [eventID=" + eventID + ", eventDate=" + eventDate + "]";
	}

}
