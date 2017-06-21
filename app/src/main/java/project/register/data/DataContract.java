package project.register.data;

import android.provider.BaseColumns;

/**
 * Created by shivani on 21/6/17.
 */

public class DataContract {
    public static class dataEntry implements BaseColumns {
        public static final String TABLE_NAME="regData";
        public static final String COLUMN_FNAME="firstName";
        public static final String COLUMN_LNAME="lastName";
        public static final String COLUMN_AGE="age";
        public static final String COLUMN_DOB="dob";
        public static final String COLUMN_EMAIL="mail_id";

    }
}
