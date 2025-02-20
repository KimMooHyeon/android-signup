package nextstep.signup

import android.app.Application
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import nextstep.signup.signup.model.SignUpState
import nextstep.signup.signup.component.SignUpContents
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호_인풋_비어있을때_버튼_비활성화_확인() {
        // when
        val signUpState =
            SignUpState(context = ApplicationProvider.getApplicationContext<Application>()).apply {
                updateName("김무현")
                updateEmail("dlfb7@naver.com")
                updatePassword("")
                updatePasswordConfirm("12345678")
            }
        // given
        composeTestRule.setContent {
            SignUpContents(
                signUpState = signUpState,
                onShowSnackbar = {},
            )
        }


        // then
        composeTestRule.onNodeWithText("Sign UP").assertIsNotEnabled()
    }

    @Test
    fun 이메일_형식_안맞을때_버튼_비활성화_확인() {
        // when
        val signUpState =
            SignUpState(context = ApplicationProvider.getApplicationContext<Application>()).apply {
                updateEmail("dlfb7naver.com")
                updateName("김무현")
                updatePassword("12345678")
                updatePasswordConfirm("12345678")
            }
        //given
        composeTestRule.setContent {
            SignUpContents(
                signUpState = signUpState,
                onShowSnackbar = {},
            )
        }

        // then
        composeTestRule.onNodeWithText("Sign UP").assertIsNotEnabled()
    }

    @Test
    fun 패스워드_매치_안될때_버튼_비활성화_확인() {
        // when
        val signUpState =
            SignUpState(context = ApplicationProvider.getApplicationContext<Application>()).apply {
                updateEmail("dlfb7naver.com")
                updateName("김무현")
                updatePassword("4567891")
                updatePasswordConfirm("12345678")
            }
        //given
        composeTestRule.setContent {
            SignUpContents(
                signUpState = signUpState,
                onShowSnackbar = {},
            )
        }

        // then
        composeTestRule.onNodeWithText("Sign UP").assertIsNotEnabled()
    }

    @Test
    fun 모든_입력필드_정상입력시_버튼_활성화_확인() {
        // when
        val signUpState =
            SignUpState(context = ApplicationProvider.getApplicationContext<Application>()).apply {
                updateEmail("dlfb7naver.com")
                updateName("김무현")
                updatePassword("4567891")
                updatePasswordConfirm("12345678")
            }
        // given
        composeTestRule.setContent {
            SignUpContents(
                signUpState = signUpState,
                onShowSnackbar = {},
            )
        }

        // then
        composeTestRule.onNodeWithText("Sign UP").assertIsNotEnabled()
    }
}
