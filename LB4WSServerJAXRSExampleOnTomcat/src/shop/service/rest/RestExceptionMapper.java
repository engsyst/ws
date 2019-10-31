package shop.service.rest;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		// ���
		System.out.println("toResponse() exception " + exception.getMessage());
		exception.printStackTrace();
		
		// ������� ������ ������ Response
		return Response.status(getStatusCode(exception)).entity(getEntity(exception)).build();
	}

	/**
	 * ���������� ����������� HTTP status code ��� ����������.
	 */
	private int getStatusCode(Throwable exception) {
		if (exception instanceof WebApplicationException) {
			return ((WebApplicationException) exception).getResponse().getStatus();
		}

		return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
	}

	/**
	 * ���������� ���� ������ ��� ����������.
	 */
	private Object getEntity(Throwable exception) {
		// ���������� ����������� ����� (�� ��� production)
		StringWriter errorMsg = new StringWriter();
		exception.printStackTrace(new PrintWriter(errorMsg));
		return errorMsg.toString();
	}
}