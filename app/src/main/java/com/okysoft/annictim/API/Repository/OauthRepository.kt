package com.okysoft.annictim.API.Repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.okysoft.annictim.API.AnnictService
import com.okysoft.annictim.API.Model.Request.OauthRequestModel
import com.okysoft.annictim.API.Model.Response.OauthResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class OauthRepository @Inject constructor(private val oauthService: AnnictService.Oauth) {

    fun getAccessToken(oauthRequestModel: OauthRequestModel): LiveData<OauthResponseModel> {
        val data = MutableLiveData<OauthResponseModel>()
        oauthService.getAccessToken(oauthRequestModel).enqueue(object : Callback<OauthResponseModel> {
            override fun onResponse(call: Call<OauthResponseModel>, response: Response<OauthResponseModel>) {
                data.value = response.body()
            }
            override fun onFailure(call: Call<OauthResponseModel>?, t: Throwable?) {
                //TODO: なんかやる
            }
        })
        return data
    }

}