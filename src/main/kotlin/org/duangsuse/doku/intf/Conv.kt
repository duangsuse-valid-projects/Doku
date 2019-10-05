/** Binary converator between bytes / primary value */
interface Conv {
  fun readInt8(ba: Buffer): Int8
  fun readInt16(ba: Buffer): Int16
  fun readInt32(ba: Buffer): Int32
  fun readInt64(ba: Buffer): Int64
  fun readRat32(ba: Buffer): Rat32
  fun readRat64(ba: Buffer): Rat64

  fun serialInt8(i: Int8): Buffer
  fun serialInt16(i: Int16): Buffer
  fun serialInt32(i: Int32): Buffer
  fun serialInt64(i: Int64): Buffer
  fun serialRat32(i: Rat32): Buffer
  fun serialRat64(i: Rat64): Buffer

  /** Byte order swapper (swap :: BE->LE; LE->BE) */
  interface Swap {
    fun swapInt8(i: Int8): Int8
    fun swapInt16(i: Int16): Int16
    fun swapInt32(i: Int32): Int32
    fun swapInt64(i: Int64): Int64
    fun swapRat32(i: Rat32): Rat32
    fun swapRat64(i: Rat64): Rat64
  }
}