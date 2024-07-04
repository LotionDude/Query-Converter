package org.moment.tyche.tyche.query;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import org.moment.tyche.tyche.query.information.QueryContext;
import org.moment.tyche.tyche.query.meta.QueryResolverProvider;
import org.moment.tyche.tyche.query.resolver.QueryResolver;

public class VoidQueryResolver<T> extends QueryResolver<T> {
    public VoidQueryResolver() {
        super(Void.class);
    }

    // TODO: Exception!
    @Override
    public Query convertQuery(T request, QueryContext queryContext, QueryResolverProvider provider) {
        return null;
    }

    @Override
    public boolean shouldResolve(T request, QueryContext ctx) {
        return false;
    }
}
