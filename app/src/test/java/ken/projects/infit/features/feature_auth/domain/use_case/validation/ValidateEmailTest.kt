package ken.projects.infit.features.feature_auth.domain.use_case.validation

import com.google.common.truth.Truth.assertThat
import ken.projects.infit.features.feature_auth.data.validators.MockAndroidEmailPatternValidator
import ken.projects.infit.features.feature_auth.domain.use_case.validation.validators.EmailPatternValidator
import org.junit.After

import org.junit.Before
import org.junit.Test

class ValidateEmailTest {

    private lateinit var androidEmailPatternValidator:MockAndroidEmailPatternValidator

    @Before
    fun setUp() {
        androidEmailPatternValidator = MockAndroidEmailPatternValidator()
    }


    @Test
    fun `Blank email, returns failure` () {

        val email = ""

        val result = ValidateEmail(androidEmailPatternValidator).invoke(email)

        assertThat(result.successful).isFalse()
    }

    @Test
    fun `Null email, returns failure` () {
        val email = null

        val result = ValidateEmail(androidEmailPatternValidator).invoke(email)

        assertThat(result.successful).isFalse()
    }

    @Test
    fun `Invalid email, returns failure` () {

        val email = "abc"

        androidEmailPatternValidator.setIsValid(false)

        val result = ValidateEmail(androidEmailPatternValidator).invoke(email)

        assertThat(result.successful).isFalse()

    }

    @Test
    fun `Blank email, returns error message` () {

        val email = ""

        val result = ValidateEmail(androidEmailPatternValidator).invoke(email)

        assertThat(result.errorMessage).isNotNull()
    }

    @Test
    fun `Null email, returns error message` () {
        val email = null

        val result = ValidateEmail(androidEmailPatternValidator).invoke(email)

        assertThat(result.errorMessage).isNotNull()
    }

    @Test
    fun `Invalid email, returns error message` () {

        val email = "abc"

        androidEmailPatternValidator.setIsValid(false)

        val result = ValidateEmail(androidEmailPatternValidator).invoke(email)

        assertThat(result.errorMessage).isNotNull()

    }

    @Test
    fun `Valid email, returns success` () {

        val email = "abc@def.com"

        androidEmailPatternValidator.setIsValid(true)

        val result = ValidateEmail(androidEmailPatternValidator).invoke(email)

        assertThat(result.successful).isTrue()

    }

    @Test
    fun `Valid email, does not return error message` () {

        val email = "abc@def.com"

        androidEmailPatternValidator.setIsValid(true)

        val result = ValidateEmail(androidEmailPatternValidator).invoke(email)

        assertThat(result.errorMessage).isNull()

    }

}