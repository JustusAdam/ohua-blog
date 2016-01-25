(defproject ohua-blog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Revised BSD License"
            :url "https://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.6.0"] [ohua/ohua "0.4.0"]]
  :main ^:skip-aot ohua-blog.core
  :target-path "target/%s"

  ;:profiles {:uberjar {:aot :all}
  ;           :precomp {:prep-tasks [["compile" "ohua-blog.render"]]
  ;                      :source-paths ["src/pre/clojure"]
  ;                      :aot [ohua-blog.render] } }

  :prep-tasks [["compile" "ohua-blog.render"] "javac" "compile"]
  :aot [ohua-blog.render]

  :source-paths ["src/clojure" "src/pre/clojure"]
  :java-source-paths ["src/java"]
  :test-paths ["test" "test/clojure"]

  ; required for safety analysis of Ohua
  :javac-options ["-target" "1.8" "-source" "1.8" "-Xlint:-options" "-g"]

  ; configuration for different JVM options
  :jvm-opts ["-Xmx1g"
             "-XX:+UseConcMarkSweepGC"])
