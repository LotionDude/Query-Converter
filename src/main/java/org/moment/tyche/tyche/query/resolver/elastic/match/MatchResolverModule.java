package org.moment.tyche.tyche.query.resolver.elastic.match;

import org.moment.tyche.tyche.query.resolver.QueryResolverModule;

public class MatchResolverModule extends QueryResolverModule {
    public MatchResolverModule() {
        this.addQueryResolver(new MatchResolver.String());
        this.addQueryResolver(new MatchResolver.Double());
        this.addQueryResolver(new MatchResolver.Integer());
        this.addQueryResolver(new MatchResolver.Long());
    }
}
