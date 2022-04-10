package com.example.costhistory.units

import com.example.costhistoryv2.R


object IconUnit {
    fun getAllIcons(): List<Int> {
        return listOf(
            R.drawable.ic_castle,
            R.drawable.ic_home,
            R.drawable.ic_family_1,
            R.drawable.ic_family_2,

            R.drawable.ic_cookie,
            R.drawable.ic_fastfood,
            R.drawable.ic_restaurant,
            R.drawable.ic_restaurant_menu,

            R.drawable.ic_car,
            R.drawable.ic_bus,
            R.drawable.ic_airplane_ticket,

            R.drawable.ic_hiking,
            R.drawable.ic_fitness,
            R.drawable.ic_roller_skating,

            R.drawable.ic_medical_services,
            R.drawable.ic_engineering,

            R.drawable.ic_iphone,
            R.drawable.ic_computer,

            R.drawable.ic_shopping_cart,
            R.drawable.ic_shopping_basket,

            R.drawable.ic_wallet,
            R.drawable.ic_note
        )
    }
    fun getCharacterIcons(): List<Int> {
        return listOf(
            R.drawable.ic_pets,
            R.drawable.ic_boy,
            R.drawable.ic_girl,
            R.drawable.ic_man,
            R.drawable.ic_woman,
            R.drawable.ic_pregnant_woman,
            R.drawable.ic_baby_changing_station,
            R.drawable.ic_elderly_woman
        )
    }
}