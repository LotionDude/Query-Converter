package org.moment.tyche.tyche.request;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.moment.tyche.tyche.query.information.QueryContext;
import org.moment.tyche.tyche.query.information.Queryable;
import org.moment.tyche.tyche.query.meta.QueryResolverProvider;
import org.moment.tyche.tyche.request.internal.FieldProperty;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReportQueryConstructor<T> {
    protected final Map<String, FieldProperty> fieldToQueryable;
    protected final QueryConstructor<T> queryConstructor;
    protected final ObjectMapper objectMapper;

    private final QueryResolverProvider provider;

    public ReportQueryConstructor(Class<T> clazz, QueryResolverProvider provider) {
        this(clazz, provider, new QueryConstructor<>());
    }

    protected ReportQueryConstructor(Class<T> clazz, QueryResolverProvider provider, QueryConstructor<T> queryConstructor) {
        this.fieldToQueryable = this.createFieldToQueryableMap(clazz);
        this.queryConstructor = queryConstructor;
        this.objectMapper = new ObjectMapper();
        this.provider = provider;
    }

    private Map<String, FieldProperty> createFieldToQueryableMap(Class<T> clazz) {
        Map<String, FieldProperty> queryableMap = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            Queryable queryable = field.getAnnotation(Queryable.class);

            if (Objects.nonNull(queryable)) {
                Method fieldValueGetterMethod = Objects.requireNonNull(BeanUtils.getPropertyDescriptor(clazz, field.getName())).getReadMethod();

                queryableMap.put(field.getName(), new FieldProperty(
                        queryable,
                        new QueryContext(queryable.type(), !queryable.field().isBlank() ? queryable.field() : field.getName()),
                        fieldValueGetterMethod));
            }
        }

        return queryableMap;
    }

    public void createQuery(T request) {
        this.fieldToQueryable.forEach((field, queryable) -> this.perQuery(request, field, queryable));
    }

    //TODO: Help the generic!
    private void perQuery(T request, String field, FieldProperty fieldProperty) {
        try {
            Object requestFieldValue = fieldProperty.getFieldValueGetterMethod().invoke(request);
            Query q = provider.getQueryResolverByType(List.class).optionallyConvertQuery(requestFieldValue, fieldProperty.getQueryContext(), this.provider);
            System.out.println(q);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
