package org.moment.tyche.application;

import org.moment.tyche.report.ReportRequest;
import org.moment.tyche.tyche.request.TycheAggregationRequest;
import org.moment.tyche.tyche.request.TycheQueryRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class WebController {
  @PostMapping("/query")
  public void query(TycheQueryRequest<ReportRequest> request) {}

  @PostMapping("/aggregate")
  public void aggregate(TycheAggregationRequest<ReportRequest> request) {}
}
