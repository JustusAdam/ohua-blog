package com.ohua_blog.functions;

import com.ohua.lang.Function;
import com.ohua_blog.types.FetchType;
import com.ohua_blog.types.PostInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

/**
 * Created by justusadam on 13/01/16.
 */
public class Fetch {

    // TODO initialize these maps with dummy values
    private final static HashMap<Integer, PostInfo> postMap = new HashMap<>();
    private final static HashMap<Integer, Integer> viewMap = new HashMap<>();
    private final static HashMap<Integer, String> contentMap = new HashMap<>();

    static {
      postMap.put(1, new PostInfo(1, "foo", "bar", new Date()));
      viewMap.put(1, 5);
      contentMap.put(1, "No content");
    }

    @Function
    public Object[] fetch (int id, String type) {
        FetchType t = FetchType.valueOf(type);
        Object res;
        switch (t) {
            case PostIds:
                res = (Object) fetchPostIds();
                break;
            case PostContent:
                res = (Object) fetchPostContent(id);
                break;
            case PostInfo:
                res = (Object) fetchPostInfo(id);
                break;
            case PostViews:
                res = (Object) fetchPostViews(id);
                break;
            default:
                throw new IllegalStateException();
        }
        return new Object[] { res };
    }

    private List<Integer> fetchPostIds() {
        return new ArrayList<Integer>(postMap.keySet());
//        return postMap.keySet().stream().collect(Collectors.toList());
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
