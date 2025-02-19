package nextstep.signup

import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.signup.component.SignUpContents
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호_인풋_비어있을때_버튼_비활성화_확인() {
        // given when
        composeTestRule.setContent {
            SignUpContents(
                nameInputText = "Philip Vincent",
                nameErrorMessage = "Sherri Walsh",
                emailInputText = "calvin.allen@example.com",
                emailErrorMessage = "",
                passwordInputText = "voluptaria",
                passwordErrorMessage = "",
                passwordConfirmInputText = "",
                passwordConfirmErrorMessage = "",
                buttonIsEnabled = false,
                onShowSnackbar = {},
                onNameValueChange = {},
                onEmailValueChange = {},
                onPasswordValueChange = {},
                onPasswordConfirmValueChange = {}
            )
        }


        // then
        composeTestRule.onNodeWithText("Sign UP").assertIsNotEnabled()
    }

    @Test
    fun 이메일_형식_안맞을때_버튼_비활성화_확인() {
        // given when
        composeTestRule.setContent {
            SignUpContents(
                nameInputText = "Philip Vincent",
                nameErrorMessage = "",
                emailInputText = "calvin.allen",
                emailErrorMessage = "",
                passwordInputText = "voluptaria",
                passwordErrorMessage = "",
                passwordConfirmInputText = "",
                passwordConfirmErrorMessage = "",
                buttonIsEnabled = false,
                onShowSnackbar = {},
                onNameValueChange = {},
                onEmailValueChange = {},
                onPasswordValueChange = {},
                onPasswordConfirmValueChange = {}
            )
        }

        // then
        composeTestRule.onNodeWithText("Sign UP").assertIsNotEnabled()
    }

    @Test
    fun 패스워드_매치_안될때_버튼_비활성화_확인() {
        // given when
        composeTestRule.setContent {
            SignUpContents(
                nameInputText = "Philip Vincent",
                nameErrorMessage = "",
                emailInputText = "calvin.allen",
                emailErrorMessage = "",
                passwordInputText = "12345678",
                passwordErrorMessage = "",
                passwordConfirmInputText = "123456789",
                passwordConfirmErrorMessage = "",
                buttonIsEnabled = false,
                onShowSnackbar = {},
                onNameValueChange = {},
                onEmailValueChange = {},
                onPasswordValueChange = {},
                onPasswordConfirmValueChange = {}
            )
        }

        // then
        composeTestRule.onNodeWithText("Sign UP").assertIsNotEnabled()
    }

    @Test
    fun 모든_입력필드_정상입력시_버튼_활성화_확인() {
        // given when
        composeTestRule.setContent {
            SignUpContents(
                nameInputText = "김무현",
                nameErrorMessage = "",
                emailInputText = "dlfb7@naver.com",
                emailErrorMessage = "",
                passwordInputText = "12345678",
                passwordErrorMessage = "",
                passwordConfirmInputText = "12345678",
                passwordConfirmErrorMessage = "",
                buttonIsEnabled = false,
                onShowSnackbar = {},
                onNameValueChange = {},
                onEmailValueChange = {},
                onPasswordValueChange = {},
                onPasswordConfirmValueChange = {}
            )
        }

        // then
        composeTestRule.onNodeWithText("Sign UP").assertIsNotEnabled()
    }
}
