package xyz.brassgoggledcoders.boilerplate.proxies;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import xyz.brassgoggledcoders.boilerplate.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.client.events.ClientEventsHandler;
import xyz.brassgoggledcoders.boilerplate.client.events.ModelBakeHandler;
import xyz.brassgoggledcoders.boilerplate.client.manual.ClientTickHandler;
import xyz.brassgoggledcoders.boilerplate.client.manual.GuiLexicon;
import xyz.brassgoggledcoders.boilerplate.client.renderers.ISpecialRenderedItem;
import xyz.brassgoggledcoders.boilerplate.client.renderers.ItemSpecialRenderStore;
import xyz.brassgoggledcoders.boilerplate.client.renderers.ItemSpecialRenderer;
import xyz.brassgoggledcoders.boilerplate.client.renderers.TESRLoader;

import java.util.ArrayList;
import java.util.List;

public class ClientProxy extends CommonProxy {
	private TESRLoader tesrLoader;

	public static void registerFluidModel(Block fluidBlock, final ModelResourceLocation loc) {
		Item fluidItem = Item.getItemFromBlock(fluidBlock);
		ModelBakery.registerItemVariants(fluidItem);
		ModelLoader.setCustomMeshDefinition(fluidItem, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return loc;
			}
		});
		ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return loc;
			}
		});
	}

	public static <T extends Enum<T> & IStringSerializable> void registerVariantsDefaulted(Block b, Class<T> enumclazz,
			String variantHeader) {
	}

	@Override
	public String translate(String text) {
		return I18n.format("boilerplate." + text);
	}

	@Override
	public void loadItemModel(Item item, int metadata, ResourceLocation resourceLocation) {
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourceLocation, "inventory");
		ModelLoader.setCustomModelResourceLocation(item, metadata, modelResourceLocation);
	}

	@Override
	public void addVariantName(Item item, String... variantNames) {
		ModelResourceLocation[] modelResourceLocations = new ModelResourceLocation[variantNames.length];
		for(int i = 0; i < modelResourceLocations.length; i++) {
			modelResourceLocations[i] = new ModelResourceLocation(mod.getPrefix() + variantNames[i]);
		}
		ModelBakery.registerItemVariants(item, modelResourceLocations);
	}

	@Override
	public void registerItemModelVariant(Item item, int metadata, String itemModelName) {
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(mod.getPrefix() + itemModelName);
		ClientHelper.getItemModelMesher().register(item, metadata, modelResourceLocation);
	}

	@Override
	@SuppressWarnings({"unchecked", "deprecation"})
	public void registerISpecialRendererItem(Item item) {
		ISpecialRenderedItem specialRenderItem = (ISpecialRenderedItem) item;
		ItemSpecialRenderer renderer = ItemSpecialRenderStore.getItemSpecialRenderer((ISpecialRenderedItem) item);
		ClientRegistry.bindTileEntitySpecialRenderer(renderer.getTileClass(), renderer);
		int length = specialRenderItem.getResourceLocations().length;
		ModelResourceLocation[] modelLocations = new ModelResourceLocation[length];

		for(int i = 0; i < length; i++) {
			modelLocations[i] = new ModelResourceLocation(mod.getPrefix() + specialRenderItem.getResourceLocations()[i],
					"inventory");
		}

		for(int i = 0; i < length; i++) {
			ModelBakeHandler.getInstance().registerModelToSwap(modelLocations[i], renderer);
			ForgeHooksClient.registerTESRItemStack(item, i, renderer.getTileClass());
		}
	}

	@Override
	public void initTESRLoader(ASMDataTable dataTable) {
		tesrLoader = new TESRLoader(dataTable);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void registerTESR(String name, Item item, Class<? extends TileEntity> tileEntityClass) {
		tesrLoader.registerTESRToTile(name, tileEntityClass);
		List<ItemStack> allSubItems = new ArrayList<>();
		item.getSubItems(item, item.getCreativeTab(), allSubItems);
		allSubItems.forEach(itemStack -> ForgeHooksClient.registerTESRItemStack(itemStack.getItem(),
				itemStack.getItemDamage(), tileEntityClass));
	}

	@Override
	public void registerEvents() {
		MinecraftForge.EVENT_BUS.register(new ClientEventsHandler());
		MinecraftForge.EVENT_BUS.register(new ClientTickHandler()); // TODO
		MinecraftForge.EVENT_BUS.register(ModelBakeHandler.getInstance());
	}

	@Override
	public void setLexiconStack(ItemStack stack) {
		GuiLexicon.stackUsed = stack;
	}
}
