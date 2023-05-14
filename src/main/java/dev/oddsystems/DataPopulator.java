package dev.oddsystems;

import static io.micronaut.context.env.Environment.TEST;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import java.util.List;
import javax.transaction.Transactional;

@Singleton
@Requires(notEnv = TEST)
public class DataPopulator {

  private final ThingRepository thingRepository;

  public DataPopulator(ThingRepository thingRepository) {
    this.thingRepository = thingRepository;
  }

  @Transactional
  @EventListener
  void init(StartupEvent event) {
    if (thingRepository.count() == 0) {

      thingRepository.saveAll(
          List.of(new Thing("Thing 1", 1), new Thing("Thing 2", 2), new Thing("Thing 3", 3)));
    }
  }
}
