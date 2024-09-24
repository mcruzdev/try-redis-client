package dev.matheuscruz;

import io.quarkus.redis.client.RedisClientName;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.KeyCommands;
import io.vertx.mutiny.redis.client.Command;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
@ApplicationScoped
public class ApiResource {

    @Inject
    @RedisClientName("villains")
    RedisDataSource villains;

    @Inject
    @RedisClientName("heroes")
    RedisDataSource heroes;

    @GET
    @Path("/heroes/keys")
    public String getHeroesKeys() {
        KeyCommands<String> keyCommands = heroes.key();
        return keyCommands.keys("*").toString();
    }


    @GET
    @Path("/villains/keys")
    public String getVillainsKeys() {
        KeyCommands<String> keyCommands = villains.key();
        return keyCommands.keys("*").toString();
    }

    @GET
    @Path("/clients/villains")
    public String villainsClientName() {
        return villains.execute(Command.CLIENT, "GETNAME").toString();
    }

    @GET
    @Path("/clients/heroes")
    public String heroesClientName() {
        return heroes.execute(Command.CLIENT, "GETNAME").toString();
    }
}
