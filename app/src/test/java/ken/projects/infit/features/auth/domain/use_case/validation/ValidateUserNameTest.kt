package ken.projects.infit.features.auth.domain.use_case.validation

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ValidateUserNameTest {

    @Test
    fun `User name is empty, returns failure`(){
        val userName = ""

        val result = ValidateUserName().invoke(userName)

        assertThat(result.successful).isFalse()
    }

    @Test
    fun `User name is empty, returns error message`(){
        val userName = ""

        val result = ValidateUserName().invoke(userName)

        assertThat(result.errorMessage).isNotNull()
    }

    @Test
    fun `User name is not empty, returns success`(){
        val userName = "username"

        val result = ValidateUserName().invoke(userName)

        assertThat(result.successful).isTrue()
    }

    @Test
    fun `User name is not empty, returns no error message`(){
        val userName = "username"

        val result = ValidateUserName().invoke(userName)

        assertThat(result.errorMessage).isNull()
    }


}