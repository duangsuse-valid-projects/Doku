/**
  (by) :: forall a b c. (b -> c) -> (a -> b) -> (a -> c)
  f (by) g = \x -> f (g x) */
inline infix fun <A, B, C> ((B) -> C).by(crossinline g: (A) -> B): (A) -> C = { x -> this(g(x)) }