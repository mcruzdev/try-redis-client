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

@Path("/hello")
@ApplicationScoped
public class GreetingResource {

    @Inject
    @RedisClientName("ddd")
    RedisDataSource redisDataSource;

    @Inject
    @RedisClientName("ggg")
    RedisDataSource ggg;

    @GET
    @Path("/keys")
    public String getKeys() {
        KeyCommands<String> keyCommands = redisDataSource.key();
        return keyCommands.keys("*").toString();
    }

    @GET
    @Path("/clients")
    public String getClient() {
        return redisDataSource.execute(Command.CLIENT, "GETNAME").toString();
    }

    @GET
    @Path("/clients/ggg")
    public String getGggClient() {
        return ggg.execute(Command.CLIENT, "GETNAME").toString();
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
   
        return "Hello from Quarkus REST";
    }
}
