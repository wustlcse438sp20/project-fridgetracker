package com.example.fridgetracker.viewModels
//
//import android.app.Application
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.MutableLiveData
//import com.example.cse438.cse438_assignment2.data.TrackInPlaylist
//import com.example.cse438.cse438_assignment2.data.TrackPlaylist
//import com.example.cse438.cse438_assignment2.data.TrackPlaylistRepository
//import com.example.cse438.cse438_assignment2.data.TrackPlaylistRoomDatabase
//
//import kotlinx.coroutines.launch
//
//class SavedRecipesViewModel(application: Application): AndroidViewModel(application) {
//    var _playlistList: LiveData<List<TrackPlaylist>> = MutableLiveData()
//    var _tracksInAPlaylist: LiveData<List<TrackInPlaylist>> = MutableLiveData()
//    private val repository: TrackPlaylistRepository
//    private val repositoryTracks: TrackPlaylistRepository
//
//    init {
//        repository = TrackPlaylistRepository(TrackPlaylistRoomDatabase.getDatabase(application).trackPlaylistDao())
//        _playlistList = repository.allTracks
//
//        repositoryTracks = TrackPlaylistRepository(TrackPlaylistRoomDatabase.getDatabase(application).trackPlaylistDao())
//        _tracksInAPlaylist = repositoryTracks.allTracksInPlaylist
//    }
//
//    fun getPlaylists() : LiveData<List<TrackPlaylist>>{
//        return _playlistList
//    }
//
//    fun getPlaylistTracks(playlistName : String) : LiveData<List<TrackInPlaylist>>{
//        _tracksInAPlaylist = repositoryTracks.getPlaylistTracks(playlistName)
//        return repositoryTracks.getPlaylistTracks(playlistName)
//        //return _tracksInAPlaylist
//    }
//    fun insertPlayList(trackPlaylist: TrackPlaylist) {
//        repository.insertPlayList(trackPlaylist)
//    }
//    fun insertTrackToPlayList(track: TrackInPlaylist) {
//        repositoryTracks.insertTrackToPlayList(track)
//    }
//
//    fun delete(trackPlaylist: TrackPlaylist) {
//        repository.deletePlayList(trackPlaylist)
//    }
//
//    //delete track in playlist
//    fun deleteTrack(track:Int){
//        repositoryTracks.deleteTrack(track)
//    }
//
//    fun clear() {
//        repository.clear()
//    }
//
//
//
//}