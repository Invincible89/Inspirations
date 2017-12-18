package knightminer.inspirations.plugins.jei;

import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import knightminer.inspirations.building.InspirationsBuilding;
import knightminer.inspirations.common.PulseBase;
import knightminer.inspirations.library.recipe.TextureRecipe;
import knightminer.inspirations.plugins.jei.texture.TextureRecipeHandler;
import knightminer.inspirations.plugins.jei.texture.TextureSubtypeInterpreter;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {
	public static IJeiHelpers jeiHelpers;
	// crafting grid slots, integer constants from the default crafting grid implementation
	private static final int craftOutputSlot = 0;
	private static final int craftInputSlot1 = 1;

	public static ICraftingGridHelper craftingGridHelper;
	public static IRecipeRegistry recipeRegistry;

	@Override
	public void registerItemSubtypes(ISubtypeRegistry registry) {
		TextureSubtypeInterpreter texture = new TextureSubtypeInterpreter();

		// tools
		if(PulseBase.isBuildingLoaded()) {
			// tool tables
			registry.registerSubtypeInterpreter(Item.getItemFromBlock(InspirationsBuilding.bookshelf), texture);
		}
	}

	@Override
	public void register(@Nonnull IModRegistry registry) {
		jeiHelpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

		// crafting helper used by the shaped table wrapper
		craftingGridHelper = guiHelper.createCraftingGridHelper(craftInputSlot1, craftOutputSlot);

		registry.handleRecipes(TextureRecipe.class, new TextureRecipeHandler(), VanillaRecipeCategoryUid.CRAFTING);
	}

	@Override
	public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {
		recipeRegistry = jeiRuntime.getRecipeRegistry();
	}
}