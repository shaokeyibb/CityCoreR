package kim.minecraft.citycore.data.economic

import kim.minecraft.citycore.data.interfaces.UniqueAble
import kim.minecraft.citycore.utils.serialzation.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
abstract class Currency : UniqueAble {
    abstract val isRemote: Boolean

    @Serializable(with = UUIDAsStringSerializer::class)
    override val uid: UUID = UUID.randomUUID()
}