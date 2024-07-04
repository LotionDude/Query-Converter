package org.moment.tyche.tyche.query.resolver;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.Getter;
import org.moment.tyche.tyche.query.information.QueryContext;
import org.moment.tyche.tyche.query.meta.QueryResolverProvider;

import java.util.Objects;

@Getter
public abstract class QueryResolver<T> {
    private final JavaType javaType;

    public QueryResolver(JavaType javaType) {
        this.javaType = javaType;
    }

    public QueryResolver(Class<?> clazz) {
        this.javaType = TypeFactory.defaultInstance().constructType(clazz);
    }

    public Query optionallyConvertQuery(T request, QueryContext ctx, QueryResolverProvider provider) {
        if (this.shouldResolve(request, ctx)) {
            return this.convertQuery(request, ctx, provider);
        }

        return QueryBuilders.bool().build()._toQuery();
    }

    public abstract Query convertQuery(T request, QueryContext ctx, QueryResolverProvider provider);

    public boolean shouldResolve(T request, QueryContext ctx) {
        return Objects.nonNull(request);
    }
}
