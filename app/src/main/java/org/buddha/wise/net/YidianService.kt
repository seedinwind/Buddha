package org.buddha.wise.net

import io.reactivex.Observable
import org.buddha.wise.unpack.yd.YDChannel
import org.buddha.wise.unpack.yd.YdLogin
import retrofit2.Call
import retrofit2.http.GET

interface YidianService {
    @GET("")
    fun getChannels(): Observable<YDChannel>

    @GET("")
    fun login(): Observable<YdLogin>
}

