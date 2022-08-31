package com.play.freso.music_player.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.play.freso.music_player.domain.model.Song
import com.play.freso.music_player.common.Constants.SONG_COLLECTION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class MusicDatabase @Inject constructor(
    db: FirebaseFirestore
) {

    private val songCollection = db.collection(SONG_COLLECTION)

    suspend fun getAllSongs() : List<Song> {
        return try {
            withContext(Dispatchers.IO){
                songCollection.get().result.toObjects(Song::class.java)
            }
        }catch (e: Exception){
            return emptyList()
        }
    }
}