package com.sol.bookchat.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sol.bookchat.model.Favorite;

import java.io.IOException;

public class FavoriteSerializer extends JsonSerializer<Favorite> {
    @Override
    public void serialize(Favorite fav, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("Id",fav.getId());
        jsonGenerator.writeStringField("imdbID", fav.getImdbID());
        jsonGenerator.writeStringField("Poster", fav.getPoster());
        jsonGenerator.writeStringField("Year", fav.getYear());
        jsonGenerator.writeStringField("Type", fav.getType().toString());
        jsonGenerator.writeStringField("Title",fav.getTitle());
        jsonGenerator.writeEndObject();
    }
}
