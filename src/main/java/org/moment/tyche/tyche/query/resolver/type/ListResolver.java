package org.moment.tyche.tyche.query.resolver.type;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import org.moment.tyche.tyche.query.information.QueryContext;
import org.moment.tyche.tyche.query.meta.QueryResolverProvider;
import org.moment.tyche.tyche.query.resolver.QueryResolver;

import java.util.List;
import java.util.Objects;

// TODO: Convert to more generic collection once resolver finder by type works with more/less generic types
public class ListResolver extends QueryResolver<List<?>> {
    public ListResolver() {
        super(List.class);
    }

    @Override
    public Query convertQuery(List<?> request, QueryContext ctx, QueryResolverProvider provider) {
        return QueryBuilders.bool()
                .should(request.stream().map(obj -> this.getResolverAndConvert(obj, ctx, provider)).toList())
                .build()
                ._toQuery();
    }

    @Override
    public boolean shouldResolve(List<?> request, QueryContext ctx) {
        return Objects.nonNull(request) && !request.isEmpty();
    }

    private Query getResolverAndConvert(Object obj, QueryContext ctx, QueryResolverProvider provider) {
        return provider.getQueryResolverByType(obj.getClass()).optionallyConvertQuery(obj, ctx, provider);
    }
}
