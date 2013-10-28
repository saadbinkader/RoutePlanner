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
 * Time: 12:04 AM
 * To change this template use File | Settings | File Templates.
 */

@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

     @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {

        String email = value.toString();
        String validEmailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";

        if( !email.matches(validEmailPattern) ){

            FacesMessage message = new FacesMessage("Invalid Email");

            message.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(message);
        }
    }
}