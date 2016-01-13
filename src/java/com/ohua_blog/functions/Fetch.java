package com.ohua_blog.functions;

import com.ohua.lang.Function;
import com.ohua_blog.types.FetchType;
import com.ohua_blog.types.PostInfo;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by justusadam on 13/01/16.
 */
public class Fetch {

    private static HashMap<Integer, PostInfo> postMap;
    private static HashMap<Integer, Integer> viewMap;
    private static HashMap<Integer, String> contentMap;

    @Function
    public Object fetch (FetchType type, int id) {
        switch (type) {
            case PostIds:
                return fetchPostIds();
            case PostContent:
                return fetchPostContent(id);
            case PostInfo:
                return fetchPostInfo(id);
            case PostViews:
                return fetchPostViews(id);
            default:
                return null;
        }
    }

    private List<Integer> fetchPostIds() {
        return postMap.keySet().stream().collect(Collectors.toList());
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
