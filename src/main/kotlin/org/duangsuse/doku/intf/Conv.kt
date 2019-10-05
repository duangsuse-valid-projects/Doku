interface Conv {
  fun readInt8(ba: Buffer): Int8
  fun readInt16(ba: Buffer): Int16
  fun readInt32(ba: Buffer): Int32
  fun readInt64(ba: Buffer): Int64
  fun readRat32(ba: Buffer): Rat32
  fun readRat64(ba: Buffer): Rat64

  fun serialInt8(x: Int8): Buffer
  fun serialInt16(x: Int16): Buffer
  fun serialInt32(x: Int32): Buffer
  fun serialInt64(x: Int64): Buffer
  fun serialRat32(x: Rat32): Buffer
  fun serialRat64(x: Rat64): Buffer
}