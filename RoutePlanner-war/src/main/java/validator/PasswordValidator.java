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
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */

@FacesValidator("PasswordValidator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {

        String password = value.toString();
        String validPasswordPattern = "^[A-Za-z0-9]+$";

        if( !password.matches(validPasswordPattern) ){

            FacesMessage message = new FacesMessage("Invalid Password, Should Contain Only Alphabet and Numeric");

            message.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(message);
        }
    }
}