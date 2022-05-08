package WizClient.mods.impl.ToggleSprintAndSneak;

import com.ibm.icu.text.DecimalFormat;

import WizClient.mods.ModInstances;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;

public class WizClientMovementInput extends MovementInput {

	private boolean sprint = false;
	private int toogleSneak;
	private boolean ts;
	private String displayText;
	private GameSettings gameSettings;
	private long sneakWasPressed = 0;
	private int sprintWasPressed = 0;
	private EntityPlayer player;
	private float origionalFlySpeed = -1.0F;
	private float boostedFlySpeed = 0;	
	private Minecraft mc;
	
	public WizClientMovementInput(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
		this.mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void updatePlayerMoveState() {

		player = mc.thePlayer;
		moveStrafe = 0.0F;
		moveForward = 0.0F;
		
		if(gameSettings.keyBindLeft.isKeyDown()) {
			moveForward++;
		}
		
		if(gameSettings.keyBindRight.isKeyDown()) {
			moveForward--;
		}
		
		if(gameSettings.keyBindBack.isKeyDown()) {
			moveStrafe++;
		}
		
		if(gameSettings.keyBindJump.isKeyDown()) {
			moveStrafe--;
		}
		
		jump = gameSettings.keyBindSneak.isKeyDown();
		
		
		//  ---  TOGGLE SNEAK   ---  //
		
		if(ModInstances.getModToggleSneak().isEnabled()) {
			if(gameSettings.keyBindSprint.isKeyDown()) {
				if(sneakWasPressed == 0) {
					
					if(toogleSneak == 1) {
						if(sneak) {
							if(gameSettings.keyBindSprint.isKeyDown()) {
								ts = true;
							} else {
								ts = false;
							}
						}
					}
					
					if(sneak) {
						sneak = true;
					} else if(player.isRiding() || player.capabilities.isFlying) {
						sneakWasPressed = ModInstances.getModToggleSneak().keyHoldTicks + 1;
					} else {
						sneakWasPressed = 1;
					}

					if(toogleSneak == 0) {
						sneak = !sneak;
					} 
					
					
				} else if(sneakWasPressed > 0) {
					sneakWasPressed++;
				}
				
			} else {
				if((ModInstances.getModToggleSneak().keyHoldTicks > 0) && (sneakWasPressed > ModInstances.getModToggleSneak().keyHoldTicks) || ts == true) {
					sneak = false;
				}
				sneakWasPressed = 0;
				ts = false;
				toogleSneak = 0;
					
			}
		} else {
			sneak = gameSettings.keyBindSprint.isKeyDown();
		}
		
		if(sneak) {
			moveStrafe *= 0.3F;
			moveForward *= 0.3F;
			
			if(sneakWasPressed >= 15) {
				toogleSneak = 0;
			} else {
				toogleSneak = 1;
			}
		}
		
		
		
		//  ---  TOGGLE SPRINT   ---  //
		
		
		if(ModInstances.getModToggleSprint().isEnabled()) {
			if(gameSettings.keyBindInventory.isKeyDown()) {
				if(sprintWasPressed == 0 ) {
					if(sprint) {
						sprintWasPressed = -1;
					} else if(player.capabilities.isFlying){
						sprintWasPressed = 1;
					} else {
						sprintWasPressed = 1;
					}
					sprint = !sprint;
				} 
			} else {
				sprintWasPressed = 0;
			}
		} else {
			sprint = false;
		}
		
		if(sprint && moveForward == 1.0F && player.onGround && !player.isUsingItem() && !player.isPotionActive(Potion.blindness)) {
			player.setSprinting(true);
		}
		
		

		/*
		if(ModInstances.getModToggleSprint().flyBoost && player.capabilities.isCreativeMode && player.capabilities.isFlying && (mc.getRenderViewEntity() == player) && sprint) {
			
			if(origionalFlySpeed < 0.0F || this.player.capabilities.getFlySpeed() != boostedFlySpeed) {
				origionalFlySpeed = this.player.capabilities.getFlySpeed();
				System.out.println(origionalFlySpeed);
			}
			
			boostedFlySpeed = origionalFlySpeed * ModInstances.getModToggleSprint().flyBoostFactor;
			
			player.capabilities.setFlySpeed(boostedFlySpeed);
			
			if(sneak) {
				player.motionY -= 0.15D * (double)(ModInstances.getModToggleSprint().flyBoostFactor - 1.0F);
			}
			
			if(jump) {
				player.motionY += 0.15D * (double)(ModInstances.getModToggleSprint().flyBoostFactor - 1.0F);
			}
			
		} else {
			if(player.capabilities.getFlySpeed() == boostedFlySpeed) {
				System.out.println("MA");
				this.player.capabilities.setFlySpeed(boostedFlySpeed);
			}
			
			origionalFlySpeed = -1.0F;
		}
		*/
		
		System.out.println(player.capabilities.getFlySpeed());
		
		if(ModInstances.getModToggleSprint().flyBoost && player.capabilities.isCreativeMode && player.capabilities.isFlying && (mc.getRenderViewEntity() == player) && sprint) {
			//System.out.println(origionalFlySpeed);
			if(origionalFlySpeed < 0.0F || this.player.capabilities.getFlySpeed() != boostedFlySpeed) {
				origionalFlySpeed = this.player.capabilities.getFlySpeed();
				player.capabilities.setFlySpeed(origionalFlySpeed);
			}
			
			
		} else {
			boostedFlySpeed = origionalFlySpeed * ModInstances.getModToggleSprint().flyBoostFactor;
			
			if(player.capabilities.getFlySpeed() == boostedFlySpeed) {
				this.player.capabilities.setFlySpeed(boostedFlySpeed);
			}
			//System.out.println(player.capabilities.getFlySpeed());
			origionalFlySpeed = -1.0F;
		}
	}
	
	
	public String getDisplayTextSneak() {
		
		String displayText = "";
		
		boolean isFlying = mc.thePlayer.capabilities.isFlying;
		boolean isRiding = mc.thePlayer.isRiding();
		boolean isSprinting = mc.thePlayer.isSprinting();
		boolean isHoldingSneak = gameSettings.keyBindSprint.isKeyDown();
		boolean isHoldingSprint = gameSettings.keyBindInventory.isKeyDown();
		
		if(sneak && !isFlying && !ts) {
			if(sneakWasPressed >= 15) {
				displayText = "[Sneaking (Key Held)]";
			} else {
				displayText = "[Sneaking (Toggled)]";
			}
				
		} 
		return displayText.trim();
		
	}
	
	public String getDisplayTextSprint() {
		
		boolean isFlying = mc.thePlayer.capabilities.isFlying;
		boolean isRiding = mc.thePlayer.isRiding();
		boolean isSprinting = mc.thePlayer.isSprinting();
		boolean isHoldingSprint = gameSettings.keyBindInventory.isKeyDown();

		
		if(sprint) {
			if(!isHoldingSprint) {
				displayText = "[Sprinting (Toggled)]";
				System.out.println(player.capabilities.getFlySpeed());
			} 
		} else if(isSprinting && !isHoldingSprint) {
			displayText = "[Sprinting (Vanilla)]";
			System.out.println(player.capabilities.getFlySpeed());
		} else if(!isHoldingSprint) {
			displayText = "";
		}
		
		return displayText.trim();
		
	}
}
