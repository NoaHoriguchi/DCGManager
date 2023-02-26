package app.yoshino.masaki.dcgmanager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.yoshino.masaki.dcgmanager.databinding.FirstFragmentBinding
import app.yoshino.masaki.dcgmanager.databinding.SecondFragmentBinding
import app.yoshino.masaki.dcgmanager.MyApplication.Companion.gameList
import app.yoshino.masaki.dcgmanager.MyApplication.Companion.fragmentList

class SecondFragment : Fragment(){

    companion object {
        fun newInstance(tabName: String): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putString("name_tab", tabName)
            fragment.setArguments(args)
            return fragment
        }
        fun getInstance(tabName: String): SecondFragment {
            val thisSecondFragment = SecondFragment()
            val titleToDisplay = tabName
            thisSecondFragment.setTitleText(titleToDisplay)
            return thisSecondFragment
        }
        fun getNewInstance(nextPosition: Int): SecondFragment {
            val thisSecondFragment = SecondFragment()
            val titleToDisplay = gameList[nextPosition]
            val args = Bundle()
            args.putString("name_tab", titleToDisplay)
            thisSecondFragment.setArguments(args)
            return thisSecondFragment
        }

        fun getNewInstance2(nextPosition: Int): SecondFragment {
            val thisSecondFragment = SecondFragment()
            val titleToDisplay = gameList[nextPosition]
            thisSecondFragment.setTitleText(titleToDisplay)
            return thisSecondFragment
        }
    }



    private lateinit var binding: FirstFragmentBinding
    private lateinit var recyclerView: RecyclerView

    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FirstFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
    var deckList = listOf("")

    var firstList = listOf(true)

    var winList = listOf(true)

    var titleToDisplay = "String"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = activity?.let { AppDatabase.getInstance(it.applicationContext) }!!

        val args = arguments
        val index = args?.getString("name_tab")

        val matchList = index?.let { db.matchesDao().getGmae(it) }

        if (matchList != null) {
            deckList = matchList.map { it.deck }.toList()
        }
        if (matchList != null) {
            firstList = matchList.map { it.first}.toList()
        }
        if (matchList != null) {
            winList = matchList.map { it.win }.toList()
        }

        val firstListUpdated = firstList.map{if (it) "First" else "Second"}
        val winListUpdated = winList.map{if (it) "Win" else "Lose"}

        recyclerView = binding.recyclerView
        recyclerView.adapter = RecyclerAdapter(deckList,firstListUpdated,winListUpdated)
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }
    fun setTitleText(title: String) {
        titleToDisplay = title
    }

    override fun onDestroy() {
        Log.d("gamelist", "DESTROYED")
        super.onDestroy()
    }
}