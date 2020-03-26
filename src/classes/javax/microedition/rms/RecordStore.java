package javax.microedition.rms;

public class RecordStore {
    /**
     * Authorization to allow access only to the current MIDlet
     *  suite. AUTHMODE_PRIVATE has a value of 0.
     * 
     * See Also:Constant Field Values
     */
    public static final int AUTHMODE_PRIVATE = ;

    /**
     * Authorization to allow access to any MIDlet
     *  suites. AUTHMODE_ANY has a value of 1.
     * 
     * See Also:Constant Field Values
     */
    public static final int AUTHMODE_ANY = ;

    /**
     * Deletes the named record store. MIDlet suites are only allowed
     *  to delete their own record stores. If the named record store is
     *  open (by a MIDlet in this suite or a MIDlet in a different
     *  MIDlet suite) when this method is called, a
     *  RecordStoreException will be thrown.  If the named record store
     *  does not exist a RecordStoreNotFoundException will be
     *  thrown. Calling this method does NOT result in recordDeleted
     *  calls to any registered listeners of this RecordStore.
     * 
     * Parameters:recordStoreName - the MIDlet suite unique record store to
     *  		delete
     * Throws:
     * RecordStoreException - if a record store-related
     *  		exception occurred
     * RecordStoreNotFoundException - if the record store
     *  		could not be found
     */
    public static void deleteRecordStore(String recordStoreName)
                              throws RecordStoreException,
                                     RecordStoreNotFoundException;

    /**
     * Open (and possibly create) a record store associated with the
     *  given MIDlet suite. If this method is called by a MIDlet when
     *  the record store is already open by a MIDlet in the MIDlet suite,
     *  this method returns a reference to the same RecordStore object.
     * 
     * Parameters:recordStoreName - the MIDlet suite unique name for the
     *           record store, consisting of between one and 32 Unicode
     *           characters inclusive.createIfNecessary - if true, the record store will be
     * 		created if necessary
     * Returns:RecordStore object for the record store
     * Throws:
     * RecordStoreException - if a record store-related
     * 		exception occurred
     * RecordStoreNotFoundException - if the record store
     * 		could not be found
     * RecordStoreFullException - if the operation cannot be
     * 		completed because the record store is full
     * IllegalArgumentException - if
     *           recordStoreName is invalid
     */
    public static RecordStore openRecordStore(String recordStoreName,
                                          boolean createIfNecessary)
                                   throws RecordStoreException,
                                          RecordStoreFullException,
                                          RecordStoreNotFoundException;

    /**
     * Open (and possibly create) a record store that can be shared
     *  with other MIDlet suites. The RecordStore is owned by the
     *  current MIDlet suite. The authorization mode is set when the
     *  record store is created, as follows:
     * 
     *  AUTHMODE_PRIVATE - Only allows the MIDlet
     *           suite that created the RecordStore to access it. This
     *           case behaves identically to
     *           openRecordStore(recordStoreName,
     *           createIfNecessary).
     *  AUTHMODE_ANY - Allows any MIDlet to access the
     *           RecordStore. Note that this makes your recordStore
     *           accessible by any other MIDlet on the device. This
     *           could have privacy and security issues depending on
     *           the data being shared. Please use carefully.
     * 
     *  The owning MIDlet suite may always access the RecordStore and
     *  always has access to write and update the store.
     * 
     *   If this method is called by a MIDlet when the record store
     *  is already open by a MIDlet in the MIDlet suite, this method
     *  returns a reference to the same RecordStore object.
     * 
     * Parameters:recordStoreName - the MIDlet suite unique name for the
     *           record store, consisting of between one and 32 Unicode
     *           characters inclusive.createIfNecessary - if true, the record store will be
     *  		created if necessaryauthmode - the mode under which to check or create access.
     *  		Must be one of AUTHMODE_PRIVATE or AUTHMODE_ANY.
     * 		This argument is ignored if the RecordStore exists.writable - true if the RecordStore is to be writable by
     * 		other MIDlet suites that are granted access.
     * 		This argument is ignored if the RecordStore exists.
     * Returns:RecordStore object for the record store
     * Throws:
     * RecordStoreException - if a record store-related
     *  		exception occurred
     * RecordStoreNotFoundException - if the record store
     *  		could not be found
     * RecordStoreFullException - if the operation
     * 		cannot be completed because the record store is full
     * IllegalArgumentException - if authmode or
     *           recordStoreName is invalidSince:
     *   MIDP 2.0
     */
    public static RecordStore openRecordStore(String recordStoreName,
                                          boolean createIfNecessary,
                                          int authmode,
                                          boolean writable)
                                   throws RecordStoreException,
                                          RecordStoreFullException,
                                          RecordStoreNotFoundException;

