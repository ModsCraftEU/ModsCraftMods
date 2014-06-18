package eu.modscraft.mods;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import eu.modscraft.mods.network.CreatePacketServerSide;

public class ExtendedPlayer implements IExtendedEntityProperties{

	public final EntityPlayer player;
	public final static String extPropName="ExtendedPlayer";
	public final static String fieldName_currentMana="CurrentMana";
	public final static String fieldName_maxMana="MaxMana";
	public static final int MANA_WATCHER=20;
	
	private int maxMana;
	private int currentMana;
	private int currentThirstTick;
	private final static int thirstActionTick=500;
	
	//used to easier initialize variables, also aesthetic
	public ExtendedPlayer(EntityPlayer player)
	{
		System.out.println("ExtendedPlayer constructor called!");
		this.player=player;
		this.maxMana=100;
		this.currentMana=100;
		this.currentThirstTick=0;
	}
	//Register Method - also only to make the registering easier and the code looking better
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.extPropName,new ExtendedPlayer(player));
	}
	public void handleReceivedThirstUpdate(int newThirstAmount, boolean direction)
	{
		//TODO: Add the actual Code.. without the client wont even see on the GUI (which at the point of this writing isnt created)
		//the thirst update!
	}
	public void doThirstOperation()
	{
		//Here we want to make our thirst stuff
		//This variables need more randomisation and of course some tweaking :)
		this.currentThirstTick++;
		if(currentThirstTick>=this.thirstActionTick)
		{
			//System.out.println("Thirst Tick 100!");
			this.currentThirstTick=0;
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
	public final void setCurrentMana(int amount)
	{
		this.currentMana=amount;
		
	}
	public final void sendManaUpdate()
	{
		CreatePacketServerSide.sendManaUpdate(this.currentMana, (EntityPlayerMP)this.player);
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
