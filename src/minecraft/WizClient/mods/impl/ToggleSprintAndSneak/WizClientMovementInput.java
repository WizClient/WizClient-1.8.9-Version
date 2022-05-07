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
	private GameSettings gameSettings;
	private int sneakWasPressed = 0;
	private int sprintWasPressed = 0;
	private EntityPlayer player;
	private float origionalFlySpeed = -1.0F;
	private float boostedFlySpeed = 0;	
	private Minecraft mc;
	
	private static final DecimalFormat df = new DecimalFormat("#.0");
	
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
		
		/*
		if(ModInstances.getModToggleSneak().isEnabled()) {
			if(gameSettings.keyBindSprint.isKeyDown()) {
				if(sneakWasPressed == 0) {
					if(sneak) {
						sneakWasPressed = -1;
					} else if(player.isRiding() || player.capabilities.isFlying) {
						sneakWasPressed = ModInstances.getModToggleSneak().keyHoldTicks + 1;
					} else {
						sneakWasPressed = 1;
					}
					sneak = !sneak;
				} else if(sneakWasPressed > 0) {
					sneakWasPressed++;
				}
				
			} else {
				if((ModInstances.getModToggleSneak().keyHoldTicks > 0) && (sneakWasPressed > ModInstances.getModToggleSneak().keyHoldTicks)) {
					sneak = false;
				}
				sneakWasPressed = 0;
			}
		} else {
			sneak = gameSettings.keyBindSprint.isKeyDown();
		}
		
		if(sneak) {
			moveStrafe *= 0.3F;
			moveForward *= 0.3F;
		}
		
		*/
		
		
		//  ---  TOGGLE SPRINT   ---  //
		
		/*
		if(ModInstances.getModToggleSprint().isEnabled()) {
			if(gameSettings.keyBindInventory.isKeyDown()) {
				if(sprintWasPressed == 0 ) {
					if(sprint) {
						sprintWasPressed = -1;
					} else if(player.capabilities.isFlying){
						sprintWasPressed = ModInstances.getModToggleSprint().keyHoldTicks + 1;
					} else {
						sprintWasPressed = 1;
					}
					sprint = !sprint;
					
				} else if(sprintWasPressed > 0){
					sprintWasPressed++;
				}
			} else {
				if((ModInstances.getModToggleSprint().keyHoldTicks > 0) && (sprintWasPressed > ModInstances.getModToggleSprint().keyHoldTicks)) {
					sprint = false;
				}
				sprintWasPressed = 0;
			}
		} else {
			sprint = false;
		}
		
		if(sprint && moveForward == 1.0F && player.onGround && !player.isUsingItem() && !player.isPotionActive(Potion.blindness)) {
			player.setSprinting(true);
		}
		*/
		
		if(ModInstances.getModToggleSprint().flyBoost && player.capabilities.isCreativeMode && player.capabilities.isFlying && (mc.getRenderViewEntity() == player) && sprint) {
			
			if(origionalFlySpeed < 0.0F || this.player.capabilities.getFlySpeed() != boostedFlySpeed) {
				origionalFlySpeed = this.player.capabilities.getFlySpeed();
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
				this.player.capabilities.setFlySpeed(boostedFlySpeed);
			}
			origionalFlySpeed = -1.0F;
		}
	}
	
	public String getDisplayText() {
		
		String displayText = "";
		
		boolean isFlying = mc.thePlayer.capabilities.isFlying;
		boolean isRiding = mc.thePlayer.isRiding();
		boolean isHoldingSneak = gameSettings.keyBindSprint.isKeyDown();
		boolean isHoldingSprint = gameSettings.keyBindInventory.isKeyDown();
		
		System.out.println("TWT");
		
		if(isFlying) {
			if(origionalFlySpeed > 0.0F) {
				displayText += "[Flying (" + df.format(boostedFlySpeed / origionalFlySpeed) + "x Boost)]";
			} else {
				displayText += "[Flying]";
			}
		}
		
		if(isRiding) {
			displayText += "[Riding] ";
		}
		
		if(sneak) {
			
			if(isFlying) {
				displayText += "[Descending] ";
			} else if(isRiding) {
				displayText += "[DisMounting]";
			} else if(isHoldingSneak) {
				displayText += "[Sneaking (Key Held)]";
			} else {
				displayText += "[Sneaking (Key Toggled)]";
			}
			
		} else if(sprint && !isRiding && !isFlying) {
			if(isHoldingSprint) {
				displayText += "[Sprinting (Key Held)]";
			} else {
				displayText += "[Sprinting (Key Toggled)]";
			}
		}
		
		return displayText.trim();
		
	}
}
