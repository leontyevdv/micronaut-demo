package dev.oddsystems;

import static org.junit.jupiter.api.Assertions.*;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
class ThingRepositoryTest {

  @Inject ThingRepository thingRepository;

  @Test
  void testCreate() {
    Thing thing = thingRepository.save(new Thing("Thing 4", 4));
    assertNotNull(thing.id());
    assertEquals(1, thingRepository.count());
  }
}
