package middleware.dal;

import java.util.Date;

public class Book {

	private long id;
	// 0 -> cancelado
	// 1 -> pendiente
	// 2 -> confirmado
	private int state;
	private long eventId;
	private Date eventDate;
	
	public Book() {
	
	}
	
	public Book(long id, int state, long eventId, Date eventDate) {
		this.id = id;
		this.state = state;
		this.eventId = eventId;
		this.eventDate = eventDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

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

}
