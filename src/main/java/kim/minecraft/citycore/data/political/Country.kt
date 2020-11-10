package kim.minecraft.citycore.data.political

import kim.minecraft.citycore.data.DataManager.toCountryOwner
import kim.minecraft.citycore.data.interfaces.DestroyAble
import kim.minecraft.citycore.data.interfaces.UniqueAble
import kim.minecraft.citycore.data.interfaces.holder.MemberHolder
import kim.minecraft.citycore.data.interfaces.holder.NameHolder
import kim.minecraft.citycore.data.interfaces.owner.CountryOwner
import kim.minecraft.citycore.data.interfaces.owner.MainRegionHolder
import kim.minecraft.citycore.data.interfaces.owner.TempRegionHolder
import kim.minecraft.citycore.data.personal.Human
import kim.minecraft.citycore.data.personal.internal.CountryHuman
import kim.minecraft.citycore.utils.serialzation.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Country(@Serializable(with = UUIDAsStringSerializer::class) override val uid: UUID,
                   @Serializable(with = UUIDAsStringSerializer::class) private var rawOwner: UUID?) : UniqueAble, NameHolder, DestroyAble, MemberHolder<Human, CountryHuman>, MainRegionHolder, TempRegionHolder {
    constructor(owner: CountryOwner) : this(UUID.randomUUID(), owner.uid)

    override var name: String = "国家#${uid.toString().replace("-", "").substring(0, 6)}"

    override fun isNameAvailable(name: String): Boolean {
        return true
    }

    override var destroyed: Boolean = false
        set(value) {
            if (!value && field) throw AssertionError("destroy status can not be reset to false") else field = value
            field = true
            TODO("callEvent")
        }

    //Transient
    var owner: CountryOwner?
        get() = rawOwner?.toCountryOwner()
        set(value) {
            rawOwner = value?.uid
        }

    var ideology = "未指定意识形态"

    override val members: MutableSet<CountryHuman> = mutableSetOf()

    override fun addMember(member: Human) {
        members.add(CountryHuman(member, this))
    }

    override fun dropMember(member: Human) {
        members.removeIf { it.source == member }
    }

}