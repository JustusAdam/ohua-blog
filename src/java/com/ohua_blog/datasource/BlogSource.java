package com.ohua_blog.datasource;

import com.ohua.fetch.IDataSource;
import com.ohua_blog.types.FetchType;
import com.ohua_blog.types.PostInfo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * Created by justusadam on 12/02/16.
 */
public class BlogSource implements IDataSource<BlogRequest, Object> {

    private final static Map<Integer, PostInfo> postMap = new HashMap<>();
    private final static Map<Integer, Integer> viewMap = new HashMap<>();
    private final static Map<Integer, String> contentMap = new HashMap<>();

    static {
        postMap.put(1, new PostInfo(1, "foo", "bar", new Date()));
        viewMap.put(1, 5);
        contentMap.put(1, "No content");
    }

    public Object getIdentifier() {
        return "Blog";
    }

    public Iterable<Object> fetch(Iterable<BlogRequest> requests) {
        return StreamSupport.stream(requests.spliterator(), false).map(this::fetchOne).collect(Collectors.toList());
    }

    public Object fetchOne (BlogRequest request) {
        int id = request.getId();
        switch (request.getFetchType()) {
            case PostIds:
                return (Object) fetchPostIds();
            case PostContent:
                return (Object) fetchPostContent(id);
            case PostInfo:
                return (Object) fetchPostInfo(id);
            case PostViews:
                return (Object) fetchPostViews(id);
            default:
                throw new IllegalStateException();
        }
    }

    private List<Integer> fetchPostIds() {
        return new ArrayList<Integer>(postMap.keySet());
    }

    private PostInfo fetchPostInfo (int id) {
        return postMap.get(id);
    }

    private int fetchPostViews (int id) {
        return viewMap.get(id);
    }

    private String fetchPostContent (int id) {
        return contentMap.get(id);
    }
}
