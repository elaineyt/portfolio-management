ALTER TABLE Users(
    ADD failed_login_attempts INT DEFAULT 0;
);