package org.moment.tyche.tyche.query.meta;

import com.fasterxml.jackson.databind.JavaType;
import lombok.RequiredArgsConstructor;
import org.moment.tyche.tyche.query.resolver.QueryResolver;

@RequiredArgsConstructor
public class QueryResolverProvider {
    private final QueryResolverRegistry registry;

    public QueryResolver<Object> getQueryResolverByType(JavaType javaType) {
        return this.getQueryResolverByType(javaType.getRawClass());
    }

    public QueryResolver<Object> getQueryResolverByType(Class<?> clazz) {
        return this.registry.getQueryResolverByType(clazz);
    }

    @SuppressWarnings("unchecked")
    public <T extends QueryResolver<?>> QueryResolver<Object> getQueryResolver(Class<T> clazz) {
        return (QueryResolver<Object>) this.registry.getQueryResolver(clazz);
    }
}
