package com.example.SpringbootApi.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ObjectWriterHelper {

	private ObjectWriterHelper() {
	}
	
	private final static ObjectWriter OBJECTWRITER = new ObjectMapper().writer().withDefaultPrettyPrinter();
	
	public static String ObjectToJson(Object object) throws JsonProcessingException {
		String json = OBJECTWRITER.writeValueAsString(object);
		return json;
	}

}
