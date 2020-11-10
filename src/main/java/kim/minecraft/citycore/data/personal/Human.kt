package kim.minecraft.citycore.data.personal

import kim.minecraft.citycore.CityCore.getConfig
import kim.minecraft.citycore.data.DataManager.findHuman
import kim.minecraft.citycore.data.DataManager.save
import kim.minecraft.citycore.data.DataManager.toCountry
import kim.minecraft.citycore.data.DataManager.toSinglePlayer
import kim.minecraft.citycore.data.interfaces.DestroyAble
import kim.minecraft.citycore.data.interfaces.UniqueAble
import kim.minecraft.citycore.data.interfaces.holder.NameHolder
import kim.minecraft.citycore.data.personal.internal.CountryHuman
import kim.minecraft.citycore.data.political.Country
import kim.minecraft.citycore.utils.serialzation.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.*

data class Human(@Serializable(with = UUIDAsStringSerializer::class) private val rawSource: UUID) : UniqueAble, NameHolder, DestroyAble {
    constructor(player: SinglePlayer) : this(player.uid)

    init {
        save()
    }

    override val uid: UUID = UUID.randomUUID()

    @Transient
    val source: SinglePlayer
        get() = rawSource.toSinglePlayer()

    override var name: String = "公民#${uid.toString().replace("-", "").substring(0, 6)}"

    override fun isNameAvailable(name: String): Boolean = !(name.isEmpty()
            || name.isBlank()
            || name.firstOrNull { it == ' ' } != null
            || name.length > getConfig().getInt("Human.MaxNameSize", 12)
            || name.findHuman() != null)

    override var destroyed: Boolean = false
        set(value) {
            if (!value && field) throw AssertionError("destroy status can not be reset to false") else field = value
            field = true
            TODO("callEvent")
        }

    val birthTime = source.bukkit().player!!.world.fullTime

    private var rawCurrencyCountry: UUID? = null

    var currentCountry: Country?
        get() = rawCurrencyCountry?.toCountry()
        set(value) {
            rawCurrencyCountry = value?.uid
        }

    val countryCharacter: CountryHuman?
        get() = currentCountry?.members?.find { it.source == this }
}