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
public class EventSchedule {

	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date hour;
	private List<EventAvailability> availabilities;

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public List<EventAvailability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<EventAvailability> availabilities) {
		this.availabilities = availabilities;
	}

	@Override
	public String toString() {
		return "EventSchedule [hour=" + hour + ", availabilities=" + availabilities + "]";
	}
	

}
