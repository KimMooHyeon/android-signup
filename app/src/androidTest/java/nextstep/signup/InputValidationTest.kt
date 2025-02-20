package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.signup.component.SignUpInputForm
import nextstep.signup.signup.getSignUpInputFieldErrorMessage
import nextstep.signup.signup.util.ValidationUtil.isEmailValid
import nextstep.signup.signup.util.ValidationUtil.isNameValid
import nextstep.signup.signup.util.ValidationUtil.isPasswordValid
import org.junit.Rule
import org.junit.Test

class InputValidationTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // given
        val nameFieldValue = mutableStateOf("김컴포즈")
        val nameErrorMessage = mutableStateOf("")

        composeTestRule.setContent {

            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_name),
                keyboardType = KeyboardType.Text,
                inputText = nameFieldValue.value,
                onValueChange = {},
                errorMessage = nameErrorMessage.value,
            )

        }
        // when
        composeTestRule.onNodeWithTag(testTag = "TEXT_FIELD")
            .performTextInput("김컴포즈")
        composeTestRule.waitForIdle()
        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // given
        val nameFieldValue = mutableStateOf("김컴포즈입니다다")
        val nameErrorMessage = mutableStateOf("")

        composeTestRule.setContent {

            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_name),
                keyboardType = KeyboardType.Text,
                inputText = nameFieldValue.value,
                onValueChange = {
                    nameErrorMessage.value = getSignUpInputFieldErrorMessage(
                        context,
                        isNameValid(nameFieldValue.value)
                    )
                },
                errorMessage = nameErrorMessage.value,
            )

        }
        // when
        composeTestRule.onNodeWithTag(testTag = "TEXT_FIELD")
            .performTextInput("김컴포즈입니다다")

        // then
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 사용자_이름에_숫자나_기호가_포함되면_에러메시지가_노출된다() {
        // given
        val nameFieldValue = mutableStateOf("")
        val nameErrorMessage = mutableStateOf("")

        composeTestRule.setContent {

            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_name),
                keyboardType = KeyboardType.Text,
                inputText = nameFieldValue.value,
                onValueChange = {
                    nameFieldValue.value = it
                    nameErrorMessage.value = getSignUpInputFieldErrorMessage(
                        context,
                        isNameValid(nameFieldValue.value)
                    )
                },
                errorMessage = nameErrorMessage.value,
            )
        }
        // when
        composeTestRule.onNodeWithTag(testTag = "TEXT_FIELD")
            .performTextInput("김무1무")

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }

    @Test
    fun 이메일이_올바른_형식이_아니면_에러메시지가_노출된다() {
        // given
        val emailFieldValue = mutableStateOf("")
        val emailErrorMessage = mutableStateOf("")

        composeTestRule.setContent {
            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_email),
                keyboardType = KeyboardType.Text,
                inputText = emailFieldValue.value,
                onValueChange = {
                    emailFieldValue.value = it
                    emailErrorMessage.value = getSignUpInputFieldErrorMessage(
                        context,
                        isEmailValid(emailFieldValue.value)
                    )
                },
                errorMessage = emailErrorMessage.value,
            )
        }
        // when

        composeTestRule.onNodeWithTag(testTag = "TEXT_FIELD")
            .performTextInput("testasd")
        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다() {
        // given
        val passwordFieldValue = mutableStateOf("")
        val passwordErrorMessage = mutableStateOf("")

        composeTestRule.setContent {
            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_password),
                keyboardType = KeyboardType.Text,
                inputText = passwordFieldValue.value,
                onValueChange = {
                    passwordFieldValue.value = it
                    passwordErrorMessage.value = getSignUpInputFieldErrorMessage(
                        context,
                        isPasswordValid(passwordFieldValue.value)
                    )
                },
                errorMessage = passwordErrorMessage.value,
            )
        }
        // when

        composeTestRule.onNodeWithTag(testTag = "TEXT_FIELD")
            .performTextInput("12das34")
        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        // given
        val passwordFieldValue = mutableStateOf("")
        val passwordErrorMessage = mutableStateOf("")
        composeTestRule.setContent {
            SignUpInputForm(
                placeHolderText = stringResource(R.string.signup_main_input_password),
                keyboardType = KeyboardType.Text,
                inputText = passwordFieldValue.value,
                onValueChange = {
                    passwordFieldValue.value = it
                    passwordErrorMessage.value = getSignUpInputFieldErrorMessage(
                        context,
                        isPasswordValid(passwordFieldValue.value)
                    )
                },
                errorMessage = passwordErrorMessage.value,
            )
        }
        // when
        composeTestRule.onNodeWithTag(testTag = "TEXT_FIELD")
            .performTextInput("123456789")
        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }

    @Test
    fun 입력_폼에_오류메시지입니다_라는_값이_들어가면_오류메시지입니다가_보여야_한다() {
        // given
        val inputText = mutableStateOf("")

        composeTestRule.setContent {
            SignUpInputForm(
                placeHolderText = "",
                keyboardType = KeyboardType.Text,
                inputText = inputText.value,
                onValueChange = {
                    inputText.value = it
                },
                errorMessage = "",
            )
        }
        // when
        composeTestRule.onNodeWithTag(testTag = "TEXT_FIELD")
            .performTextInput("오류메세지입니다")
        // then
        composeTestRule
            .onNodeWithText("오류메세지입니다")
            .assertExists()
    }
}
