package com.towich.kinopoiskDev.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.towich.kinopoiskDev.data.room.entity.QueryEntity

@Dao
interface QueryDao {
    @Query("SELECT * FROM queries_table")
    suspend fun getAllQueries(): List<QueryEntity>

    @Query("SELECT COUNT(*) FROM queries_table")
    suspend fun getQueriesCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(query: QueryEntity)

    @Query("DELETE FROM queries_table WHERE id = (SELECT id FROM queries_table ORDER BY id LIMIT 1)")
    suspend fun deleteFirstQuery()

    @Query("UPDATE queries_table SET id = id - 1 WHERE id > 1")
    suspend fun shiftIds()
}