/** Toolkit for manipulating bits */
interface BitHelper {
  /** Bit width of types with `bytesz` bytes */
  fun bitwidth(bytesz: Cnt): Cnt
  /** Type loss is inevitable  */
  fun <T> narrow(i: T): Number where T: Number
  /** Byte size of type */
  fun <T> bytesizeOf(_i: T): Cnt where T: Number
  /** All-zero values */
  object AllZeros
  /** All-ones values, 1bit(1)+ [negative -(1+n)](complement of binary representation) */
  object AllOnes

  /** Byte order (order of byte sequence for machine words) */
  enum class ByteOrder(private val title: String, val aka: String, val repr255: Short) {
    BIG_ENDIAN("BigEndian", "BE", 0x00FF.toShort()), LITTLE_ENDIAN("LittleEndian", "LE", 0xFF00.toShort());
    override fun toString() = this.title
  }

  /** Gets the system byte order */
  val systemByteOrder: ByteOrder get
}