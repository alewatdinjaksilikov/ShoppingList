package com.example.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

class ShopListRepositoryImpl(
    application: Application
):ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override suspend fun AddShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun DeleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun EditShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun GetShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

//    override fun GetShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
//        addSource(shopListDao.getShopList()){
//            value = mapper.mapListDbModelToListEntity(it)
//        }
//    }   1-ВАРИАНТ

    override fun GetShopList(): LiveData<List<ShopItem>> =
        Transformations.map(shopListDao.getShopList()){
        mapper.mapListDbModelToListEntity(it)
    }




}