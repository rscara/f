package middleware.dal;

import java.util.Date;
import java.util.List;

public class MemoryEventDatasource implements EventDatasource {
	
	private List<Event> events;

	@Override
	public Event getEvent(long id, Date date) {
		
		for (Event event : events) {
			if (event.getId()==id && event.getDate().equals(date))
				return event;
		}
		return null;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
