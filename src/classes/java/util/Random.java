package java.util;

public class Random {
    /**
     * Creates a new random number generator. Its seed is initialized to
     *  a value based on the current time:
     * 
     *  public Random() { this(System.currentTimeMillis()); }
     * 
     * See Also:System.currentTimeMillis()
     */
    public Random() {
        construct();
    }

    private native void construct();

    /**
     * Sets the seed of this random number generator using a single
     *  long seed. The general contract of setSeed
     *  is that it alters the state of this random number generator
     *  object so as to be in exactly the same state as if it had just
     *  been created with the argument seed as a seed. The method
     *  setSeed is implemented by class Random as follows:
     * 
     *  synchronized public void setSeed(long seed) {
     *        this.seed = (seed ^ 0x5DEECE66DL) & ((1L
     *  The implementation of setSeed by class Random
     *  happens to use only 48 bits of the given seed. In general, however,
     *  an overriding method may use all 64 bits of the long argument
     *  as a seed value.
     * 
     * Parameters:seed - the initial seed.
     */
    public void setSeed(long seed);

    /**
     * Generates the next pseudorandom number. Subclass should
     *  override this, as this is used by all other methods.
     *  The general contract of next is that it returns an
     *  int value and if the argument bits is between 1
     *  and 32 (inclusive), then that many low-order bits of the
     *  returned value will be (approximately) independently chosen bit
     *  values, each of which is (approximately) equally likely to be
     *  0 or 1. The method next is implemented
     *  by class Random as follows:
     * 
     *  synchronized protected int next(int bits) {
     *        seed = (seed * 0x5DEECE66DL + 0xBL) & ((1L >> (48 - bits));
     *  }
     *  This is a linear congruential pseudorandom number generator, as
     *  defined by D. H. Lehmer and described by Donald E. Knuth in The
     *  Art of Computer Programming, Volume 2: Seminumerical
     *  Algorithms, section 3.2.1.
     * 
     * Parameters:bits - random bits
     * Returns:the next pseudorandom value from this random number generator's sequence.
     */
    protected int next(int bits);

    /**
     * Returns the next pseudorandom, uniformly distributed int
     *  value from this random number generator's sequence. The general
     *  contract of nextInt is that one int value is
     *  pseudorandomly generated and returned. All 232
     *   possible int values are produced with
     *  (approximately) equal probability. The method nextInt is
     *  implemented by class Random as follows:
     * 
     *  public int nextInt() {  return next(32); }
     * 
     * Returns:the next pseudorandom, uniformly distributed int
     *           value from this random number generator's sequence.
     */
    public int nextInt();

    /**
     * Returns a pseudorandom, uniformly distributed int value
     *  between 0 (inclusive) and the specified value (exclusive), drawn from
     *  this random number generator's sequence.  The general contract of
     *  nextInt is that one int value in the specified range
     *  is pseudorandomly generated and returned.  All n possible
     *  int values are produced with (approximately) equal
     *  probability.  The method nextInt(int n) is implemented by
     *  class Random as follows:
     * 
     *  public int nextInt(int n) {
     *      if (n> 31);
     * 
     *      int bits, val;
     *      do {
     *          bits = next(31);
     *          val = bits % n;
     *      } while(bits - val + (n-1)
     * 
     *  The hedge "approximately" is used in the foregoing description only
     *  because the next method is only approximately an unbiased source of
     *  independently chosen bits.  If it were a perfect source of randomly
     *  chosen bits, then the algorithm shown would choose int
     *  values from the stated range with perfect uniformity.
     * 
     *  The algorithm is slightly tricky.  It rejects values that would result
     *  in an uneven distribution (due to the fact that 2^31 is not divisible
     *  by n). The probability of a value being rejected depends on n.  The
     *  worst case is n=2^30+1, for which the probability of a reject is 1/2,
     *  and the expected number of iterations before the loop terminates is 2.
     * 
     *  The algorithm treats the case where n is a power of two specially: it
     *  returns the correct number of high-order bits from the underlying
     *  pseudo-random number generator.  In the absence of special treatment,
     *  the correct number of low-order bits would be returned.  Linear
     *  congruential pseudo-random number generators such as the one
     *  implemented by this class are known to have short periods in the
     *  sequence of values of their low-order bits.  Thus, this special case
     *  greatly increases the length of the sequence of values returned by
     *  successive calls to this method if n is a small power of two.
     * 
     * Parameters:n - the bound on the random number to be returned.  Must be
     *           positive.
     * Returns:a pseudorandom, uniformly distributed int
     *           value between 0 (inclusive) and n (exclusive).
     * Throws:
     * IllegalArgumentException - n is not positive.Since:
     *   CLDC 1.1
     */
    public int nextInt(int n);

    /**
     * Returns the next pseudorandom, uniformly distributed long
     *  value from this random number generator's sequence. The general
     *  contract of nextLong is that one long value is pseudorandomly
     *  generated and returned. All 264
     *  possible long values are produced with (approximately) equal
     *  probability. The method nextLong is implemented by class
     *  Random as follows:
     * 
     *  public long nextLong() {
     *        return ((long)next(32)
     * 
     * Returns:the next pseudorandom, uniformly distributed long
     *           value from this random number generator's sequence.
     */
    public long nextLong();

    /**
     * Returns the next pseudorandom, uniformly distributed float
     *  value between 0.0 and 1.0 from this random
     *  number generator's sequence.
     *  The general contract of nextFloat is that one float
     *  value, chosen (approximately) uniformly from the range 0.0f
     *  (inclusive) to 1.0f (exclusive), is pseudorandomly
     *  generated and returned. All 224
     *  possible float values of the form
     *  m&nbsp;x&nbsp2-24, where
     *  m is a positive integer less than 224
     *  , are produced with (approximately) equal probability. The
     *  method nextFloat is implemented by class Random as
     *  follows:
     * 
     *  public float nextFloat() {
     *       return next(24) / ((float)(1
     *  The hedge "approximately" is used in the foregoing description only
     *  because the next method is only approximately an unbiased source of
     *  independently chosen bits. If it were a perfect source or randomly
     *  chosen bits, then the algorithm shown would choose float
     *  values from the stated range with perfect uniformity.
     *  [In early versions of Java, the result was incorrectly calculated as:
     * 
     *  return next(30) / ((float)(1
     *  This might seem to be equivalent, if not better, but in fact it
     *  introduced a slight nonuniformity because of the bias in the rounding
     *  of floating-point numbers: it was slightly more likely that the
     *  low-order bit of the significand would be 0 than that it would be 1.]
     * 
     * Returns:the next pseudorandom, uniformly distributed float
     *           value between 0.0 and 1.0 from this
     *           random number generator's sequence.Since:
     *   CLDC 1.1
     */
    public float nextFloat();

}
