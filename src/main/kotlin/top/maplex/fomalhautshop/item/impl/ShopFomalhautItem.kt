package top.maplex.fomalhautshop.item.impl

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.platform.util.isAir
import top.maplex.fomalhautshop.item.ShopItem
import top.maplex.fomalhautshop.item.itemlib.ItemSaveLib

object ShopFomalhautItem : ShopItem {

    override val source: String = "FS"

    @Awake(LifeCycle.ENABLE)
    fun init() {
        registerItem()
    }

    override fun getItemList(): MutableList<ItemStack> {
        return ItemSaveLib.items.values.toMutableList()
    }

    override fun getItem(player: Player, id: String): ItemStack? {
        return ItemSaveLib.getItem(id)
    }

    override fun getItemId(itemStack: ItemStack): String {
        if (ItemSaveLib.items.isEmpty()){
            return "none"
        }
        return ItemSaveLib.items.firstNotNullOf { (id, item) ->
            if (item.isAir()) {
                return "none"
            }
            if (itemStack.isSimilar(item)) {
                return@firstNotNullOf id
            }
            return@firstNotNullOf "none"
        }
    }
}
