package kim.minecraft.citycore.data.interfaces.holder

interface NameHolder {
    var name: String
    fun isNameAvailable(name: String): Boolean
    fun rename(newName: String): Boolean = isNameAvailable(newName)
}