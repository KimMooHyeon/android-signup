package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import nextstep.signup.signup.SignUpScreen
import nextstep.signup.signup.model.SignUpState
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                val context = LocalContext.current
                val signUpState = remember { SignUpState(context) }
                SignUpScreen(signUpState = signUpState)
            }
        }
    }
}



