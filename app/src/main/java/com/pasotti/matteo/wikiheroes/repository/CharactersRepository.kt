package com.pasotti.matteo.wikiheroes.repository

import androidx.lifecycle.LiveData
import com.pasotti.matteo.wikiheroes.BuildConfig
import com.pasotti.matteo.wikiheroes.api.ApiResponse
import com.pasotti.matteo.wikiheroes.api.MarvelApi
import com.pasotti.matteo.wikiheroes.api.Resource
import com.pasotti.matteo.wikiheroes.models.Character
import com.pasotti.matteo.wikiheroes.models.CharacterResponse
import com.pasotti.matteo.wikiheroes.models.DetailResponse
import com.pasotti.matteo.wikiheroes.models.FavCharacter
import com.pasotti.matteo.wikiheroes.room.CharacterDao
import com.pasotti.matteo.wikiheroes.room.FavCharacterDao
import com.pasotti.matteo.wikiheroes.utils.PreferenceManager
import com.pasotti.matteo.wikiheroes.utils.Utils
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.thread

@Singleton
class CharactersRepository @Inject
constructor(val characterDao: CharacterDao, val favCharacterDao: FavCharacterDao, val marvelApi: MarvelApi , val preferenceManager: PreferenceManager) {

    val defaultLimit = 10

    var offset = 0

    val timestamp = Date().time

    fun getCharacters(page: Int): LiveData<Resource<List<Character>>> {

        return object : NetworkBoundResource<List<Character>, CharacterResponse>() {

            override fun saveFetchData(item: CharacterResponse) {

                offset += defaultLimit
                val newCharacters = item.data.results

                newCharacters.forEach { character -> character.page = page }

                characterDao.insertCharacters(newCharacters)
            }

            override fun shouldFetch(data: List<Character>?): Boolean {
                if(data != null && data.isNotEmpty()) {
                    offset = data.size
                }
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Character>> {
                return if(page == 0) {
                    characterDao.getCharacters()
                } else  {
                    characterDao.getCharacters(page)
                }

            }

            override fun fetchService(): LiveData<ApiResponse<CharacterResponse>> {
                return marvelApi.getCharacters("-modified", offset, defaultLimit)
                //return marvelApi.getCharacters("-modified", timestamp.toString(), Utils.MARVEL_PUBLIC_KEY, hash, offset, defaultLimit)
            }

            override fun onFetchFailed() {

            }

        }.asLiveData

    }

    fun checkSyncCharacters() {

        val todayDate = Utils.getCurrentDate()
        val lastSynchDate = preferenceManager.getString(PreferenceManager.LAST_DATE_SYNC, "")

        // refresh comics every 5 days
        if (lastSynchDate != null && lastSynchDate != "" && Utils.getDifferenceBetweenDates(lastSynchDate, todayDate) == 2L) {
            thread {
                characterDao.deleteCharacters()
            }

        }
    }

    fun getFavCharacters() : LiveData<List<FavCharacter>> {
        return favCharacterDao.getFavCharacters()
    }

    fun addFavCharacter( character: Character) {
        thread {
            val favCharacter = FavCharacter( character.id , character)
            favCharacterDao.insertFavCharacter(favCharacter)
        }
    }

    fun removeFavCharacter( character: Character) {
        thread {
            favCharacterDao.removeFavCharacter(character.id)
        }
    }

    fun getFavCharacterById( id : Int) : LiveData<FavCharacter> {
        return favCharacterDao.getFavCharacterById(id)
    }


    fun searchCharacterByName( nameStartsWith : String) : LiveData<ApiResponse<CharacterResponse>> {
        return marvelApi.searchCharacterNameStartsWith(nameStartsWith  , "name", offset, defaultLimit)
    }
}
