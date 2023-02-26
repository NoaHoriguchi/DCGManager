package app.yoshino.masaki.dcgmanager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import app.yoshino.masaki.dcgmanager.MyApplication.Companion.gameList
import app.yoshino.masaki.dcgmanager.MyApplication.Companion.fragmentList

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
FragmentStateAdapter(fragmentActivity){

//        var gameList: MutableList<String> = arrayListOf("hunter","MRC","noa")
//        var fragmentList: MutableList<Fragment> = arrayListOf(SecondFragment.newInstance("hunter"),SecondFragment.newInstance("MRC"), SecondFragment.newInstance("noa"))
//
    override fun getItemCount(): Int {
        return fragmentList.size
    }

//    override fun createFragment(position: Int): Fragment {
//        when(position){
//            position -> return fragmentList[position]
//        }
//        return FirstFragment()
//    }


    override fun createFragment(position: Int): Fragment {
        return SecondFragment.getNewInstance(position)
    }

    fun addTab(fragment: Fragment,title:String){
        //gameList.add(title)
        fragmentList.add(fragment)
        notifyDataSetChanged()
    }

    fun deleteTab(currentIndex: Int){
        if (gameList.size > 1 && fragmentList.size > 1) {
            gameList.removeAt(currentIndex)
            fragmentList.removeAt(currentIndex)
        } else {
            return
        }
        notifyDataSetChanged()
    }

    fun deleteTab(titleName: String, currentIndex: Int){
        if (gameList.size > 1 && fragmentList.size > 1) {
            gameList.remove(titleName)
            fragmentList.removeAt(currentIndex)
        } else {
            return
        }
        notifyDataSetChanged()
    }
}