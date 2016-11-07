package middleware.dal;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import middleware.DateAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventConfirmation {


	private long eventId;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date eventDate;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date hour;
	private String sector;
	private int quantity;
	private long bookId;
	private double price;
	private long paymentConfirmationId;
	private long paymentMode;

	public EventConfirmation(long eventId, Date eventDate, Date hour, String sector, int quantity, long bookId, double price, 
			long paymentConfirmationId, long paymentMode) {
		super();
		this.eventId = eventId;
		this.eventDate = eventDate;
		this.hour = hour;
		this.sector = sector;
		this.quantity = quantity;
		this.bookId = bookId;
		this.price = price;
		this.paymentConfirmationId = paymentConfirmationId;
		this.paymentMode = paymentMode;
	}
	
	public EventConfirmation() {

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

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getPaymentConfirmationId() {
		return paymentConfirmationId;
	}

	public void setPaymentConfirmationId(long paymentConfirmationId) {
		this.paymentConfirmationId = paymentConfirmationId;
	}

	public long getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(long paymentMode) {
		this.paymentMode = paymentMode;
	}

	@Override
	public String toString() {
		return "EventConfirmation [eventId=" + eventId + ", eventDate=" + eventDate + ", hour=" + hour + ", sector="
				+ sector + ", quantity=" + quantity + ", bookId=" + bookId + ", price=" + price
				+ ", paymentConfirmationId=" + paymentConfirmationId + ", paymentMode=" + paymentMode + "]";
	}

}
