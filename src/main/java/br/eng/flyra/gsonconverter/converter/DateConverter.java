package br.eng.flyra.gsonconverter.converter;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.inject.Specializes;

import br.com.caelum.vraptor.serialization.gson.DateGsonConverter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

@Specializes
public class DateConverter extends DateGsonConverter implements
		JsonSerializer<Date>, JsonDeserializer<Date> {

	private SimpleDateFormat formatter;

	public DateConverter() {
		formatter = new SimpleDateFormat("yyyy-MM-dd");
	}

	@Override
	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		try {
			return formatter.parse(json.getAsString());
		} catch (ParseException e) {
			throw new JsonSyntaxException(json.getAsString(), e);
		}
	}

	@Override
	public JsonElement serialize(Date src, Type typeOfSrc,
			JsonSerializationContext context) {
		return new JsonPrimitive(formatter.format(src));
	}

}
