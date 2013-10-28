package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created with IntelliJ IDEA.
 * User: saad
 * Date: 10/14/13
 * Time: 12:58 AM
 * To change this template use File | Settings | File Templates.
 */

@FacesValidator("InputFieldValidator")
public class InputFieldValidator  implements Validator {

    private String inputField;

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {

        inputField = value.toString();

        checkIfStartsWithWhiteSpace();

        checkIfHasValidCharacters();

        checkIfHasConjugativeWhitespaces();

    }

    private void checkIfStartsWithWhiteSpace() {

        String startsWithSpacePattern = "^[\\s]+.*$";

        if(inputField.matches(startsWithSpacePattern))
            throwValidationException("Invalid Input, Should Not Start With Whitespace");

    }

    private void checkIfHasValidCharacters() {

        String validCharacterPattern = "^[\\sA-Za-z0-9]+$";

        if(!inputField.matches(validCharacterPattern))
            throwValidationException("Invalid Input, Should Contain Only Alphabet, Numeric and Whitespace");

    }

    private  void checkIfHasConjugativeWhitespaces() {

        String conjugativeWhitespacesPattern = ".*[\\s]{2,}.*";

        if(inputField.matches(conjugativeWhitespacesPattern))
            throwValidationException("Invalid Input, Conjugative Whitespaces");

    }

    public void throwValidationException(String message) {

        FacesMessage facesMessage = new FacesMessage(message);
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(facesMessage);

    }
}