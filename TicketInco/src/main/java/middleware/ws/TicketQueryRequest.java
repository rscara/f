package middleware.ws;

import java.util.Date;

public class TicketQueryRequest {

	private int eventID;
	private Date eventDate;

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

}
