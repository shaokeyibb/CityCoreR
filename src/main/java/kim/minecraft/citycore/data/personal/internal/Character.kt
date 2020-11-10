package kim.minecraft.citycore.data.personal.internal

import kim.minecraft.citycore.data.interfaces.UniqueAble
import kim.minecraft.citycore.utils.serialzation.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
abstract class Character<T> : UniqueAble {
    @Serializable(with = UUIDAsStringSerializer::class)
    override val uid: UUID = UUID.randomUUID()
    abstract val rawSource: UUID
    abstract val source: T
}