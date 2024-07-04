package org.moment.tyche.tyche.query.resolver.elastic.match;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import org.moment.tyche.tyche.query.QueryType;
import org.moment.tyche.tyche.query.resolver.elastic.GenericElasticResolver;
import org.moment.tyche.tyche.query.information.QueryContext;
import org.moment.tyche.tyche.query.meta.QueryResolverProvider;

public abstract class MatchResolver<T> extends GenericElasticResolver<T> {
    public MatchResolver(Class<?> clazz) {
        super(clazz, QueryType.MATCH);
    }

    protected Query buildQuery(FieldValue fieldValue, QueryContext ctx) {
        return QueryBuilders.match()
                .field(ctx.getField())
                .query(fieldValue)
                .build()
                ._toQuery();
    }

    public static class String extends MatchResolver<java.lang.String> {
        public String() {
            super(java.lang.String.class);
        }

        @Override
        public Query convertQuery(java.lang.String request, QueryContext ctx, QueryResolverProvider queryResolverProvider) {
            return this.buildQuery(FieldValue.of(request), ctx);
        }
    }

    public static class Double extends MatchResolver<java.lang.Double> {
        public Double() {
            super(java.lang.Double.class);
        }

        @Override
        public Query convertQuery(java.lang.Double request, QueryContext ctx, QueryResolverProvider queryResolverProvider) {
            return this.buildQuery(FieldValue.of(request), ctx);
        }
    }

    public static class Long extends MatchResolver<java.lang.Long> {
        public Long() {
            super(java.lang.Long.class);
        }

        @Override
        public Query convertQuery(java.lang.Long request, QueryContext ctx, QueryResolverProvider queryResolverProvider) {
            return this.buildQuery(FieldValue.of(request), ctx);
        }
    }

    public static class Integer extends MatchResolver<java.lang.Integer> {
        public Integer() {
            super(java.lang.Integer.class);
        }

        @Override
        public Query convertQuery(java.lang.Integer request, QueryContext ctx, QueryResolverProvider queryResolverProvider) {
            return this.buildQuery(FieldValue.of(request), ctx);
        }
    }
}
