package app.yoshino.masaki.dcgmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import app.yoshino.masaki.dcgmanager.databinding.ActivityAddMatchesBinding
import app.yoshino.masaki.dcgmanager.databinding.ActivityAddTabBinding
import com.google.android.material.tabs.TabLayout

class AddTabActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTabBinding
    lateinit var vpa: ViewPagerAdapter
    lateinit var ma: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTabBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        vpa = ViewPagerAdapter(this)
        ma = MainActivity()
        val MainIntent = Intent(this,MainActivity::class.java)


        binding.buttonAddTab.setOnClickListener {
            //vpa.addTab(SecondFragment.newInstance(binding.textNew.text.toString()),binding.textNew.text.toString())
            //vpa.addTab(SecondFragment(),binding.textNew.text.toString())
            //ma.tabLayoutAdapter.addTab(ma.tabLayoutAdapter.newTab().setText(binding.textNew.text.toString()))
//            vpa.gameList.add("masaki")
//            vpa.fragmentList.add(SecondFragment.newInstance("masaki"))
            MyApplication.gameList.add(binding.textNew.text.toString())
            val nextTitlePosition = MyApplication.gameList.size - 1
            //val nextOrdinalId = titlesOrdinals.size - 1
            var nextTitle = MyApplication.gameList[nextTitlePosition]

            // if a title has been added before, don't add it
            // new tabs cannot have the same name as old tabs
            vpa.addTab(SecondFragment.newInstance(nextTitle), nextTitle)
            startActivity(MainIntent)
        }
    }
}