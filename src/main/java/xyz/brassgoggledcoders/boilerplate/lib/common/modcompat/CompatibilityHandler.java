package xyz.brassgoggledcoders.boilerplate.lib.common.modcompat;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.lib.common.config.Type;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ConfigRegistry;

import java.util.HashMap;

/**
 * @author SkySom
 */
public class CompatibilityHandler
{
	private HashMap<String, ModCompat> modCompatEnabled = new HashMap<String, ModCompat>();

	public HashMap<String, ModCompat> getModCompatEnabled()
	{
		return modCompatEnabled;
	}

	public void addModCompat(ModCompat modCompat)
	{
		modCompatEnabled.put(modCompat.getName(), modCompat);
	}

	public void preInit(FMLPreInitializationEvent event)
	{
		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			if (!modCompat.areRequirementsMet() && modCompat.getIsActive())
			{
				modCompat.setIsActive(false);
				BoilerplateLib.getLogger().error("Requirements are not met for " + modCompat.getName() + ". Deactivating");
			}
			if (modCompat.getIsActive())
			{
				BoilerplateLib.getLogger().info("Loading " + modCompat.getName() + " module");
			}
		}

		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			if (modCompat.getIsActive())
			{
				modCompat.preInit(event);
			}
		}
	}

	public void init(FMLInitializationEvent event)
	{
		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			if (modCompat.getIsActive())
			{
				modCompat.init(event);
			}
		}
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			if (modCompat.getIsActive())
			{
				modCompat.postInit(event);
			}
		}
	}

	public void clientInit(FMLInitializationEvent event)
	{
		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			if (modCompat.getIsActive())
			{
				modCompat.clientInit(event);
			}
		}
	}

	public Configuration configureModCompat(Configuration configuration)
	{
		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			ConfigRegistry.addEntry(modCompat.getName(),
					new ConfigEntry("ModCompat", modCompat.getName() + " Enabled", Type.BOOLEAN, "true"));
			modCompat.setIsActive(ConfigRegistry.getBoolean(modCompat.getName(), true));
		}
		return configuration;
	}

	public ModCompat getModCompat(String name)
	{
		return modCompatEnabled.get(name);
	}

	public boolean isModCompatEnabled(String name)
	{
		if(getModCompat(name) != null)
		{
			return getModCompat(name).getIsActive();
		}
		return false;
	}

}
