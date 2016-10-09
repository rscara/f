package middleware.dal;

import java.util.List;

public class MemoryEventDatasource implements EventDatasource {
	
	private List<Event> events;

	@Override
	public Event getEvent(long id) {
		
		for (Event event : events) {
			if (event.getId()==id)
				return event;
		}
		return null;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
