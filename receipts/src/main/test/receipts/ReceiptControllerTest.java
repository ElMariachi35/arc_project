package receipts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Allergen;
import model.Ingredient;
import model.MenuType;
import model.Receipt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.AllergenService;
import service.ReceiptService;
import controller.ReceiptController;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptControllerTest {

	private static final long INGREDIENT_ID = 2l;
	private static final long RECEIPT_ID = 1l;
	
	@Mock
	private ReceiptService receiptService;
	@Mock
	private AllergenService allergenService;
	
	@InjectMocks
	private ReceiptController receiptController;
	
	private Receipt receipt= new Receipt();;
	private Ingredient ingredient= new Ingredient();
	
	@Before
	public void setUp(){
		receipt.setId(RECEIPT_ID);
		ingredient.setId(INGREDIENT_ID);
	}
	
	@Test
	public void receiptIsInitialized() {
		receiptController.setReceipt(receipt);
		receiptController.intializeReceipt();
		assertNotEquals(receipt, receiptController.getReceipt());
	}
	
	@Test
	public void ingredientIsInitialized() {
		receiptController.setIngredient(ingredient);
		receiptController.intializeReceipt();
		assertNotEquals(ingredient, receiptController.getIngredient());
	}
	
	@Test
	public void onSaveReceiptServiceIsCalled() {
		receiptController.setReceipt(receipt);
		receiptController.save();
		verify(receiptService).save(receipt);
	}
	
	@Test
	public void onSaveReceiptIsRenewed() {
		receiptController.setReceipt(receipt);
		receiptController.setIngredient(ingredient);
		receiptController.save();
		assertNotEquals(receipt, receiptController.getReceipt());
		assertNotEquals(ingredient, receiptController.getIngredient());
	}
	
	@Test
	public void anIngredientCanBeAddedToReceipt() {
		receiptController.setReceipt(receipt);
		receiptController.setIngredient(ingredient);		
		assertEquals(0, receiptController.getReceipt().getIngredients().size());

		receiptController.addIngredient();
		assertEquals(1, receiptController.getReceipt().getIngredients().size());
		assertEquals(ingredient, receiptController.getReceipt().getIngredients().get(0));
	}
	
	@Test
	public void theIngredientIsRenewedAfterItIsAddedToReceipt() {
		receiptController.setReceipt(receipt);
		receiptController.setIngredient(ingredient);		
		receiptController.addIngredient();
		assertNotEquals(ingredient, receiptController.getIngredient());
	}
	
	@Test
	public void anIngredientCanBeRemovedFromReceipt() {
		receipt.addIngredient(ingredient);
		receiptController.setReceipt(receipt);
		receiptController.removeIngredient(ingredient);
		assertEquals(0, receiptController.getReceipt().getIngredients().size());
	}
	
	@Test
	public void menuTypeSelectItemsAreReturned() {
		List<SelectItem> menuTypes = receiptController.getMenuTypes();
		assertEquals(MenuType.STARTER.getDisplayName(), menuTypes.get(0).getLabel());
		assertEquals(MenuType.MAIN.getDisplayName(), menuTypes.get(1).getLabel());
		assertEquals(MenuType.DESSERT.getDisplayName(), menuTypes.get(2).getLabel());
		
		assertEquals(MenuType.STARTER, menuTypes.get(0).getValue());
		assertEquals(MenuType.MAIN, menuTypes.get(1).getValue());
		assertEquals(MenuType.DESSERT, menuTypes.get(2).getValue());
	}
	
	@Test
	public void allergenSelectItemsAreReturned() {
		Allergen allergen1 = createAllergen("A", "Allergen A");
		Allergen allergen2 = createAllergen("B", "Allergen B");
		when(allergenService.findAll()).thenReturn(Arrays.asList(allergen1, allergen2));
		List<SelectItem> allergenSelectItems = receiptController.getAllergenSelectItems();
		assertEquals("A - Allergen A", allergenSelectItems.get(0).getLabel());
		assertEquals("B - Allergen B", allergenSelectItems.get(1).getLabel());
		assertEquals(allergen1, allergenSelectItems.get(0).getValue());
		assertEquals(allergen2, allergenSelectItems.get(1).getValue());
	}

	private Allergen createAllergen(String letter, String name) {
		Allergen allergen = new Allergen();
		allergen.setLetter(letter);
		allergen.setName(name);
		return allergen;
	}
}
