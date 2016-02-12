package com.ohua_blog.datasource;

import com.ohua.fetch.Request;
import com.ohua.lang.Function;

/**
 * Created by justusadam on 12/02/16.
 */
public class MkRequest {

    @Function
    public Request<BlogRequest, Object> mkRequest(BlogSource source, int id, String type) {
        return new Request<>(new BlogRequest(id, type), source);
    }
}
