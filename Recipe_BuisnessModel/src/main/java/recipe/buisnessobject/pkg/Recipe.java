package recipe.buisnessobject.pkg;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Recipe", uniqueConstraints = { @UniqueConstraint(columnNames = { "IdRecipe" }) })
public class Recipe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdRecipe", nullable = false, unique = true, length = 11)
	private Long id;

	@Column(name="Name", nullable = false, length = 250 )
	private String name;
	
	@Column(name="Componenent", nullable = false, length = 450 )
	private String component;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable = false)
	private RecipeCategory recipeCategory;

	public Recipe() {

	}

	public Recipe(Long id, String name, String component, RecipeCategory recipeCategory) {
		super();
		this.id = id;
		this.name = name;
		this.component = component;
		this.recipeCategory = recipeCategory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComponent() {
		return component;
	}

	public void setComponents(String component) {
		this.component = component;
	}

	public RecipeCategory getRecipeCategory() {
		return recipeCategory;
	}

	public void setRecipeCategory(RecipeCategory recipeCategory) {
		this.recipeCategory = recipeCategory;
	}

}
