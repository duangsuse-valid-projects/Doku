const val WByte = Byte.SIZE_BITS
/** Function template for bit conversation */
abstract class ConvTempl {
  abstract protected fun <T> readAryPrim(bitlen: Cnt, initial: T, shl: T.(Cnt) -> T, or: T.(T) -> T): (ByteArray) -> T
  abstract protected fun <T> serialPrimAry(shr: T.(Cnt) -> T, and: T.(T) -> T): (T) -> ByteArray
  /** Function template for byte order (fast) rotate */
  abstract class Swap {
    protected inline fun <T> swapPrimOrd(crossinline from: (ByteArray) -> T, crossinline to: (T) -> ByteArray)
      : (T) -> T = { i -> to(i).let { it.reverse(); from(it) } }
    protected inline fun <T> rotatePrimOrd(
      crossinline shl: T.(Cnt) -> T, crossinline or: T.(T) -> T, // construct T
      crossinline shr: T.(Cnt) -> T, crossinline and: T.(T) -> T, // destruct T
      byteselect: T, bytew: Cnt): (T) -> T  = closure@{
        var stack = it // original
        var swapped = it // clone
        for (_t in 1..bytew) {
          val shifted = stack.and(byteselect)
          stack = stack.shr(WByte) // |00 [FF]<<00 FF|
          swapped = swapped.shl(WByte).or(shifted) 
      }
      return@closure swapped
    }
  }
}