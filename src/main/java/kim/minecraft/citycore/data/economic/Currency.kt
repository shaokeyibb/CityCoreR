package kim.minecraft.citycore.data.economic

import kim.minecraft.citycore.data.interfaces.UniqueAble
import kim.minecraft.citycore.data.interfaces.owner.CurrencyCreator
import kim.minecraft.citycore.utils.serialzation.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
abstract class Currency(@Serializable(with = UUIDAsStringSerializer::class) val rawSource: UUID) : UniqueAble {
    constructor(source: CurrencyCreator) : this(source.uid)

    abstract val isRemote: Boolean

    @Serializable(with = UUIDAsStringSerializer::class)
    override val uid: UUID = UUID.randomUUID()

    abstract var name: String
    abstract var symbol: Char
}