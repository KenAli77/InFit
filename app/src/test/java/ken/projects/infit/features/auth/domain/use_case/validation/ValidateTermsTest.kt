package ken.projects.infit.features.auth.domain.use_case.validation

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidateTermsTest{


    @Test
    fun `Accepted terms, returns success`(){
        val result = ValidateTerms().invoke(true)

        assertThat(result.successful).isTrue()
        assertThat(result.errorMessage).isNull()
    }

    @Test
    fun `Not accepted terms, returns failure`(){
        val result = ValidateTerms().invoke(false)

        assertThat(result.successful).isFalse()
        assertThat(result.errorMessage).isNotNull()
    }
}