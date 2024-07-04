package org.moment.tyche.report;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.moment.tyche.tyche.query.QueryType;
import org.moment.tyche.tyche.query.information.Queryable;
import org.moment.tyche.tyche.types.MatchNot;
import org.moment.tyche.tyche.types.Range;

import java.util.Date;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
public class ReportRequest {
    //    @Queryable(QueryType.RANGE)
    private final MatchNot<List<Range<Date>>> time;

    //    @Queryable(type = QueryType.TERM, field = "match")
    private final MatchNot<String> stringMatchNot;

    //    @Queryable(QueryType.TERM)
    private final Boolean isTrigger;

    @Queryable(type = QueryType.MATCH)
    private final List<String> identifiers;
}
