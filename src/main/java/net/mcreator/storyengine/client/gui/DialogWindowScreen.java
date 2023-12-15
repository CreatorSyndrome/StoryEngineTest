
package net.mcreator.storyengine.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.Minecraft;

import net.mcreator.storyengine.world.inventory.DialogWindowMenu;
import net.mcreator.storyengine.procedures.EntityForDialogProcedure;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class DialogWindowScreen extends AbstractContainerScreen<DialogWindowMenu> {
	private final static HashMap<String, Object> guistate = DialogWindowMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_dialogbutton;
	ImageButton imagebutton_dialogbutton1;
	ImageButton imagebutton_dialogbutton2;

	public DialogWindowScreen(DialogWindowMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		if (EntityForDialogProcedure.execute(world, x, y, z) instanceof LivingEntity livingEntity) {
			InventoryScreen.renderEntityInInventory(this.leftPos + -76, this.topPos + 123, 50, (float) Math.atan((this.leftPos + -76 - mouseX) / 40.0), (float) Math.atan((this.topPos + 73 - mouseY) / 40.0), livingEntity);
		}
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("storyengine:textures/screens/oldpaper.png"));
		this.blit(ms, this.leftPos + -55, this.topPos + 125, 0, 0, 300, 100, 300, 100);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, new TranslatableComponent("gui.storyengine.dialog_window.label_variant_1"), 63, 30, -1);
		this.font.draw(poseStack, new TranslatableComponent("gui.storyengine.dialog_window.label_variant_2"), 63, 66, -1);
		this.font.draw(poseStack, new TranslatableComponent("gui.storyengine.dialog_window.label_variant_3"), 63, 101, -1);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		imagebutton_dialogbutton = new ImageButton(this.leftPos + 11, this.topPos + 24, 153, 25, 0, 0, 25, new ResourceLocation("storyengine:textures/screens/atlas/imagebutton_dialogbutton.png"), 153, 50, e -> {
		});
		guistate.put("button:imagebutton_dialogbutton", imagebutton_dialogbutton);
		this.addRenderableWidget(imagebutton_dialogbutton);
		imagebutton_dialogbutton1 = new ImageButton(this.leftPos + 11, this.topPos + 59, 153, 25, 0, 0, 25, new ResourceLocation("storyengine:textures/screens/atlas/imagebutton_dialogbutton1.png"), 153, 50, e -> {
		});
		guistate.put("button:imagebutton_dialogbutton1", imagebutton_dialogbutton1);
		this.addRenderableWidget(imagebutton_dialogbutton1);
		imagebutton_dialogbutton2 = new ImageButton(this.leftPos + 11, this.topPos + 93, 153, 25, 0, 0, 25, new ResourceLocation("storyengine:textures/screens/atlas/imagebutton_dialogbutton2.png"), 153, 50, e -> {
		});
		guistate.put("button:imagebutton_dialogbutton2", imagebutton_dialogbutton2);
		this.addRenderableWidget(imagebutton_dialogbutton2);
	}
}
