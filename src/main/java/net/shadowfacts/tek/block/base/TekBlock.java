package net.shadowfacts.tek.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.shadowfacts.tek.Tek;

/**
 * @author shadowfacts
 */
public class TekBlock extends Block {

	protected String name;
	protected String texturePath;

	protected boolean isNormalBlock;

	public TekBlock(Material material, String name) {
		super(material);
		this.name = name;
		setBlockName("tek." + name);
	}

	public TekBlock(String name) {
		this(Material.rock, name);
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

	public boolean isNormalBlock() {
		return isNormalBlock;
	}

	public void setIsNormalBlock(boolean isNormalBlock) {
		this.isNormalBlock = isNormalBlock;
	}

	@Override
	protected String getTextureName() {
		return Tek.modId + ":" + getTexturePath() + name;
	}

	@Override
	public boolean isOpaqueCube() {
		return isNormalBlock();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return isNormalBlock();
	}
}
