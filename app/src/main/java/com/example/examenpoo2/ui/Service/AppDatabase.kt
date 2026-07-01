package com.example.examenpoo2.ui.Service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.examenpoo2.ui.Model.Converters
import com.example.examenpoo2.ui.Model.TestResult
import com.example.examenpoo2.ui.Model.User

/**
 * Base de datos principal de la aplicación utilizando Room.
 * 
 * Esta clase centraliza la configuración de persistencia local, definiendo las entidades
 * y proporcionando acceso a los Objetos de Acceso a Datos (DAOs).
 *
 * Sigue el patrón Singleton para asegurar que solo exista una instancia de la base de datos
 * abierta en toda la aplicación, optimizando así el uso de recursos.
 */
@Database(entities = [User::class, TestResult::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Proporciona el DAO para operaciones relacionadas con usuarios.
     */
    abstract fun userDao(): UserDao

    /**
     * Proporciona el DAO para operaciones relacionadas con los resultados de los tests.
     */
    abstract fun testResultDao(): TestResultDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Retorna la instancia única de la base de datos.
         * Si no existe, la crea utilizando el builder de Room.
         *
         * @param context Contexto de la aplicación necesario para inicializar Room.
         * @return La instancia de [AppDatabase].
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "examen_poo2_db"
                )
                .fallbackToDestructiveMigration() // Útil durante el desarrollo para evitar crashes por cambios en el esquema
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
