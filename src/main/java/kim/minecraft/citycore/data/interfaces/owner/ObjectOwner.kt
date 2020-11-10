package kim.minecraft.citycore.data.interfaces.owner

import kim.minecraft.citycore.data.interfaces.DestroyAble
import kim.minecraft.citycore.data.interfaces.UniqueAble
import kim.minecraft.citycore.data.interfaces.holder.NameHolder

interface ObjectOwner : UniqueAble, NameHolder, DestroyAble {
}