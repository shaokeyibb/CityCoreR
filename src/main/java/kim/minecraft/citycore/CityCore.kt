package kim.minecraft.citycore

import io.izzel.taboolib.loader.Plugin
import io.izzel.taboolib.loader.PluginBase
import io.izzel.taboolib.module.config.TConfig

@Suppress("unused")
object CityCore : Plugin() {

    private val config: TConfig by lazy {
        TConfig.create(plugin, "config.yml")
    }

    fun Any.getPlugin(): PluginBase = plugin

    fun Any.getConfig(): TConfig = config

    fun Any.getLocale(key: String): String = config.getStringColored("Message.$key", "lost locale for Message.$key")

    fun Any.getLocaleList(key: String): List<String> = config.getStringListColored("Message.$key")

    fun Any.info(msg: String) = plugin.logger.info(msg)

    fun Any.getAdminPermission(): String = config.getString("Global.AdminPermission", "citycore.admin")!!

    override fun onEnable() {
        if (plugin.getResource("config.yml") == null)
            plugin.saveResource("config.yml", false)
    }

    override fun onDisable() {

    }
}