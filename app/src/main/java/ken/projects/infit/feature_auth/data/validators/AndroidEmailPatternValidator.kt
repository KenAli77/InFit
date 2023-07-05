package ken.projects.infit.feature_auth.data.validators

import android.util.Patterns
import ken.projects.infit.feature_auth.domain.validators.EmailPatternValidator

class AndroidEmailPatternValidator:EmailPatternValidator {

    override fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}