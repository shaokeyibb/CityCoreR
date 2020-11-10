package kim.minecraft.citycore.data.interfaces.holder

import kim.minecraft.citycore.data.interfaces.UniqueAble
import kim.minecraft.citycore.data.personal.internal.Character

interface MemberHolder<T : UniqueAble, R : Character<T>> {
    val members: MutableSet<R>
    fun addMember(member: T)
    fun dropMember(member: T)
}