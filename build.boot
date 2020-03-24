(set-env! :source-paths #{"src" "test"}
          :resource-paths #{"src"}
          :dependencies '[[org.clojure/clojure "1.10.1"]
                          [org.clojure/core.async "1.0.567"]
                          [org.clojure/data.json "1.0.0"]
                          [clj-http "3.10.0"]
                          [metosin/bat-test "0.4.3" :scope "test"]])

(task-options!
  pom {:project 'anthonyshull/boot
       :description "A basic boot build."
       :version "1.0.0-SNAPSHOT"}
  repl {:init-ns 'anthonyshull.boot.core})

(require '[metosin.bat-test :refer [bat-test]])

(deftask build
  "Builds an uberjar of this project that can be run with java -jar"
  []
  (comp
   (aot :namespace #{'anthonyshull.boot.core})
   (uber)
   (jar :file "boot.jar" :main 'anthonyshull.boot.core)
   (sift :include #{#"boot.jar"})
   (target)))
