/**
 * Base class for BinReader, provide various function for input stream reading
 *
 * + Base class is (basically) DataInput. Functions to be renamed is `protected`
 * + No complex functions are provided (ie. readMultiply, readWhile, readDate)
 * + Unsigned reading is hided before introducing Nat data
 * + String (readUTF) is not public for impl StringFormat feature
 */
abstract class Reader {
  protected abstract fun readBoolean(): Boolean
  protected abstract fun readChar(): Char16
  protected abstract fun readFully(dst: Buffer)
  protected abstract fun readFully(dst: Buffer, pos: Cnt, len: Cnt)
  protected abstract fun readUnsignedByte(): Int32
  protected abstract fun readUnsignedShort(): Int32
  protected abstract fun readUTF(): String
  protected abstract fun skipBytes(n: Cnt): Cnt

  fun readBool(): Boolean = readBoolean()
  fun readChar16(): Char16 = readChar()
  abstract fun readInt8(): Int8
  abstract fun readInt16(): Int16
  abstract fun readInt32(): Int32
  abstract fun readInt64(): Int64
  abstract fun readRat32(): Rat32
  abstract fun readRat64(): Rat64

  fun readAllToBuffer(dst: Buffer) = readFully(dst)
  fun readToBuffer(dst: Buffer, pos: Cnt, len: Cnt) = readFully(dst, pos, len)
  /** I'm kidding */
  fun skip(n: Cnt) {
    var skipped = skipBytes(n)
    while (skipped != 0) // EOF
      { skipped = skipBytes(n - skipped) }
  }
}