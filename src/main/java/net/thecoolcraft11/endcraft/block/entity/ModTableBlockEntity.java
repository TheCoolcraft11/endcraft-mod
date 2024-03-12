package net.thecoolcraft11.endcraft.block.entity;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import net.minecraft.world.dimension.DimensionTypes;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.damagetypes.ModDamageTypes;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.screen.ModTableScreenHandler;
import net.thecoolcraft11.endcraft.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ModTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int INPUT_SLOT1 = 0;
    private static final int INPUT_SLOT2 = 1;
    private static final int INPUT_SLOT3 = 2;
    private static final int OUTPUT_SLOT = 3;
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 100;

    public ModTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MOD_TABLE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ModTableBlockEntity.this.progress;
                    case 1 -> ModTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ModTableBlockEntity.this.progress = value;
                    case 1 -> ModTableBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 4;
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
        return Text.translatable("endcraft.gui.mod_table");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("mod_table.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("mod_table.progress");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ModTableScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
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
        if(this.getStack(OUTPUT_SLOT).getOrCreateNbt().getString("upgrade1").equals("none")) {
            this.getStack(OUTPUT_SLOT).getOrCreateNbt().putString("upgrade1", this.getStack(INPUT_SLOT3).getOrCreateNbt().getString("type"));
            this.getStack(OUTPUT_SLOT).getOrCreateNbt().putInt("upgrade1level", this.getStack(INPUT_SLOT3).getOrCreateNbt().getInt("level"));
            this.removeStack(INPUT_SLOT1, 5);
            this.removeStack(INPUT_SLOT3);
            this.getStack(INPUT_SLOT2).getOrCreateNbt().putInt("Energies", this.getStack(INPUT_SLOT2).getOrCreateNbt().getInt("Energies") - 100);
        }else if (this.getStack(OUTPUT_SLOT).getOrCreateNbt().getString("upgrade2").equals("none")) {
            this.getStack(OUTPUT_SLOT).getOrCreateNbt().putString("upgrade2", this.getStack(INPUT_SLOT3).getOrCreateNbt().getString("type"));
            this.getStack(OUTPUT_SLOT).getOrCreateNbt().putInt("upgrade2level", this.getStack(INPUT_SLOT3).getOrCreateNbt().getInt("level"));
            this.removeStack(INPUT_SLOT1,5);
            this.removeStack(INPUT_SLOT3);
            this.getStack(INPUT_SLOT2).getOrCreateNbt().putInt("Energies", this.getStack(INPUT_SLOT2).getOrCreateNbt().getInt("Energies") - 100);
        }else if(this.getStack(OUTPUT_SLOT).getOrCreateNbt().getString("upgrade3").equals("none")) {
            this.getStack(OUTPUT_SLOT).getOrCreateNbt().putString("upgrade3", this.getStack(INPUT_SLOT3).getOrCreateNbt().getString("type"));
            this.getStack(OUTPUT_SLOT).getOrCreateNbt().putInt("upgrade3level", this.getStack(INPUT_SLOT3).getOrCreateNbt().getInt("level"));
            this.removeStack(INPUT_SLOT1,5);
            this.removeStack(INPUT_SLOT3);
            this.getStack(INPUT_SLOT2).getOrCreateNbt().putInt("Energies", this.getStack(INPUT_SLOT2).getOrCreateNbt().getInt("Energies") - 100);
        }

    }


    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }


    private boolean hasRecipe() {
        return (hasItemInSlot(INPUT_SLOT1, ModItems.ENDERITE_INGOT, 5) && hasItemInSlot(INPUT_SLOT2, ModItems.ENERGY_CELL, 1) && this.getStack(INPUT_SLOT2).getOrCreateNbt().getInt("Energies") > 100 && this.getStack(INPUT_SLOT3).getItem().getDefaultStack().isIn(ModTags.Items.ENDER_UPGRADE) && this.getStack(OUTPUT_SLOT).getItem() == ModItems.ENDER_STAFF);

    }

    private boolean hasItemInSlot(int slot, Item item, int count) {
        boolean istrue = false;
        if(this.getStack(slot).getItem() == item) {
            if(this.getStack(slot).getCount() >= count) {
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
    private static void removeBlocksAroundPoint(World world, BlockPos centerPos, int radius) {
        int halfRadius = radius / 2;

        for (int x = -halfRadius; x <= halfRadius; x++) {
            for (int y = -100; y <= 320; y++) {
                for (int z = -halfRadius; z <= halfRadius; z++) {
                    BlockPos blockPos = centerPos.add(x, y-halfRadius, z);
                    if(!(world.getBlockState(blockPos).getBlock() == ModBlocks.MOD_TABLE || world.getBlockState(blockPos).getBlock().getBlastResistance() >= 3600000)) {
                        FluidState fluidState = world.getFluidState(blockPos);
                        if (fluidState.getFluid() == Fluids.WATER || fluidState.getFluid() == Fluids.LAVA) {
                            world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                        } else {
                        world.removeBlock(blockPos, true);}

                    }
                }
            }
        }
    }
}