    /**
     * Open a record store associated with the named MIDlet suite.
     *  The MIDlet suite is identified by MIDlet vendor and MIDlet
     *  name.  Access is granted only if the authorization mode of the
     *  RecordStore allows access by the current MIDlet suite.  Access
     *  is limited by the authorization mode set when the record store
     *  was created:
     * 
     *  AUTHMODE_PRIVATE - Succeeds only if vendorName
     *  		and suiteName identify the current MIDlet suite; this
     *  		case behaves identically to
     * 		openRecordStore(recordStoreName,
     * 		createIfNecessary).
     *  AUTHMODE_ANY - Always succeeds.
     *           Note that this makes your recordStore
     *           accessible by any other MIDlet on the device. This
     *           could have privacy and security issues depending on
     *           the data being shared. Please use carefully.
     * 		Untrusted MIDlet suites are allowed to share data but
     * 		this is not recommended. The authenticity of the
     * 		origin of untrusted MIDlet suites cannot be verified
     * 		so shared data may be used unscrupulously.
     * 
     *   If this method is called by a MIDlet when the record store
     *  is already open by a MIDlet in the MIDlet suite, this method
     *  returns a reference to the same RecordStore object.
     * 
     *   If a MIDlet calls this method to open a record store from
     *  its own suite, the behavior is identical to calling:
     *  openRecordStore(recordStoreName, false)
     * 
     * Parameters:recordStoreName - the MIDlet suite unique name for the
     *           record store, consisting of between one and 32 Unicode
     *           characters inclusive.vendorName - the vendor of the owning MIDlet suitesuiteName - the name of the MIDlet suite
     * Returns:RecordStore object for the record store
     * Throws:
     * RecordStoreException - if a record store-related
     * 		exception occurred
     * RecordStoreNotFoundException - if the record store
     *  		could not be found
     * SecurityException - if this MIDlet Suite is not
     *   	allowed to open the specified RecordStore.
     * IllegalArgumentException - if recordStoreName is
     *           invalidSince:
     *   MIDP 2.0
     */
    public static RecordStore openRecordStore(String recordStoreName,
                                          String vendorName,
                                          String suiteName)
                                   throws RecordStoreException,
                                          RecordStoreNotFoundException;

    /**
     * Changes the access mode for this RecordStore. The authorization
     *  mode choices are:
     * 
     *  AUTHMODE_PRIVATE - Only allows the MIDlet
     *           suite that created the RecordStore to access it. This
     *           case behaves identically to
     *           openRecordStore(recordStoreName,
     *           createIfNecessary).
     *  AUTHMODE_ANY - Allows any MIDlet to access the
     *           RecordStore. Note that this makes your recordStore
     *           accessible by any other MIDlet on the device. This
     *           could have privacy and security issues depending on
     *           the data being shared. Please use carefully.
     * 
     *  The owning MIDlet suite may always access the RecordStore and
     *  always has access to write and update the store. Only the
     *  owning MIDlet suite can change the mode of a RecordStore.
     * 
     * Parameters:authmode - the mode under which to check or create access.
     *  		Must be one of AUTHMODE_PRIVATE or AUTHMODE_ANY.writable - true if the RecordStore is to be writable by
     * 		other MIDlet suites that are granted access
     * Throws:
     * RecordStoreException - if a record store-related
     * 		exception occurred
     * SecurityException - if this MIDlet Suite is not
     * 		allowed to change the mode of the RecordStore
     * IllegalArgumentException - if authmode is invalidSince:
     *   MIDP 2.0
     */
    public void setMode(int authmode,
                    boolean writable)
             throws RecordStoreException;

    /**
     * This method is called when the MIDlet requests to have the
     *  record store closed. Note that the record store will not
     *  actually be closed until closeRecordStore() is called as many
     *  times as openRecordStore() was called. In other words, the
     *  MIDlet needs to make a balanced number of close calls as open
     *  calls before the record store is closed.
     * 
     *  When the record store is closed, all listeners are removed
     *  and all RecordEnumerations associated with it become invalid.
     *  If the MIDlet attempts to perform
     *  operations on the RecordStore object after it has been closed,
     *  the methods will throw a RecordStoreNotOpenException.
     * 
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     * 		not open
     * RecordStoreException - if a different record
     * 		store-related exception occurred
     */
    public void closeRecordStore()
                      throws RecordStoreNotOpenException,
                             RecordStoreException;

