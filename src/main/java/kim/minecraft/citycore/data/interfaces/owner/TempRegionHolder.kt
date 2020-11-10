package kim.minecraft.citycore.data.interfaces.owner

import kim.minecraft.citycore.data.DataManager.getDataManger
import kim.minecraft.citycore.data.Region

interface TempRegionHolder : ObjectOwner {
    val tempRegions: Set<Region>
        get() = getDataManger().regions.filter { this in it.tempBelongs }.toSet()

    fun addTempRegion(region: Region) {
        region.addTempBelongs(this)
    }

    fun dropTempRegion(region: Region) {
        region.dropTempBelongs(this)
    }
}