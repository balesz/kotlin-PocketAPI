package net.solutinno.pocket.model

enum class AuthError (val code: Int) {
    NO_ERROR (0),
    MISSING_CONSUMER_KEY (138),
    MISSING_REDIRECT_URI (140),
    INVALID_CONSUMER_KEY (152),
    USER_REJECTED_CODE (158),
    ALREADY_USED_CODE (159),
    INVALID_REDIRECT_URI (181),
    MISSING_CODE (182),
    CODE_NOT_FOUND (185),
    POCKET_SERVER_ISSUE (199)
}
