package kim.minecraft.citycore.data.economic

import kim.minecraft.citycore.data.DataManager.toCurrency
import kim.minecraft.citycore.data.economic.internal.EconomyResponse
import kim.minecraft.citycore.data.interfaces.holder.WalletHolder
import kim.minecraft.citycore.utils.serialzation.UUIDAsStringSerializer
import kotlinx.serialization.Serializable
import org.bukkit.Material
import java.util.*

@Serializable
class Wallet(val source: WalletHolder) {
    private val rawWallet: MutableMap<@Serializable(with = UUIDAsStringSerializer::class) UUID, Double> = mutableMapOf()

    private val wallet: Map<LocalCurrency, Double>
        get() {
            val temp: MutableMap<LocalCurrency, Double> = mutableMapOf()
            rawWallet.forEach {
                temp[it.key.toCurrency() as LocalCurrency] = it.value
            }
            return temp
        }

    private val rawExtraWallet: MutableMap<Material, Double> = mutableMapOf()

    private val extraWallet: Map<MaterialCurrency, Double>
        get() {
            val temp: MutableMap<MaterialCurrency, Double> = mutableMapOf()
            rawWallet.forEach {
                temp[it.key.toCurrency() as MaterialCurrency] = it.value
            }
            return temp
        }

    fun get(currency: Currency): Double {
        return when (currency) {
            is LocalCurrency -> {
                wallet[currency] ?: 0.0
            }
            is MaterialCurrency -> {
                extraWallet[currency] ?: 0.0
            }
            else -> {
                throw AssertionError("Currency is neither LocalCurrency nor MaterialCurrency")
            }
        }
    }

    fun set(currency: Currency, amount: Double): EconomyResponse {
        when (currency) {
            is LocalCurrency -> {
                rawWallet[currency.uid] = amount
            }
            is MaterialCurrency -> {
                rawExtraWallet[currency.material] = amount
            }
            else -> {
                throw AssertionError("Currency is neither LocalCurrency nor MaterialCurrency")
            }
        }
        return EconomyResponse(currency, amount, get(currency))
    }

    fun has(currency: Currency, amount: Double): Boolean = get(currency) >= amount

    fun deposit(currency: Currency, amount: Double): EconomyResponse {
        set(currency, get(currency) + amount)
        return EconomyResponse(currency, amount, get(currency))
    }

    fun withdraw(currency: Currency, amount: Double): EconomyResponse {
        set(currency, get(currency) - amount)
        return EconomyResponse(currency, amount, get(currency))
    }
}