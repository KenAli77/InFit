package ken.projects.infit.features.auth.domain.use_case.validation

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ArePasswordMatchingTest{


    @Test
    fun `Passwords not matching, returns unsuccessful` () {
        val password = "test1234"
        val confirmPassword = "test12345"

        val result = ArePasswordMatching().invoke(password = password,confirmPassword = confirmPassword)

        assertThat(result.successful).isFalse()

    }

    @Test
    fun `Passwords not matching, returns error message` () {
        val password = "test1234"
        val confirmPassword = "test12345"

        val result = ArePasswordMatching().invoke(password = password,confirmPassword = confirmPassword)

        assertThat(result.errorMessage).isNotNull()

    }

    @Test
    fun `Matching passwords, returns successful` () {
        val password = "test1234"
        val confirmPassword = "test1234"

        val result = ArePasswordMatching().invoke(password = password,confirmPassword = confirmPassword)

        assertThat(result.successful).isTrue()

    }

    @Test
    fun `Passwords matching, returns no error message` () {
        val password = "test1234"
        val confirmPassword = "test1234"

        val result = ArePasswordMatching().invoke(password = password,confirmPassword = confirmPassword)

        assertThat(result.errorMessage).isNull()

    }

}