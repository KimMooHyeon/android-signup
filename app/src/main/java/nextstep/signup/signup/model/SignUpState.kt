package nextstep.signup.signup.model

import android.content.Context
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import nextstep.signup.signup.SignUpInputValidationType
import nextstep.signup.signup.getSignUpInputFieldErrorMessage
import nextstep.signup.signup.util.ValidationUtil.isEmailValid
import nextstep.signup.signup.util.ValidationUtil.isNameValid
import nextstep.signup.signup.util.ValidationUtil.isPasswordMatch
import nextstep.signup.signup.util.ValidationUtil.isPasswordValid

class SignUpState(private val context: Context) {
    var name: String by mutableStateOf("")
        private set
    val nameValidation: SignUpInputValidationType by derivedStateOf {
        isNameValid(name)
    }
    val nameErrorMessage: String by derivedStateOf {
        getSignUpInputFieldErrorMessage(context, nameValidation)
    }

    var email: String by mutableStateOf("")
        private set
    val emailValidation: SignUpInputValidationType by derivedStateOf {
        isEmailValid(email)
    }
    val emailErrorMessage: String by derivedStateOf {
        getSignUpInputFieldErrorMessage(context, emailValidation)
    }

    var password: String by mutableStateOf("")
        private set
    val passwordValidation: SignUpInputValidationType by derivedStateOf {
        isPasswordValid(password)
    }
    val passwordErrorMessage: String by derivedStateOf {
        getSignUpInputFieldErrorMessage(context, passwordValidation)
    }

    var passwordConfirm: String by mutableStateOf("")
        private set
    val passwordConfirmValidation: SignUpInputValidationType by derivedStateOf {
        isPasswordMatch(password, passwordConfirm)
    }
    val passwordConfirmErrorMessage: String by derivedStateOf {
        getSignUpInputFieldErrorMessage(context, passwordConfirmValidation)
    }

    val buttonIsEnabled: Boolean by derivedStateOf {
        nameValidation == SignUpInputValidationType.VALID &&
                emailValidation == SignUpInputValidationType.VALID &&
                passwordValidation == SignUpInputValidationType.VALID &&
                passwordConfirmValidation == SignUpInputValidationType.VALID &&
                name.isNotEmpty() &&
                email.isNotEmpty() &&
                password.isNotEmpty() &&
                passwordConfirm.isNotEmpty()
    }

    fun updateName(newName: String) {
        name = newName
    }

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun updatePasswordConfirm(newPasswordConfirm: String) {
        passwordConfirm = newPasswordConfirm
    }
}
