package nextstep.signup.signup.util

import nextstep.signup.signup.SignUpInputValidationType

object ValidationUtil {
    private val USERNAME_REGEX = "^[a-zA-Z가-힣]+$".toRegex()
    private val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
    private val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$".toRegex()

    fun isNameValid(username: String): SignUpInputValidationType {
        return when {
            username.length < 2 || username.length > 5 -> {
                SignUpInputValidationType.USERNAME_LENGTH_ERROR
            }

            !username.matches(USERNAME_REGEX) -> {
                SignUpInputValidationType.USERNAME_FORMAT_ERROR
            }

            else -> {
                SignUpInputValidationType.VALID
            }
        }
    }

    fun isEmailValid(email: String): SignUpInputValidationType {
        return when {

            !email.matches(EMAIL_REGEX) -> {
                SignUpInputValidationType.EMAIL_FORMAT_ERROR
            }

            else -> {
                SignUpInputValidationType.VALID
            }
        }
    }

    fun isPasswordValid(password: String): SignUpInputValidationType {
        return when {
            password.length < 8 || password.length > 16 -> {
                SignUpInputValidationType.PASSWORD_LENGTH_ERROR
            }

            !password.matches(PASSWORD_REGEX) -> {
                SignUpInputValidationType.PASSWORD_FORMAT_ERROR
            }

            else -> {
                SignUpInputValidationType.VALID
            }
        }
    }

    fun isPasswordMatch(password: String, passwordConfirm: String): SignUpInputValidationType {
        return when {
            password.isEmpty() || passwordConfirm.isEmpty() -> {
                SignUpInputValidationType.VALID
            }

            password != passwordConfirm -> {
                SignUpInputValidationType.PASSWORD_MISMATCH_ERROR
            }

            else -> {
                SignUpInputValidationType.VALID
            }
        }
    }
}
