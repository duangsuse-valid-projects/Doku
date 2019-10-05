import java.io.Flushable
import java.io.Closeable

/** Base class for Doku output (see `DataOutput`) */
abstract class WriterStream: Flushable, Closeable {
  // Methods hidden from DataOutput
  protected abstract fun writeBoolean(b: Boolean)
  protected abstract fun writeChar(c: Int)
  protected abstract fun writeBytes(str: String)
  protected abstract fun writeChars(str: String)
  protected abstract fun writeUTF(str: String)

  abstract fun writeBool(b: Boolean)
  abstract fun writeChar16(c: Char16)
  abstract fun writeInt8(i: Int8)
  abstract fun writeInt16(i: Int16)
  abstract fun writeInt32(i: Int32)
  abstract fun writeInt64(i: Int64)
  abstract fun writeRat32(r: Rat32)
  abstract fun writeRat64(r: Rat64)
  abstract fun writeAllFromBuffer(src: Buffer)
  abstract fun writeFromBuffer(src: Buffer, len: Cnt, pos: Cnt)
  abstract fun writeString(str: String, fmt: StringReprFmt = StringReprFmt.UTF)
  enum class StringReprFmt { BYTES, CHARS, UTF }
  abstract override fun flush()
  abstract override fun close()
}