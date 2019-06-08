package com.gassc.fillmyseat.activities

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.KeyEvent
import android.view.MenuItem
import android.widget.Toast
import com.gassc.fillmyseat.R
import com.gassc.fillmyseat.constants.FragmentConstant
import com.gassc.fillmyseat.fragments.EventDurationFragment
import com.gassc.fillmyseat.fragments.EventNameFragment
import com.gassc.fillmyseat.fragments.EventParticipantsFragment
import com.gassc.fillmyseat.model.Participant
import com.gassc.fillmyseat.utility.SharedPreferenceUtility
import kotlinx.android.synthetic.main.activity_create_event.*

class CreateEventActivity : AppCompatActivity() {

    private var mStepNumber: String = "1"


    // Fragments declaration
    private val mEventName: Fragment = EventNameFragment()
    private val mEventDuration: Fragment = EventDurationFragment()
    private val mEventParticipants: Fragment = EventParticipantsFragment()

    private var mCurrentFragment = mEventName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)
        setToolbarConfiguration()

        // Steps configuration
        setStepsConfiguration(mStepNumber)

        //
        initializerFragments()

    }

    override fun onResume() {
        super.onResume()

        // Button Next/Finish
        tv_next.setOnClickListener {
            when (mStepNumber) {
                "1" -> {
                    // Go to step 2!
                    mStepNumber = "2"
                    setStepsConfiguration(mStepNumber)
                    updateCurrentFragment(mCurrentFragment, mEventDuration, FragmentConstant.Tag.EVENT_DURATION)

                    // Change button name to Prev
                    tv_prev.text = resources.getString(R.string.bt_prev)
                }
                "2" -> {
                    // Go to step 3!
                    mStepNumber = "3"
                    setStepsConfiguration(mStepNumber)
                    updateCurrentFragment(mCurrentFragment, mEventParticipants, FragmentConstant.Tag.EVENT_PARTICIPANTS)

                    // Change button name to Create Event
                    tv_next.text = resources.getString(R.string.bt_createEvent)

                    // Change color to Accent
                    tv_next.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
                    tv_next.typeface = Typeface.DEFAULT_BOLD
                    tv_next.setTextColor(ContextCompat.getColor(this, R.color.colorWhite))
                }
                "3" -> {
                    // Create Event!
                    Toast.makeText(this,getSelectedParticipants().size.toString(),Toast.LENGTH_SHORT).show()
                    // Notify event created
                    val preference = SharedPreferenceUtility(this)
                    preference.setEmptyEvent(false)

                    // Go to Events Screen
                    val intent = Intent(this, HomeEventsActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                }
            }
        }

        // Button Cancel/Prev
        tv_prev.setOnClickListener {
            when (mStepNumber) {
                "1" -> {
                    finish()
                }
                "2" -> {
                    backStack()
                }
                "3" -> {
                    backStack()
                }
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                backStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        var back = false
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back = true
            backStack()
        } else {
            super.onKeyUp(keyCode, event)
        }
        return back
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun getSelectedParticipants() :List<Participant>{
        val fragment = mEventParticipants as EventParticipantsFragment
        return fragment.getSelectedParticipants()
    }

    private fun initializerFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragmentsSteps, mEventParticipants, FragmentConstant.Tag.EVENT_PARTICIPANTS)
            .hide(mEventParticipants).commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragmentsSteps, mEventDuration, FragmentConstant.Tag.EVENT_DURATION).hide(mEventDuration)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragmentsSteps, mCurrentFragment, FragmentConstant.Tag.EVENT_NAME)
            .addToBackStack(FragmentConstant.Tag.EVENT_NAME).show(mCurrentFragment).commit()
    }

    private fun updateCurrentFragment(
        activeFragment: Fragment,
        newCurrentFragment: Fragment,
        tagNewCurrentFragment: String
    ) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        if (activeFragment.tag.equals(newCurrentFragment.tag)) { // No fragments in Stack
            transaction.hide(activeFragment)
            transaction.show(newCurrentFragment)
            transaction.commit()
        } else { // Fragments in Stack
            transaction.addToBackStack(tagNewCurrentFragment)
            transaction.hide(activeFragment)
            transaction.show(newCurrentFragment)
            transaction.commit()
        }
        mCurrentFragment = newCurrentFragment
    }

    private fun backStack() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()

            val fTagName: FragmentManager.BackStackEntry =
                supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)

            //Highlight item
            when (fTagName.name) {
                FragmentConstant.Tag.EVENT_NAME -> {
                    mCurrentFragment = mEventName
                    mStepNumber = "1"
                    setStepsConfiguration(mStepNumber)

                    tv_prev.text = resources.getString(R.string.bt_cancel)
                }
                FragmentConstant.Tag.EVENT_DURATION -> {
                    mCurrentFragment = mEventDuration
                    mStepNumber = "2"
                    setStepsConfiguration(mStepNumber)

                    // Change text
                    tv_next.text = resources.getString(R.string.bt_next)

                    // Change color to Primary
                    tv_next.setBackgroundColor(ContextCompat.getColor(this, R.color.mtrl_btn_transparent_bg_color))
                    tv_next.typeface = Typeface.DEFAULT
                    tv_next.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                }
                FragmentConstant.Tag.EVENT_PARTICIPANTS -> {
                    mCurrentFragment = mEventParticipants
                }
            }
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            this.finish()
        }
    }

    private fun setStepsConfiguration(step: String) {
        when(step){
            "1" ->{
                iv_steps.setImageResource(R.drawable.ic_step1)
            }
            "2"->{
                iv_steps.setImageResource(R.drawable.ic_step2)
            }
            "3"->{
                iv_steps.setImageResource(R.drawable.ic_step3)
            }
        }
    }

    private fun setToolbarConfiguration() {
        setSupportActionBar(tb_myEvents)
        supportActionBar!!.title = getString(R.string.tv_titleMyEvents)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
