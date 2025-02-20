package nextstep.signup.signup

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.signup.component.SignUpContents
import nextstep.signup.signup.component.SignUpTitle
import nextstep.signup.signup.model.SignUpState
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignUpScreen(signUpState: SignUpState, modifier: Modifier = Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    SignUpScreen(modifier, snackbarHostState, scope, signUpState)
}

@Composable
private fun SignUpScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    signUpState: SignUpState
) {

    Scaffold(modifier = modifier.fillMaxSize(),
        topBar = {
            SignUpTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 42.dp),
                title = stringResource(R.string.signup_main_title)
            )
        }, snackbarHost = {
            SnackbarHost(snackbarHostState)
        }) { contentPadding ->
        SignUpContents(
            onShowSnackbar = { message ->
                scope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            },
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 32.dp),
            signUpState = signUpState,
        )
    }
}

fun getSignUpInputFieldErrorMessage(
    context: Context, validationType: SignUpInputValidationType
): String {
    return when (validationType) {
        SignUpInputValidationType.USERNAME_LENGTH_ERROR -> context.resources.getString(R.string.error_username_length)
        SignUpInputValidationType.USERNAME_FORMAT_ERROR -> context.resources.getString(R.string.error_username_format)
        SignUpInputValidationType.EMAIL_FORMAT_ERROR -> context.resources.getString(R.string.error_email_format)
        SignUpInputValidationType.PASSWORD_LENGTH_ERROR -> context.resources.getString(R.string.error_password_length)
        SignUpInputValidationType.PASSWORD_FORMAT_ERROR -> context.resources.getString(R.string.error_password_format)
        SignUpInputValidationType.PASSWORD_MISMATCH_ERROR -> context.resources.getString(R.string.error_password_mismatch)
        SignUpInputValidationType.VALID -> "" // 정상 상태일 때는 빈 문자열 반환
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignUpScreenPreview() {
    SignupTheme {
        val context = LocalContext.current
        SignUpScreen(signUpState = SignUpState(context = context))
    }
}
