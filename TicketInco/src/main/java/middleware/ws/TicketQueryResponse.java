package middleware.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import middleware.business.Event;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketQueryResponse {
	
	public Event event;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
