package kim.minecraft.citycore.data.interfaces

interface DestroyAble {
    var destroyed: Boolean
    fun destroy() {
        destroyed = true
    }
}