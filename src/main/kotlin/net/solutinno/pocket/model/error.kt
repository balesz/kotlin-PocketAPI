package net.solutinno.pocket.model

/**
 * @property statusCode Http status code
 * @property errorCode Error code
 * @property message Error message
 */
class PocketError(
        val statusCode: Int,
        val errorCode: Int,
        message: String
) : Error(message)

/**
 * @property OK 200 - Request was successful
 * @property INVALID_REQUEST 400 - Invalid request, please make sure you follow the documentation for proper syntax
 * @property AUTHENTICATION_ERROR 401 - Problem authenticating the user
 * @property RATE_LIMIT_ERROR 403 - User was authenticated, but access denied due to lack of permission or rate limiting
 * @property SERVER_DOWN 503 - Pocket's sync server is down for scheduled maintenance.
 */
object StatusCode {
    val OK = 200
    val INVALID_REQUEST = 400
    val AUTHENTICATION_ERROR = 401
    val RATE_LIMIT_ERROR = 403
    val SERVER_DOWN = 503
}

/**
 * @property INVALID_TOKEN 107 - A valid access token is required to access the requested API endpoint.
 * @property INVALID_REQUEST 130 - Invalid request, please refer to API documentation.
 * @property CONSUMER_KEY_NOT_FOUND 136 - This Consumer Key was not found.
 * @property MISSING_CONSUMER_KEY 138 -
 * @property MISSING_REDIRECT_URI 140 -
 * @property INVALID_CONSUMER_KEY 152 -
 * @property USER_REJECTED_CODE 158 -
 * @property ALREADY_USED_CODE 159 -
 * @property INVALID_REDIRECT_URI 181 -
 * @property MISSING_CODE 182 -
 * @property CODE_NOT_FOUND 185 -
 * @property POCKET_SERVER_ERROR 198 -
 * @property POCKET_SERVER_ISSUE 199 -
 */
object  ErrorCode {
    val INVALID_TOKEN = 107
    val INVALID_REQUEST = 130
    val CONSUMER_KEY_NOT_FOUND = 136
    val MISSING_CONSUMER_KEY = 138
    val MISSING_REDIRECT_URI = 140
    val INVALID_CONSUMER_KEY = 152
    val USER_REJECTED_CODE = 158
    val ALREADY_USED_CODE = 159
    val INVALID_REDIRECT_URI = 181
    val MISSING_CODE = 182
    val CODE_NOT_FOUND = 185
    val POCKET_SERVER_ERROR = 198
    val POCKET_SERVER_ISSUE = 199
}
