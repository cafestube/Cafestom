package net.minestom.server.instance.block;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import net.minestom.server.registry.RegistryData;
import net.minestom.server.registry.StaticProtocolObject;
import net.minestom.server.sound.SoundEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Represents a BlockSoundType object, a set of sounds associated with a particular block (or group of blocks).
 * <p>
 * Note: Although this extends StaticProtocolObject, it's not actually Registry sent through the protocol, and purely for data organization.
 */
public sealed interface BlockSoundType extends StaticProtocolObject<BlockSoundType>, BlockSoundTypes permits BlockSoundImpl {

    /**
     * Returns the 'registry' data for the block sound type. Note: Block sound types are not an actual minecraft registry
     */
    @Contract(pure = true)
    @NotNull
    RegistryData.BlockSoundTypeEntry registry();

    @Override
    @NotNull
    default Key key() {
        return registry().key();
    }

    default float volume() {
        return registry().volume();
    }

    default float pitch() {
        return registry().pitch();
    }

    default @NotNull SoundEvent breakSound() {
        return registry().breakSound();
    }

    default @NotNull SoundEvent hitSound() {
        return registry().hitSound();
    }

    default @NotNull SoundEvent fallSound() {
        return registry().fallSound();
    }

    default @NotNull SoundEvent placeSound() {
        return registry().placeSound();
    }

    default @NotNull SoundEvent stepSound() {
        return registry().stepSound();
    }

    default int id() {
        return 0; // Not sent through packets in the protocol, also must be between 0 and [size of block sound type list] because id mappings are stored in an array
    }

    static @NotNull Collection<@NotNull BlockSoundType> values() {
        return BlockSoundImpl.REGISTRY.values();
    }

    static @Nullable BlockSoundType fromKey(@KeyPattern @NotNull String key) {
        return fromKey(Key.key(key));
    }

    static @Nullable BlockSoundType fromKey(@NotNull Key key) {
        return BlockSoundImpl.REGISTRY.get(key);
    }

}
