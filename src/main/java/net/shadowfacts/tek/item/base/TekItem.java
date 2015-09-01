package net.shadowfacts.tek.item.base;

import net.minecraft.item.Item;
import net.shadowfacts.tek.Tek;

/**
 * @author shadowfacts
 */
public class TekItem extends Item {

	protected String name;
	protected String texturePath;

	public TekItem(String name) {
		this.name = name;
		setUnlocalizedName("tek." + name);
	}

	public String getName() {
		return name;
	}

	public String getTexturePath() {
		return texturePath;
	}

	public void setTexturePath(String texturePath) {
		this.texturePath = texturePath;
	}

	@Override
	protected String getIconString() {
		return Tek.modId + ":" + getTexturePath() + getName();
	}
}
