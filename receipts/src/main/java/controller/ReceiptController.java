package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Allergen;
import model.Ingredient;
import model.MenuType;
import model.Receipt;
import service.AllergenService;
import service.ReceiptService;
import cdi.Controller;

@Controller
public class ReceiptController implements Serializable {

	@Inject
	private ReceiptService receiptService;
	@Inject
	private AllergenService allergenService;

	private Receipt receipt;
	private Ingredient ingredient;

	@PostConstruct
	public void intializeReceipt() {
		receipt = new Receipt();
		ingredient = new Ingredient();
	}

	@Transactional
	public void save() {
		receiptService.save(receipt);
		receipt = new Receipt();
	}

	public void addIngredient() {
		receipt.getIngredients().add(ingredient);
		ingredient = new Ingredient();
	}

	public void removeIngredient(Ingredient ingredientToRemove) {
		receipt.getIngredients().remove(ingredientToRemove);
	}

	public List<SelectItem> getMenuTypes() {
		List<SelectItem> menuTypes = new ArrayList<SelectItem>();
		for (MenuType menuType : MenuType.values()) {
			menuTypes.add(new SelectItem(menuType, menuType.getDisplayName()));
		}
		return menuTypes;
	}

	public List<SelectItem> getAllergens() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Allergen allergen : allergenService.findAll()) {
			items.add(createSelectItem(allergen));
		}
		return items;
	}

	private SelectItem createSelectItem(Allergen allergen) {
		return new SelectItem(allergen, String.format("%s - %s",
				allergen.getLetter(), allergen.getName()));
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
}
