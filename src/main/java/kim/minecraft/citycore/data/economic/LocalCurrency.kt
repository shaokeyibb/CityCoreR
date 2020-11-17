package kim.minecraft.citycore.data.economic

import kim.minecraft.citycore.data.interfaces.owner.CurrencyCreator
import kotlinx.serialization.Serializable

@Serializable
data class LocalCurrency(val source: CurrencyCreator) : Currency(source) {
    override val isRemote: Boolean = true
    override var name: String = "货币#${uid.toString().replace("-", "").substring(0, 6)}"
    override var symbol: Char = '$'
}