package kim.minecraft.citycore.data.personal

import kim.minecraft.citycore.data.DataManager.getHumanForcefully
import kim.minecraft.citycore.data.DataManager.save
import kim.minecraft.citycore.data.DataManager.toHuman
import kim.minecraft.citycore.data.interfaces.UniqueAble
import kim.minecraft.citycore.utils.serialzation.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import java.util.*

@Serializable
data class SinglePlayer(@Serializable(with = UUIDAsStringSerializer::class) override val uid: UUID) : UniqueAble {
    constructor(player: OfflinePlayer) : this(player.uniqueId)

    init {
        save()
    }

    @Serializable(with = UUIDAsStringSerializer::class)
    private var rawHuman: UUID = getHumanForcefully().uid

    //Transient
    var human: Human
        get() = rawHuman.toHuman()
        set(value) {
            human.destroy()
            gen++
            rawHuman = value.uid
        }

    var gen: Int = 0
        private set

    fun bukkit(): OfflinePlayer = Bukkit.getOfflinePlayer(uid)

}