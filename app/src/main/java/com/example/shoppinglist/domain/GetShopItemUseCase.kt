package com.example.shoppinglist.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun GetShopItem(shopItemId:Int):ShopItem{
       return shopListRepository.GetShopItem(shopItemId)
    }
}