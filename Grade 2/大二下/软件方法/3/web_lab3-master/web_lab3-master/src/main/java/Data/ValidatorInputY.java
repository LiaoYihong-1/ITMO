package Data;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;
@FacesValidator("validatorInputY")
public class ValidatorInputY implements Validator{
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        if (value == null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"y validation failed","field must not be null");
            throw new ValidatorException(msg);
        }
        try{
            double y = Double.parseDouble(value.toString());
            System.out.print(y+"\n");
            if (y > 3.0 || y < -3.0){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"y validation failed","y must be in range (-3;3)");
                throw new ValidatorException(msg);
            }
        } catch (NumberFormatException e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"y validation failed","y must be a number");
            throw new ValidatorException(msg);
        }
    }
}
