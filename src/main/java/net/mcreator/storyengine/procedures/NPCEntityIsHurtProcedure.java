package net.mcreator.storyengine.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.Util;

public class NPCEntityIsHurtProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastMessage(new TextComponent(("" + (entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null))), ChatType.SYSTEM, Util.NIL_UUID);
	}
}
