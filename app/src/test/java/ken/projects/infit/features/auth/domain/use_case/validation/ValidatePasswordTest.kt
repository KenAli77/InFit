package ken.projects.infit.features.auth.domain.use_case.validation

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ValidatePasswordTest {

    @Test
    fun `Password less than min length, returns failure`(){

        val password = "abc123"

        val result = ValidatePassword().invoke(password)

        assertThat(result.successful).isFalse()
    }

    @Test
    fun `Password does not contain letter, returns failure`(){

        val password = "12345678"

        val result = ValidatePassword().invoke(password)

        assertThat(result.successful).isFalse()
    }

    @Test
    fun `Password does not contain digit, returns failure`(){

        val password = "abcdefgh"

        val result = ValidatePassword().invoke(password)

        assertThat(result.successful).isFalse()
    }


    @Test
    fun `Password less than min length, returns error message`(){

        val password = "abc123"

        val result = ValidatePassword().invoke(password)

        assertThat(result.errorMessage).isNotNull()
    }

    @Test
    fun `Password does not contain letter, returns error message`(){

        val password = "12345678"

        val result = ValidatePassword().invoke(password)

        assertThat(result.errorMessage).isNotNull()
    }

    @Test
    fun `Password does not contain digit, returns error message`(){

        val password = "abcdefgh"

        val result = ValidatePassword().invoke(password)

        assertThat(result.errorMessage).isNotNull()
    }


    @Test
    fun `Password more than min length, returns success`(){

        val password = "abcd1234"

        val result = ValidatePassword().invoke(password)

        assertThat(result.successful).isTrue()
    }

    @Test
    fun `Password more than min length, returns no error message`(){

        val password = "abcd1234"

        val result = ValidatePassword().invoke(password)

        assertThat(result.errorMessage).isNull()
    }



}