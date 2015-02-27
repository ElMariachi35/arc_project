package model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = Allergen.FIND_BY_LETTER, query = "SELECT a FROM Allergen a WHERE a.letter=:"
		+ Allergen.PARAM_LETTER)
public class Allergen extends BaseEntity {
	public static final String FIND_BY_LETTER = "Allergen.findByLetter";
	public static final String PARAM_LETTER = "letter";

	private String letter;
	private String name;

	public Allergen() {
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
