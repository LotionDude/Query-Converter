package org.moment.tyche.tyche.query.resolver.type;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import org.moment.tyche.tyche.query.information.QueryContext;
import org.moment.tyche.tyche.query.meta.QueryResolverProvider;
import org.moment.tyche.tyche.query.resolver.QueryResolver;
import org.moment.tyche.tyche.types.MatchNot;

public class MatchNotResolver extends QueryResolver<MatchNot<?>> {
    public MatchNotResolver() {
        super(MatchNot.class);
    }

    @Override
    public Query convertQuery(MatchNot<?> request, QueryContext ctx, QueryResolverProvider provider) {
        return QueryBuilders.bool()
                .must(provider.getQueryResolverByType(request.getMatch().getClass()).optionallyConvertQuery(request.getMatch(), ctx, provider))
                .mustNot(provider.getQueryResolverByType(request.getNot().getClass()).optionallyConvertQuery(request.getNot(), ctx, provider))
                .build()
                ._toQuery();
    }
}
