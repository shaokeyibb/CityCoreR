package kim.minecraft.citycore.data.interfaces.owner

import kim.minecraft.citycore.data.DataManager.getDataManger
import kim.minecraft.citycore.data.Region

interface MainRegionHolder : ObjectOwner {
    val mainRegions: Set<Region>
        get() = getDataManger().regions.filter { it.belongs == this }.toSet()

    fun addMainRegion(region: Region) {
        region.belongs = this
    }

    fun dropMainRegion(region: Region) {
        region.belongs = null
    }
}