package middleware.ws.security;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;

public class ServerPasswordCallback implements CallbackHandler {
 
    public void handle(Callback[] callbacks) throws IOException, 
        UnsupportedCallbackException {
 
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
 
        if (pc.getIdentifier().equals("default")) {
            // set the password on the callback. This will be compared to the
            // password which was sent from the client.
            pc.setPassword("passw0rd");
        }else if (pc.getIdentifier().equals("user0")) {
            // set the password on the callback. This will be compared to the
            // password which was sent from the client.
            pc.setPassword("passw0rd");
        }
    }
 
}