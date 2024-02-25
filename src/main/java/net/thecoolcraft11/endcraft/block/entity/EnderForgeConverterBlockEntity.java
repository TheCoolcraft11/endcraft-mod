package net.thecoolcraft11.endcraft.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.recipe.EnderForgeConverterRecipe;
import net.thecoolcraft11.endcraft.screen.EnderForgeConvertingScreenHandler;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;

public class EnderForgeConverterBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 1200;

    public EnderForgeConverterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENDER_FORGE_CONVERTER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> EnderForgeConverterBlockEntity.this.progress;
                    case 1 -> EnderForgeConverterBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> EnderForgeConverterBlockEntity.this.progress = value;
                    case 1 -> EnderForgeConverterBlockEntity.this.maxProgress = value;
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
        return Text.translatable("endcraft.gui.ender_forge_converter_name");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("ender_forge_converter.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("ender_forge_converter.progress");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new EnderForgeConvertingScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

        if(isOutputSlotEmptyOrReceivable()) {
            if(this.hasRecipe()) {
                this.increaseCraftProgress();
                Endcraft.LOGGER.info("1");
                markDirty(world, pos, state);

                if(hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<RecipeEntry<EnderForgeConverterRecipe>> recipe = getCurrentRecipe();

        this.removeStack(INPUT_SLOT, 1);

        if(this.getStack(OUTPUT_SLOT).getItem() == Items.NETHERITE_PICKAXE) {
            this.setStack(OUTPUT_SLOT, new ItemStack(ModItems.ENDERITE_PICKAXE,1));
        }

        if(this.getStack(OUTPUT_SLOT).getItem() == Items.NETHERITE_SWORD) {
            this.setStack(OUTPUT_SLOT, new ItemStack(ModItems.ENDERITE_SWORD,1));
        }

        if(this.getStack(OUTPUT_SLOT).getItem() == Items.NETHERITE_AXE) {
            this.setStack(OUTPUT_SLOT, new ItemStack(ModItems.ENDERITE_AXE,1));
        }

        if(this.getStack(OUTPUT_SLOT).getItem() == Items.NETHERITE_SHOVEL) {
            this.setStack(OUTPUT_SLOT, new ItemStack(ModItems.ENDERITE_SHOVEL,1));
        }

        if(this.getStack(OUTPUT_SLOT).getItem() == Items.NETHERITE_HOE) {
            this.setStack(OUTPUT_SLOT, new ItemStack(ModItems.ENDERITE_HOE,1));
        }
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<EnderForgeConverterRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() /* && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null))
                && canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem())*/;
    }

    private Optional<RecipeEntry<EnderForgeConverterRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(EnderForgeConverterRecipe.Type.INSTANCE, inv, getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        ItemStack oi = this.getStack(OUTPUT_SLOT).getItem().getDefaultStack();
        if (oi.toString().contains("sword") || oi.toString().contains("pickaxe")|| oi.toString().contains("axe") || oi.toString().contains("shovel") || oi.toString().contains("hoe")) {
            return oi.toString().contains("netherite");
        }
        return false;
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