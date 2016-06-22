package xyz.brassgoggledcoders.boilerplate;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.proxies.CommonProxy;

@Mod(modid = Boilerplate.ID, name = Boilerplate.NAME, version = Boilerplate.VERSION,
		dependencies = Boilerplate.DEPENDENCIES)
public class Boilerplate extends BoilerplateModBase {
	public final static String ID = "boilerplate";
	public final static String NAME = "Boilerplate";
	public final static String VERSION = "@VERSION@";
	public final static String DEPENDENCIES = "";

	/**
	 * warlordjones - c2e83bd4-e8df-40d6-a639-58ba8b05401e
	 * decebaldecebal - 5eed1615-0ec9-4f4b-a4c9-58454ad5b04f
	 * Sky_Som - 27672103-b8c7-400d-8817-49de433336dd
	 * Snurly - ???
	 */

	public static String[] donors = {};
	public static String[] devs = {"c2e83bd4-e8df-40d6-a639-58ba8b05401e", "5eed1615-0ec9-4f4b-a4c9-58454ad5b04f",
			"27672103-b8c7-400d-8817-49de433336dd"};

	@SidedProxy(clientSide = "xyz.brassgoggledcoders.boilerplate.proxies.ClientProxy",
			serverSide = "xyz.brassgoggledcoders.boilerplate.proxies.CommonProxy")
	public static CommonProxy proxy;

	@Instance(Boilerplate.ID)
	public static Boilerplate instance;

	public static CreativeTabs tabOres;

	public Boilerplate() {
		super(Boilerplate.ID, Boilerplate.NAME, Boilerplate.VERSION, CreativeTabs.MISC);
	}

	@Override
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		proxy.registerBlockModels();
	}

	@Override
	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@Override
	protected void modPostInit(FMLPostInitializationEvent event) {
		this.getLogger().info("GNU Terry Prachett");
	}

	@Override
	public Object getInstance() {
		return instance;
	}

	@Override
	public CreativeTabs getCreativeTab() {
		return tabOres;
	}

	public static class TabOres extends BaseCreativeTab {
		public TabOres() {
			super("boilerplate");
		}

		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(MaterialsModule.metal_ore);
		}

	}
}
