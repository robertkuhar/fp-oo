(ns Ch05)

(def class-from-instance
  (fn [instance]
    (eval (:__class_symbol__ instance))))

(def apply-message-to
  (fn [class instance message args]
    (apply (method-from-message message class)
           instance args)))

(def make
  (fn [class & args]
    (let [seeded {:__class_symbol__ (:__own_symbol__ class)}]
      (apply-message-to class seeded :add-instance-values args))))

(def send-to
  (fn [instance message & args]
    (apply-message-to (class-from-instance instance)
                      instance message args)))

(def Point
  {
   :__own_symbol__ 'Point
   :__instance_methods__
   {
    :add-instance-values (fn [this x y]
                           (assoc this :x x :y y))
    :shift (fn [this xinc yinc]
             (make Point (+ (:x this) xinc) (+ (:y this) yinc)))
    }
   }
  )
