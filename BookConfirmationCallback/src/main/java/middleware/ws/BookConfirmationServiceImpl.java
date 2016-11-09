package middleware.ws;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

@Addressing(required=true)
@WebService(endpointInterface = "middleware.ws.BookConfirmationService", name = "BookConfirmationService" 
	, targetNamespace = "http://ticketinco.com/")
public class BookConfirmationServiceImpl implements BookConfirmationService {

	private static final Logger logger = LoggerFactory.getLogger(BookConfirmationServiceImpl.class);

	private XStream xstream;
	
	public BookConfirmationServiceImpl() {
		xstream = new XStream();
		xstream.autodetectAnnotations(true);
	}
	
	@Override
	public void confirmBook(BookConfirmationResponse request) {
		
		logger.debug("Request:" + request.toString());
		
		if (request.isError()) {
			logger.debug("ERROR:" + request.getErrorDescription());
		}
		else {
			try {
				int imageIndex = 0;
				for (DataHandler imageHandler : request.getImages()) {
					InputStream is = imageHandler.getInputStream();
					
					File file = new File("entradas/Entrada-" + request.getBookId() + "-" + imageIndex++ + ".png");
					FileOutputStream fos = new FileOutputStream(file);
					BufferedOutputStream dest = new BufferedOutputStream(fos, 2048);
					
					byte data[] = new byte[2048];
					int count;
					while ((count = is.read(data, 0, 2048)) != -1) {
						dest.write(data, 0, count);
					}
					
					dest.flush();
					dest.close();		
				}
			} catch (Exception e) {
				logger.error("Error grabando imagenes", e);
			}			
		}
		
	}

}
