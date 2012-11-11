(ns Ch05
  (:require [Ch03 :only [make] :as c3]))

(def Point
  {
   :__instance_methods__
   {
    :add-instance-values (fn [this x y]
                           (assoc this :x x :y y))
    :shift (fn [this xinc yinc]
             (c3/make Point (+ (:x this) xinc) (+ (:y this) yinc)))
    }
   }
  )
