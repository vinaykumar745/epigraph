namespace some

import foo

record R {
  attr = value
  attr1 = value
  attr2 = (q.w/a: b, c: <d: z.w/e> z: x)
  f: map[Q, list[W]]
}

list[list[Bar]] bars

abstract polymorphic record E

enum E {
  A B
  C
}
