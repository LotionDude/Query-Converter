package org.moment.tyche.tyche.query.resolver.elastic;

import org.moment.tyche.tyche.query.QueryType;
import org.moment.tyche.tyche.query.information.QueryContext;
import org.moment.tyche.tyche.query.resolver.QueryResolver;

import java.util.Objects;

public abstract class GenericElasticResolver<T> extends QueryResolver<T> {
    private final QueryType queryType;

    public GenericElasticResolver(Class<?> clazz, QueryType queryType) {
        super(clazz);
        this.queryType = queryType;
    }

    @Override
    public boolean shouldResolve(T request, QueryContext ctx) {
        return Objects.nonNull(ctx.getQueryType()) && ctx.getQueryType().equals(this.queryType);
    }
}
