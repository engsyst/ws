package ua.nure.itech.jaxws.handler;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

public class HelloHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
		final SOAPMessage msg = context.getMessage();
		if ((boolean) context.get(context.MESSAGE_OUTBOUND_PROPERTY)) {
			return handleOutboundMessage(msg);
        }
		return handleInboundMessage(msg);
    }

	private boolean handleOutboundMessage(SOAPMessage msg) {
		System.out.println(this.getClass().getCanonicalName() + " OUTBOUND Message");
		return true;
	}

	private boolean handleInboundMessage(SOAPMessage msg) {
		System.out.println(this.getClass().getCanonicalName() + " INBOUND Message");
        try {
            SOAPHeader header = msg.getSOAPHeader();

            Iterator<?> it = header.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
            if (!it.hasNext()) {
                generateSOAPErrMessage(msg, "No recuired header info");
                return false;
            }

            SOAPHeaderElement el = (SOAPHeaderElement) it.next();
            String token = el.getTextContent();

            if (!isValidToken(token)) {
                generateSOAPErrMessage(msg, "No recuired header info");
                return false;
            }
        } catch (SOAPException e) {
            generateSOAPErrMessage(msg, e.getMessage());
        }
        return true;
    }

    private boolean isValidToken(String token) {
        if (token != null && token.trim().length() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault soapFault = soapBody.addFault();
            soapFault.setFaultString(reason);
            soapFault.setFaultCode(reason);
            throw new SOAPFaultException(soapFault);
        } catch (SOAPException e) {
        }
    }

}