    /**
     * Returns an array of the names of record stores owned by the
     *  MIDlet suite. Note that if the MIDlet suite does not
     *  have any record stores, this function will return null.
     * 
     *  The order of RecordStore names returned is implementation
     *  dependent.
     * 
     * Returns:array of the names of record stores owned by the
     *  MIDlet suite. Note that if the MIDlet suite does not
     *  have any record stores, this function will return null.
     */
    public static String[] listRecordStores();

    /**
     * Returns the name of this RecordStore.
     * 
     * Returns:the name of this RecordStore
     * Throws:
     * RecordStoreNotOpenException - if the record store is not open
     */
    public String getName()
               throws RecordStoreNotOpenException;

    /**
     * Each time a record store is modified (by
     *  addRecord, setRecord, or
     *  deleteRecord methods) its version is
     *  incremented. This can be used by MIDlets to quickly tell if
     *  anything has been modified.
     * 
     *  The initial version number is implementation dependent.
     *  The increment is a positive integer greater than 0.
     *  The version number increases only when the RecordStore is updated.
     * 
     *  The increment value need not be constant and may vary with each
     *  update.
     * 
     * Returns:the current record store version
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     *             not open
     */
    public int getVersion()
               throws RecordStoreNotOpenException;

    /**
     * Returns the number of records currently in the record store.
     * 
     * Returns:the number of records currently in the record store
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     *           not open
     */
    public int getNumRecords()
                  throws RecordStoreNotOpenException;

    /**
     * Returns the amount of space, in bytes, that the record store
     *  occupies. The size returned includes any overhead associated
     *  with the implementation, such as the data structures
     *  used to hold the state of the record store, etc.
     * 
     * Returns:the size of the record store in bytes
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     *           not open
     */
    public int getSize()
            throws RecordStoreNotOpenException;

    /**
     * Returns the amount of additional room (in bytes) available for
     *  this record store to grow. Note that this is not necessarily
     *  the amount of extra MIDlet-level data which can be stored,
     *  as implementations may store additional data structures with
     *  each record to support integration with native applications,
     *  synchronization, etc.
     * 
     * Returns:the amount of additional room (in bytes) available for
     *           this record store to grow
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     *           not open
     */
    public int getSizeAvailable()
                     throws RecordStoreNotOpenException;

    /**
     * Returns the last time the record store was modified, in the
     *  format used by System.currentTimeMillis().
     * 
     * Returns:the last time the record store was modified, in the
     *  		format used by System.currentTimeMillis()
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     *           not open
     */
    public long getLastModified()
                     throws RecordStoreNotOpenException;

    /**
     * Adds the specified RecordListener. If the specified listener
     *  is already registered, it will not be added a second time.
     *  When a record store is closed, all listeners are removed.
     * 
     * Parameters:listener - the RecordChangedListenerSee Also:removeRecordListener(javax.microedition.rms.RecordListener)
     */
    public void addRecordListener(RecordListener listener);

    /**
     * Removes the specified RecordListener. If the specified listener
     *  is not registered, this method does nothing.
     * 
     * Parameters:listener - the RecordChangedListenerSee Also:addRecordListener(javax.microedition.rms.RecordListener)
     */
    public void removeRecordListener(RecordListener listener);

    /**
     * Returns the recordId of the next record to be added to the
     *  record store. This can be useful for setting up pseudo-relational
     *  relationships. That is, if you have two or more
     *  record stores whose records need to refer to one another, you can
     *  predetermine the recordIds of the records that will be created
     *  in one record store, before populating the fields and allocating
     *  the record in another record store. Note that the recordId returned
     *  is only valid while the record store remains open and until a call
     *  to addRecord().
     * 
     * Returns:the recordId of the next record to be added to the
     *           record store
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     *           not open
     * RecordStoreException - if a different record
     * 		store-related exception occurred
     */
    public int getNextRecordID()
                    throws RecordStoreNotOpenException,
                           RecordStoreException;

