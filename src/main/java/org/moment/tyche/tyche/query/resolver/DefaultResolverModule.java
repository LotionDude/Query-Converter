package org.moment.tyche.tyche.query.resolver;

import org.moment.tyche.tyche.query.resolver.elastic.match.MatchResolverModule;
import org.moment.tyche.tyche.query.resolver.type.WrapperResolverModule;

public class DefaultResolverModule extends QueryResolverModule {
    public DefaultResolverModule() {
        this.addQueryModule(new MatchResolverModule());
        this.addQueryModule(new WrapperResolverModule());
    }
}
