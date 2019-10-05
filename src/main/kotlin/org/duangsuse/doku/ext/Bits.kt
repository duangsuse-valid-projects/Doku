import BitHelper.*
import BitHelper.ByteOrder.*

object Bits: BitHelper {
  override fun bitwidth(bytesz: Cnt): Cnt = Byte.SIZE_BITS* bytesz

  override fun <T: Number> narrow(i: T) = cases_narrow(i)
  internal object cases_narrow {
    operator fun <T: Number> invoke(k: T): Number = when (k) {
      is Byte -> br(k); is Short -> br(k); is Int -> br(k);
      is Long -> br(k); is Float -> br(k); is Double -> br(k)
      else -> TODO("Unknown narrowing type ${k::class}") }
    fun br(k: Byte) = k
    fun br(k: Short) = k.toByte()
    fun br(k: Int) = k.toShort()
    fun br(k: Long) = k.toInt()
    fun br(k: Float) = k
    fun br(k: Double) = k.toFloat()
  }

  // See https://github.com/JetBrains/kotlin/blob/master/compiler/frontend/src/org/jetbrains/kotlin/diagnostics/rendering/DefaultErrorMessages.java
  override fun <T: Number> bytesizeOf(_i: T) = cases_bytesizeOf(_i)
  @Suppress("UNUSED_PARAMETER", "FINAL_UPPER_BOUND")
  internal object cases_bytesizeOf {
    operator fun <T: Number> invoke(_k: T): Cnt = when (_k) {
      is Byte -> br(_k); is Short -> br(_k); is Int -> br(_k);
      is Long -> br(_k); is Float -> br(_k); is Double -> br(_k)
      else -> TODO("Unknown narrowing type ${_k::class}") }
    fun <T: Byte>  br(_k: T) = Byte.SIZE_BYTES
    fun <T: Short> br(_k: T) = Short.SIZE_BYTES
    fun <T: Int>   br(_k: T) = Int.SIZE_BYTES
    fun <T: Long>  br(_k: T) = Long.SIZE_BYTES
    fun <T: Float> br(_k: T) = 4
    fun <T:Double> br(_k: T) = 8
  }

  object AllZeros {
    const val B0: Byte = 0.toByte()
    const val S0: Short = 0.toShort()
    const val I0: Int = 0.toInt()
    const val L0: Long = 0.toLong()
    const val F0: Float = 0.toFloat()
    const val D0: Double = 0.toDouble()
  }
  object AllOnes {
    const val B1: Byte = (-0b0000_0001).toByte()
    const val S1: Short = (-0b1).toShort()
    const val I1: Int = (-0b1)
    const val L1: Long = (-0b1L)
  }
  private inline val p get() = AllZeros

  val Byte.Companion.BIT_WIDTH: Cnt get() = bitwidth(bytesizeOf(p.B0))
  val Short.Companion.BIT_WIDTH: Cnt get() = bitwidth(bytesizeOf(p.S0))
  val Int.Companion.BIT_WIDTH: Cnt get() = bitwidth(bytesizeOf(p.I0))
  val Long.Companion.BIT_WIDTH: Cnt get() = bitwidth(bytesizeOf(p.L0))
  val Float.Companion.BIT_WIDTH: Cnt get() = bitwidth(bytesizeOf(p.F0))
  val Double.Companion.BIT_WIDTH: Cnt get() = bitwidth(bytesizeOf(p.D0))

  override val systemByteOrder: ByteOrder get() {
    val repr255: Short = (0xFF shl Byte.SIZE_BITS).toShort()
    // and on Little-Endian machines repr255 will left-overflow
    return if (repr255 == BIG_ENDIAN.repr255) BIG_ENDIAN else LITTLE_ENDIAN
  }
}