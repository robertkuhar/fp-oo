(ns Ch04
  (:require [Ch03 :only [make] :as c3]))

(def Point
  (fn [x y]
    { ;; initializing instance variables
     :x x
     :y y
     ;; Metadata
     :__class_symbol__ 'Point
     :__methods__ {
                   :class :__class_symbol__
                   :x :x
                   :y :y
                   :shift (fn [this xinc yinc]
                            (c3/make Point (+ (:x this) xinc) (+ (:y this) yinc)))
                   :add ( fn [this that]
                              (:shift this (:x that) (:y that)))
                   }}))
(def send-to
  (fn [object message & args]
    (apply (message (:__methods__ object)) object args)))