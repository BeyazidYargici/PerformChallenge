import android.content.Context
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beyazid.perform.MainActivity
import com.beyazid.perform.R
import com.google.gson.Gson
import fr.arnaudguyon.xmltojsonlib.XmlToJson

///**
// *  Kotlin extensions
// *  Created by beyazid on 2019-05-17.
// */
//
//// ------------------- Views ------------------- //
//
//fun View.visible() = run { this.visibility = View.VISIBLE }
//
//fun View.gone() = run { this.visibility = View.GONE }
//
//fun TextView.textColor(color: Int) = run { this.setTextColor(ContextCompat.getColor(this.context, color)) }
//
//fun TextView.percentageColor(value: Double) =
//    if (value > 0) this.textColor(R.color.positive_percentage) else this.textColor(R.color.negative_percentage)
//
//fun RecyclerView.adapterSettings(adapter: CoinListAdapter, layoutManager: LinearLayoutManager) {
//    this.layoutManager = layoutManager
//    this.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down_animation)
//    this.setHasFixedSize(true)
//    this.itemAnimator = DefaultItemAnimator()
//    this.adapter = adapter
//}
//
//// ------------------- Network ------------------- //
//
//suspend fun coinListAsync(apiService: ApiService, start: Int, limit: Int) =
//    apiService.getCoinListAsync(start.toString(), limit.toString()).await().body()?.data
//
//suspend fun coinInfoList(apiService: ApiService, string: String) =
//    apiService.getCoinInfoAsync(string).await().body()?.coinInfoData
//
//suspend fun coinInfo(apiService: ApiService, string: String) =
//    apiService.getCoinInfoAsync(string).await()
//
//// ------------------- Variables ------------------- //
//
//fun symbolList(list: List<DataItem>): String {
//    val stringBuilder = StringBuilder()
//    stringBuilder.clear()
//    list.forEach { stringBuilder.append(it.symbol + COMMA) }
//    return stringBuilder.toString().substring(0, stringBuilder.length - COMMA.length)
//}
//
//fun String.percentageValue() = if (this.startsWith("-")) "%${this.substring(0, 5)}" else "%${this.substring(0, 4)}"
//
//fun String.price() = this.substring(0, 8) + "$"
//
// ------------------- Navigation ------------------- //

fun openFragment(context: Context, layoutId: Int, @Nullable bundle: Bundle?) =
    if (bundle!=null) (context as MainActivity).navController.navigate(layoutId, bundle) else (context as MainActivity).navController.navigate(layoutId)

//private fun rvAnimation() : AnimationSet{
//    val set = AnimationSet(true)
//    var animation: Animation = AlphaAnimation(0.0f, 1.0f)
//    animation.duration = 600
//    set.addAnimation(animation)
//
//    animation = TranslateAnimation(
//        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
//        Animation.RELATIVE_TO_SELF, -0.1f, Animation.RELATIVE_TO_SELF, 0.0f
//    )
//    animation.duration = 500
//    set.addAnimation(animation)
//    return set
//}


fun RecyclerView.init(adapter: RecyclerView.Adapter<*>) {
    val fallDown = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down_animation)
    this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    this.layoutAnimation = fallDown
    this.setHasFixedSize(true)
    this.itemAnimator = DefaultItemAnimator()
    this.adapter = adapter
    adapter.notifyDataSetChanged()
}


fun <T> xmlToJson(gson: Gson, xml: String, clazz: Class<T>): T =
    gson.fromJson(XmlToJson.Builder(xml).build().toJson().toString(), clazz) as T

fun TextView.slideToRight() = this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_to_right))
fun TextView.slideToLeft() = this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_to_left))
fun TextView.slideToRightGroupName() = this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_to_right_group_name))
fun TextView.slideToLeftGroupName() = this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_to_left_group_name))




