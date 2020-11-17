package kim.minecraft.citycore.data.economic

import kim.minecraft.citycore.data.interfaces.owner.CurrencyCreator
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCurrency(val upstream: LocalCurrency, val source: CurrencyCreator) : Currency(source) {
    override val isRemote: Boolean = true
    override var name: String
        get() = upstream.name
        set(value) {
            upstream.name = value
        }
    override var symbol: Char
        get() = upstream.symbol
        set(value) {
            upstream.symbol = value
        }

}