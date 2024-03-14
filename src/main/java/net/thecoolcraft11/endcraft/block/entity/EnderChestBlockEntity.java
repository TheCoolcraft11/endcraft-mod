package net.thecoolcraft11.endcraft.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
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
import net.thecoolcraft11.endcraft.screen.EnderChestScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnderChestBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 27;

    private static UUID player = null;


    protected final PropertyDelegate propertyDelegate;
    private UUID pwd = null;
    private List<UUID> guests = new ArrayList<>();
    private UUID guest_1 = null;
    private UUID guest_2 = null;
    private UUID guest_3 = null;

    public EnderChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENDER_CHEST_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                }
            }

            @Override
            public int size() {
                return 27;
            }
        };
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }
    public void setPlacer(UUID placer) {
        this.player = placer;
        markDirty();
    }
    public void setPwd(UUID pwd) {
        this.pwd = pwd;
        markDirty();
    }
    public void addGuest(UUID guest) {
        //this.guests.add(guest);
        this.guest_1 = guest_1;
        markDirty();
    }
    public void removeGuest(UUID guest) {
//        this.guests.remove(guest);
        this.guest_1 = null;
        markDirty();
    }
    public UUID getGuests() {
        //return this.guests;
        return this.guest_1;
    }
    public UUID getPwd() {
        return this.pwd;
    }
    public UUID getPlacer() {
        return  this.player;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.endcraft.enderite_chest");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putUuid("pwd", pwd);
        nbt.putUuid("placer", player);
        if (guest_1 != null) {
            nbt.putUuid("guest_1", guest_1);
        }
        //nbt.putUuid("guest_2", guest_2);
            //nbt.putUuid("guest_3", guest_3);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        pwd = nbt.getUuid("pwd");
        player = nbt.getUuid("placer");
        if (nbt.getUuid("guest_1") != null) {
            guest_1 = nbt.getUuid("guest_1");
        }
        //guest_2 = nbt.getUuid("guest_2");
        //guest_3 = nbt.getUuid("guest_3");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new EnderChestScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
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