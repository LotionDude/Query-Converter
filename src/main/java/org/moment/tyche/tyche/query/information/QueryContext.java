package org.moment.tyche.tyche.query.information;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.moment.tyche.tyche.query.QueryType;

@Getter
@RequiredArgsConstructor
public class QueryContext {
    private final QueryType queryType;
    private final String field;
}
