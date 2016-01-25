package com.ohua_blog.functions;

import com.ohua.lang.Function;
import com.ohua_blog.types.FetchType;
import com.ohua_blog.types.PostInfo;
import ohua_blog.render.Render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by justusadam on 13/01/16.
 */
public class Fetch {

    private static void __test () {
        Render r = new Render();
        r.renderMainPane(new ArrayList<>());
    }

    private static HashMap<Integer, PostInfo> postMap;
    private static HashMap<Integer, Integer> viewMap;
    private static HashMap<Integer, String> contentMap;

    @Function
    public Object[] fetch (FetchType type, int id) {
        Object res;
        switch (type) {
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
                res = null;
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
