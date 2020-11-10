package kim.minecraft.citycore.data

import kim.minecraft.citycore.data.interfaces.owner.CountryOwner
import kim.minecraft.citycore.data.interfaces.owner.MainRegionHolder
import kim.minecraft.citycore.data.interfaces.owner.SubRegionHolder
import kim.minecraft.citycore.data.interfaces.owner.TempRegionHolder
import kim.minecraft.citycore.data.personal.Human
import kim.minecraft.citycore.data.personal.SinglePlayer
import kim.minecraft.citycore.data.political.Country
import java.util.*

object DataManager {

    fun Any.getDataManger(): DataManager = this@DataManager

    private val players: MutableSet<SinglePlayer> = mutableSetOf()
    fun UUID.toSinglePlayer() = players.find { it.uid == this }
            ?: throw AssertionError("UUID from SinglePlayer can not be found.")

    fun SinglePlayer.save() {
        if (this in players) throw AssertionError("A Object can not be saved twice.")
        players.add(this)
    }

    val humans: MutableSet<Human> = mutableSetOf()
    fun UUID.toHuman() = humans.find { it.uid == this }
            ?: throw AssertionError("UUID from Human can not be found.")

    fun Human.save() {
        if (this in humans) throw AssertionError("A Object can not be saved twice.")
        humans.add(this)
    }

    fun SinglePlayer.getHumanForcefully(): Human = humans.find { it.source == this } ?: Human(this)

    fun String.findHuman(): Human? = humans.find { it.name == this }

    fun Human.sendMessage(message: String) {
        TODO()
    }

    val countries: MutableSet<Country> = mutableSetOf()

    fun Country.save() {
        if (this in countries) throw AssertionError("A Object can not be saved twice.")
        countries.add(this)
    }

    fun UUID.toCountry(): Country = countries.find { it.uid == this }
            ?: throw AssertionError("UUID from Human can not be found.")

    fun UUID.toCountryOwner(): CountryOwner {
        TODO()
    }

    val regions: MutableSet<Region> = mutableSetOf()

    fun UUID.toMainRegionHolder(): MainRegionHolder {
        TODO()
    }

    fun UUID.toSubRegionHolder(): SubRegionHolder {
        TODO()
    }

    fun UUID.toTempRegionHolder(): TempRegionHolder {
        TODO()
    }
}