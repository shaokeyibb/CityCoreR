package kim.minecraft.citycore.data.interfaces.owner

import kim.minecraft.citycore.data.DataManager.toMainRegionHolder
import kim.minecraft.citycore.data.Region
import java.util.*

interface SubRegionHolder : ObjectOwner {
    val rawUpstream: UUID

    val upStreamMainRegionHolder: MainRegionHolder
        get() = rawUpstream.toMainRegionHolder()

    val subRegions: Set<Region>
        get() {
            val region = mutableSetOf<Region>()
            upStreamMainRegionHolder.mainRegions.forEach {
                if (this in it.subBelongs) region.add(it)
            }
            return region
        }

    fun addSubRegion(region: Region) {
        region.addSubBelongs(this)
    }

    fun dropSubRegion(region: Region) {
        region.dropSubBelongs(this)
    }
}