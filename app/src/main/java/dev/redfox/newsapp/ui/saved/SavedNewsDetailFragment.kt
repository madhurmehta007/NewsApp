package dev.redfox.newsapp.ui.saved

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import dev.redfox.newsapp.R
import dev.redfox.newsapp.database.News
import dev.redfox.newsapp.databinding.FragmentNewsDetailBinding
import dev.redfox.newsapp.databinding.FragmentSavedNewsDetailBinding

class SavedNewsDetailFragment(val news:News) : BottomSheetDialogFragment() {

    private var _binding: FragmentSavedNewsDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedNewsDetailBinding.inflate(inflater, container, false)

        binding.apply {
            Picasso.get().load(news.imageUrl).into(ivNews)
            tvNewsDetails.text = news.content
            tvDate.text = news.date
            tvReadMore.setOnClickListener {
                if(news.readMoreUrl!=null){
                    openCustomTab(activity, Uri.parse(news.readMoreUrl))
                }
                else{
                    Toast.makeText(context, "Sorry no more details available", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        return binding.root
    }

    private fun openCustomTab(activity: FragmentActivity?, url: Uri){
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.build().launchUrl(requireActivity(),url)
    }

}