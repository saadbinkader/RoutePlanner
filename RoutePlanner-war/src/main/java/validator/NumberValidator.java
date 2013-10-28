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
 * Time: 12:22 AM
 * To change this template use File | Settings | File Templates.
 */

@FacesValidator("NumberValidator")
public class NumberValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {

        String number = value.toString();
        String validNumberPattern = "^[0-9]{1,3}$";

        if( !number.matches(validNumberPattern) ){

            FacesMessage message = new FacesMessage("Invalid Number, Number Too Big For Transport Capacity");

            message.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(message);
        }
    }
}
