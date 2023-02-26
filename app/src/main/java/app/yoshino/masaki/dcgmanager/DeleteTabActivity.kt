package app.yoshino.masaki.dcgmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import app.yoshino.masaki.dcgmanager.databinding.ActivityAddMatchesBinding
import app.yoshino.masaki.dcgmanager.databinding.ActivityDeleteTabBinding
import app.yoshino.masaki.dcgmanager.MyApplication.Companion.gameList
import app.yoshino.masaki.dcgmanager.MyApplication.Companion.fragmentList

class DeleteTabActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteTabBinding
    lateinit var db: AppDatabase

    //var pagerAdapter:ViewPagerAdapter? = null
    var pagerAdapter = ViewPagerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityDeleteTabBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        db = AppDatabase.getInstance(this.applicationContext)!!
        val tabPosition = intent.getIntExtra("pI", 0)
        //val gameList = pagerAdapter.gameList

        //val gameList = ArrayList(pagerAdapter?.gameList)
//        val matchlist = db.matchesDao().getGmae(pagerAdapter.gameList[tabPosition])
//        matchlist.sortedBy { it.deck }
//        val enemylist = matchlist.map { it.deck }.toList()
//        val distinctlist = enemylist.distinct().toList()

        val spinner = findViewById<Spinner>(R.id.spinner_gamelist)
        val arrayAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            gameList//pageradapter.
        )
//        val spinnerenemy = findViewById<Spinner>(R.id.spinner_enemy)
//        val enemyAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, distinctlist)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(applicationContext, "Selction made", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
//        spinnerenemy.adapter = enemyAdapter
//        spinnerenemy.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                Toast.makeText(applicationContext,"Selction made", Toast.LENGTH_SHORT).show()
//            }
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//        }
        val MainIntent = Intent(this, MainActivity::class.java)
        binding.deleteConfirmButton.setOnClickListener {
//            val matches :Matches = Matches(
//                game = binding.spinnerGame2.selectedItem.toString(),
//                deck = if (binding.enemyDeckText.text.toString() == "") binding.spinnerEnemy.selectedItem.toString() else binding.enemyDeckText.text.toString(),
//                first = binding.firstSwitch.isChecked,
//                win = binding.winSwitch.isChecked
//            )
//            db.matchesDao().insert(matches)
            pagerAdapter.deleteTab(binding.spinnerGamelist.selectedItemPosition)
            startActivity(MainIntent)
        }
    }
}