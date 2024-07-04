package org.moment.tyche.tyche.types;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class MatchNot<T> {
  private final T match;
  private final T not;
}
