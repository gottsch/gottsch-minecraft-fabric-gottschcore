package mod.gottsch.fabric.gottschcore.nbt;

import net.minecraft.nbt.NbtCompound;

/**
 * Created by Mark Gottschling on Feb 12, 2023
 */
public interface INbtSerializer {

    default public NbtCompound writeNbt() {
        return writeNbt(new NbtCompound());
    }

    public NbtCompound writeNbt(NbtCompound nbt);

    public void readNbt(NbtCompound nbt);

}
