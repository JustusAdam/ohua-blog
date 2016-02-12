package com.ohua_blog.datasource;

import com.ohua_blog.types.FetchType;

public final class BlogRequest {
    private final FetchType fetchType;
    private final int id;

    public BlogRequest (int id, FetchType fetchType) {
        this.id = id;
        this.fetchType = fetchType;
    }

    public BlogRequest (int id, String fetchType) {
        this.id = id;
        this.fetchType = FetchType.valueOf(fetchType);
    }

    public FetchType getFetchType() {
        return fetchType;
    }

    public int getId() {
        return id;
    }
}
