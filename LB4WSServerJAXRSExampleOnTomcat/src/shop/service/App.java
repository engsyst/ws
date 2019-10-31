package shop.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import shop.service.rest.JSONMessageBodyWriter;
import shop.service.rest.ProductListJSONMessageBodyWriter;
import shop.service.rest.ProductNotAvailableExceptionMapper;
import shop.service.rest.ProductXMLMessageBodyWriter;
import shop.service.rest.TextPlainMessageBodyWriter;

@ApplicationPath(Constants.APPLICATION_PATH)
public class App extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<Class<?>>();
		resources.add(ProductServiceRestImpl.class);
		resources.add(TextPlainMessageBodyWriter.class);
		resources.add(ProductXMLMessageBodyWriter.class);
		resources.add(ProductListJSONMessageBodyWriter.class);
		resources.add(JSONMessageBodyWriter.class);
		resources.add(ProductNotAvailableExceptionMapper.class);
		return Collections.unmodifiableSet(resources);
	}
}
