package com.example.newfirstapphomeee

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.sofirstapp.databinding.FragmentContactBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ContactViewModel (private val database: ContactDataDAO,
                        private val binding: FragmentContactBinding,
                        application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val contacts = database.get()
    val contactString = Transformations.map(contacts) { contacts ->
        formatContact(contacts)
    }

    private fun formatContact(contact: List<ContactData>): Spanned {
        val sb = StringBuilder()
        sb.apply {
            //append(resources.getString(R.string.title))
            contact.forEach {
                append(it.id)
                append(" : ")
                append(it.name)
                append(", Tel : ")
                append(it.phone)
                append("<br>")
            }
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }