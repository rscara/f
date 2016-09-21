package service;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import middleware.ws.RedStrawberryOrder;

@WebService(endpointInterface = "service.ItemsListService", name = "ItemsListService", targetNamespace = "http://redstrawberry.com/")
public class ItemsListServiceImpl implements ItemsListService {

	private static final Logger logger = Logger.getLogger(ItemsListServiceImpl.class);
	@Override
	public void order(RedStrawberryOrder order) {
		logger.info("llego orden:" + order);			
	}

}
