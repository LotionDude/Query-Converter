package org.moment.tyche.tyche.request;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class TycheAggregationRequest<R> {
  private final R request;
}
