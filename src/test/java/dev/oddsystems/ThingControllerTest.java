package dev.oddsystems;

import static io.micronaut.http.HttpStatus.BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.List;
import org.junit.jupiter.api.Test;

@MicronautTest
class ThingControllerTest {

  @Client("/things")
  interface ThingClient {

    @Get
    List<Thing> all();

    @Post("/{name}/{amount}")
    HttpResponse<Thing> create(String name, int amount);
  }

  @Test
  void testCreate(ThingClient thingClient) {
    List<Thing> before = thingClient.all();

    Thing thing = thingClient.create("Thing 4", 4).body();
    assertNotNull(thing);
    assertNotNull(thing.id());

    List<Thing> after = thingClient.all();
    assertEquals(before.size() + 1, after.size());
    assertTrue(after.containsAll(before));
  }

  @Test
  void testCreateFail(ThingClient thingClient) {
    HttpClientResponseException e =
        assertThrows(
            HttpClientResponseException.class,
            () -> {
              thingClient.create("Thing 4", -4);
            });

    assertEquals(BAD_REQUEST, e.getStatus());
  }
}
