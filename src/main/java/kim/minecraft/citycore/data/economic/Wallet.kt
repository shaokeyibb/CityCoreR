package kim.minecraft.citycore.data.economic

import kim.minecraft.citycore.data.DataManager.toCurrency
import kim.minecraft.citycore.data.interfaces.holder.WalletHolder
import kim.minecraft.citycore.utils.serialzation.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import org.bukkit.Material
import java.util.*

@Serializable
class Wallet(val source: WalletHolder) {
    private val rawWallet: MutableMap<@Serializable(with = UUIDAsStringSerializer::class) UUID, Double> = mutableMapOf()

    val wallet: MutableMap<LocalCurrency, Double>
        get() {
            val temp: MutableMap<LocalCurrency, Double> = mutableMapOf()
            rawWallet.forEach {
                temp[it.key.toCurrency() as LocalCurrency] = it.value
            }
            return temp
        }

    private val rawExtraWallet: MutableMap<Material, Double> = mutableMapOf()

    val extraWallet: MutableMap<MaterialCurrency, Double>
        get() {
            val temp: MutableMap<MaterialCurrency, Double> = mutableMapOf()
            rawWallet.forEach {
                temp[it.key.toCurrency() as MaterialCurrency] = it.value
            }
            return temp
        }
}