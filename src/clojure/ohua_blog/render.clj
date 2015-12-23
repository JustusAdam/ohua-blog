(ns ohua-blog.render)

(defn render-topics [topics]
  (join "\n" (map str topics)))

(defn render-post-list [posts]
  (join "\n" (map #(str "Post " (:id (head %))) posts)))

(defn render-side-pane [posts topics]
  (str posts "\n" topics))

(def render-posts render-post-list)
