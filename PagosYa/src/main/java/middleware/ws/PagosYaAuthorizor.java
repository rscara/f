package middleware.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

@Path("pagosya")
@Component
@Consumes("application/json")
@Produces("application/json")
public interface PagosYaAuthorizor {
	
	@POST
	@Path("/authorizePayment")
	public PagosYaAuthorizeResponse authorizePayment(PagosYaAuthorizeRequest request);
	
	@POST
	@Path("/voidPayment")
	public PagosYaVoidResponse voidPayment(PagosYaVoidRequest request);

}
