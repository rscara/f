package middleware.dal;

import java.util.Date;

public interface EventDatasource {
	
	public Event getEvent(long id, Date date);

}
