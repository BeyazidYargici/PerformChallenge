import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beyazid.perform.MainActivity
import com.beyazid.perform.R
import com.google.gson.Gson
import fr.arnaudguyon.xmltojsonlib.XmlToJson

/**
 *  Kotlin extensions
 *  Created by beyazid on 2019-05-22.
 */

/**
 * helper for opening new fragment
 *
 * @param layoutId  fragment layout
 * @param bundle    share data with new fragment
 * @param singleTop clear backstack if singleTop is true
 */
fun openFragment(context: Context, layoutId: Int, @Nullable bundle: Bundle?, singleTop: Boolean) {
    (context as MainActivity)
    if (singleTop) {
        context.navController.popBackStack()
    }

    if (bundle != null) context.navController.navigate(
        layoutId,
        bundle
    ) else context.navController.navigate(layoutId)
}


// ------------------- Xml to Json ------------------- //
/**
 * This method converts the plain xml strings to JSON
 * @param T Response Object
 * @param xml Plain xml string
 * @param clazz Response object
 * @return JSON object
 */
fun <T> xmlToJson(gson: Gson, xml: String, clazz: Class<T>): T =
    gson.fromJson(XmlToJson.Builder(xml).build().toJson().toString(), clazz) as T


// ------------------- Views ------------------- //

fun RecyclerView.init(adapter: RecyclerView.Adapter<*>) {
    val fallDown = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down_animation)
    this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    this.layoutAnimation = fallDown
    this.setHasFixedSize(true)
    this.itemAnimator = DefaultItemAnimator()
    this.adapter = adapter
    adapter.notifyDataSetChanged()
}

fun TextView.textColor(textColorRes: Int) = this.setTextColor(ContextCompat.getColor(context, textColorRes))
fun TextView.rankColor(@Nullable zone: String) {
    when (zone) {
        "CL" -> this.setBackgroundColor(ContextCompat.getColor(context, R.color.cl))
        "CL qf" -> this.setBackgroundColor(ContextCompat.getColor(context, R.color.cl_qf))
        "EuroL" -> this.setBackgroundColor(ContextCompat.getColor(context, R.color.ec))
        "releg" -> this.setBackgroundColor(ContextCompat.getColor(context, R.color.releg))
    }
}


fun View.visible() = run { this.visibility = View.VISIBLE }

fun View.gone() = run { this.visibility = View.GONE }


// ------------------- Basic Animations ------------------- //

fun TextView.slideToRight() = this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_to_right))
fun TextView.slideToLeft() = this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_to_left))
fun TextView.slideToRightGroupName() =
    this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_to_right_group_name))

fun TextView.slideToLeftGroupName() =
    this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_to_left_group_name))

fun ImageView.launcherAnim() = this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_icon))



