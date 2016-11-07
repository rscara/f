package middleware.dal;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import middleware.DateAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventBookVoid {

	private long eventId;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date eventDate;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date hour;
	private String sector;
	private int quantity;
	private double price;
	private long bookId;
	private long paymentConfirmationId;
	private int voidPaymentConfirmationId;
	private long paymentMode;
	
	public EventBookVoid(long eventId, Date eventDate, Date hour, String sector, int quantity, double price,
			long bookId, int voidPaymentConfirmationId, long paymentMode, long paymentConfirmationId) {
		super();
		this.eventId = eventId;
		this.eventDate = eventDate;
		this.hour = hour;
		this.sector = sector;
		this.quantity = quantity;
		this.price = price;
		this.bookId = bookId;
		this.voidPaymentConfirmationId = voidPaymentConfirmationId;
		this.paymentMode = paymentMode;
		this.paymentConfirmationId = paymentConfirmationId;
	}
	
	
	public EventBookVoid() {
	
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


	public String getSector() {
		return sector;
	}


	public void setSector(String sector) {
		this.sector = sector;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public long getBookId() {
		return bookId;
	}


	public void setBookId(long bookId) {
		this.bookId = bookId;
	}


	public int getVoidPaymentConfirmationId() {
		return voidPaymentConfirmationId;
	}


	public void setVoidPaymentConfirmationId(int voidPaymentConfirmationId) {
		this.voidPaymentConfirmationId = voidPaymentConfirmationId;
	}


	public long getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(long paymentMode) {
		this.paymentMode = paymentMode;
	}


	public long getPaymentConfirmationId() {
		return paymentConfirmationId;
	}


	public void setPaymentConfirmationId(long paymentConfirmationId) {
		this.paymentConfirmationId = paymentConfirmationId;
	}

}
