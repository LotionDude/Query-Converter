package org.moment.tyche.tyche.types;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class Range<T extends Comparable<T>> {
  private final T gt;
  private final T gte;
  private final T lt;
  private final T lte;
}
