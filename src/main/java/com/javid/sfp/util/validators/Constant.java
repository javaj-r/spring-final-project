package com.javid.sfp.util.validators;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * application validator constrains
 *
 * @author javid
 * Created on 5/20/2022
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Pattern {
        public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Message {
        private static final String EMAIL = "Email";
        private static final String PASSWORD = "Password";
        private static final String FIRSTNAME = "Firstname";
        private static final String LASTNAME = "Lastname";
        private static final String PRICE = "Price";
        private static final String DESCRIPTION = "Description";
        private static final String ADDRESS = "Address";
        private static final String COMMENT = "Comment";
        private static final String SCORE = "Score";
        private static final String IMAGE = "Image";
        private static final String EXPERT = "Expert";
        private static final String ORDER = "Order";
        private static final String WORK_DURATION = "Work duration";
        private static final String NAME = "Name";
        private static final String WORKGROUP = "Workgroup";

        public static final String SIZE_MIN_MAX = " must be between {min} and {max} character";
        public static final String NOT_NULL = " cannot be null";
        public static final String NOT_BLANK = " cannot be null or blank";
        public static final String NOT_NEGATIVE = " cannot be negative";
        public static final String MIN = " cannot be les than {value}";
        public static final String MAX = " cannot be greater than {value}";
        public static final String MAX_KB = " cannot be larger than ${max / 1024}KB";
        public static final String IMAGE_SIZE = " must size be between ${min / 1024} and ${max / 1024}KB";
        public static final String IMAGE_TYPE = " type must be .jpg or .jpeg";

        public static final String INVALID_EMAIL = "Invalid email address [${validatedValue}]";
        public static final String EMAIL_SIZE = EMAIL + SIZE_MIN_MAX;
        public static final String NULL_EMAIL = EMAIL + NOT_NULL;

        public static final String INVALID_PASSWORD = "Password must contain (a-z,A-Z,0-9,[@,$,!,etc])";
        public static final String PASSWORD_SIZE = PASSWORD + SIZE_MIN_MAX;
        public static final String NULL_PASSWORD = PASSWORD + NOT_NULL;

        public static final String NAME_SIZE = NAME + SIZE_MIN_MAX;
        public static final String BLANK_NAME = NAME + NOT_BLANK;

        public static final String BLANK_FIRSTNAME = FIRSTNAME + NOT_BLANK;
        public static final String FIRSTNAME_SIZE = FIRSTNAME + SIZE_MIN_MAX;

        public static final String BLANK_LASTNAME = LASTNAME + NOT_BLANK;
        public static final String LASTNAME_SIZE = LASTNAME + SIZE_MIN_MAX;

        public static final String NEGATIVE_PRICE = PRICE + NOT_NEGATIVE;
        public static final String NULL_PRICE = PRICE + NOT_NULL;

        public static final String DESCRIPTION_SIZE = DESCRIPTION + SIZE_MIN_MAX;
        public static final String ADDRESS_SIZE = ADDRESS + SIZE_MIN_MAX;
        public static final String COMMENT_SIZE = COMMENT + SIZE_MIN_MAX;

        public static final String SCORE_MIN = SCORE + MIN;
        public static final String SCORE_MAX = SCORE + MAX;

        public static final String IMAGE_MAX_KB = IMAGE + MAX_KB;
        public static final String IMAGE_SIZE_TYPE = IMAGE + IMAGE_SIZE + IMAGE_TYPE;
        public static final String NULL_IMAGE = IMAGE + NOT_NULL;

        public static final String NULL_EXPERT = EXPERT + NOT_NULL;
        public static final String NULL_ORDER = ORDER + NOT_NULL;
        public static final String NULL_WORKGROUP = WORKGROUP + NOT_NULL;

        public static final String NEGATIVE_WORK_DURATION = WORK_DURATION + NOT_NEGATIVE;

    }

}
