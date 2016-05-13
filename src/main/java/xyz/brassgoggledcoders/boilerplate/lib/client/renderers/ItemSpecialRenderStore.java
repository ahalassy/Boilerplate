package xyz.brassgoggledcoders.boilerplate.lib.client.renderers;

import net.minecraft.item.Item;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.Utils;

import java.util.HashMap;

public class ItemSpecialRenderStore
{
	private static ItemSpecialRenderStore instance;
	private static HashMap<String, ItemSpecialRenderer> renderersLoaded = new HashMap<String, ItemSpecialRenderer>();

	public static ItemSpecialRenderStore getInstance()
	{
		if(instance == null)
		{
			instance = new ItemSpecialRenderStore();
		}
		return instance;
	}

	public static ItemSpecialRenderer getItemSpecialRenderer(ISpecialRenderedItem item)
	{
		return getItemSpecialRenderer(item.getSpecialRendererPath());
	}

	public static ItemSpecialRenderer getItemSpecialRenderer(String path)
	{
		if(!renderersLoaded.containsKey(path))
		{
			Object renderer = Utils.createObjectInstance(path);
			if(renderer instanceof ItemSpecialRenderer)
			{
				renderersLoaded.put(path, (ItemSpecialRenderer)renderer);
			}
		}
		return renderersLoaded.get(path);
	}
}