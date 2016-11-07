package middleware.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import middleware.dal.EventBook;
import middleware.dal.EventBookVoid;
import middleware.dal.EventConfirmation;
import middleware.dal.EventTicket;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LogSystemResponse {

	private List<EventTicket> totalTickets;
	private List<EventBook> allEventBooks;
	private List<EventConfirmation> allEventConfirmations;
	private List<EventBookVoid> allEventBookVoids;

	public List<EventTicket> getTotalTickets() {
		return totalTickets;
	}

	public void setTotalTickets(List<EventTicket> totalTickets) {
		this.totalTickets = totalTickets;
	}

	public List<EventBook> getAllEventBooks() {
		return allEventBooks;
	}

	public void setAllEventBooks(List<EventBook> allEventBooks) {
		this.allEventBooks = allEventBooks;
	}

	public List<EventConfirmation> getAllEventConfirmations() {
		return allEventConfirmations;
	}

	public void setAllEventConfirmations(List<EventConfirmation> allEventConfirmations) {
		this.allEventConfirmations = allEventConfirmations;
	}

	public List<EventBookVoid> getAllEventBookVoids() {
		return allEventBookVoids;
	}

	public void setAllEventBookVoids(List<EventBookVoid> allEventBookVoids) {
		this.allEventBookVoids = allEventBookVoids;
	}

}
