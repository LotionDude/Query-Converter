package org.moment.tyche.tyche.query.meta;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.moment.tyche.tyche.query.resolver.DefaultResolverModule;
import org.moment.tyche.tyche.query.resolver.QueryResolver;
import org.moment.tyche.tyche.query.resolver.QueryResolverModule;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QueryResolverRegistry {
    private final Map<JavaType, QueryResolver<?>> queryResolverMap;

    public QueryResolverRegistry() {
        this.queryResolverMap = new HashMap<>();
    }

    public static QueryResolverRegistry WITH_DEFAULT() {
        QueryResolverRegistry registry = new QueryResolverRegistry();
        registry.register(new DefaultResolverModule());

        return registry;
    }

    // Registers
    public <T> void register(QueryResolver<?> queryResolver) {
        this.queryResolverMap.put(queryResolver.getJavaType(), queryResolver);
    }

    public <T> void register(Set<QueryResolver<?>> queryResolvers) {
        queryResolvers.forEach(this::register);
    }

    public <T> void register(QueryResolverModule module) {
        this.register(module.getQueryResolvers());
    }

    // Retrievers
    public QueryResolver<Object> getQueryResolverByType(Class<?> clazz) {
        return this.getQueryResolverByType(TypeFactory.defaultInstance().constructType(clazz));
    }

    @SuppressWarnings("unchecked")
    public QueryResolver<Object> getQueryResolverByType(JavaType javaType) {
        return (QueryResolver<Object>) this.queryResolverMap.get(javaType);
    }

    // TODO: What?
    @SuppressWarnings("unchecked")
    public <T extends QueryResolver<?>> T getQueryResolver(Class<T> clazz) {
        return (T) this.queryResolverMap.values().stream().filter(qr -> qr.getClass().equals(clazz)).findFirst().get();
    }
}
