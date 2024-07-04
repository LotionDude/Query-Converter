package org.moment.tyche.tyche.query.resolver.type;

import org.moment.tyche.tyche.query.resolver.QueryResolverModule;

public class WrapperResolverModule extends QueryResolverModule {
    public WrapperResolverModule() {
        this.addQueryResolver(new ListResolver());
    }
}
