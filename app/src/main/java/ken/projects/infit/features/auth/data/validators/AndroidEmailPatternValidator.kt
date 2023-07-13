package ken.projects.infit.features.auth.data.validators

import android.util.Patterns
import ken.projects.infit.features.auth.domain.use_case.validation.validators.EmailPatternValidator

class AndroidEmailPatternValidator: EmailPatternValidator {

    override fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}