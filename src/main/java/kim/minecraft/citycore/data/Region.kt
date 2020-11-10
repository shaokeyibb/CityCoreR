package kim.minecraft.citycore.data

import kim.minecraft.citycore.data.DataManager.toMainRegionHolder
import kim.minecraft.citycore.data.DataManager.toSubRegionHolder
import kim.minecraft.citycore.data.DataManager.toTempRegionHolder
import kim.minecraft.citycore.data.interfaces.DestroyAble
import kim.minecraft.citycore.data.interfaces.owner.MainRegionHolder
import kim.minecraft.citycore.data.interfaces.owner.SubRegionHolder
import kim.minecraft.citycore.data.interfaces.owner.TempRegionHolder
import kim.minecraft.citycore.utils.serialzation.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import org.bukkit.Bukkit
import org.bukkit.Chunk
import java.util.*

@Serializable
data class Region(val details: RegionDetails) : DestroyAble {

    override var destroyed: Boolean = false
        set(value) {
            if (!value && field) throw AssertionError("destroy status can not be reset to false") else field = value
            field = true
            TODO("callEvent")
        }

    @Serializable(with = UUIDAsStringSerializer::class)
    private var rawMainBelongs: UUID? = null

    //Transient
    var belongs: MainRegionHolder?
        get() = rawMainBelongs?.toMainRegionHolder()
        set(value) {
            rawMainBelongs = value?.uid
        }

    @Serializable(with = UUIDAsStringSerializer::class)
    private var rawSubBelongs: MutableSet<@Serializable(with = UUIDAsStringSerializer::class) UUID> = mutableSetOf()

    //Transient
    val subBelongs: Set<SubRegionHolder>
        get() = rawSubBelongs.map { it.toSubRegionHolder() }.toSet()

    fun addSubBelongs(belongs: SubRegionHolder) {
        rawSubBelongs.add(belongs.uid)
    }

    fun dropSubBelongs(belongs: SubRegionHolder) {
        rawSubBelongs.remove(belongs.uid)
    }

    fun isInSubBelongs(belongs: SubRegionHolder): Boolean = belongs.uid in rawSubBelongs

    private var rawTempBelongs: MutableSet<@Serializable(with = UUIDAsStringSerializer::class) UUID> = mutableSetOf()

    //Transient
    val tempBelongs: Set<TempRegionHolder>
        get() = rawTempBelongs.map { it.toTempRegionHolder() }.toSet()

    fun addTempBelongs(belongs: TempRegionHolder) {
        rawTempBelongs.add(belongs.uid)
    }

    fun dropTempBelongs(belongs: TempRegionHolder) {
        rawTempBelongs.remove(belongs.uid)
    }

    fun isInTempBelongs(belongs: TempRegionHolder): Boolean = belongs.uid in rawTempBelongs

    fun bukkit(): Chunk = Bukkit.getWorld(details.world)!!.getChunkAt(details.x, details.z)

    @Serializable
    data class RegionDetails(val world: String, val x: Int, val z: Int) {
        fun toRegion(): Region {
            TODO()
        }
    }
}