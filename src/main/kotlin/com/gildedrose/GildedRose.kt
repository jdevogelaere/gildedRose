package com.gildedrose

import com.gildedrose.ItemType.*

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            when (items[i].type()) {
                GENERAL -> items[i].decrease(GENERAL)
                CONJURED -> items[i].decrease(CONJURED)
                OLDER_BETTER -> items[i].increase(OLDER_BETTER)
                LEGENDARY -> {
                }
            }

            if (items[i].type() != LEGENDARY) {
                items[i].updateSellIn()
            }

            if (items[i].isExpired()) {
                items[i].updateQualityAfterSellInExpired()
            }
        }
    }
}