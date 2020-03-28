package javax.microedition.pki;

public class CertificateException extends IOException {
    /**
     * Indicates a certificate has unrecognized critical extensions.
     *  The value is 1.
     * 
     * See Also:Constant Field Values
     */
    public static final byte BAD_EXTENSIONS = ;

    /**
     * Indicates the server certificate chain exceeds the length allowed
     *  by an issuer's policy.
     *  The value is 2.
     * 
     * See Also:Constant Field Values
     */
    public static final byte CERTIFICATE_CHAIN_TOO_LONG = ;

    /**
     * Indicates a certificate is expired.
     *  The value is 3.
     * 
     * See Also:Constant Field Values
     */
    public static final byte EXPIRED = ;

    /**
     * Indicates an intermediate certificate in the chain does not have the
     *  authority to be a intermediate CA. The value is 4.
     * 
     * See Also:Constant Field Values
     */
    public static final byte UNAUTHORIZED_INTERMEDIATE_CA = ;

    /**
     * Indicates a certificate object does not contain a signature.
     *  The value is 5.
     * 
     * See Also:Constant Field Values
     */
    public static final byte MISSING_SIGNATURE = ;

    /**
     * Indicates a certificate is not yet valid.
     *  The value is 6.
     * 
     * See Also:Constant Field Values
     */
    public static final byte NOT_YET_VALID = ;

    /**
     * Indicates a certificate does not contain the correct site name.
     *  The value is 7.
     * 
     * See Also:Constant Field Values
     */
    public static final byte SITENAME_MISMATCH = ;

    /**
     * Indicates a certificate was issued by an unrecognized entity.
     *  The value is 8.
     * 
     * See Also:Constant Field Values
     */
    public static final byte UNRECOGNIZED_ISSUER = ;

    /**
     * Indicates a certificate was signed using an unsupported algorithm.
     *  The value is 9.
     * 
     * See Also:Constant Field Values
     */
    public static final byte UNSUPPORTED_SIGALG = ;

    /**
     * Indicates a certificate public key has been used in way deemed
     *  inappropriate by the issuer. The value is 10.
     * 
     * See Also:Constant Field Values
     */
    public static final byte INAPPROPRIATE_KEY_USAGE = ;

    /**
     * Indicates a certificate in a chain was not issued by the next
     *  authority in the chain. The value is 11.
     * 
     * See Also:Constant Field Values
     */
    public static final byte BROKEN_CHAIN = ;

    /**
     * Indicates the root CA's public key is expired. The value is 12.
     * 
     * See Also:Constant Field Values
     */
    public static final byte ROOT_CA_EXPIRED = ;

    /**
     * Indicates that type of the public key in a certificate is not
     *  supported by the device. The value is 13.
     * 
     * See Also:Constant Field Values
     */
    public static final byte UNSUPPORTED_PUBLIC_KEY_TYPE = ;

    /**
     * Indicates a certificate failed verification.
     *  The value is 14.
     * 
     * See Also:Constant Field Values
     */
    public static final byte VERIFICATION_FAILED = ;

    /**
     * Create a new exception with a Certificate
     *  and specific error reason. The descriptive message for the new exception
     *  will be automatically provided, based on the reason.
     * 
     * Parameters:certificate - the certificate that caused the exceptionstatus - the reason for the exception;
     *   the status MUST be between BAD_EXTENSIONS and VERIFICATION_FAILED
     *   inclusive.
     */
    public CertificateException(Certificate certificate, byte status) {
        construct(certificate, status);
    }

    private native void construct(Certificate certificate, byte status);

    /**
     * Create a new exception with a message, Certificate,
     *  and specific error reason.
     * 
     * Parameters:message - a descriptive messagecertificate - the certificate that caused the exceptionstatus - the reason for the exception;
     *   the status MUST be between BAD_EXTENSIONS and VERIFICATION_FAILED
     *   inclusive.
     */
    public CertificateException(String message, Certificate certificate, byte status) {
        construct(message, certificate, status);
    }

    private native void construct(String message, Certificate certificate, byte status);

    /**
     * Get the Certificate that caused the exception.
     * 
     * Returns:the Certificate that included the failure.
     */
    public Certificate getCertificate();

    /**
     * Get the reason code.
     * 
     * Returns:the reason code
     */
    public byte getReason();

}
