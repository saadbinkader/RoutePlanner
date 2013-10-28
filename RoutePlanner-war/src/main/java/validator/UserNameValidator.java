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
 * Date: 10/13/13
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */

@FacesValidator("UserNameValidator")
public class UserNameValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {

        String userName = value.toString();
        String validUserNamePattern = "^[A-Za-z]+[A-Za-z0-9]*$";

        if( !userName.matches(validUserNamePattern) ){

            FacesMessage message;

            String startingWithNumericPattern = "^[0-9]+.*";

            if(userName.matches(startingWithNumericPattern))
                message = new FacesMessage("Invalid UserName, Should Not Start With Numeric");
            else
                message = new FacesMessage("Invalid UserName, Should Contain Only Alphabet and Numeric");

            message.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(message);
        }
    }
}
