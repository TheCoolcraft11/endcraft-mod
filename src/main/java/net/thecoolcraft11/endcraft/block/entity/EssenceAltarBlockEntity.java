package net.thecoolcraft11.endcraft.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
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
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerMetadata;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.damagetypes.ModDamageTypes;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.screen.EssenceAltarScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static net.thecoolcraft11.endcraft.item.custom.EmptyEssenceItem.removeItem;

public class EssenceAltarBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(18, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 16;
    private static final int OUTPUT_SLOT = 17;
    private static final int CRAFT_SLOT1 = 0;
    private static final int CRAFT_SLOT2 = 1;
    private static final int CRAFT_SLOT3 = 2;
    private static final int CRAFT_SLOT4 = 3;
    private static final int CRAFT_SLOT5 = 4;
    private static final int CRAFT_SLOT6 = 5;
    private static final int CRAFT_SLOT7 = 6;
    private static final int CRAFT_SLOT8 = 7;
    private static final int CRAFT_SLOT9 = 8;
    private static final int CRAFT_SLOT10 = 9;
    private static final int CRAFT_SLOT11 = 10;
    private static final int CRAFT_SLOT12 = 11;
    private static final int CRAFT_SLOT13 = 12;
    private static final int CRAFT_SLOT14 = 13;
    private static final int CRAFT_SLOT15 = 14;
    private static final int CRAFT_SLOT16 = 15;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 100;

    public EssenceAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ESSENCE_ALTAR_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> EssenceAltarBlockEntity.this.progress;
                    case 1 -> EssenceAltarBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> EssenceAltarBlockEntity.this.progress = value;
                    case 1 -> EssenceAltarBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 18;
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
        return Text.translatable("endcraft.gui.essence_altar");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("essence_altar.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("essence_altar.progress");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new EssenceAltarScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
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
        int destroyamount = this.getStack(INPUT_SLOT).getCount() * 2;
    this.removeStack(CRAFT_SLOT1);
    this.removeStack(CRAFT_SLOT2);
    this.removeStack(CRAFT_SLOT3);
    this.removeStack(CRAFT_SLOT4);
    this.removeStack(CRAFT_SLOT5);
    this.removeStack(CRAFT_SLOT6);
    this.removeStack(CRAFT_SLOT7);
    this.removeStack(CRAFT_SLOT8);
    this.removeStack(CRAFT_SLOT9);
    this.removeStack(CRAFT_SLOT10);
    this.removeStack(CRAFT_SLOT11);
    this.removeStack(CRAFT_SLOT12);
    this.removeStack(CRAFT_SLOT13);
    this.removeStack(CRAFT_SLOT14);
    this.removeStack(CRAFT_SLOT15);
    this.removeStack(CRAFT_SLOT16);
    this.removeStack(INPUT_SLOT);
         removeBlocksAroundPoint(this.getWorld(),this.getPos(),destroyamount);
         damagePlayer(this.getWorld(),findNearestPlayerInArea(this.getWorld(),this.getPos(),destroyamount), destroyamount);
        this.getStack(OUTPUT_SLOT).getOrCreateNbt().putInt("MaxDurability", this.getStack(OUTPUT_SLOT).getOrCreateNbt().getInt("MaxDurability") + destroyamount);
        if(this.getWorld().getDimensionKey() == DimensionTypes.OVERWORLD) {
            this.getStack(OUTPUT_SLOT).getOrCreateNbt().putString("essence", "overworld");
        }
        if(this.getWorld().getDimensionKey() == DimensionTypes.THE_NETHER) {
            this.getStack(OUTPUT_SLOT).getOrCreateNbt().putString("essence", "nether");
        }
        if(this.getWorld().getDimensionKey() == DimensionTypes.THE_END) {
            this.getStack(OUTPUT_SLOT).getOrCreateNbt().putString("essence", "the_end");
        }
        this.getStack(OUTPUT_SLOT).setDamage(this.getStack(OUTPUT_SLOT).getMaxDamage() - this.getStack(OUTPUT_SLOT).getOrCreateNbt().getInt("MaxDurability"));
        if(this.getStack(OUTPUT_SLOT).getDamage() == 0) {
        if(this.getStack(OUTPUT_SLOT).getOrCreateNbt().getString("essence").equals("overworld")) {
            this.setStack(OUTPUT_SLOT,ModItems.OVERWORLD_ESSENCE.getDefaultStack());
            }
        if(this.getStack(OUTPUT_SLOT).getOrCreateNbt().getString("essence").equals("nether")) {
            this.setStack(OUTPUT_SLOT,ModItems.NETHER_ESSENCE.getDefaultStack());
            }
        if(this.getStack(OUTPUT_SLOT).getOrCreateNbt().getString("essence").equals("the_end")) {
            this.setStack(OUTPUT_SLOT,ModItems.THE_END_ESSENCE.getDefaultStack());
            }
        }
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }


    private boolean hasRecipe() {
        boolean istrue = false;
        if(this.hasItemInSlot(CRAFT_SLOT1,Items.TINTED_GLASS,64)) {
            if(this.hasItemInSlot(CRAFT_SLOT2,Items.TINTED_GLASS,64)) {
                if(this.hasItemInSlot(CRAFT_SLOT3,Items.TINTED_GLASS,64)) {
                    if(this.hasItemInSlot(CRAFT_SLOT4,Items.TINTED_GLASS,64)) {
                        if(this.hasItemInSlot(CRAFT_SLOT5,Items.TINTED_GLASS,64)) {
                            if(this.hasItemInSlot(CRAFT_SLOT6,ModItems.ESSENCE_CORE,1)) {
                                if(this.hasItemInSlot(CRAFT_SLOT7,ModItems.ENDERITE_CORE,1)) {
                                    if(this.hasItemInSlot(CRAFT_SLOT8,Items.TINTED_GLASS,64)) {
                                        if(this.hasItemInSlot(CRAFT_SLOT9,Items.TINTED_GLASS,64)) {
                                            if(this.hasItemInSlot(CRAFT_SLOT10,Items.OBSIDIAN,64)) {
                                                if(this.hasItemInSlot(CRAFT_SLOT11,Items.OBSIDIAN,64)) {
                                                    if(this.hasItemInSlot(CRAFT_SLOT12,Items.TINTED_GLASS,64)) {
                                                        if(this.hasItemInSlot(CRAFT_SLOT13,ModBlocks.ENDERITE_BLOCK.asItem(),3)) {
                                                            if(this.hasItemInSlot(CRAFT_SLOT14,ModBlocks.ENDERITE_BLOCK.asItem(),2)) {
                                                                if(this.hasItemInSlot(CRAFT_SLOT14,ModBlocks.ENDERITE_BLOCK.asItem(),2)) {
                                                                    if(this.hasItemInSlot(CRAFT_SLOT16,ModBlocks.ENDERITE_BLOCK.asItem(),3)) {
                                                                        if(this.getStack(INPUT_SLOT).getItem() == ModItems.ENDERITE_ENERGY) {
                                                                            if(this.hasItemInSlot(OUTPUT_SLOT,ModItems.EMPTY_ESSENCE,1)) {
                                                                                istrue = true;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return istrue;
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
    private static void removeBlocksAroundPoint(World world, BlockPos centerPos, int radius) {
        int halfRadius = radius / 2;

        for (int x = -halfRadius; x <= halfRadius; x++) {
            for (int y = -100; y <= 320; y++) {
                for (int z = -halfRadius; z <= halfRadius; z++) {
                    BlockPos blockPos = centerPos.add(x, y-halfRadius, z);
                    if(!(world.getBlockState(blockPos).getBlock() == ModBlocks.ESSENCE_ALTAR || world.getBlockState(blockPos).getBlock().getBlastResistance() >= 3600000)) {
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
    private static List<PlayerEntity> findNearestPlayerInArea(World world, BlockPos centerPos, int radius) {
        List<PlayerEntity> PlayersInArea = new ArrayList<>();
        double nearestDistanceSquared = Double.POSITIVE_INFINITY;

        int minX = centerPos.getX() - radius;
        int minY = centerPos.getY() - radius;
        int minZ = centerPos.getZ() - radius;

        int maxX = centerPos.getX() + radius;
        int maxY = centerPos.getY() + radius;
        int maxZ = centerPos.getZ() + radius;

        // Berechne das Quadrat des maximalen Entfernungsradius, um mathematische Wurzeln zu vermeiden
        double maxDistanceSquared = radius * radius;

        for (PlayerEntity player : world.getPlayers()) {
            BlockPos playerPos = player.getBlockPos();

            // Überprüfe, ob der Spieler innerhalb des Bereichs liegt
            if (playerPos.getX() >= minX && playerPos.getX() <= maxX &&
                    playerPos.getY() >= minY && playerPos.getY() <= maxY &&
                    playerPos.getZ() >= minZ && playerPos.getZ() <= maxZ) {
                if(player instanceof PlayerEntity) {
                    PlayersInArea.add(player);
                }
            }
        }
        return PlayersInArea;
    }
    private void damagePlayer(World world, List<PlayerEntity> players, int damage) {

        if(players != null) {
        for(PlayerEntity player : players) {



            player.damage(ModDamageTypes.of(world, ModDamageTypes.ESSENCE_AREA_REMOVED), damage);
            }
        }
    }
    public float getScale() {
        if (this.getStack(INPUT_SLOT).getCount() >= 1) {
            return this.getStack(INPUT_SLOT).getCount() * 2;
        }
        return 8;
    }
}