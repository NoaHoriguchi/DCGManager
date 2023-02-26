package app.yoshino.masaki.dcgmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.room.Room
import app.yoshino.masaki.dcgmanager.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import app.yoshino.masaki.dcgmanager.MyApplication.Companion.gameList
import app.yoshino.masaki.dcgmanager.MyApplication.Companion.fragmentList


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var vpa: ViewPagerAdapter
    lateinit var tabLayoutAdapter: TabLayout

    val activityViewPagerAdapter: ViewPagerAdapter by lazy { ViewPagerAdapter(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val AddIntent = Intent(this,AddMatchesActivity::class.java)
        val Addtabintent = Intent(this,AddTabActivity::class.java)
        val InfoIntent = Intent(this,InfoActivity::class.java)
        val DeleteIntent = Intent(this,DeleteTabActivity::class.java)
        //val vpa = ViewPagerAdapter(this, lifecycle)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        val matchesDao = db.matchesDao()


// DO NOT ADD AFTER CHANGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        val matches: List<Matches> = matchesDao.getAll()
//        val recyclerAdapter =RecyclerAdapter(
//            OnClickListener{memo ->
//                toEditIntent.putExtra("ID".matches())
//                startActivity(toEditIntent)
//            }
//        )

        try{
            this.supportActionBar!!.hide()
        }catch (e: NullPointerException){
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        tabLayoutAdapter = TabLayout(this)

        viewPager.adapter = ViewPagerAdapter(this)
        vpa = ViewPagerAdapter(this)
        Log.d("gamelist", fragmentList.toString())
        Log.d("gamelist", gameList.toString())

//        TabLayoutMediator(tabLayout,viewPager){tab,position ->
//            tab.text = gameList[position]//vpa.
//        }.attach()

        var tabPosition = tabLayout.selectedTabPosition

        var newtabnumber = 0

        binding.buttonDelete.setOnClickListener {
            tabPosition = tabLayout.selectedTabPosition
            DeleteIntent.putExtra("pI", tabPosition)
            startActivity(DeleteIntent)
        }

        binding.buttonAdd.setOnClickListener{
            tabPosition = tabLayout.selectedTabPosition
            AddIntent.putExtra("pI", tabPosition)
            startActivity(AddIntent)
        }
        binding.buttonTab.setOnClickListener {
            tabPosition = tabLayout.selectedTabPosition
            Addtabintent.putExtra("pI", tabPosition)
            startActivity(Addtabintent)
        }
        binding.buttonInfo.setOnClickListener {
            tabPosition = tabLayout.selectedTabPosition
            InfoIntent.putExtra("pI", tabPosition)
            startActivity(InfoIntent)
        }

        setUpTabs()
        //addTabFabOnClick()
    }
    private fun setUpTabs() {
        // Set the title of the tabs
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = gameList[position]
        }.attach()
    }
//    private fun addTabFabOnClick() {
//        binding.buttonTab.setOnClickListener {
//            gameList.add("newgame")
//            val nextTitlePosition = gameList.size - 1
//            //val nextOrdinalId = titlesOrdinals.size - 1
//            var nextTitle = gameList[nextTitlePosition]
//
//            // if a title has been added before, don't add it
//            // new tabs cannot have the same name as old tabs
//            activityViewPagerAdapter.addTab(SecondFragment.newInstance(nextTitle), nextTitle)
//        }
//    }
}