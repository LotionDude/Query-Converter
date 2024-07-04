package org.moment.tyche.tyche.request;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class TycheQueryRequest<T> {
  private final Integer size;
  private final Boolean fetchPaging;
  private final String pageTracker;
  private final T request;
}
