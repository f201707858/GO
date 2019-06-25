package com.example.inseego

import android.app.Activity

import android.app.ActionBar
import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toolbar
import com.example.inseego.Fragments.DeviceFragment

class add_group : AppCompatActivity() {
    var drawer_layout: DrawerLayout? = null
    var toolbar: android.support.v7.widget.Toolbar? = null
    var deviceFragment = DeviceFragment()
    var list = arrayOf("Log Out ")
    var List_Item: ListView? = null
    var navigationview: NavigationView? = null
    var tv : TextView?=null
    //var mNavigationDrawerFragment = fragmentManager.findFragmentById(R.id.nav_view) as NavigationDrawerFragment
    // Set up the drawer.
    //mNavigationDrawerFragment!!.setUp(R.id.nav_view,findViewById<View>(R.id.drawer_layout) as DrawerLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addgrpwithnavigationview)

        tv = findViewById(R.id.userinnav)
        var intent = getIntent()
        tv?.setText(intent.getStringExtra("message"))
        List_Item = findViewById<ListView>(R.id.nav_list)
        val list_adapter = ArrayAdapter(this, R.layout.login_list_item, list)
        List_Item?.setAdapter(list_adapter)

        navigationview = findViewById(R.id.nav_view)
        drawer_layout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.Tools)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)


        val toggle = ActionBarDrawerToggle(
            this@add_group,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout?.addDrawerListener(toggle)
        toggle.syncState()

        drawer_layout?.closeDrawers()
        this.supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_details, deviceFragment, "DeviceFragment")
            .commit()


    }

}


/*private var mNavigationDrawerFragment: NavigationDrawerFragment? = null
private var mTitle: CharSequence? = null

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.addgrpwithnavigationview)

    mNavigationDrawerFragment = fragmentManager.findFragmentById(R.id.navigation_drawer) as NavigationDrawerFragment
    mTitle = title

    // Set up the drawer.
    mNavigationDrawerFragment!!.setUp(
        R.id.navigation_drawer,
        findViewById<View>(R.id.drawer_layout) as DrawerLayout
    )
}

override fun onNavigationDrawerItemSelected(position: Int) {
    // update the main content by replacing fragments
    val fragmentManager = fragmentManager
    fragmentManager.beginTransaction()
        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
        .commit()
}

fun onSectionAttached(number: Int) {
    when (number) {
        1 -> mTitle = getString(R.string.title_section1)
        2 -> mTitle = getString(R.string.title_section2)
        3 -> mTitle = getString(R.string.title_section3)
    }
}

fun restoreActionBar() {
    val actionBar = actionBar
    actionBar!!.navigationMode = ActionBar.NAVIGATION_MODE_STANDARD
    actionBar.setDisplayShowTitleEnabled(true)
    actionBar.title = mTitle
}

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle
    ): View? {
        return inflater.inflate(R.layout.fragment_add_group, container, false)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        (activity as add_group).onSectionAttached(
            arguments.getInt(ARG_SECTION_NUMBER)
        )
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}

}
*/