package com.sol.bookchat.serialize;


import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sol.bookchat.model.Favorite;
import com.sol.bookchat.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public SimpleModule favoriteModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Favorite.class, new FavoriteSerializer());
        return module;
    }
    @Bean
    public SimpleModule userModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(User.class, new UserSerializer());
        return module;
    }
}