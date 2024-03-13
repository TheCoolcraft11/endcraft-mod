package net.thecoolcraft11.endcraft.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.thecoolcraft11.endcraft.block.entity.EnderChestBlockEntity;

public class EnderChestScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final EnderChestBlockEntity blockEntity;

    public EnderChestScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(27));
    }
    public EnderChestScreenHandler(int syncId, PlayerInventory playerInventory,
                                   BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.ENDER_CHEST_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 27);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((EnderChestBlockEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0, 8, 18));
        this.addSlot(new Slot(inventory, 1, 26, 18));
        this.addSlot(new Slot(inventory, 2, 44, 18));
        this.addSlot(new Slot(inventory, 3, 62,18));
        this.addSlot(new Slot(inventory, 4, 80, 18));
        this.addSlot(new Slot(inventory, 5, 98, 18));
        this.addSlot(new Slot(inventory, 6, 116, 18));
        this.addSlot(new Slot(inventory, 7, 134, 18));
        this.addSlot(new Slot(inventory, 8, 152, 18));

        this.addSlot(new Slot(inventory, 9, 8, 36));
        this.addSlot(new Slot(inventory, 10, 26, 36));
        this.addSlot(new Slot(inventory, 11, 44, 36));
        this.addSlot(new Slot(inventory, 12, 62,36));
        this.addSlot(new Slot(inventory, 13, 80, 36));
        this.addSlot(new Slot(inventory, 14, 98, 36));
        this.addSlot(new Slot(inventory, 15, 116, 36));
        this.addSlot(new Slot(inventory, 16, 134, 36));
        this.addSlot(new Slot(inventory, 17, 152, 36));

        this.addSlot(new Slot(inventory, 18, 8, 54));
        this.addSlot(new Slot(inventory, 19, 26, 54));
        this.addSlot(new Slot(inventory, 20, 44, 54));
        this.addSlot(new Slot(inventory, 21, 62,54));
        this.addSlot(new Slot(inventory, 22, 80, 54));
        this.addSlot(new Slot(inventory, 23, 98, 54));
        this.addSlot(new Slot(inventory, 24, 116, 54));
        this.addSlot(new Slot(inventory, 25, 134, 54));
        this.addSlot(new Slot(inventory, 26, 152, 54));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}