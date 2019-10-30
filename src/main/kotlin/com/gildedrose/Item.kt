package com.gildedrose

import com.gildedrose.ItemType.*

data class Item(var name: String, var sellIn: Int, var quality: Int)

fun Item.type(): ItemType {
    return if (name == "Sulfuras, Hand of Ragnaros") {
        LEGENDARY
    } else if (name == "Conjured Mana Cake") {
        CONJURED
    } else if (this.name != "Aged Brie" && this.name != "Backstage passes to a TAFKAL80ETC concert") {
        GENERAL
    } else {
        OLDER_BETTER
    }
}

fun Item.isExpired(): Boolean {
    return sellIn < 0
}

fun Item.decrease(type: ItemType) {
    when (type) {
        OLDER_BETTER, LEGENDARY -> {
        }
        GENERAL -> if (quality > 1) quality -= 1 else quality = 0
        CONJURED -> if (quality > 1) quality -= 2 else quality = 0
    }
}

fun Item.increase(type: ItemType) {
    if (quality < 50) {
        if (type == OLDER_BETTER) {
            if (sellIn in 6..10) {
                if (quality < 50) {
                    quality++
                }
            } else if (sellIn in 0..6) {
                if (quality < 50) {
                    quality++
                }
            } else {
                quality ++
            }
        }
    }
}

fun Item.updateSellIn() {
    sellIn--
}

fun Item.updateQualityAfterSellInExpired() {
    if (type() != OLDER_BETTER) {
        if (name != "Backstage passes to a TAFKAL80ETC concert") {
            if (quality > 0) {
                if (type() != LEGENDARY) {
                    quality -= 1
                }
            }
        } else {
            quality -= 2
        }
    } else {
        if (quality < 50) {
            quality += 1
        }
    }
}