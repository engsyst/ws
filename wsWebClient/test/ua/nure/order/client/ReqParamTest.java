package ua.nure.order.client;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReqParamTest {
	static ReqParam params;
	static Map<String, String[]> mapParams;
	static final String[] p1Values = new String[]{"v 1"};
	static final String[] p2Values = new String[]{"v 21", "v 22"};
	static final String expected = "p1=v 1&p2=v 21&p2=v 22";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mapParams = new LinkedHashMap<>();
		mapParams.put("p1", p1Values);
		mapParams.put("p2", p2Values);
		params = new ReqParam();
		params.params.putAll(mapParams);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetParams() {
		Map<String, String[]> mapParams = new LinkedHashMap<>();
		mapParams.put("p2", p2Values);
		params.setParams(mapParams);
		assertEquals("", mapParams, params.params);
	}

	@Test
	public void testAddParams() {
		Map<String, String[]> mapParams = new LinkedHashMap<>();
		String[] p3Values = new String[] {"v 31"};
		mapParams.put("p3", p3Values);
		params.addParams(mapParams);
		Map<String, String[]> expected = new LinkedHashMap<>();
		expected.putAll(ReqParamTest.mapParams);
		expected.putAll(mapParams);
		assertEquals("Maps not equals", expected, params.params);
		String expectedStr = ReqParamTest.expected + "&p3=v 31";
		assertEquals("Maps not equals", expectedStr, params.toString());
	}

	@Test
	public void testSetParam() {
		String expected = ReqParamTest.expected + "&p3=v 31";
		assertEquals("Set new not existed parameter", expected, params.setParam("p3", "v 31").toString());
		expected = ReqParamTest.expected + "&p3=v 32";
		assertEquals("Replace existed parameter", expected, params.setParam("p3", "v 32").toString());
		expected = ReqParamTest.expected;
		assertEquals("Replace existed parameter", expected, params.setParam("p3").toString());
	}
	
	@Test
	public void testAddParam() {
		String expected = ReqParamTest.expected + "&p3=v 31";
		assertEquals("Add new not existed parameter", expected, params.addParam("p3", "v 31").toString());
		expected = ReqParamTest.expected + "&p3=v 31&p3=v 32";
		assertEquals("Add value to existed parameter", expected, params.addParam("p3", "v 32").toString());
		expected = ReqParamTest.expected + "&p3=v 31&p3=v 32&p3=v 32";
		assertEquals("Add value to existed parameter", expected, params.addParam("p3", "v 32").toString());
		expected = ReqParamTest.expected + "&p3=v 31&p3=v 32&p3=v 32";
		assertEquals("Add value to existed parameter", expected, params.addParam("p3").toString());
	}
	
	@Test
	public void testGetParam() {
		String[] actual = params.getParam("p1");
		assertArrayEquals("Expected --> " + p1Values, p1Values, actual);
		actual = params.getParam("p2");
		assertArrayEquals("Expected --> " + p2Values, p2Values, actual);
	}

	@Test
	public void testGetParams() {
		assertEquals(mapParams, params.getParams());
	}
	
	@Test
	public void testRemoveParam() {
		Map<String, String[]> mapParams = new LinkedHashMap<>();;
		mapParams.put("p2", p2Values);
		params.removeParam("p1");
		assertEquals(mapParams, params.params);
		assertEquals("p2=v 21&p2=v 22", params.toString());
	}

}
