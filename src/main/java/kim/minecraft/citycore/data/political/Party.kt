package kim.minecraft.citycore.data.political

import kim.minecraft.citycore.data.economic.Wallet
import kim.minecraft.citycore.data.interfaces.DestroyAble
import kim.minecraft.citycore.data.interfaces.UniqueAble
import kim.minecraft.citycore.data.interfaces.holder.MemberHolder
import kim.minecraft.citycore.data.interfaces.holder.NameHolder
import kim.minecraft.citycore.data.interfaces.holder.WalletHolder
import kim.minecraft.citycore.data.interfaces.owner.PartyOwner
import kim.minecraft.citycore.data.interfaces.owner.SubRegionHolder
import kim.minecraft.citycore.data.personal.Human
import kim.minecraft.citycore.data.personal.internal.PartyHuman
import kim.minecraft.citycore.utils.serialzation.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Party(@Serializable(with = UUIDAsStringSerializer::class) override val uid: UUID,
                 @Serializable(with = UUIDAsStringSerializer::class) private var rawOwner: UUID?,
                 @Serializable(with = UUIDAsStringSerializer::class) override val rawUpstream: UUID) : UniqueAble, NameHolder, DestroyAble, MemberHolder<Human, PartyHuman>, SubRegionHolder, WalletHolder {
    constructor(owner: PartyOwner, upstream: Country) : this(UUID.randomUUID(), owner.uid, upstream.uid)

    override var name: String = "党派#${uid.toString().replace("-", "").substring(0, 6)}"

    override fun isNameAvailable(name: String): Boolean {
        return true
    }

    override var destroyed: Boolean = false
        set(value) {
            if (!value && field) throw AssertionError("destroy status can not be reset to false") else field = value
            field = true
            TODO("callEvent")
        }

    override val members: MutableSet<PartyHuman> = mutableSetOf()

    override fun addMember(member: Human) {
        members.add(PartyHuman(member, this))
    }

    override fun dropMember(member: Human) {
        members.removeIf { it.source == member }
    }

    override val wallet: Wallet = Wallet(this)

}