package kim.minecraft.citycore.data.personal.internal

import kim.minecraft.citycore.data.DataManager.toHuman
import kim.minecraft.citycore.data.interfaces.owner.CountryOwner
import kim.minecraft.citycore.data.interfaces.owner.PartyOwner
import kim.minecraft.citycore.data.interfaces.owner.SubRegionHolder
import kim.minecraft.citycore.data.personal.Human
import kim.minecraft.citycore.data.political.Country
import kim.minecraft.citycore.utils.serialzation.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CountryHuman(@Serializable(with = UUIDAsStringSerializer::class) override val rawSource: UUID,
                        @Serializable(with = UUIDAsStringSerializer::class) override val rawUpstream: UUID) : Character<Human>(), SubRegionHolder, CountryOwner, PartyOwner {
    constructor(source: Human, upstream: Country) : this(source.uid, upstream.uid)

    override val source: Human
        get() = rawSource.toHuman()

    override var name: String
        get() = source.name
        set(value) {
            source.name = value
        }

    override fun isNameAvailable(name: String): Boolean = source.isNameAvailable(name)

    override var destroyed: Boolean = false
        set(value) {
            if (!value && field) throw AssertionError("destroy status can not be reset to false") else field = value
            field = true
            TODO("callEvent")
        }
}