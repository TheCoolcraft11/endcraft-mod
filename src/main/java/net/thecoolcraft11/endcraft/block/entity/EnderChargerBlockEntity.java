package net.thecoolcraft11.endcraft.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.item.ModItems;

import net.thecoolcraft11.endcraft.screen.EnderChargerScreenHandler;
import org.jetbrains.annotations.Nullable;

public class EnderChargerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;


    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 0;

    public EnderChargerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENDER_CHARGER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> EnderChargerBlockEntity.this.progress;
                    case 1 -> EnderChargerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> EnderChargerBlockEntity.this.progress = value;
                    case 1 -> EnderChargerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }
    public ItemStack getRenderStack() {
            return this.getStack(OUTPUT_SLOT);
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.endcraft.ender_charger");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("ender_charger.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("ender_charger.progress");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new EnderChargerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

            if(this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if(hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
    }

    private void resetProgress() {
        this.progress = 0;
    }


    private void craftItem() {
        int energytomove = 1;

        this.getStack(OUTPUT_SLOT).setDamage(this.getStack(OUTPUT_SLOT).getDamage() - energytomove);
        this.getStack(INPUT_SLOT).getOrCreateNbt().putInt("Energies", this.getStack(INPUT_SLOT).getOrCreateNbt().getInt("Energies") - energytomove);
        if(energytomove <= this.getStack(INPUT_SLOT).getOrCreateNbt().getInt("Energies")) {
            energytomove++;
        }else {
            energytomove = this.getStack(INPUT_SLOT).getOrCreateNbt().getInt("Energies");
        }
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }


    private boolean hasRecipe() {
        return (this.getStack(INPUT_SLOT).getItem() == ModItems.ENERGY_CELL  && this.getStack(INPUT_SLOT).getDamage() > 0 && this.getStack(OUTPUT_SLOT).getItem() == ModItems.ENDER_STAFF && this.getStack(OUTPUT_SLOT).getDamage() > 0);
    }

    private boolean hasItemInSlot(int slot, Item item, int count) {
        boolean istrue = false;
        if(this.getStack(slot).getItem() == item) {
            if(this.getStack(slot).getCount() == count) {
                istrue = true;
            }
        }
        return istrue;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

}