package mods.minecessity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemCactusStick extends Item
{
    protected ItemCactusStick(int i)
    {
        super(i);
		setMaxStackSize(1);
		setMaxDamage(32);
    }
    @Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int k, boolean flag)
    {
		super.onUpdate(itemstack,world,entity,k,flag);
		
		if(itemstack!=null && itemstack.itemID == Minecessity.cactusStick.itemID && entity instanceof EntityPlayer)
		{
			List list = world.getEntitiesWithinAABB(EntityCreature.class,((EntityPlayer)entity).boundingBox.expand(1,1,1));
			if(!list.isEmpty())
			{
				for(int i=0 ; i<list.size() ; i++)
				{
					EntityCreature entities = ((EntityCreature)list.get(i));
					if(!entities.isDead && ((EntityPlayer)entity).ticksExisted%20==0)
					{
						entities.attackEntityFrom(DamageSource.cactus,2);
						itemstack.damageItem(1, (EntityPlayer)entity);
					}
				}
			}
		}
	}
}
