package lacrus.fuck.terriblechest.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.UUID;

public class StateSaverAndLoader extends PersistentState {
	
	public Integer totalDirtBlocksBroken = 0;
	
	public HashMap<UUID, PlayerData> players = new HashMap<>();
	
	@Override
	public CompoundTag writeNbt(CompoundTag nbt) {
		nbt.putInt("totalDirtBlocksBroken", totalDirtBlocksBroken);
		
		CompoundTag playersNbt = new CompoundTag();
		players.forEach((uuid, playerData) -> {
			CompoundTag playerNbt = new CompoundTag();
			
			playerNbt.putInt("dirtBlocksBroken", playerData.dirtBlocksBroken);
			
			playerNbt.putIntArray("oldCravings", playerData.oldCravings);
			
			CompoundTag fatigueTag = new CompoundTag();
			playerData.fatigue.forEach((foodID, fatigueAmount) -> fatigueTag.putInt(String.valueOf(foodID), fatigueAmount));
			playerNbt.put("fatigue", fatigueTag);
			
			playersNbt.put(uuid.toString(), playerNbt);
		});
		nbt.put("players", playersNbt);
		
		return nbt;
	}
	
	public static StateSaverAndLoader createFromNbt(CompoundTag tag) {
		StateSaverAndLoader state = new StateSaverAndLoader();
		state.totalDirtBlocksBroken = tag.getInt("totalDirtBlocksBroken");
		
		CompoundTag playersNbt = tag.getCompound("players");
		playersNbt.getKeys().forEach(key -> {
			PlayerData playerData = new PlayerData();
			
			playerData.dirtBlocksBroken = playersNbt.getCompound(key).getInt("dirtBlocksBroken");
			
			CompoundTag fatigueCompound = playersNbt.getCompound(key).getCompound("fatigue");
			fatigueCompound.getKeys().forEach(s -> {
				Integer foodID = Integer.valueOf(s);
				int fatigueAmount = fatigueCompound.getInt(s);
				playerData.fatigue.put(foodID, fatigueAmount);
			});
			
			for (int oldCravings : playersNbt.getCompound(key).getIntArray("oldCravings")) {
				playerData.oldCravings.add(oldCravings);
			}
			
			UUID uuid = UUID.fromString(key);
			state.players.put(uuid, playerData);
		});
		
		return state;
	}
	
	private static Type<StateSaverAndLoader> type = new Type<>(
			StateSaverAndLoader::new, // 若不存在 'StateSaverAndLoader' 则创建
			StateSaverAndLoader::createFromNbt, // 若存在 'StateSaverAndLoader' NBT, 则调用 'createFromNbt' 传入参数
			null // 此处理论上应为 'DataFixTypes' 的枚举，但我们直接传递为空(null)也可以
	);
	
	public static StateSaverAndLoader getServerState(MinecraftServer server) {
		// (注：如需在任意维度生效，请使用 'World.OVERWORLD' ，不要使用 'World.END' 或 'World.NETHER')
		PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();
		
		// 当第一次调用了方法 'getOrCreate' 后，它会创建新的 'StateSaverAndLoader' 并将其存储于  'PersistentStateManager' 中。
		//  'getOrCreate' 的后续调用将本地的 'StateSaverAndLoader' NBT 传递给 'StateSaverAndLoader::createFromNbt'。
		StateSaverAndLoader state = persistentStateManager.getOrCreate(type, ExampleMod.MOD_ID);
		
		// 若状态未标记为脏(dirty)，当 Minecraft 关闭时， 'writeNbt' 不会被调用，相应地，没有数据会被保存。
		// 从技术上讲，只有在事实上发生数据变更时才应当将状态标记为脏(dirty)。
		// 但大多数开发者和模组作者会对他们的数据未能保存而感到困惑，所以不妨直接使用 'markDirty' 。
		// 另外，这只将对应的布尔值设定为 TRUE，代价是文件写入磁盘时模组的状态不会有任何改变。(这种情况非常少见)
		state.markDirty();
		
		return state;
	}
	
	public static PlayerData getPlayerState(LivingEntity player) {
		StateSaverAndLoader serverState = getServerState(player.getWorld().getServer());
		
		// 根据 UUID 获取对应玩家的状态，如果没有该玩家的数据，就创建一个新的玩家状态。
		PlayerData playerState = serverState.players.computeIfAbsent(player.getUuid(), uuid -> new PlayerData());
		
		return playerState;
	}
}