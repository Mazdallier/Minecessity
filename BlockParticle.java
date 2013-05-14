package mods.minecessity;import java.util.Random;import net.minecraft.block.BlockContainer;import net.minecraft.block.material.Material;import net.minecraft.entity.player.EntityPlayer;import net.minecraft.src.ModLoader;import net.minecraft.tileentity.TileEntity;import net.minecraft.world.World;public class BlockParticle extends BlockContainer{    protected BlockParticle(int i)    {		super(i,Material.ground);		setTickRandomly(true);    }    @Override	public int tickRate(World world)    {        return 1;    }    @Override	public void onNeighborBlockChange(World world, int i, int j, int k, int l)    {		if(powered(world,i,j,k)) 			shootParticles(world,i,j,k);    }    @Override	public void randomDisplayTick(World world, int i, int j, int k, Random random)    {		if(powered(world,i,j,k)) 			shootParticles(world,i,j,k);    }    @Override	public void updateTick(World world, int i, int j, int k, Random random)    {		if(powered(world,i,j,k))			shootParticles(world,i,j,k);    }		public boolean powered(World world,int i,int j,int k)	{		return world.getBlockPowerInput(i,j,k)!=0 || world.isBlockIndirectlyGettingPowered(i,j,k);	}		public void shootParticles(World world, int i,int j,int k)	{		TileEntityParticleBlock tileparticle = (TileEntityParticleBlock)world.getBlockTileEntity(i, j, k);		tileparticle.shootParticles(world,i,j,k);	}	@Override	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)    {        if(world.isRemote)        {            return true;        } else        {            TileEntityParticleBlock tileparticle = (TileEntityParticleBlock)world.getBlockTileEntity(i, j, k);			ModLoader.openGUI(entityplayer, new GuiParticleBlock(tileparticle));            return true;        }    }	@Override	public TileEntity createNewTileEntity(World world)    {        return new TileEntityParticleBlock();    }}