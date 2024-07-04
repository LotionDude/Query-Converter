package org.moment.tyche.tyche.query.resolver;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class QueryResolverModule {
    private final Set<QueryResolver<?>> queryResolvers;

    public QueryResolverModule() {
        this.queryResolvers = new HashSet<>();
    }

    public QueryResolverModule(Set<QueryResolver<?>> queryResolvers) {
        this.queryResolvers = queryResolvers;
    }

    protected void addQueryResolver(QueryResolver<?> queryResolver) {
        this.queryResolvers.add(queryResolver);
    }

    protected void addQueryModule(QueryResolverModule module) {
        module.getQueryResolvers().forEach(this::addQueryResolver);
    }
}
