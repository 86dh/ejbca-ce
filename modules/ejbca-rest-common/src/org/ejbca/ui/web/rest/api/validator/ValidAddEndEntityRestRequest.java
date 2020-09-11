package org.ejbca.ui.web.rest.api.validator;

import org.ejbca.ui.web.rest.api.io.request.AddEndEntityRestRequest;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validation annotation for input parameter with built-in validator. An input AddEndEntityRestRequest is validated for:
 * <ul>
 *     <li>Not null.</li>
 * </ul>
 *
 * AddEndEntityRestRequest's username attribute is validated for:
 * <ul>
 *     <li>Not null;</li>
 *     <li>Not empty;</li>
 * </ul>
 * 
 * AddEndEntityRestRequest's subjectDn attribute is validated for:
 * <ul>
 *     <li>Not null;</li>
 *     <li>Not empty;</li>
 * </ul>
 * 
 * AddEndEntityRestRequest's caName attribute is validated for:
 * <ul>
 *     <li>Not null;</li>
 *     <li>Not empty;</li>
 * </ul>
 * 
 * AddEndEntityRestRequest's certificateProfileName attribute is validated for:
 * <ul>
 *     <li>Not null;</li>
 *     <li>Not empty;</li>
 * </ul>
 * 
 * AddEndEntityRestRequest's endEntityProfileName attribute is validated for:
 * <ul>
 *     <li>Not null;</li>
 *     <li>Not empty;</li>
 * </ul>
 *
 * AddEndEntityRestRequest's token attribute is validated for:
 * <ul>
 *     <li>Not null;</li>
 *     <li>Not empty;</li>
 *     <li>The value has to be one of AddEndEntityRestRequest.TokenTypes.</li>
 * </ul>
 */
@Target({TYPE, FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {ValidAddEndEntityRestRequest.Validator.class})
@Documented
public @interface ValidAddEndEntityRestRequest {

    String message() default "{ValidAddEndEntityRestRequest.invalid.default}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<ValidAddEndEntityRestRequest, AddEndEntityRestRequest> {

        @Override
        public void initialize(final ValidAddEndEntityRestRequest validAddEndEntityRestRequest) {
        }

        @Override
        public boolean isValid(final AddEndEntityRestRequest addEndEntityRestRequest, final ConstraintValidatorContext constraintValidatorContext) {
            if (addEndEntityRestRequest == null) {
                ValidationHelper.addConstraintViolation(constraintValidatorContext, "{ValidAddEndEntityRestRequest.invalid.null}");
                return false;
            }
            final String username = addEndEntityRestRequest.getUsername();
            if (username == null || username.isEmpty()) {
                ValidationHelper.addConstraintViolation(constraintValidatorContext, "{ValidAddEndEntityRestRequest.invalid.username.nullOrEmpty}");
                return false;
            }
            final String subjectDn = addEndEntityRestRequest.getSubjectDn();
            if (subjectDn == null || subjectDn.isEmpty()) {
                ValidationHelper.addConstraintViolation(constraintValidatorContext, "{ValidAddEndEntityRestRequest.invalid.subjectDn.nullOrEmpty}");
                return false;
            }
            final String caName = addEndEntityRestRequest.getCaName();
            if (caName == null || caName.isEmpty()) {
                ValidationHelper.addConstraintViolation(constraintValidatorContext, "{ValidAddEndEntityRestRequest.invalid.caName.nullOrEmpty}");
                return false;
            }
            final String certificateProfileName = addEndEntityRestRequest.getCertificateProfileName();
            if (certificateProfileName == null || certificateProfileName.isEmpty()) {
                ValidationHelper.addConstraintViolation(constraintValidatorContext, "{ValidAddEndEntityRestRequest.invalid.certificateProfileName.nullOrEmpty}");
                return false;
            }
            final String endEntityProfileName = addEndEntityRestRequest.getEndEntityProfileName();
            if (endEntityProfileName == null || endEntityProfileName.isEmpty()) {
                ValidationHelper.addConstraintViolation(constraintValidatorContext, "{ValidAddEndEntityRestRequest.invalid.endEntityProfileName.nullOrEmpty}");
                return false;
            }
            final String tokenValue = addEndEntityRestRequest.getToken();
            if (tokenValue == null || tokenValue.isEmpty()) {
            	ValidationHelper.addConstraintViolation(constraintValidatorContext, "{ValidAddEndEntityRestRequest.invalid.token.nullOrEmpty}");
                return false;
            }
            final AddEndEntityRestRequest.TokenType tokenType = AddEndEntityRestRequest.TokenType.resolveEndEntityTokenByName(tokenValue);
            if (tokenType == null) {
            	ValidationHelper.addConstraintViolation(constraintValidatorContext, "{ValidAddEndEntityRestRequest.invalid.token.unknown}");
                return false;
            }

            return true;
        }
    }
}