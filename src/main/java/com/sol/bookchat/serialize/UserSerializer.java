package com.sol.bookchat.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sol.bookchat.model.Favorite;
import com.sol.bookchat.model.User;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {
    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("Id", user.getId());
        jsonGenerator.writeStringField("FirstName", user.getFirstName());
        jsonGenerator.writeStringField("LastName", user.getLastName());
        jsonGenerator.writeStringField("Login", user.getLogin());
        jsonGenerator.writeStringField("Password", user.getPassword());
        jsonGenerator.writeArrayFieldStart("Favorites");
        for (Favorite fav : user.getFavorites()) {
            jsonGenerator.writeObject(fav);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}