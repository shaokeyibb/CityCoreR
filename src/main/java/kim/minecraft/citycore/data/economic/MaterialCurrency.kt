package kim.minecraft.citycore.data.economic

import io.izzel.taboolib.module.i18n.I18n
import kim.minecraft.citycore.data.interfaces.owner.CurrencyCreator
import kotlinx.serialization.Serializable
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

@Serializable
data class MaterialCurrency(val source: CurrencyCreator, val material: Material) : Currency(source) {
    override val isRemote: Boolean = true
    override var name: String = I18n.get().getName(ItemStack(material))
    override var symbol: Char = '个'
}