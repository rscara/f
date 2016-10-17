package middleware.ws.security;

import org.apache.cxf.interceptor.security.AuthenticationException;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.message.token.UsernameToken;
import org.apache.ws.security.validate.UsernameTokenValidator;

public class CredentialValidator extends UsernameTokenValidator { 

	@Override 
	protected void verifyPlaintextPassword(UsernameToken usernameToken, RequestData data) throws WSSecurityException { 
		try { 
			//Connect to your data source and validate the user credentials
			System.out.println(usernameToken);
			System.out.println(data);
			
			
		} catch (AuthenticationException aex) { 
			throw new WSSecurityException(WSSecurityException.FAILED_AUTHENTICATION); 
		} 
	} 
	
}
