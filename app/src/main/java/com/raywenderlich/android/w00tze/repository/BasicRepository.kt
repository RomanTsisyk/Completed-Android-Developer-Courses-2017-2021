package com.raywenderlich.android.w00tze.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import com.raywenderlich.android.w00tze.app.Constants.fullUrlString
import com.raywenderlich.android.w00tze.model.Gist
import com.raywenderlich.android.w00tze.model.Repo
import com.raywenderlich.android.w00tze.model.User
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

object BasicRepository : Repository {

    private const val TAG = "BasicRepository"

    private const val LOGIN = "w00tze"

    override fun getRepos(): LiveData<List<Repo>> {
        val liveData = MutableLiveData<List<Repo>>()

        FetchReposAsyncTask { liveData.value = it }.execute()

        return liveData
    }

    private fun parseRepos (jsonString: String): List<Repo>{
        val repos = mutableListOf<Repo>()
        val reposArray = JSONArray(jsonString)
        for (i in 0 until reposArray.length()){
            val repoObject = reposArray.getJSONObject(i)
            val repo = Repo(repoObject.getString("name"))
            repos.add(repo)
        }
        return repos
    }

    private fun parseGists (jsonString: String): List<Gist>{
        val gists = mutableListOf<Gist>()
        val gistsArray = JSONArray(jsonString)
        for (i in 0 until gistsArray.length()){
            val gistObject = gistsArray.getJSONObject(i)
            val createdAt = gistObject.getString("created_at")
            val description = gistObject.getString("description")
            gists.add(Gist(createdAt, description))
        }
        return gists
    }

    override fun getGists(): LiveData<List<Gist>> {
        val liveData = MutableLiveData<List<Gist>>()

        FetchGistAsyncTask { liveData.value = it }.execute()

        return liveData
    }

    override fun getUser(): LiveData<User> {
        val liveData = MutableLiveData<User>()

        val user = User(
                1234L,
                "w00tze",
                "w00tze",
                "W00tzeWootze",
                "https://avatars0.githubusercontent.com/u/36771440?v=4")

        liveData.value = user

        return liveData
    }

    private fun fetchRepos(): List<Repo>? {
        try {
            val url = Uri.parse(fullUrlString("/users/$LOGIN/repos")).toString()
            val jsonString = getUrlAsString(url)
            return parseRepos(jsonString)
        } catch (e: IOException) {
            Log.e(TAG, "Error retrieving repos: ${e.localizedMessage}")
        } catch (e : JSONException){
            Log.e(TAG, "Error retrieving repos: ${e.localizedMessage}")
        }
        return null
    }


    private fun fetchGists(): List<Gist>? {
        try {
            val url = Uri.parse(fullUrlString("/users/$LOGIN/gists")).toString()
            val jsonString = getUrlAsString(url)
            return  parseGists(jsonString)
        } catch (e: IOException) {
            Log.e(TAG, "Error retrieving repos: ${e.localizedMessage}")
        } catch (e : JSONException){
            Log.e(TAG, "Error retrieving repos: ${e.localizedMessage}")
        }
        return null
    }

    private class FetchReposAsyncTask(val callback: ReposCallback) : AsyncTask<ReposCallback, Void, List<Repo>>() {
        override fun doInBackground(vararg params: ReposCallback?): List<Repo>? {
            return fetchRepos()
        }

        override fun onPostExecute(result: List<Repo>?) {
            super.onPostExecute(result)
            if (result != null) {
                callback(result)
            }
        }
    }

    private class FetchGistAsyncTask(val callback: GistsCallback) : AsyncTask<GistsCallback, Void, List<Gist>>() {
        override fun doInBackground(vararg params: GistsCallback?): List<Gist>? {
            return fetchGists()
        }

        override fun onPostExecute(result: List<Gist>?) {
            super.onPostExecute(result)
            if (result != null) {
                callback(result)
            }
        }
    }

}