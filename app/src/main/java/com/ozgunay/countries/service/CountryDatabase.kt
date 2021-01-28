package com.ozgunay.countries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ozgunay.countries.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    //Singleton

    //sadece 1 obje oluşturmak için kullanılır. Obje varsa onu kullanır. Her sınıftan veya fragmenttan ulaşılabilir.
    companion object {

        //volatile ekleyerek farklı threadlerde çağırılabilir.
        @Volatile
        private var instance: CountryDatabase? = null

        private val lock = Any()

        //instance objemizin olup olmadığını kontrol etmek için yazılan fonksiyon. Yoksa oluşturuyor.varsa olanı döndürüyor.
        operator fun invoke(context: Context) = instance
            ?: synchronized(lock) {  //birden fazla thread instance'a erişmeye çalışırsa sadece bir tanesine izin verir.
                instance ?: makeDatabase(context).also {
                    instance = it
                }
            }


        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, CountryDatabase::class.java, "countrydatabase"
        ).build()
    }

}