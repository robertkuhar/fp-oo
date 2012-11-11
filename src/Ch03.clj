(ns Ch03)

(def point {:x 1, :y 2, :__class_symbol__ 'Point})

(def Point
     (fn [x y]
       {:x x,
        :y y
        :__class_symbol__ 'Point}))

(def x :x)
(def y :y)
(def class-of :__class_symbol__)

(def shift
     (fn [this xinc yinc]
       (Point (+ (x this) xinc)
              (+ (y this) yinc))))

(def Triangle
     (fn [point1 point2 point3]
       {:point1 point1, :point2 point2, :point3 point3
        :__class_symbol__ 'Triangle}))


(def right-triangle (Triangle (Point 0 0)
                              (Point 0 1)
                              (Point 1 0)))

(def equal-right-triangle (Triangle (Point 0 0)
                                    (Point 0 1)
                                    (Point 1 0)))

(def different-triangle (Triangle (Point 0 0)
                                  (Point 0 10)
                                  (Point 10 0)))

;; 3.4 implement add without shift
(def add
  (fn [this that]
    (Point (+ (this x) (that x))
           (+ (this y) (that y)))))

(def oneTwo (Point 1 2))
(def threeFour (Point 3 4))
(def fourSix (Point 4 6))

(= (add oneTwo threeFour) fourSix)
;; 3.4 implement add with shift
(def add
  (fn [this that]
    (shift this (x that) (y that))))
(= (add oneTwo threeFour) fourSix)

;; Exercise 2, something like "new"
(def make (fn [clazz & params] (apply clazz params)))
(make Point 2 4)

(make Triangle (make Point 1 2) (make Point 1 3) (make Point 3 1))

(= right-triangle equal-right-triangle)

(def equal-triangles? (fn [ & candidates ] (apply = candidates)))
(equal-triangles? right-triangle equal-right-triangle)
(equal-triangles? right-triangle equal-right-triangle different-triangle)

(def valid-triangle?
  (fn [p1 p2 p3]
    (and (not= p1 p2)
         (not= p1 p3)
         (not= p2 p3))))
(= (valid-triangle?
    (make Point 1 2)
    (make Point 1 3)
    (make Point 1 2))
   false)
