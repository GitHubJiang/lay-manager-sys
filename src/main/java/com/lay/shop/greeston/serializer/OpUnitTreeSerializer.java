package com.lay.shop.greeston.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lay.shop.greeston.command.auth.OpUnitTreeCommand;

public class OpUnitTreeSerializer extends JsonSerializer<OpUnitTreeCommand> {
	
	@Override
	public void serialize(OpUnitTreeCommand value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		jgen.writeStartObject();
		
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("text", value.getName());
		jgen.writeStringField("selectable", "true");
		jgen.writeStringField("href", "/auth/org/change?ouId=" + value.getId());
		if (value.getNodes() != null) {
			jgen.writeObjectField("nodes", value.getNodes());
		}
        jgen.writeEndObject(); 
	}
}