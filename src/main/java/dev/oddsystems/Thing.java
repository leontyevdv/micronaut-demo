package dev.oddsystems;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity
public record Thing(@GeneratedValue @Id @Nullable Long id, String name, int amount) {

  public Thing(String name, int amount) {
    this(null, name, amount);
  }
}
