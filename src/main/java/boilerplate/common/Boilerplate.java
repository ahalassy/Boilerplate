/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

import net.minecraftforge.common.config.Configuration;

/**
 * @author Surseance
 *
 */
@Mod(modid = "boilerplate", name = "Boilerplate", version = "6.0.0", dependencies = "after:BuildCraft|Core; after:TConstruct; after:ForgeMultipart; after:MineFactoryReloaded")
public class Boilerplate
{
	/**
	 * warlordjones - c2e83bd4-e8df-40d6-a639-58ba8b05401e
	 *
	 * decebaldecebal - 5eed1615-0ec9-4f4b-a4c9-58454ad5b04f
	 *
	 * Sky_Som - 27672103-b8c7-400d-8817-49de433336dd
	 *
	 * Snurly - ???
	 */

	public static String[] donors = {};
	public static String[] devs = { "c2e83bd4-e8df-40d6-a639-58ba8b05401e", "5eed1615-0ec9-4f4b-a4c9-58454ad5b04f",
			"27672103-b8c7-400d-8817-49de433336dd" };

	public static int trailParticles;

	@SidedProxy(clientSide = "boilerplate.client.ClientProxy", serverSide = "boilerplate.common.CommonProxy")
	public static CommonProxy proxy;

	@Instance("boilerplate")
	public static Boilerplate instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		// TODO: particles config option on client only
		trailParticles = config.get("general", "numberOfParticlesInDonorTrails", 0, "0 to disable").getInt();
		config.save();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(new ForgeEventHandler());
		proxy.registerRenderHandlers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		FMLLog.getLogger().info("GNU Terry Prachett");
		/*
		 * NBTTagCompound tag = new NBTTagCompound(); NBTTagCompound item1 = new
		 * NBTTagCompound(); new ItemStack(Items.cake).writeToNBT(item1);
		 * item1.setTag("input1", tag); NBTTagCompound item2 = new
		 * NBTTagCompound(); new ItemStack(Items.apple).writeToNBT(item2);
		 * item2.setTag("input2", tag); NBTTagCompound item3 = new
		 * NBTTagCompound(); new
		 * ItemStack(Items.baked_potato).writeToNBT(item3);
		 * item3.setTag("result", tag);
		 * FMLInterModComms.sendMessage("steamcraft2", "addBloomeryRecipe",
		 * tag);
		 */
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
	}
}
