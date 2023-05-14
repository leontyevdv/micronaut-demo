package dev.oddsystems;

import static java.time.temporal.ChronoUnit.SECONDS;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.validation.constraints.Positive;

@Controller("/things")
@ExecuteOn(TaskExecutors.IO)
public class ThingController {

  private final ThingRepository thingRepository;

  public ThingController(ThingRepository thingRepository) {
    this.thingRepository = thingRepository;
  }

  @Get
  public Iterable<Thing> getAll() {
    return thingRepository.findAll();
  }

  @Get("/{id}")
  public Optional<Thing> get(long id) {
    return thingRepository.findById(id);
  }

  @Post("/{name}/{amount}")
  HttpResponse<Thing> create(String name, @Positive int amount) {
    Thing thing = thingRepository.save(new Thing(name, amount));
    return HttpResponse.created(thing)
        .headers(
            headers ->
                headers
                    .location(URI.create(String.format("/things/%d", thing.id())))
                    .expires(LocalDateTime.now().plus(60, SECONDS)));
  }
}
