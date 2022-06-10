package com.example.dreamsalonfinderapp;

public final class TableSchema {

    private TableSchema() {
    }

    public static class UserEntry {

        public static final String TABLENAME = "Usersdetails";
        public static final String USERS_COLUMN_ID  = "id";
        public static final String USERS_COLUMN_FULLNAME = "fullName";
        public static final String USERS_COLUMN_EMAIL = "email";
        public static final String USERS_COLUMN_PASSWORD = "password";

    }
}
