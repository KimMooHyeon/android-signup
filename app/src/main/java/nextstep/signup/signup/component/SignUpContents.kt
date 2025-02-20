package nextstep.signup.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.signup.model.SignUpState
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpContents(
    signUpState: SignUpState,
    onShowSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_name),
            keyboardType = KeyboardType.Text,
            inputText = signUpState.name,
            onValueChange = { newText ->
                signUpState.updateName(newText)
            },
            errorMessage = signUpState.nameErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_email),
            keyboardType = KeyboardType.Text,
            inputText = signUpState.email,
            onValueChange = { newText ->
                signUpState.updateEmail(newText)
            },
            errorMessage = signUpState.emailErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_password),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            inputText = signUpState.password,
            onValueChange = { newText ->
                signUpState.updatePassword(newText)
            },
            errorMessage = signUpState.passwordErrorMessage
        )

        SignUpInputForm(
            placeHolderText = stringResource(R.string.signup_main_input_password_confirm),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            inputText = signUpState.passwordConfirm,
            onValueChange = { newText ->
                signUpState.updatePasswordConfirm(newText)
            },
            errorMessage = signUpState.passwordConfirmErrorMessage
        )

        SignUpButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            buttonTitle = stringResource(R.string.signup_main_signtup_button),
            enabled = signUpState.buttonIsEnabled,
            onClick = { onShowSnackbar(context.getString(R.string.signup_finish)) })
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpContentsPreview() {
    SignupTheme {
        SignUpContents(
            onShowSnackbar = { message ->
            },
            signUpState = SignUpState(context = LocalContext.current),
            modifier = Modifier
        )
    }
}
