package controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import service.AllergenService;
import model.Allergen;

@FacesConverter("allergenConverter")
public class AllergenConverter implements Converter{

	@Inject 
	private AllergenService allergenService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return allergenService.findByLetter(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Allergen) value).getLetter();
	}

}
