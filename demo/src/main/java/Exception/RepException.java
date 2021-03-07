package Exception;

import javax.xml.ws.WebFault;

@WebFault(name="RepException", targetNamespace = "")
public class RepException extends Exception{
    public RepException(){
        super("The method is not yet supported.");
    }
}