    /**
     * Adds a new record to the record store. The recordId for this
     *  new record is returned. This is a blocking atomic operation.
     *  The record is written to persistent storage before the
     *  method returns.
     * 
     * Parameters:data - the data to be stored in this record. If the record
     *  		is to have zero-length data (no data), this parameter may be
     *  		null.offset - the index into the data buffer of the first
     *  		relevant byte for this recordnumBytes - the number of bytes of the data buffer to use
     *  		for this record (may be zero)
     * Returns:the recordId for the new record
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     *  		not open
     * RecordStoreException - if a different record
     *  		store-related exception occurred
     * RecordStoreFullException - if the operation cannot be
     *  		completed because the record store has no more room
     * SecurityException - if the MIDlet has read-only access
     *  		to the RecordStore
     */
    public int addRecord(byte[] data,
                     int offset,
                     int numBytes)
              throws RecordStoreNotOpenException,
                     RecordStoreException,
                     RecordStoreFullException;

    /**
     * The record is deleted from the record store. The recordId for
     *  this record is NOT reused.
     * 
     * Parameters:recordId - the ID of the record to delete
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     *  		not open
     * InvalidRecordIDException - if the recordId is invalid
     * RecordStoreException - if a general record store
     *  		exception occurs
     * SecurityException - if the MIDlet has read-only access
     *  		to the RecordStore
     */
    public void deleteRecord(int recordId)
                  throws RecordStoreNotOpenException,
                         InvalidRecordIDException,
                         RecordStoreException;

    /**
     * Returns the size (in bytes) of the MIDlet data available
     *  in the given record.
     * 
     * Parameters:recordId - the ID of the record to use in this operation
     * Returns:the size (in bytes) of the MIDlet data available
     *           in the given record
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     *  		not open
     * InvalidRecordIDException - if the recordId is invalid
     * RecordStoreException - if a general record store
     *  		exception occurs
     */
    public int getRecordSize(int recordId)
                  throws RecordStoreNotOpenException,
                         InvalidRecordIDException,
                         RecordStoreException;

    /**
     * Returns the data stored in the given record.
     * 
     * Parameters:recordId - the ID of the record to use in this operationbuffer - the byte array in which to copy the dataoffset - the index into the buffer in which to start copying
     * Returns:the number of bytes copied into the buffer, starting at
     *           index offset
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     *           not open
     * InvalidRecordIDException - if the recordId is invalid
     * RecordStoreException - if a general record store
     *           exception occurs
     * ArrayIndexOutOfBoundsException - if the record is
     *           larger than the buffer suppliedSee Also:setRecord(int, byte[], int, int)
     */
    public int getRecord(int recordId,
                     byte[] buffer,
                     int offset)
              throws RecordStoreNotOpenException,
                     InvalidRecordIDException,
                     RecordStoreException;

    /**
     * Returns a copy of the data stored in the given record.
     * 
     * Parameters:recordId - the ID of the record to use in this operation
     * Returns:the data stored in the given record. Note that if the
     *  		record has no data, this method will return null.
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     * 		not open
     * InvalidRecordIDException - if the recordId is invalid
     * RecordStoreException - if a general record store
     * 		exception occursSee Also:setRecord(int, byte[], int, int)
     */
    public byte[] getRecord(int recordId)
                 throws RecordStoreNotOpenException,
                        InvalidRecordIDException,
                        RecordStoreException;

    /**
     * Sets the data in the given record to that passed in. After
     *  this method returns, a call to getRecord(int recordId)
     *  will return an array of numBytes size containing the data
     *  supplied here.
     * 
     * Parameters:recordId - the ID of the record to use in this operationnewData - the new data to store in the recordoffset - the index into the data buffer of the first
     *  		relevant byte for this recordnumBytes - the number of bytes of the data buffer to use
     *  		for this record
     * Throws:
     * RecordStoreNotOpenException - if the record store is
     *  		not open
     * InvalidRecordIDException - if the recordId is invalid
     * RecordStoreException - if a general record store
     *  		exception occurs
     * RecordStoreFullException - if the operation cannot be
     *  		completed because the record store has no more room
     * SecurityException - if the MIDlet has read-only access
     *  		to the RecordStoreSee Also:getRecord(int, byte[], int)
     */
    public void setRecord(int recordId,
                      byte[] newData,
                      int offset,
                      int numBytes)
               throws RecordStoreNotOpenException,
                      InvalidRecordIDException,
                      RecordStoreException,
                      RecordStoreFullException;

}
