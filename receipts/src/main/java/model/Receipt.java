package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Receipt extends BaseEntity{

	private String receiptName;
	@Enumerated(EnumType.STRING)
	private MenuType menuType;
	private boolean vitalMenu;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "receipt_id", nullable = false)
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	public Receipt() {
	}

	public String getReceiptName() {
		return receiptName;
	}

	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

	public boolean isVitalMenu() {
		return vitalMenu;
	}

	public void setVitalMenu(boolean vitalMenu) {
		this.vitalMenu = vitalMenu;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}