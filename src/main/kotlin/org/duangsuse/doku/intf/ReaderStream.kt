import java.io.InputStream
import java.io.Closeable

/**
 * Base class for BinReader's `DataInput`, provide various function for input stream reading
 *
 * + Base class is (basically) DataInput. Functions to be renamed is `protected`
 * + No complex functions are provided (ie. readMultiply, readWhile, readDate)
 * + Unsigned reading is hided before introducing Nat data
 * + String (readUTF) is not public for impl StringFormat feature
 */
abstract class ReaderStream: Closeable {
  // Methods hidden from DataInput
  protected abstract fun readFully(dst: Buffer)
  protected abstract fun readFully(dst: Buffer, pos: Cnt, len: Cnt)
  protected abstract fun skipBytes(n: Cnt): Cnt
  protected abstract fun readBoolean(): Boolean
  protected abstract fun readChar(): Char16
  protected abstract fun readUnsignedByte(): Int32
  protected abstract fun readUnsignedShort(): Int32
  protected abstract fun readUTF(): String

  /** `InputStream#markSupported` */
  abstract val hasBackseek: Boolean get

  // Extended from InputStream
  /** Read byte count */
  abstract val position: Cnt get
  /** Avaliable byte count */
  abstract val estimate: Cnt? get

  /**
   * Mark current position for resetting
   * see also: `InputStream#mark`
   */
  abstract fun markPosition(rl: Cnt?)
  /**
   * Seek back to last marked position where `markPosition` is called
   * see also: `InputStream#reset`
   */
  abstract fun seekBack()
  /**
   * Skip a number of bytes
   * see also: `InputStream#skip`, `skipBytes`
   */
  abstract fun seek(skip: Cnt)

  abstract fun readBool(): Boolean
  abstract fun readChar16(): Char16
  abstract fun readInt8(): Int8
  abstract fun readInt16(): Int16
  abstract fun readInt32(): Int32
  abstract fun readInt64(): Int64
  abstract fun readRat32(): Rat32
  abstract fun readRat64(): Rat64
  abstract fun readAllToBuffer(dst: Buffer)
  abstract fun readToBuffer(dst: Buffer, len: Cnt, pos: Cnt)
  abstract override fun close()
}