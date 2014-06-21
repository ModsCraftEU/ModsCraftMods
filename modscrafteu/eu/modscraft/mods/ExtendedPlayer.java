package eu.modscraft.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import eu.modscraft.mods.network.CreatePacketServerSide;

public class ExtendedPlayer implements IExtendedEntityProperties{

	public final EntityPlayer player;
	public final static String extPropName="ExtendedPlayer";
	public final static String fieldName_currentMana="CurrentMana";
	public final static String fieldName_maxMana="MaxMana";
	public final static String fieldName_thirst="CurrentThirst";
	public static final int MANA_WATCHER=20;
	
	private int maxMana;
	private int currentMana;
	private int currentThirstTick;
	private int currentThirst;
	private final static int thirstActionTick=1000;
	
	//used to easier initialize variables, also aesthetic
	public ExtendedPlayer(EntityPlayer player)
	{
		System.out.println("ExtendedPlayer constructor called!");
		this.player=player;
		this.maxMana=100;
		this.currentMana=100;
		this.currentThirst=20;
		this.currentThirstTick=0;
	}
	//Register Method - also only to make the registering easier and the code looking better
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.extPropName,new ExtendedPlayer(player));
	}
	public void replenishThirst(int amount,Entity entity)
	{
		//System.out.println("this.currentThirst="+this.currentThirst);
		//this.currentThirst=this.currentThirst+amount;
		//if(this.currentThirst>20)this.currentThirst=20;
		//sendThirstUpdate(this.currentThirst,true,entity);
		
	}
	public void handleReceivedThirstUpdate(int newThirstAmount, boolean direction)
	{
		//TODO: Add the actual Code.. without the client wont even see on the GUI (which at the point of this writing isnt created)
		//the thirst update!
		if(direction)
		{
			currentThirst=newThirstAmount;
		}
	}
	public int getThirst()
	{
		return currentThirst;
	}
	public void doThirstOperation(Entity entity)
	{
		//Here we want to make our thirst stuff
		//This variables need more randomisation and of course some tweaking :)
		if(entity instanceof EntityPlayerMP)
		{
			this.currentThirstTick++;
			if(currentThirstTick>=this.thirstActionTick)
			{
				//System.out.println("Thirst Tick 100!");
				if(this.currentThirst>0)
				{
					//We have some thirst we can do stuff to
					this.currentThirst--;
					if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("MODSCRAFT: New Thirst: "+this.currentThirst);
					sendThirstUpdate(currentThirst,false, entity);
				}else{
					//We are out of thirst. Theoretically we should die now! I will set the hunger to zero for our test.
					// par1EntityPlayer.attackEntityFrom(DamageSource.starve, 1.0F);
					if(player.getFoodStats().getFoodLevel()>0){
						((EntityPlayer) entity).getFoodStats().setFoodLevel(0);
						((EntityPlayer) entity).getFoodStats().setFoodSaturationLevel(0);
						((EntityPlayer) entity).getFoodStats().addExhaustion(20);
					}
					
					entity.attackEntityFrom(DamageSource.starve, 4.0F);
					sendThirstUpdate(currentThirst,false, entity);
				}
				this.currentThirstTick=0;
			}
		}
	}
	//To make the code look nicer
	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return(ExtendedPlayer) player.getExtendedProperties(extPropName);
	}
	
	//Save-Method
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		//The Tag Compound is used to save everything
		NBTTagCompound properties=new NBTTagCompound();
		properties.setInteger(fieldName_currentMana, this.currentMana);
		properties.setInteger(fieldName_maxMana, this.maxMana);
		properties.setInteger(fieldName_thirst, this.currentThirst);
		//if we dont have a unique name we might have conflicts - the tutorial says avoiding the use of typical names like item items and co - vanilla conflicts are no good!
		compound.setTag(extPropName,properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		// What we saved in saveNBTData we load here again
		NBTTagCompound properties=(NBTTagCompound) compound.getTag(extPropName);
		//..and then we retrieve everything again!
		this.currentMana=properties.getInteger(fieldName_currentMana);
		this.maxMana=properties.getInteger(fieldName_maxMana);
		this.currentThirst=properties.getInteger(fieldName_thirst);
		//For debugging purposes
	}

	@Override
	public void init(Entity entity, World world) {
		//This seems not to be good for anything
	}
	
	public int getCurrentMana()
	{
		return this.currentMana;
	}
	public int getMaxMana()
	{
		return this.maxMana;
	}
	public final void setCurrentThirst(int amount)
	{
		this.currentThirst=amount;
		
	}
	public final void setCurrentMana(int amount)
	{
		this.currentMana=amount;
	}
	public final void sendManaUpdate()
	{
		CreatePacketServerSide.sendManaUpdate(this.currentMana, (EntityPlayerMP)this.player);
	}
	public final void sendThirstUpdate(int newThirst, boolean direction, Entity entity)
	{
		CreatePacketServerSide.sendThirstUpdate(newThirst, direction, entity);
	}
	public final boolean consumeMana(int amount)
	{
		// This variable makes it easier to write the rest of the method
		int mana = this.currentMana;
		boolean sufficient = amount <= mana;
		mana -= (amount < mana ? amount : mana);
		// Update the data watcher object with the new value
		this.currentMana=mana;
		sendManaUpdate();
		return sufficient;
	}

	public void replenishMana()
	{
		currentMana=maxMana;
		
		sendManaUpdate();
	}
}
