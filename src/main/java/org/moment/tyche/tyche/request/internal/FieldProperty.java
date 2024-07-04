package org.moment.tyche.tyche.request.internal;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.moment.tyche.tyche.query.information.QueryContext;
import org.moment.tyche.tyche.query.information.Queryable;

import java.lang.reflect.Method;

@Data
@Builder
@RequiredArgsConstructor
public class FieldProperty {
    private final Queryable queryable;
    private final QueryContext queryContext;
    private final Method fieldValueGetterMethod;
}
