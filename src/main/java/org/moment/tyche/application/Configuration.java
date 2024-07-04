package org.moment.tyche.application;

import org.moment.tyche.report.ReportRequest;
import org.moment.tyche.tyche.query.meta.QueryResolverProvider;
import org.moment.tyche.tyche.query.meta.QueryResolverRegistry;
import org.moment.tyche.tyche.query.resolver.type.MatchNotResolver;
import org.moment.tyche.tyche.request.ReportQueryConstructor;
import org.moment.tyche.tyche.types.MatchNot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public QueryResolverRegistry registry() {
        QueryResolverRegistry registry = QueryResolverRegistry.WITH_DEFAULT();

        registry.register(new MatchNotResolver());

        return registry;
    }

    @Bean
    @Autowired
    public QueryResolverProvider provider(QueryResolverRegistry registry) {
        return new QueryResolverProvider(registry);
    }

    @Bean
    @Autowired
    public ReportQueryConstructor<ReportRequest> constructor(QueryResolverProvider provider) {
        ReportQueryConstructor<ReportRequest> reportRequestQueryConstructor = new ReportQueryConstructor<>(ReportRequest.class, provider);
        ReportRequest request = ReportRequest.builder()
                .isTrigger(true)
                .stringMatchNot(MatchNot.<String>builder()
                        .match("true")
                        .not("false")
                        .build())
                .identifiers(List.of("a", "b", "c", "d"))
                .build();

        reportRequestQueryConstructor.createQuery(request);

        return reportRequestQueryConstructor;
    }
}
